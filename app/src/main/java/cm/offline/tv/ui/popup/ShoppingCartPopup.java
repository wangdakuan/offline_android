package cm.offline.tv.ui.popup;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.ui.adapter.ShoppingCartAdapter;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.utils.SizeUtils;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: ShoppingCartPopup
 * Author: wangdakuan
 * Date: 2020-03-09 16:30
 * Description: 购物车列表弹框
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ShoppingCartPopup extends BasePopupWindow {

    @BindView(R.id.list_shopping)
    RecyclerView mListShopping;
    @BindView(R.id.btn_determine)
    TextView mBtnDetermine;

    private LinearLayoutManager mLayoutManager;
    private ShoppingCartAdapter mShoppingCartAdapter; //商品列表

    public ShoppingCartPopup(Context context) {
        super(context);
        setBackground(0);
//        setOutSideTouchable(false);
        setOutSideDismiss(true);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_shopping_cart);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mShoppingCartAdapter = new ShoppingCartAdapter(getContext());
        mListShopping.setLayoutManager(mLayoutManager);
        mListShopping.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = SizeUtils.dp2px(17);
                outRect.top = SizeUtils.dp2px(17);
            }
        });
        mListShopping.setAdapter(mShoppingCartAdapter);

    }

    @OnClick({R.id.btn_close, R.id.btn_determine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            case R.id.btn_determine: //确定按钮
                dismiss();
                break;
        }
    }
}
