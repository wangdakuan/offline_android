package cm.offline.tv.event;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: MessageEvent
 * Author: wangdakuan
 * Date: 2020-03-07 16:43
 * Description: eventbus使用的对象
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class MessageEvent {

    public int mEventKey;
    public String mEventMsg;

    public static int START_CUSTOM_PAGE = 0x0001; //跳转到订制页面
    public static int START_ADVERTISING_PAGE = 0x0002; //跳转到全屏广告页
    public static int START_PAY_STATUS_PAGE = 0x0003; //跳转到支付状态页
    public static int START_FAULT_ERROR_PAGE = 0x0004; //跳转到异常页面

    public MessageEvent(int eventKey) {
        mEventKey = eventKey;
    }

    public MessageEvent(int eventKey, String eventMsg) {
        mEventKey = eventKey;
        mEventMsg = eventMsg;
    }
}
