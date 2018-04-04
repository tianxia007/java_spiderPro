package com.favorites.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import com.favorites.vo.RequestWithCookie;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/02
 */
public class ParseBase {
    private static final Logger logger = LoggerFactory.getLogger(ParseBase.class);
    private static final String SPLITSTR = "~~~";

    public static String FAVORITESURLS = "favorites_urls";

    public static void main(String[] args) {
    }

    public Map<String, RequestWithCookie> getFavoritesSetting() {
        Map<String, RequestWithCookie> re = new HashMap<String, RequestWithCookie>();
        Setting setting = new Setting("favorites.setting");
        System.out.println(setting.getSettingPath());
        Map<String, String> favorites_urls = (Map<String, String>) setting.getMap(FAVORITESURLS);
        System.out.println(favorites_urls.size());
        favorites_urls.forEach((key, value) -> {
            logger.debug("url: " + value);
            RequestWithCookie recookie = null;
            if (StrUtil.isNotBlank(value)) {
                recookie = new RequestWithCookie();
                getUrlAndCookies(recookie, value);
                re.put(key, recookie);
            }
        });
        return re.size() > 0 ? re : null;

    }

    private void getUrlAndCookies(RequestWithCookie recookie, String value) {
        try {
            String[] re = value.split(SPLITSTR);
            recookie.setUrl(re[0]);
            recookie.setCookies(re[1]);
        } catch (Exception e) {
            logger.error("定义链接地址和cookies方式异常(url和cookies直接用" + SPLITSTR + "):" + value, e);
        }
    }

    public void makeSingsFile(String filePath) {
        File temp = new File(filePath);

        OutputStreamWriter opsw = null;
        BufferedWriter bw = null;

        try {
            if (!temp.exists()) {
                temp.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(temp);
            opsw = new OutputStreamWriter(fileOutputStream);
            bw = new BufferedWriter(opsw);

            bw.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                opsw.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
