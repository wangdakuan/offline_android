package cm.offline.tv.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cm.offline.tv.R;
import cm.offline.tv.model.SizeChartModel;
import cm.offline.tv.utils.ButterKnifeUtil;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: SizeChartAdapter
 * Author: wangdakuan
 * Date: 2020-03-13 16:07
 * Description: 尺码指南显示适配器
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class SizeChartAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<SizeChartModel> mSizeChartModels;

    public SizeChartAdapter(Context mContext, List<SizeChartModel> chartModels) {
        this.mContext = mContext;
        this.mSizeChartModels = chartModels;
        if(mSizeChartModels == null){
            mSizeChartModels = new ArrayList<>();
            mSizeChartModels.add(new SizeChartModel("衣服尺码","S","M","L","XL","XXL","XXXL"));
            mSizeChartModels.add(new SizeChartModel("肩宽(cm)","41","44","47","50","53","56"));
            mSizeChartModels.add(new SizeChartModel("衣长(cm)","63","66","70","74","78","83"));
            mSizeChartModels.add(new SizeChartModel("胸围(cm)","92","98","104","110","116","122"));
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_size_chart, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (position < mSizeChartModels.size()) {
            SizeChartModel model = mSizeChartModels.get(position);
            ViewHolder holder = (SizeChartAdapter.ViewHolder) viewHolder;
            holder.mTvName.setText(model.name);
            holder.mTvS.setText(model.key_s);
            holder.mTvM.setText(model.key_m);
            holder.mTvL.setText(model.key_l);
            holder.mTvXl.setText(model.key_xl);
            holder.mTvXxl.setText(model.key_xxl);
            holder.mTvXxxl.setText(model.key_xxxl);
            if (position > 0) {
                holder.mTvS.setTextColor(mContext.getResources().getColor(R.color.text_colors_99343338));
                holder.mTvM.setTextColor(mContext.getResources().getColor(R.color.text_colors_99343338));
                holder.mTvL.setTextColor(mContext.getResources().getColor(R.color.text_colors_99343338));
                holder.mTvXl.setTextColor(mContext.getResources().getColor(R.color.text_colors_99343338));
                holder.mTvXxl.setTextColor(mContext.getResources().getColor(R.color.text_colors_99343338));
                holder.mTvXxxl.setTextColor(mContext.getResources().getColor(R.color.text_colors_99343338));
            }else {
                holder.mTvS.setTextColor(mContext.getResources().getColor(R.color.text_colors_343338));
                holder.mTvM.setTextColor(mContext.getResources().getColor(R.color.text_colors_343338));
                holder.mTvL.setTextColor(mContext.getResources().getColor(R.color.text_colors_343338));
                holder.mTvXl.setTextColor(mContext.getResources().getColor(R.color.text_colors_343338));
                holder.mTvXxl.setTextColor(mContext.getResources().getColor(R.color.text_colors_343338));
                holder.mTvXxxl.setTextColor(mContext.getResources().getColor(R.color.text_colors_343338));
            }
        }
    }

    @Override
    public int getItemCount() {
        return null == mSizeChartModels ? 0 : mSizeChartModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_s)
        TextView mTvS;
        @BindView(R.id.tv_m)
        TextView mTvM;
        @BindView(R.id.tv_l)
        TextView mTvL;
        @BindView(R.id.tv_xl)
        TextView mTvXl;
        @BindView(R.id.tv_xxl)
        TextView mTvXxl;
        @BindView(R.id.tv_xxxl)
        TextView mTvXxxl;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnifeUtil.bind(this, itemView);
        }
    }
}
