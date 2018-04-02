package com.spider;

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
        List<Song> songs = parseMusic163.parseMusics();
        parseMusic163.makeSingsFile("mysings.txt", songs);
    }

}
