package com.socks.course3.model;

import org.springframework.lang.Nullable;

import java.awt.*;

public enum SocksColor {
    WHITE("белый"),
    BLACK("черный"),
    GREY("серый"),
    GREEN("зеленый"),
    YELLOW("желтый"),
    BLUE("синий"),
    PURPLE("фиолетовый");
    private String color;
    @Nullable
    public static SocksColor parse(String color){
        for (SocksColor c : values()){
            if (c.name().equals(color)){
                return c;
            }
        }
        return null;
    }

    SocksColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}

