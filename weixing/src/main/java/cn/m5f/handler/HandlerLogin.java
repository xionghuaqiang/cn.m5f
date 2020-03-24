package cn.m5f.handler;


import cn.m5f.Server.CheckUtil;
import cn.m5f.utils.MessageDispatcher;
import cn.m5f.utils.MessageUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
public class HandlerLogin {
    /**
     * signature 表示微信加密签名，
     * signature 结合了开发者填写的 token
     * 参数和请求中的timestamp参数、nonce参数；timestamp 表示时间戳；
     * nonce 表示随机数；echostr 则表示一个随机字符串。
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/verify_wx_token")
    public void login(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
                out.write(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    /**
     * ToUserName	开发者微信号
     * FromUserName	发送方帐号（一个OpenID）
     * CreateTime	消息创建时间 （整型）
     * MsgType	消息类型，文本为text
     * Content	文本消息内容
     * MsgId	消息id，64位整型
     * @param request
     * @param response
     * @throws Exception
     */
//    @PostMapping("/verify_wx_token")
//    public void handler(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        Map<String, String> parseXml = MessageUtil.xmlToMap(request);
//        String msgType = parseXml.get("MsgType");
//        String content = parseXml.get("Content");
//        String fromusername = parseXml.get("FromUserName");
//        String tousername = parseXml.get("ToUserName");
//        System.out.println(msgType);
//        System.out.println(content);
//        System.out.println(fromusername);
//        System.out.println(tousername);
//        //实现不同信息回复
//        if("text".equals(msgType)){
//                //内容回复
//            if("1".equals(content)){
//
//            }
//        }
//    }

    @PostMapping(value = "/verify_wx_token",produces = "application/xml;charset=utf-8")
    public String handler(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        Map<String, String> map = MessageUtil.xmlToMap(request);
        String msgType = map.get("MsgType");
        System.out.println(msgType);
        if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)) {
            return MessageDispatcher.processEvent(map);
        }else{
            return MessageDispatcher.processMessage(map);
        }
    }

}
