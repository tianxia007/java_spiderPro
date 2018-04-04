package com.favorites.vo;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/04
 */
public class TouTiaoBlog {
    private String title;
    private String display_url;
    private String image_url;


    private String repin_time;

    private String behot_time;



    public String getRepin_time() {
        return repin_time;
    }

    public void setRepin_time(String repin_time) {
        this.repin_time = toDateStr(repin_time);
    }

    public String getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(String behot_time) {
        this.behot_time = toDateStr(behot_time);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    private String toDateStr(String timestamp) {
        long t = Long.valueOf(timestamp);
        return DateUtil.date(t * 1000).toString(DatePattern.NORM_DATETIME_FORMAT);
    }
}
