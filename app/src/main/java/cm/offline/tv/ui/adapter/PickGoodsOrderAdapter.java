package cm.offline.tv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.ui.view.GoodsOrderView;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: PickGoodsOrderAdapter
 * Author: wangdakuan
 * Date: 2020-03-15 15:41
 * Description: 订单列表
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class PickGoodsOrderAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public PickGoodsOrderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pick_goods_order, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.mViewGoodsOrder.setGoodsOrderViewData();
        holder.mTvOrderNo.setSelected(true);
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_order_no)
        TextView mTvOrderNo;
        @BindView(R.id.view_goods_order)
        GoodsOrderView mViewGoodsOrder;
        @BindView(R.id.ly_order_view)
        LinearLayout mLyOrderView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }
}
