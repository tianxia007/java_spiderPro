package com.spider;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/02
 */
public class ParseMusic163 extends ParseBase {
    private static final Logger logger = LoggerFactory.getLogger(ParseMusic163.class);

    private static String TABLECLASS = "m-table";
    /**
     * 歌曲名称所在td序号
     */
    private static Integer TD_MUSICNAME_INDEX = 1;
    /**
     * 歌曲演唱者所在td序号
     */
    private static Integer TD_MUSICSINGER_INDEX = 3;

    /**
     * 模拟登陆下载个人喜好的收藏夹
     */
    public void loginAndDownlodByid() {

    }

    public List<Song> parseMusics(String filePath) {
        List<Song> res = new ArrayList<Song>();

        Document document = getDocument(filePath);

        Elements elementsByClass = document.getElementsByClass(TABLECLASS);
        if (null != elementsByClass && elementsByClass.size() > 0) {
            logger.info("通过class属性找到了{}个播放列表.", elementsByClass.size());
            if (elementsByClass.size() > 1) {
                logger.error("网易云音乐解析table的class已经不是:{},匹配到了多个播放列表异常!");
                throw new RuntimeException("网易云音乐解析table的class已经不是:{},匹配到了多个播放列表异常!");
            } else {
                logger.info("通过class属性找到了唯一的播放列表!解析播放列表成功!");
                Element element = elementsByClass.get(0);
                Elements tbodys = element.getElementsByTag("tbody");
                Elements trs = tbodys.get(0).getElementsByTag("tr");

                String musicName;
                String musicSinger;
                Song s = null;
                if (null != trs && trs.size() > 0) {
                    try {
                        for (Element tr : trs) {
                            Elements tds = tr.getElementsByTag("td");
                            Element elementMusicName = tds.get(TD_MUSICNAME_INDEX);
                            Elements bs = elementMusicName.getElementsByTag("b");
                            // 歌曲名称
                            musicName = bs.get(0).attr("title");
                            Element elementMusicSinger = tds.get(TD_MUSICSINGER_INDEX);
                            Elements spans = elementMusicSinger.getElementsByTag("span");
                            // 歌曲演唱者
                            musicSinger = spans.get(0).attr("title");
                            logger.debug("{}  {}", musicName, musicSinger);
                            s = new Song();
                            s.setMusicName(musicName);
                            s.setMusicSinger(musicSinger);
                            res.add(s);
                        }
                        return res;
                    } catch (Exception e) {
                        logger.error("歌单解析异常信息:" + e.getMessage(), e);
                        throw new RuntimeException(e);
                    }
                } else {
                    throw new RuntimeException("网易云音乐解析table的tr数据集异常.");
                }
            }
        } else {
            logger.error("网易云音乐解析table的class已经不是:{},请查看最新的class属性. ", TABLECLASS);
            throw new RuntimeException("网易云音乐解析table的class已经不是:" + TABLECLASS + ",请查看最新的class属性. ");
        }
    }
}
