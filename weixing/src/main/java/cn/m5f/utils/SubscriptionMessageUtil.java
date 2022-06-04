package cn.m5f.utils;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author xhq
 * @date 2022/5/15 16:50
 */
public class SubscriptionMessageUtil {
    /**
     * 下单后通知供货商
     */
    public static void sendOrderMsg(String appid,
                                    String appSecret,
                                    String userOpenid,
                                    String orderId,
                                    String serviceName) {

        // 模板消息 ID
        // {{first.DATA}}
        // 订单编号：{{keyword1.DATA}}
        // 订货终端：{{keyword2.DATA}}
        // 下单时间：{{keyword3.DATA}}
        // {{remark.DATA}}
        String OrderMsgTemplateId = "QPmauScz-r6hu_fvkVxG57XQ_8MP4pKDa2Fn_6nsp3w";
        // 卡片详情跳转页，设置此值，当点击消息时会打开指定的页面
        String detailUrl = "https://bing.com";

        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm");
        Date date = new Date();
        String timeNow = sdf.format(date);

        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appSecret);

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);

        // 此处的 key/value 需和模板消息对应
        List<WxMpTemplateData> wxMpTemplateDataList = Arrays.asList(
                new WxMpTemplateData("first", "您有一个未打卡通知", "#000000"),
                new WxMpTemplateData("keyword1", orderId),
                new WxMpTemplateData("keyword2", serviceName),
                new WxMpTemplateData("keyword3", timeNow),
//                new WxMpTemplateData("keyword4", "123"),
                new WxMpTemplateData("remark", "请及时完成")
        );

        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(userOpenid)
                .templateId(OrderMsgTemplateId)
                .data(wxMpTemplateDataList)
                .url(detailUrl)
                .build();

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
        }

    }

}
