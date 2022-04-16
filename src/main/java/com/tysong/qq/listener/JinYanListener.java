package com.tysong.qq.listener;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.tysong.blog.service.BlogDiaoYongService;
import com.tysong.qq.service.SiteMapService;
import com.tysong.qq.utils.StringUtil;

import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.Listen;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;

@Beans
public class JinYanListener {

    //只处理哪些qq群
    private static List<String> processGroupNumber= Lists.newArrayList();
    static {
        processGroupNumber.add("616908975");
        processGroupNumber.add("1048397013");
        processGroupNumber.add("457337579");
        processGroupNumber.add("198963776");
        processGroupNumber.add("1045834054");
        processGroupNumber.add("722344383");
    }
    
    // 管理员
    private static List<String> privateNumber = Lists.newArrayList();
    static {
    	privateNumber.add("531779708");
    	privateNumber.add("1048397013");
    }
    
    public static String WEIJIN_WORD = "";

    /**
     * 群消息
     * @param msg
     * @param sender
     */
    @Listen(GroupMsg.class)
    public void groupMsg(GroupMsg msg, MsgSender sender) {
//    	if(!StringUtil.isNotEmpty(WEIJIN_WORD)) {
//    		WEIJIN_WORD = BlogDiaoYongService.INSTANCE.searchWeiJinWord();     
//    	}
//    	List<String> mingan_word = StringUtil.splitToList(",", WEIJIN_WORD);
//    	String groupNumber = msg.getGroupInfo().getGroupCode();
//    	for (String single : mingan_word) {
//    		if(StringUtil.isNotEmpty(single)) {
//    			if(StringUtils.contains(msg.getMsg(), single) && !StringUtil.equals(msg.getAccountInfo().getAccountCode(), "531779708", "2938633328")) {
//    				sender.SETTER.setMsgRecall(msg.getFlag());
//    				sender.SETTER.setGroupBan(groupNumber, msg.getAccountInfo().getAccountCode(), 60);
//    			}
//    		}
//		}
    	
        
    }
}
