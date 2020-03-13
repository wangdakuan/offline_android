package cm.offline.tv.ui.right;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.ui.popup.ChooseSizePopup;
import cm.offline.tv.ui.popup.DiyLayerControlPopup;
import cm.offline.tv.ui.popup.DiyMaterialPopup;
import cm.offline.tv.ui.popup.MethodPaymentPopup;
import cm.offline.tv.ui.popup.NotEditorPopup;
import cm.offline.tv.ui.popup.ShoppingCartPopup;
import cm.offline.tv.ui.view.ImagesTemplates;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: RightCustomMadeFragment
 * Author: wangdakuan
 * Date: 2020-03-05 18:47
 * Description:右边订制操作页面
 * History:
 * version：
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class RightCustomMadeFragment extends Fragment {

    @BindView(R.id.ly_fragment)
    RelativeLayout mLyFragment;

    @BindView(R.id.fy_template)
    FrameLayout mFyTemplate; // 整个模板控件
    @BindView(R.id.iv_clothes_style)
    ImageView mIvClothesStyle; //款式模板显示控件
    @BindView(R.id.making_diy_view)
    ImagesTemplates mMakingDiyView; //diy操作控件
    @BindView(R.id.btn_preview)
    TextView mBtnPreview; //预览效果按钮

    /**
     * 底部按钮
     */
    @BindView(R.id.ly_diy_btn_bottom)
    LinearLayout mLyDiyBtnBottom; //diy按钮
    @BindView(R.id.ly_bottom_edit_view)
    LinearLayout mLyBottomEditView; //编辑底部按钮
    @BindView(R.id.diy_btn_bottom_kuanshi)
    ImageView mDiyBtnBottomKuanshi; //款式按钮
    @BindView(R.id.diy_btn_bottom_tupian)
    ImageView mDiyBtnBottomTupian; //图片按钮
    @BindView(R.id.diy_btn_bottom_moban)
    ImageView mDiyBtnBottomMoban; //模板按钮
    @BindView(R.id.diy_btn_bottom_wenzi)
    ImageView mDiyBtnBottomWenzi; //文字

    @BindView(R.id.ly_bottom_preview_view)
    LinearLayout mLyBottomPreviewView; //预览底部按钮
    @BindView(R.id.btn_bottom_shopping_list)
    ImageView mBtnBottomShoppingList; //购物车按钮
    @BindView(R.id.btn_bottom_shopping_add)
    ImageView mBtnBottomShoppingAdd; //添加购物车按钮
    @BindView(R.id.btn_bottom_shopping_buy)
    ImageView mBtnBottomShoppingBuy; //立即购买

    /**
     * 右边按钮
     */
    @BindView(R.id.ly_diy_right_btn)
    LinearLayout mLyDiyRightBtn; //按钮总控件
    @BindView(R.id.btn_diy_right_shopping_list)
    ImageView mBtnDiyRightShoppingList; //购物车
    @BindView(R.id.tv_shopping_list_num)
    TextView mTvShoppingListNum; //购物车数量标签
    @BindView(R.id.btn_diy_right_btn_shopping_add)
    ImageView mBtnDiyRightBtnShoppingAdd; //添加购物车
    @BindView(R.id.btn_diy_right_btn_shopping_buy)
    ImageView mBtnDiyRightBtnShoppingBuy; //购买

    //左边按钮
    @BindView(R.id.btn_layer)
    ImageView mBtnLayer; //图层按钮
    @BindView(R.id.tv_layer_num)
    TextView mTvLayerNum; //图层数量标签
    @BindView(R.id.view_mask)
    View mViewMask; //遮罩
    @BindView(R.id.view_mask_all)
    View mViewMaskAll; //遮罩


    private DiyMaterialPopup mDiyMaterialPopup; //素材选择
    private DiyLayerControlPopup mLayerControlPopup; //图层控制
    private ChooseSizePopup mChooseSizePopup; //尺寸选择框

    private ShoppingCartPopup mShoppingCartPopup; //购物车列表框

    private MethodPaymentPopup mPaymentPopup; //支付方式框

    private NotEditorPopup mNotEditorPopup; //未编辑时的弹框

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_custom_made, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnifeUtil.bind(this, view);
    }

    @OnClick({R.id.btn_preview, R.id.diy_btn_bottom_kuanshi, R.id.diy_btn_bottom_tupian, R.id.diy_btn_bottom_moban,
            R.id.diy_btn_bottom_wenzi, R.id.btn_diy_right_shopping_list, R.id.btn_diy_right_btn_shopping_add,
            R.id.btn_diy_right_btn_shopping_buy, R.id.btn_layer, R.id.ly_fragment, R.id.view_mask, R.id.view_mask_all
            , R.id.btn_bottom_shopping_list, R.id.btn_bottom_shopping_add, R.id.btn_bottom_shopping_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_fragment: // 用于屏幕监听 MonitorTouchService
                break;
            case R.id.view_mask: //遮罩点击事件
            case R.id.view_mask_all:
                popupDismiss();
                break;
            case R.id.btn_preview: //预览效果按钮
                popupDismiss();
                if (mBtnPreview.getText().toString().contains("编辑")) {
                    setPreviewEditSwitch(true);
                } else {
                    setPreviewEditSwitch(false);
                }
                break;
            case R.id.diy_btn_bottom_kuanshi: //款式
                setDiyBtnBottomStyle(0);
                break;
            case R.id.diy_btn_bottom_tupian: //图片
                setDiyBtnBottomStyle(1);
                break;
            case R.id.diy_btn_bottom_moban: //模板
                setDiyBtnBottomStyle(2);
                break;
            case R.id.diy_btn_bottom_wenzi: //文字
                setDiyBtnBottomStyle(3);
                break;
            case R.id.btn_bottom_shopping_list: ////购物车
            case R.id.btn_bottom_shopping_add: //添加购物车
            case R.id.btn_bottom_shopping_buy://购买
            case R.id.btn_diy_right_shopping_list: //购物车
            case R.id.btn_diy_right_btn_shopping_add: //添加购物车
            case R.id.btn_diy_right_btn_shopping_buy: //购买
            case R.id.btn_layer: //图层
                if (null != mDiyMaterialPopup && mDiyMaterialPopup.isShowing()) {
                    mDiyMaterialPopup.dismiss();
                }
                //diy图层控制弹框
                clickedDiyLayerControlPopup(view.getId());
                //购物车列表弹框
                clickedShoppingCartPopup(view.getId());

                if (view.getId() == R.id.btn_diy_right_btn_shopping_buy) {
                    clickedDiyNotEditorPopup();
                }
                if (view.getId() == R.id.btn_bottom_shopping_buy) {
                    clickedChooseSizePopup();
                }
                break;

        }
    }

    /**
     * 预览编辑切换
     *
     * @param isEdit
     */
    private void setPreviewEditSwitch(boolean isEdit) {
        if (isEdit) {
            mBtnPreview.setText("预览效果");
            mLyBottomPreviewView.setVisibility(View.GONE);
            mLyBottomEditView.setVisibility(View.VISIBLE);
            mBtnLayer.setVisibility(View.VISIBLE);
            mTvLayerNum.setVisibility(View.VISIBLE);
        } else {
            mBtnPreview.setText("继续编辑");
            mLyBottomPreviewView.setVisibility(View.VISIBLE);
            mLyBottomEditView.setVisibility(View.GONE);
            mBtnLayer.setVisibility(View.GONE);
            mTvLayerNum.setVisibility(View.GONE);
        }
    }


    /**
     * 关闭popup
     */
    private void popupDismiss() {
        //未编辑提示框
        if (null != mNotEditorPopup && mNotEditorPopup.isShowing()) {
            mNotEditorPopup.dismiss();
        }
        //图片框
        if (null != mLayerControlPopup && mLayerControlPopup.isShowing()) {
            mLayerControlPopup.dismiss();
        }
        //购物车列表框
        if (null != mShoppingCartPopup && mShoppingCartPopup.isShowing()) {
            mShoppingCartPopup.dismiss();
        }
        //支付框
        if (null != mPaymentPopup && mPaymentPopup.isShowing()) {
            mPaymentPopup.dismiss();
        }
        //尺寸选择框
        if (null != mChooseSizePopup && mChooseSizePopup.isShowing()) {
            mChooseSizePopup.dismiss();
        }
    }

    /**
     * 点击购买弹框
     */
    private void clickedChooseSizePopup() {
        if (null == mChooseSizePopup) {
            mChooseSizePopup = new ChooseSizePopup(getActivity());
            mChooseSizePopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    if (null != mViewMask && mViewMask.isShown()) {
                        mViewMask.setVisibility(View.GONE);
                    }
                }
            });
            mChooseSizePopup.setChooseSizeCallback(new ChooseSizePopup.onChooseSizeCallback() {
                @Override
                public void onClickedPay() {
                    clickedMethodPayment();
                }
            });
        }
        if (null != mChooseSizePopup && !mChooseSizePopup.isShowing()) {
            mChooseSizePopup.setPopupGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            mChooseSizePopup.showPopupWindow(mLyDiyBtnBottom);
            mViewMask.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 未编辑时提示框
     */
    private void clickedDiyNotEditorPopup() {
        if (null == mNotEditorPopup) {
            mNotEditorPopup = new NotEditorPopup(getActivity());
            mNotEditorPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    if (null != mViewMaskAll && mViewMaskAll.isShown()) {
                        mViewMaskAll.setVisibility(View.GONE);
                    }
                }
            });
            mNotEditorPopup.setNotEditorCallback(new NotEditorPopup.onNotEditorCallback() {
                @Override
                public void onConfirm() {
                    mNotEditorPopup.dismiss();
                    setPreviewEditSwitch(false);
                    clickedChooseSizePopup();
                }
            });
        }
        if (null != mNotEditorPopup && !mNotEditorPopup.isShowing()) {
            mNotEditorPopup.setPopupGravity(Gravity.CENTER);
            mNotEditorPopup.showPopupWindow(mLyFragment);
            mViewMaskAll.setVisibility(View.VISIBLE);
        }
    }

    /**
     * diy图层控制弹框
     */
    private void clickedDiyLayerControlPopup(int viewId) {
        if (viewId == R.id.btn_layer) {
            if (null == mLayerControlPopup) {
                mLayerControlPopup = new DiyLayerControlPopup(getActivity());
            }
            if (null != mLayerControlPopup && !mLayerControlPopup.isShowing()) {
                mLayerControlPopup.setPopupGravity(Gravity.TOP);
                mLayerControlPopup.showPopupWindow(mBtnLayer);
            }
        }
    }

    /**
     * 购物车列表弹框
     */
    private void clickedShoppingCartPopup(int viewId) {
        if (viewId == R.id.btn_diy_right_shopping_list) {
            if (null == mShoppingCartPopup) {
                mShoppingCartPopup = new ShoppingCartPopup(getActivity());
            }
            if (null != mShoppingCartPopup && !mShoppingCartPopup.isShowing()) {
                mShoppingCartPopup.setPopupGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                mShoppingCartPopup.showPopupWindow(mLyDiyBtnBottom);
            }
        }
    }

    /**
     * 支付方式弹框
     */
    private void clickedMethodPayment() {
        if (null == mPaymentPopup) {
            mPaymentPopup = new MethodPaymentPopup(getActivity());
            mPaymentPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    if (null != mViewMask && mViewMask.isShown()) {
                        mViewMask.setVisibility(View.GONE);
                    }
                }
            });
        }
        if (null != mPaymentPopup && !mPaymentPopup.isShowing()) {
            mPaymentPopup.setPopupGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            mPaymentPopup.showPopupWindow(mLyDiyBtnBottom);
            mViewMask.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 0 = 款式  1 = 图片  2 = 模板  3 = 文字
     *
     * @param diyBtn
     */
    private void setDiyBtnBottomStyle(int diyBtn) {
        if (diyBtn == 0) {
            mDiyBtnBottomKuanshi.setImageResource(R.mipmap.ic_diy_btn_bottom_kuanshi_pressed);
        } else {
            mDiyBtnBottomKuanshi.setImageResource(R.mipmap.ic_diy_btn_bottom_kuanshi_default);
        }
        if (diyBtn == 1) {
            mDiyBtnBottomTupian.setImageResource(R.mipmap.ic_diy_btn_bottom_tupian_pressed);
        } else {
            mDiyBtnBottomTupian.setImageResource(R.mipmap.ic_diy_btn_bottom_tupian_default);
        }
        if (diyBtn == 2) {
            mDiyBtnBottomMoban.setImageResource(R.mipmap.ic_diy_btn_bottom_moban_pressed);
        } else {
            mDiyBtnBottomMoban.setImageResource(R.mipmap.ic_diy_btn_bottom_moban_default);
        }
        if (diyBtn == 3) {
            mDiyBtnBottomWenzi.setImageResource(R.mipmap.ic_diy_btn_bottom_wenzi_pressed);
        } else {
            mDiyBtnBottomWenzi.setImageResource(R.mipmap.ic_diy_btn_bottom_wenzi_default);
        }
        if (null == mDiyMaterialPopup) {
            mDiyMaterialPopup = new DiyMaterialPopup(getActivity());
            mDiyMaterialPopup.setOnDismissListener(new BasePopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    setDiyBtnBottomStyle(-1);
                }
            });
            mDiyMaterialPopup.setMaterialCallback(new DiyMaterialPopup.onMaterialCallback() {
                @Override
                public void onCallback(int step) {
                    setDiyBtnBottomStyle(step);
                }
            });
        }
        if (diyBtn != -1 && null != mLayerControlPopup && mLayerControlPopup.isShowing()) {
            mLayerControlPopup.dismiss();
        }
        if (diyBtn != -1 && null != mDiyMaterialPopup && !mDiyMaterialPopup.isShowing()) {
            mDiyMaterialPopup.setPopupGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            mDiyMaterialPopup.showPopupWindow(mLyDiyBtnBottom);
            mDiyMaterialPopup.setStep(diyBtn);
        } else {
            if (null != mDiyMaterialPopup && mDiyMaterialPopup.isShowing()) {
                mDiyMaterialPopup.setStep(diyBtn);
            }
        }
    }
}
