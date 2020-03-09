package cm.offline.tv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: ChooseLayerAdapter
 * Author: wangdakuan
 * Date: 2020-03-09 13:39
 * Description: diy图层选择操作适配器
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ChooseLayerAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public ChooseLayerAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_layer, viewGroup, false);
        return new ChooseLayerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_layer)
        ImageView mIvLayer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }
}
