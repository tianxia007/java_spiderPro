package com.spider;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/02
 */
public class Song {
    private Integer id;
    private String musicName;
    private String musicSinger;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicSinger() {
        return musicSinger;
    }

    public void setMusicSinger(String musicSinger) {
        this.musicSinger = musicSinger;
    }
}
