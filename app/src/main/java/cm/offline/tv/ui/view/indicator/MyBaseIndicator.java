package cm.offline.tv.ui.view.indicator;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.Indicator;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: MyBaseIndicator
 * Author: wangdakuan
 * Date: 2020-03-07 15:51
 * Description: 自定义指示器
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class MyBaseIndicator extends View implements Indicator {
    protected IndicatorConfig config;
    protected Paint mPaint;

    public MyBaseIndicator(Context context) {
        this(context, null);
    }

    public MyBaseIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBaseIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config = new IndicatorConfig();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(config.getNormalColor());
    }

    @NonNull
    @Override
    public View getIndicatorView() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        switch (config.getGravity()) {
            case IndicatorConfig.Direction.LEFT:
                layoutParams.gravity = Gravity.BOTTOM | Gravity.START;
                break;
            case IndicatorConfig.Direction.CENTER:
                layoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
                break;
            case IndicatorConfig.Direction.RIGHT:
                layoutParams.gravity = Gravity.BOTTOM | Gravity.END;
                break;
        }
        layoutParams.leftMargin=config.getMargins().leftMargin;
        layoutParams.rightMargin=config.getMargins().rightMargin;
        layoutParams.topMargin=config.getMargins().topMargin;
        layoutParams.bottomMargin=config.getMargins().bottomMargin;
        setLayoutParams(layoutParams);
        return this;
    }

    @Override
    public IndicatorConfig getIndicatorConfig() {
        return config;
    }

    @Override
    public void onPageChanged(int count, int currentPosition) {
        config.setIndicatorSize(count);
        config.setCurrentPosition(currentPosition);
        requestLayout();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        config.setCurrentPosition(position);
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
