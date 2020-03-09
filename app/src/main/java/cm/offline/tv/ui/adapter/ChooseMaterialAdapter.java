package cm.offline.tv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cm.offline.tv.R;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: ChooseMaterialAdapter
 * Author: wangdakuan
 * Date: 2020-03-09 12:43
 * Description: 选择款式适配器
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ChooseMaterialAdapter extends RecyclerView.Adapter {
    private Context mContext;

    public ChooseMaterialAdapter(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_material, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_material)
        ImageView mIvMaterial;
        @BindView(R.id.tv_material_name)
        TextView mTvMaterialName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }

}
