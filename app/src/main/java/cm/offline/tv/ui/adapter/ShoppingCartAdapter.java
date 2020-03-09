package cm.offline.tv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: ShoppingCartAdapter
 * Author: wangdakuan
 * Date: 2020-03-09 17:04
 * Description: 购物车列表适配器
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ShoppingCartAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public ShoppingCartAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_shopping_cart_view, viewGroup, false);
        return new ShoppingCartAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_shopping)
        ImageView mIvShopping; //产品图片
        @BindView(R.id.iv_delete)
        ImageView mIvDelete; //删除按钮
        @BindView(R.id.tv_name)
        TextView mTvName; //商品名称
        @BindView(R.id.tv_price)
        TextView mTvPrice; //价格
        @BindView(R.id.tv_style_size)
        TextView mTvStyleSize; //款式 尺寸
        @BindView(R.id.ly_content)
        LinearLayout mLyContent;
        @BindView(R.id.btn_reduction)
        ImageView mBtnReduction; //减
        @BindView(R.id.btn_add)
        ImageView mBtnAdd; //加

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }
}
