package com.favorites.vo;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

import java.util.List;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/04
 */
public class TouTiao {
    private long id;
    private boolean has_more;
    private String message;
    private List<TouTiaoBlog> data;
    private String max_repin_time;

    public long getId() {
        return id;
    }

    public String getMax_repin_time() {
        return max_repin_time;
    }

    public void setMax_repin_time(String max_repin_time) {
        this.max_repin_time = max_repin_time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TouTiaoBlog> getData() {
        return data;
    }

    public void setData(List<TouTiaoBlog> data) {
        this.data = data;
    }

    private String toDateStr(String timestamp) {
        long t = Long.valueOf(timestamp);
        return DateUtil.date(t * 1000).toString(DatePattern.NORM_DATETIME_FORMAT);
    }
}
