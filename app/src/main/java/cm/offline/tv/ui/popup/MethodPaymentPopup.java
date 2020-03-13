package cm.offline.tv.ui.popup;

import android.content.Context;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.event.MessageEvent;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: MethodPaymentPopup
 * Author: wangdakuan
 * Date: 2020-03-09 18:35
 * Description: 支付方式选择框
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class MethodPaymentPopup extends BasePopupWindow {


    public MethodPaymentPopup(Context context) {
        super(context);
        setBackground(0);
        setOutSideTouchable(true);
        setOutSideDismiss(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_method_payment);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);

    }

    @OnClick({R.id.btn_close,R.id.btn_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.btn_exit:
                dismiss();
                EventBus.getDefault().post(new MessageEvent(MessageEvent.START_PAY_STATUS_PAGE));
                break;
        }
    }
}
