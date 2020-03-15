package cm.offline.tv.ui.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: LeftPrintPopup
 * Author: wangdakuan
 * Date: 2020-03-14 16:18
 * Description: 打印中页面
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class LeftPrintPopup extends BasePopupWindow {

    @BindView(R.id.iv_state_image)
    ImageView mIvStateImage; //状态图片
    @BindView(R.id.tv_service_no_code)
    TextView mTvServiceNoCode;

    public LeftPrintPopup(Context context) {
        super(context);
        setBackground(0);
        setOutSideTouchable(true);
        setOutSideDismiss(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_left_print);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);

    }
}
