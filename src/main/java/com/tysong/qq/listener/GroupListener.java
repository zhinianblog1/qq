package com.tysong.qq.listener;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.tysong.blog.service.BlogDiaoYongService;
import com.tysong.qq.service.BeiAnService;
import com.tysong.qq.service.CaiHongPiService;
import com.tysong.qq.service.CeSuService;
import com.tysong.qq.service.FangHongService;
import com.tysong.qq.service.PingService;
import com.tysong.qq.service.ShouLuService;
import com.tysong.qq.service.SiteMapService;
import com.tysong.qq.service.TianQiService;
import com.tysong.qq.service.TuPianService;
import com.tysong.qq.service.WebSiteInfoService;
import com.tysong.qq.utils.StringUtil;

import catcode.CatCodeUtil;
import catcode.CodeTemplate;
import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.Listen;
import love.forte.simbot.annotation.OnGroupMsgRecall;
import love.forte.simbot.annotation.OnGroupMute;
import love.forte.simbot.api.message.containers.GroupInfo;
import love.forte.simbot.api.message.events.GroupMemberIncrease;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.GroupMsgRecall;
import love.forte.simbot.api.message.events.GroupMute;
import love.forte.simbot.api.message.events.MuteGet.ActionType;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;

@Beans
public class GroupListener {

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
    private static List<String> privateNumber= Lists.newArrayList();
    static {
    	privateNumber.add("531779708");
    	privateNumber.add("1048397013");
    	privateNumber.add("1223927723");
    }

    /**
     * 菜单
     */
    private static List<String> menuList = Arrays.asList(
    		"● 天气查询： 北京天气",
    		"● 收录查询：收录查询 zhinianblog.com",
    		"● 备案查询：备案查询 zhinianblog.com",
    		"● ping：ping zhinianblog.com",
    		"● 防洪生成：防洪生成 zhinianblog.com",
    		"● 测速：测速 zhinianblog.com",
    		"● 网站添加：网站添加 https://zhinianblog.com/sitemap.xml",
    		"● 网站更新：网站更新 https://zhinianblog.com/sitemap.xml",
    		"● 搜索：搜索 聊天室"
    		);
    
    /**
     * 群禁言事件
     */
    @OnGroupMsgRecall()
    public void onGroupMsgRecall(GroupMsgRecall msg, MsgSender sender){
//    	MessageContent msgContent = msg.getMsgContent();
//    	String originalData = msg.getOriginalData();
//    	String person = msg.getAccountInfo().getAccountCode();
//    	GroupInfo groupInfo = msg.getGroupInfo();
//    		sender.SENDER.sendGroupMsg(groupInfo.getGroupCode(), person + " 由于太过嘚瑟已被禁言，望安心改造，早见天日");
    }
    
    /**
     * 群禁言事件
     */
    @OnGroupMute()
    public void OnGroupMute(GroupMute msg, MsgSender sender){
    	String person = msg.getAccountInfo().getAccountCode();
    	ActionType muteActionType = msg.getMuteActionType();
    	GroupInfo groupInfo = msg.getGroupInfo();
//    	if(muteActionType == ActionType.UNMUTE) {
//    		sender.SENDER.sendGroupMsg(groupInfo.getGroupCode(), person + " 已被解除禁言，望改邪归正");
//    	}
//    	else {
//    		sender.SENDER.sendGroupMsg(groupInfo.getGroupCode(), person + " 由于太过嘚瑟已被禁言，望安心改造，早见天日");
//    	}
    }

    /**
     * 私信消息
     */
    @Listen(PrivateMsg.class)
    public void privateMsg(PrivateMsg msg, MsgSender sender){
    	String groupNumber = msg.getAccountInfo().getAccountCode();
    	sender.SENDER.sendPrivateMsg(groupNumber, "请在群里发送消息，私聊不支持");
    }

    
    @Listen(GroupMsg.class)
    @Filter(atBot=true)
    public void groupMsgAndAtMe(GroupMsg msg, MsgSender sender){
    	CatCodeUtil util = CatCodeUtil.INSTANCE;
        CodeTemplate<String> template = util.getStringTemplate();
    	String groupNumber = msg.getGroupInfo().getGroupCode();
    	System.out.println("收到消息：" + msg);
    	if(privateNumber.contains(msg.getAccountInfo().getAccountCode())) {
//    		sender.SENDER.sendGroupNotice(groupNumber, "测试公告标题", "公告内容", false, false, false, false);
    		if (StringUtils.contains(msg.getMsg(),"功能")||StringUtils.contains(msg.getMsg(),"菜单")){
                String outText= StringUtil.joinString(menuList, "\n");
                sender.SENDER.sendGroupMsg(groupNumber,outText);
                return;
            }
    		if (StringUtils.contains(msg.getMsg(),"添加违禁词")){
    			String param = msg.getMsg().replaceAll("添加违禁词", "").trim();
    			param = param.replaceAll("[CAT:at,code=2938633328]", "").trim();
    			param = StringUtil.substring(param, 2).trim();
            	if(StringUtil.isNotEmpty(param)) {
            		String resultMsg = BlogDiaoYongService.INSTANCE.addWeiJinWord(param, msg.getAccountInfo().getAccountCode());       
            		if(StringUtil.isNotEmpty(resultMsg)) {
            			sender.SENDER.sendGroupMsg(groupNumber, resultMsg);
            		}
            	}
                return;
            }
    	}
    	else {
    		sender.SENDER.sendGroupMsg(groupNumber, template.at(msg.getAccountInfo().getAccountCode()) + "\n" + "我是人工智障，你艾特我干啥嘞");
    	}
    	
    }
    

    /**
     * 群消息
     *  @Filter(at=true) 只要at我的信息
     * @param msg
     * @param sender
     */
    @Listen(GroupMsg.class)
//    @Filter(anyAt=true)
    public void groupMsg(GroupMsg msg, MsgSender sender){
    	
        String groupNumber=msg.getGroupInfo().getGroupCode();
        if (!processGroupNumber.contains(groupNumber)){
//            return;
        }
        System.out.println("收到消息：" + msg);
        if (StringUtils.contains(msg.getMsg(), "天气")) {
            String city = msg.getMsg().replaceAll("天气", "").trim();
            String weather = TianQiService.INSTANCE.getWeather(city);
            sender.SENDER.sendGroupMsg(groupNumber,weather);
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"图片")) {
            String msg1 = TuPianService.INSTANCE.exec();
//            sender.SENDER.sendGroupMsg(groupNumber,"[CAT:image,id={CF4CA76A-CDFD-131E-AA51-48DBFBD25A9A},url=http://b.zol-img.com.cn/desk/bizhi/image/1/1680x1050/1349289433496.jpg,flash=true]");
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"网站添加") && msg.getMsg().startsWith("网站添加")) {
        	String param = msg.getMsg().replaceAll("网站添加", "").trim();
        	if(StringUtil.isNotEmpty(param)) {
        		String resultMsg = SiteMapService.INSTANCE.addSiteMap(param, msg.getAccountInfo().getAccountCode());       
        		if(StringUtil.isNotEmpty(resultMsg)) {
        			sender.SENDER.sendGroupMsg(groupNumber, resultMsg);
        		}
        	}
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"网站详情") && msg.getMsg().startsWith("网站详情")) {
        	String param = msg.getMsg().replaceAll("网站详情", "").trim();
        	if(StringUtil.isNotEmpty(param)) {
        		String resultMsg = WebSiteInfoService.INSTANCE.exec(param, msg.getAccountInfo().getAccountCode());       
        		if(StringUtil.isNotEmpty(resultMsg)) {
        			sender.SENDER.sendGroupMsg(groupNumber, resultMsg);
        		}
        	}
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"网站更新") && msg.getMsg().startsWith("网站更新")) {
        	String param = msg.getMsg().replaceAll("网站更新", "").trim();
        	if(StringUtil.isNotEmpty(param)) {
        		String resultMsg = SiteMapService.INSTANCE.updateSiteMap(param, msg.getAccountInfo().getAccountCode());       
        		if(StringUtil.isNotEmpty(resultMsg)) {
        			sender.SENDER.sendGroupMsg(groupNumber, resultMsg);
        		}
        	}
            return;
        }
        if (StringUtil.isNotEmpty(msg.getMsg()) && msg.getMsg().startsWith("搜索")) {
        	String param = msg.getMsg().replaceAll("搜索", "").trim();
        	if(StringUtil.isNotEmpty(param)) {
        		String resultMsg = SiteMapService.INSTANCE.searchArchive(param, msg.getAccountInfo().getAccountCode());       
        		if(StringUtil.isNotEmpty(resultMsg)) {
        			sender.SENDER.sendGroupMsg(groupNumber, resultMsg);
        		}
        	}
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"夸我")){
            String msg1 = CaiHongPiService.INSTANCE.caihongpi("");
            sender.SENDER.sendGroupMsg(groupNumber,msg1);
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"ping")){
        	String result = msg.getMsg().replaceAll("ping", "").trim();
        	String msg1 = PingService.INSTANCE.exec(result, msg.getAccountInfo().getAccountCode());       
        	if(StringUtil.isNotEmpty(msg1)) {
        		sender.SENDER.sendGroupMsg(groupNumber, msg1);
        	}
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"备案查询")){
        	String REGEX_CHINESE = "[\u4e00-\u9fa5]";
        	String url = msg.getMsg().replaceAll(REGEX_CHINESE, "").trim();
        	if(StringUtil.isEmpty(url)) {
        		return;
        	}
            String shoulu = BeiAnService.INSTANCE.exec(url, msg.getAccountInfo().getAccountCode());
            sender.SENDER.sendGroupMsg(groupNumber, shoulu);
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"防洪生成")){
        	String REGEX_CHINESE = "[\u4e00-\u9fa5]";
        	String url = msg.getMsg().replaceAll(REGEX_CHINESE, "").trim();
        	if(StringUtil.isEmpty(url)) {
        		return;
        	}
            String shoulu = FangHongService.INSTANCE.exec(url, msg.getAccountInfo().getAccountCode());
            if(StringUtil.isNotEmpty(shoulu)) {
            	sender.SENDER.sendGroupMsg(groupNumber, shoulu);
            }
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"收录查询")){
//        	sender.SETTER.setGroupBan(groupNumber, msg.getAccountInfo().getAccountCode(),10);
        	String REGEX_CHINESE = "[\u4e00-\u9fa5]";
        	String url = msg.getMsg().replaceAll(REGEX_CHINESE, "").trim();
        	if(StringUtil.isEmpty(url)) {
        		return;
        	}
            String shoulu = ShouLuService.INSTANCE.getShouLu(url, msg.getAccountInfo().getAccountCode());
            if(StringUtil.isNotEmpty(shoulu)) {
            	sender.SENDER.sendGroupMsg(groupNumber, shoulu);
            }
            return;
        }
        if (StringUtils.contains(msg.getMsg(),"测速")){
        	String REGEX_CHINESE = "[\u4e00-\u9fa5]";
        	String url = msg.getMsg().replaceAll(REGEX_CHINESE, "").trim();
        	if(StringUtil.isEmpty(url)) {
        		return;
        	}
            String result = CeSuService.INSTANCE.exec(url, msg.getAccountInfo().getAccountCode());
            if(StringUtil.isNotEmpty(result)) {
            	sender.SENDER.sendGroupMsg(groupNumber, result);
            }
            return;
        }
        return;

    }

    @Listen(GroupMemberIncrease.class)
    public void groupMemAdd(GroupMemberIncrease groupMemberIncrease, MsgSender sender){
//        String groupNumber=groupMemberIncrease.getGroupInfo().getGroupCode();
//        if (!processGroupNumber.contains(groupNumber)){
//            return;
//        }
//        String beOperatedQQ = groupMemberIncrease.getBeOperatorInfo().getBeOperatorCode();
//        CatCodeUtil util = CatCodeUtil.INSTANCE;
//        CodeTemplate<String> template = util.getStringTemplate();
//        String result = JinQunService.INSTANCE.exec();
//        if(StringUtil.isNotEmpty(result)) {
//        	sender.SENDER.sendGroupMsg(groupNumber, template.at(beOperatedQQ) + "\n" + result);
//        }
//        else {
//            sender.SENDER.sendGroupMsg(groupNumber, template.at(beOperatedQQ) + "\n" + "欢迎大佬入驻本群，男的退群，女的爆照");
//        }
    }
}
