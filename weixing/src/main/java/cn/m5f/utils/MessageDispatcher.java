package cn.m5f.utils;

import cn.m5f.entil.TextMessage;

import java.util.Date;
import java.util.Map;

/**
 * 发送给用户
 */
public class MessageDispatcher {
    public static String processMessage(Map<String, String> map) {
        String openid = map.get("FromUserName"); //用户 openid
        String mpid = map.get("ToUserName");   //公众号原始 ID
        String context =map.get("Content");

       String fasong ="";
        System.out.println("用户发送的内容"+context);
        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            //普通文本消息
            TextMessage txtmsg = new TextMessage();
            txtmsg.setToUserName(openid);
            txtmsg.setFromUserName(mpid);
            txtmsg.setCreateTime(new Date().getTime());
            txtmsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            System.out.println(context.substring(context.length()-1));
            if(context.substring(context.length()-1).equalsIgnoreCase("！")){
                StringBuilder sb = new StringBuilder(context);
                sb.replace(context.length()-1,context.length(),"?");
                System.out.println(sb);
                fasong =""+sb;
                txtmsg.setContent(fasong);
            }else {
                txtmsg.setContent("菜鸟");
            }

            return MessageTool.textMessageToXml(txtmsg);
        }
        return null;
    }
    public static String processEvent(Map<String, String> map) {
        //在这里处理事件
        return "kon";
    }


}