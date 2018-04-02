package com.spider;

import com.spider.parsemusic.ParseMusic163;
import com.spider.vo.Song;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/02
 */
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ParseMusic163 parseMusic163 = new ParseMusic163();
        List<Song> songs = parseMusic163.parseMusics("c://opt//2018年三月最热新歌TOP50.html");
        parseMusic163.makeSingsFile("2018年三月最热新歌TOP50.txt", songs);
    }

}
