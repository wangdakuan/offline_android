package cm.offline.tv.ui.popup;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.ui.adapter.ChooseColorAdapter;
import cm.offline.tv.ui.adapter.ChooseMaterialAdapter;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.utils.SizeUtils;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;


/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: DiyMaterialPopup
 * Author: wangdakuan
 * Date: 2020-03-08 19:04
 * Description: 款式、图片、模板素材弹框
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class DiyMaterialPopup extends BasePopupWindow {


    @BindView(R.id.list_material)
    RecyclerView mListMaterial; //素材列表
    @BindView(R.id.btn_previous_step)
    TextView mBtnPreviousStep; //上一步
    @BindView(R.id.tv_material_name)
    TextView mTvMaterialName; //名称
    @BindView(R.id.btn_next_step)
    TextView mBtnNextStep; //下一步
    /**
     * 文字添加布局
     */
    @BindView(R.id.btn_edit_text)
    TextView mBtnEditText; //点击弹出输入框
    @BindView(R.id.btn_select_font)
    TextView mBtnSelectFont; // 点击选择字体
    @BindView(R.id.list_color)
    RecyclerView mListColor; //颜色列表
    @BindView(R.id.ly_add_text_view)
    LinearLayout mLyAddTextView; // 字体添加总布局

    private LinearLayoutManager mLayoutManager;
    private ChooseMaterialAdapter mMaterialAdapter; //选择素材适配器

    private ChooseColorAdapter mChooseColorAdapter; //选择颜色适配器

    private int steps = 0; // 0 = 款式；1 = 图片；2=模板；3=添加文字
    private onMaterialCallback mMaterialCallback;

    public DiyMaterialPopup(Context context) {
        super(context);
        setBackground(0);
        setOutSideTouchable(true);
        setOutSideDismiss(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_diy_material);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);
        mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mListMaterial.setLayoutManager(mLayoutManager);
        mListMaterial.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(SizeUtils.dp2px(10), 0, SizeUtils.dp2px(10), 0);
            }
        });
        mMaterialAdapter = new ChooseMaterialAdapter(getContext());
        mListMaterial.setAdapter(mMaterialAdapter);
    }

    /**
     * 初始化
     */
    private void initAddTextView() {
        if (null == mChooseColorAdapter) {
            mChooseColorAdapter = new ChooseColorAdapter(getContext());
            LinearLayoutManager manager = new LinearLayoutManager(getContext());
            manager.setOrientation(RecyclerView.HORIZONTAL);
            mListColor.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    super.getItemOffsets(outRect, view, parent, state);
                    int childAdapterPosition = parent.getChildAdapterPosition(view);
                    if (childAdapterPosition == 0) {
                        outRect.set(0, SizeUtils.dp2px(25), 0, 0);
                    } else {
                        outRect.set(SizeUtils.dp2px(24), SizeUtils.dp2px(25), 0, 0);
                    }
                }
            });
            mListColor.setLayoutManager(manager);
            mListColor.setAdapter(mChooseColorAdapter);
        }
        if (mLyAddTextView.getVisibility() != View.VISIBLE) {
            mLyAddTextView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.btn_previous_step, R.id.btn_next_step, R.id.btn_edit_text
            , R.id.btn_select_font, R.id.iv_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_previous_step:
                if (steps > 0) {
                    steps--;
                }
                setStepView(steps, true);
                break;
            case R.id.btn_next_step:
                if (steps < 3) {
                    steps++;
                }
                setStepView(steps, true);
                break;
            case R.id.btn_edit_text: //点击出现输入框
                break;
            case R.id.btn_select_font: //现在字体
                break;
            case R.id.iv_close: //现在字体
                dismiss();
                break;
        }
    }

    /**
     * 0 = 款式；1 = 图片；2=模板；3=添加文字
     *
     * @param step
     */
    private void setStepView(int step, boolean isCallBack) {
        if (step < 3) {
            if (step == 0) {
                mBtnPreviousStep.setVisibility(View.GONE);
            } else {
                mBtnPreviousStep.setVisibility(View.VISIBLE);
            }
            mBtnNextStep.setText("下一步");
            if (mLyAddTextView.getVisibility() != View.GONE) {
                mLyAddTextView.setVisibility(View.GONE);
            }
            if (mListMaterial.getVisibility() != View.VISIBLE) {
                mListMaterial.setVisibility(View.VISIBLE);
            }
        } else {
            mBtnNextStep.setText("完成");
            initAddTextView();
            mListMaterial.setVisibility(View.GONE);
            mBtnPreviousStep.setVisibility(View.VISIBLE);
        }

        if (step == 0) {
            mTvMaterialName.setText("选择款式");
        } else if (step == 1) {
            mTvMaterialName.setText("添加图片");
        } else if (step == 2) {
            mTvMaterialName.setText("选择模版");
        } else if (step == 3) {
            mTvMaterialName.setText("添加文字");
        }
        if (isCallBack && null != mMaterialCallback) {
            mMaterialCallback.onCallback(step);
        }
    }

    /**
     * 0 = 款式；1 = 图片；2=模板；3=添加文字
     *
     * @param step
     */
    public void setStep(int step) {
        steps = step;
        setStepView(step, false);
    }

    public interface onMaterialCallback {
        void onCallback(int step);
    }

    public void setMaterialCallback(onMaterialCallback materialCallback) {
        mMaterialCallback = materialCallback;
    }
}
