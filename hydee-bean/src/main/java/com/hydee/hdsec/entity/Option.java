package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * Created by King.Liu
 * 2016/9/22.
 */
public class Option implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;//选择ABCD

    private String content;//选项内容

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
