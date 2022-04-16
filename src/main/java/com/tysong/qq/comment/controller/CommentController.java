package com.tysong.qq.comment.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.bot.BotManager;

@Component
@Controller
public class CommentController {

	/**
     * 获取bot管理器。
     */
    @Depend
    private BotManager botManager;
    
    /**
     * 评论后自动通知
     */
    @RequestMapping(value = "/autoSend")
    public void commentAutoSend() {
    	System.out.println(111);
    	botManager.getDefaultBot().getSender().SENDER.sendPrivateMsg(1223927723, "定时！");
    }
}
