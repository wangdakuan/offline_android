package cm.offline.tv.ui.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: GoodsOrderView
 * Author: wangdakuan
 * Date: 2020-03-15 16:01
 * Description: 取货订单列表
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class GoodsOrderView extends LinearLayout {

    public GoodsOrderView(Context context) {
        super(context);
    }

    public GoodsOrderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GoodsOrderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GoodsOrderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setGoodsOrderViewData() {
        for (int i = 0; i < 2; i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_goods_order_view, null, false);
            ViewHolder viewHolder = new ViewHolder(view);
            addView(view);
        }
    }

    static class ViewHolder {

        @BindView(R.id.iv_shopping)
        ImageView mIvShopping;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_price)
        TextView mTvPrice;
        @BindView(R.id.tv_style)
        TextView mTvStyle;
        @BindView(R.id.tv_number)
        TextView mTvNumber;

        ViewHolder(View view) {
            ButterKnifeUtil.bind(this, view);
        }
    }
}
