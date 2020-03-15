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
import cm.offline.tv.ui.adapter.PickGoodsOrderAdapter;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.utils.SizeUtils;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: PickGoodsOrderPopup
 * Author: wangdakuan
 * Date: 2020-03-15 13:59
 * Description: 取货订单列表显示
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class PickGoodsOrderPopup extends BasePopupWindow {

    @BindView(R.id.list_shopping)
    RecyclerView mListShopping;
    @BindView(R.id.btn_determine)
    TextView mBtnDetermine;
    @BindView(R.id.btn_problem)
    TextView mBtnProblem;

    PickGoodsOrderAdapter mGoodsOrderAdapter;

    public PickGoodsOrderPopup(Context context) {
        super(context);
        setBackground(0);
        setOutSideTouchable(true);
        setOutSideDismiss(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_pick_goods_order);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);
        mGoodsOrderAdapter = new PickGoodsOrderAdapter(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        mListShopping.setLayoutManager(manager);
        mListShopping.setAdapter(mGoodsOrderAdapter);
        mListShopping.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int childAdapterPosition = parent.getChildAdapterPosition(view);
                if (childAdapterPosition == 0) {
                    outRect.set(SizeUtils.dp2px(18), SizeUtils.dp2px(18), SizeUtils.dp2px(18), SizeUtils.dp2px(18));
                } else {
                    outRect.set(SizeUtils.dp2px(18), 0, SizeUtils.dp2px(18), SizeUtils.dp2px(18));
                }
            }
        });
    }

    @OnClick({R.id.btn_problem, R.id.btn_determine,R.id.btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_problem: //问题
                break;
            case R.id.btn_determine: //确认取货
                dismiss();
                break;
            case R.id.btn_close: //取消
                dismiss();
                break;
        }
    }

    @OnClick(R.id.btn_close)
    public void onViewClicked() {
    }
}
