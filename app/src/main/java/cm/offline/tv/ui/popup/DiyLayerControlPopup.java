package cm.offline.tv.ui.popup;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;
import cm.offline.tv.widget.popup.basepopup.BasePopupWindow;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: DiyLayerControlPopup
 * Author: wangdakuan
 * Date: 2020-03-08 21:08
 * Description: diy图层控制
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class DiyLayerControlPopup extends BasePopupWindow {

    @BindView(R.id.btn_close_layers)
    ImageView mBtnCloseLayers;
    @BindView(R.id.layer_list)
    RecyclerView mLayerList;
    @BindView(R.id.btn_diy_left_zhihuan)
    ImageView mBtnDiyLeftZhihuan;
    @BindView(R.id.btn_diy_left_shuiping)
    ImageView mBtnDiyLeftShuiping;
    @BindView(R.id.btn_diy_left_chuizhi)
    ImageView mBtnDiyLeftChuizhi;
    @BindView(R.id.btn_diy_left_zhiqian)
    ImageView mBtnDiyLeftZhiqian;
    @BindView(R.id.btn_diy_left_zhihou)
    ImageView mBtnDiyLeftZhihou;

    public DiyLayerControlPopup(Context context) {
        super(context);
        setBackground(0);
        setOutSideTouchable(true);
        setOutSideDismiss(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_diy_layer_control);
    }

    @Override
    public void onViewCreated(View contentView) {
        ButterKnifeUtil.bind(this, contentView);
    }

    @OnClick({R.id.btn_close_layers, R.id.btn_diy_left_zhihuan, R.id.btn_diy_left_shuiping,
            R.id.btn_diy_left_chuizhi, R.id.btn_diy_left_zhiqian, R.id.btn_diy_left_zhihou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close_layers:
                dismiss();
                break;
            case R.id.btn_diy_left_zhihuan:
                setDiyBtnLeftStyle(0);
                break;
            case R.id.btn_diy_left_shuiping:
                setDiyBtnLeftStyle(1);
                break;
            case R.id.btn_diy_left_chuizhi:
                setDiyBtnLeftStyle(2);
                break;
            case R.id.btn_diy_left_zhiqian:
                setDiyBtnLeftStyle(3);
                break;
            case R.id.btn_diy_left_zhihou:
                setDiyBtnLeftStyle(4);
                break;
        }
    }

    private void setDiyBtnLeftStyle(int btn){
        mBtnDiyLeftZhihuan.setSelected(btn == 0);
        mBtnDiyLeftShuiping.setSelected(btn == 1);
        mBtnDiyLeftChuizhi.setSelected(btn == 2);
        mBtnDiyLeftZhiqian.setSelected(btn == 3);
        mBtnDiyLeftZhihou.setSelected(btn == 4);
    }}