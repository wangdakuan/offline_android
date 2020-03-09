package cm.offline.tv.ui.popup;

import android.content.Context;
import android.view.View;

import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: ChooseSizePopup
 * Author: wangdakuan
 * Date: 2020-03-09 12:03
 * Description: 购买时选择尺寸框
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ChooseSizePopup extends BasePopupWindow {

    public ChooseSizePopup(Context context) {
        super(context);
        setBackground(0);
//        setOutSideTouchable(false);
        setOutSideDismiss(true);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_shoose_size);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);
    }
}
