package com.spider.parsemusic;

import com.spider.vo.Song;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/02
 */
public class ParseBase {
    private static final Logger logger = LoggerFactory.getLogger(ParseBase.class);

    public static String DEFULTFILE = "c://opt//网易云音乐.html";
    public static String FILECODETYPE = "UTF-8";
    public static String ROWENDSTR = "  \r\n";

    public Document getDocument(String filePath) {
        try {
            if (StringUtil.isBlank(filePath)) {
                filePath = DEFULTFILE;
            }
            Document parse = Jsoup.parse(new File(filePath), FILECODETYPE);
            return parse;
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                logger.error("未找到您的歌单文件!下载163音乐歌单教程见readme!");
                return null;
            }
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public void makeSingsFile(String filePath, List<Song> songs) {
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

            for (Song s : songs) {
                bw.write(s.getMusicName() + " " + s.getMusicSinger() + ROWENDSTR);
            }
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
