package com.favorites.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.favorites.vo.RequestWithCookie;
import com.favorites.vo.TouTiao;
import com.sun.media.sound.SoftTuning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/04
 */
public class ParseFavorites extends ParseBase {
    private static final Logger logger = LoggerFactory.getLogger(ParseFavorites.class);

    public void parse() {
        Map<String, RequestWithCookie> favoritesMap = getFavoritesSetting();

        favoritesMap.forEach((key, value) -> {
            logger.debug("解析key:" + key);
            switch (key) {
                case "json":
                    parseJson(value);
                    break;
                case "oschina":
                    parseHtml(value);
                    break;
                default:
                    logger.error("默认解析器");
            }
        });
    }

    private void parseHtml(RequestWithCookie value) {
        logger.debug("parseHtml...");
    }

    private void parseJson(RequestWithCookie value) {
        logger.debug("parseJson...");
        String next = "0";
        List dates = new ArrayList();
        while (null != next) {
            try {
                logger.debug("下一页访问标记：" + next);
                String url = null;
                if ("".equals(url)) {
                    url = value.getUrl();
                } else {
                    url = value.getUrl() + "?max_repin_time=" + next;
                }
                logger.debug("采集页面:" + url);
                HttpRequest httpRequest = HttpUtil.createGet(url);
                httpRequest.cookie(value.getCookies());
                httpRequest.timeout(Integer.MAX_VALUE);
                httpRequest.contentType("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
                httpRequest.contentType("application/x-www-form-urlencoded");

                java.lang.String alljson = httpRequest.execute().body();
                System.out.println(alljson);
                TouTiao o = JSONUtil.toBean(alljson, TouTiao.class);
                int[] i = {1};
                if (null != o && "success".equals(o.getMessage())) {
                    o.getData().forEach(datev -> {
                        logger.debug("序号" + i[0] + "  标题:{} 发布时间{}  访问链接:{}", datev.getTitle(), datev.getBehot_time(), datev.getDisplay_url());
                        i[0]++;
                    });
                }
                //通过next停止循环
                next = StrUtil.isBlank(o.getMax_repin_time()) ? null : o.getMax_repin_time();
                String behot_time = o.getData().get(0).getBehot_time();
                if (behot_time.contains("2017")) {
                    next = null;
                }
                //通过next停止循环

                dates.addAll(o.getData());
                Thread.sleep(RandomUtil.randomSleep());
            } catch (Exception e) {
            }
        }

        FreemakerUtils.makeFieByFtl("toutiao.ftl", dates);
    }
}
