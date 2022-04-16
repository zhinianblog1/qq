package com.tysong.qq;


import love.forte.simbot.annotation.SimbotApplication;
import love.forte.simbot.annotation.SimbotResource;
import love.forte.simbot.core.SimbotApp;

@SimbotApplication(@SimbotResource(value = "conf.properties", orIgnore = true))
public class QQApplication {

    public static void main(String[] args) {
        SimbotApp.run(QQApplication.class, args);
    }

}