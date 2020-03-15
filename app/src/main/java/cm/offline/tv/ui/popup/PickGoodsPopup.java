package cm.offline.tv.ui.popup;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.ui.adapter.KeyboardBtnAdapter;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.utils.SizeUtils;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: PickGoodsPopup
 * Author: wangdakuan
 * Date: 2020-03-15 11:43
 * Description: 取货弹框
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class PickGoodsPopup extends BasePopupWindow {

    @BindView(R.id.ly_btn_view)
    LinearLayout mLyBtnView; //按钮总布局
    @BindView(R.id.ly_scan_code)
    LinearLayout mLyScanCode; //二维码总布局
    @BindView(R.id.ly_keyboard_view)
    LinearLayout mLyKeyboardView; //键盘按钮总布局
    @BindView(R.id.tv_keyboard)
    TextView mTvKeyboard; //输入文本框
    @BindView(R.id.list_keyboard_btn)
    RecyclerView mListKeyboardBtn;  //键盘按钮

    private KeyboardBtnAdapter mKeyboardBtnAdapter;
    private PickGoodsOrderPopup mGoodsOrderPopup;

    public PickGoodsPopup(Context context) {
        super(context);
        setBackground(0);
        setOutSideTouchable(true);
        setOutSideDismiss(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_pick_goods);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);
    }

    @OnClick({R.id.btn_scan_goods, R.id.btn_code_goods, R.id.btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scan_goods:
                mLyBtnView.setVisibility(View.GONE);
                mLyKeyboardView.setVisibility(View.GONE);
                mLyScanCode.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_code_goods:
                mLyBtnView.setVisibility(View.GONE);
                mLyKeyboardView.setVisibility(View.VISIBLE);
                mLyScanCode.setVisibility(View.GONE);
                showKeyboardView();
                break;
            case R.id.btn_close:
                dismiss();
                break;
        }
    }

    /**
     * 键盘显示
     */
    private void showKeyboardView() {
        if (null == mKeyboardBtnAdapter) {
            mKeyboardBtnAdapter = new KeyboardBtnAdapter(getContext());
            GridLayoutManager manager = new GridLayoutManager(getContext(), 4);
            mListKeyboardBtn.setLayoutManager(manager);
            mListKeyboardBtn.setAdapter(mKeyboardBtnAdapter);
            mListKeyboardBtn.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    int childAdapterPosition = parent.getChildAdapterPosition(view);
                    if (childAdapterPosition % 4 == 0) {
                        outRect.set(0, 0, 0, SizeUtils.dp2px(10));
                    } else {
                        outRect.set(SizeUtils.dp2px(10), 0, 0, SizeUtils.dp2px(10));
                    }
                }
            });
            mKeyboardBtnAdapter.setItemClickListener(new KeyboardBtnAdapter.itemClickListener() {
                @Override
                public void onAddNumber(int number) {
                    mTvKeyboard.setText(mTvKeyboard.getText().toString() + number);
                }

                @Override
                public void onDelNumber() {
                    if (mTvKeyboard.getText().toString().length() > 0) {
                        mTvKeyboard.setText(mTvKeyboard.getText().toString().substring(0, mTvKeyboard.getText().toString().length() - 1));
                    }
                }

                @Override
                public void oncConfirm() {
                    Toast.makeText(getContext(),"确认按钮",Toast.LENGTH_LONG).show();
                    showGoodsOrder();
                }
            });
        }
    }

    /**
     * 订单列表显示
     */
    private void showGoodsOrder(){
        if(null == mGoodsOrderPopup){
            mGoodsOrderPopup = new PickGoodsOrderPopup(getContext());
        }
        mGoodsOrderPopup.showPopupWindow();
    }
    @Override
    public void onDismiss() {
        mLyBtnView.setVisibility(View.VISIBLE);
        mLyKeyboardView.setVisibility(View.GONE);
        mLyScanCode.setVisibility(View.GONE);
        super.onDismiss();
    }
}
