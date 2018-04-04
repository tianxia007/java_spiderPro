package com.favorites;

import com.favorites.common.ParseFavorites;
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
        ParseFavorites parseFavorites = new ParseFavorites();
        parseFavorites.parse("2016");

    }

}
