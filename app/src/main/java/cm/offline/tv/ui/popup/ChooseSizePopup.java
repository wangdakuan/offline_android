package cm.offline.tv.ui.popup;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.ui.adapter.SizeChartAdapter;
import cm.offline.tv.ui.adapter.SizeReferenceAdapter;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.utils.SizeUtils;
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

    @BindView(R.id.view_choose)
    CardView mViewChoose;

    @BindView(R.id.tv_design_color)
    TextView mTvDesignColor; //款式颜色
    @BindView(R.id.btn_number_reduce)
    ImageView mBtnNumberReduce; //减数量
    @BindView(R.id.tv_number)
    TextView mTvNumber; //数量显示
    @BindView(R.id.btn_number_add)
    ImageView mBtnNumberAdd; //加数量
    @BindView(R.id.btn_XS)
    TextView mBtnXS; // 尺码 XS
    @BindView(R.id.btn_S)
    TextView mBtnS;// 尺码 S
    @BindView(R.id.btn_M)
    TextView mBtnM;  // 尺码 M
    @BindView(R.id.btn_L)
    TextView mBtnL;  // 尺码 L
    @BindView(R.id.btn_XL)
    TextView mBtnXL;  // 尺码 XL
    @BindView(R.id.btn_size_chart)
    TextView mBtnSizeChart; //尺码表按钮
    @BindView(R.id.btn_pay)
    TextView mBtnPay; //支付


    @BindView(R.id.view_size_chart)
    CardView mViewSizeChart;
    @BindView(R.id.list_size_guide)
    RecyclerView mListSizeGuide;
    @BindView(R.id.list_reference)
    RecyclerView mListReference;


    private int mNumber = 1;

    private SizeChartAdapter mChartAdapter; //尺码指南适配器
    private SizeReferenceAdapter mReferenceAdapter; //尺码参考适配器

    private onChooseSizeCallback mChooseSizeCallback;

    public ChooseSizePopup(Context context) {
        super(context);
        setBackground(0);
        setOutSideTouchable(true);
        setOutSideDismiss(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_shoose_size);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);
    }

    @OnClick({R.id.tv_design_color, R.id.btn_number_reduce, R.id.btn_number_add, R.id.btn_close,
            R.id.btn_XS, R.id.btn_S, R.id.btn_M, R.id.btn_L, R.id.btn_XL, R.id.btn_size_chart, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_design_color: //款式颜色
                break;
            case R.id.btn_number_reduce: //减数量
                if (mNumber > 1) {
                    mNumber--;
                }
                break;
            case R.id.btn_number_add: //加数量
                if (mNumber < 9) {
                    mNumber++;
                }
                break;
            case R.id.btn_XS: //尺码
                setSize("XS");
                break;
            case R.id.btn_S:
                setSize("S");
                break;
            case R.id.btn_M:
                setSize("M");
                break;
            case R.id.btn_L:
                setSize("L");
                break;
            case R.id.btn_XL:
                setSize("XL");
                break;
            case R.id.btn_size_chart: //尺码表

                mViewSizeChart.setVisibility(View.VISIBLE);
                mViewChoose.setVisibility(View.GONE);
                if (null == mChartAdapter) {
                    mChartAdapter = new SizeChartAdapter(getContext(), null);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(RecyclerView.VERTICAL);
                    mListSizeGuide.setLayoutManager(manager);
                    mListSizeGuide.setAdapter(mChartAdapter);
                    mListSizeGuide.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                            super.getItemOffsets(outRect, view, parent, state);
                            int childAdapterPosition = parent.getChildAdapterPosition(view);
                            if (childAdapterPosition == 0) {
                                outRect.set(SizeUtils.dp2px(1), SizeUtils.dp2px(1), SizeUtils.dp2px(1), SizeUtils.dp2px(1));
                            } else {
                                outRect.set(SizeUtils.dp2px(1), 0, SizeUtils.dp2px(1), SizeUtils.dp2px(1));
                            }
                        }
                    });
                }

                if (null == mReferenceAdapter) {
                    mReferenceAdapter = new SizeReferenceAdapter(getContext(), null);
                    LinearLayoutManager manager = new LinearLayoutManager(getContext());
                    manager.setOrientation(RecyclerView.VERTICAL);
                    mListReference.setLayoutManager(manager);
                    mListReference.setAdapter(mReferenceAdapter);
                    mListReference.addItemDecoration(new RecyclerView.ItemDecoration() {
                        @Override
                        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                            super.getItemOffsets(outRect, view, parent, state);
                            int childAdapterPosition = parent.getChildAdapterPosition(view);
                            if (childAdapterPosition == 0) {
                                outRect.set(SizeUtils.dp2px(1), SizeUtils.dp2px(1), SizeUtils.dp2px(1), SizeUtils.dp2px(1));
                            } else {
                                outRect.set(SizeUtils.dp2px(1), 0, SizeUtils.dp2px(1), SizeUtils.dp2px(1));
                            }
                        }
                    });
                }

                break;
            case R.id.btn_pay: //支付
                dismiss();
                if (null != mChooseSizeCallback) {
                    mChooseSizeCallback.onClickedPay();
                }
                break;
            case R.id.btn_close: //关闭
                if (mViewSizeChart.getVisibility() == View.VISIBLE) {
                    mViewSizeChart.setVisibility(View.GONE);
                    mViewChoose.setVisibility(View.VISIBLE);
                } else {
                    dismiss();
                }
                break;
        }
    }

    private void setSize(String size) {
        if (TextUtils.equals(size, "XS")) {
            mBtnXS.setSelected(true);
        } else {
            mBtnXS.setSelected(false);
        }
        if (TextUtils.equals(size, "S")) {
            mBtnS.setSelected(true);
        } else {
            mBtnS.setSelected(false);
        }
        if (TextUtils.equals(size, "M")) {
            mBtnM.setSelected(true);
        } else {
            mBtnM.setSelected(false);
        }
        if (TextUtils.equals(size, "L")) {
            mBtnL.setSelected(true);
        } else {
            mBtnL.setSelected(false);
        }
        if (TextUtils.equals(size, "XL")) {
            mBtnXL.setSelected(true);
        } else {
            mBtnXL.setSelected(false);
        }
    }

    public interface onChooseSizeCallback {
        void onClickedPay();
    }

    public void setChooseSizeCallback(onChooseSizeCallback chooseSizeCallback) {
        mChooseSizeCallback = chooseSizeCallback;
    }
}
