package cm.offline.tv.ui.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: NotEditorPopup
 * Author: wangdakuan
 * Date: 2020-03-13 12:41
 * Description: 未编辑时提示框
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class NotEditorPopup extends BasePopupWindow {

    @BindView(R.id.btn_close)
    ImageView mBtnClose;
    @BindView(R.id.btn_cancel)
    TextView mBtnCancel;
    @BindView(R.id.btn_confirm)
    TextView mBtnConfirm;

    private onNotEditorCallback mNotEditorCallback;

    public NotEditorPopup(Context context) {
        super(context);
        setBackground(0);
        setOutSideTouchable(true);
        setOutSideDismiss(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_not_editor);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);

    }

    @OnClick({R.id.btn_close, R.id.btn_cancel, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close: //取消弹框
            case R.id.btn_cancel: //取消按钮
                dismiss();
                break;
            case R.id.btn_confirm: //确认按钮
                if (null != mNotEditorCallback) {
                    mNotEditorCallback.onConfirm();
                }
                break;
        }
    }

    public interface onNotEditorCallback {
        void onConfirm();
    }

    public void setNotEditorCallback(onNotEditorCallback notEditorCallback) {
        mNotEditorCallback = notEditorCallback;
    }
}
