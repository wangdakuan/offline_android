package cm.offline.tv.ui.view.indicator;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;


/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: MyCircleIndicator
 * Author: wangdakuan
 * Date: 2020-03-07 15:52
 * Description: 自定义上下滑动圆形指示器
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class MyCircleIndicator extends MyBaseIndicator {
    private float mNormalRadius;
    private float mSelectedRadius;
    private float maxRadius;

    public MyCircleIndicator(Context context) {
        this(context, null);
    }

    public MyCircleIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mNormalRadius = config.getNormalWidth() / 2;
        mSelectedRadius = config.getSelectedWidth() / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = config.getIndicatorSize();
        if (count <= 1) return;

        mNormalRadius = config.getNormalWidth() / 2;
        mSelectedRadius = config.getSelectedWidth() / 2;
        //考虑当 选中和默认 的大小不一样的情况
        maxRadius = Math.max(mSelectedRadius, mNormalRadius);
        //间距*（总数-1）+最大的半径（考虑有时候选中时会变大的情况）+默认半径*（总数-1）
        int height= (int) ((count - 1) * config.getIndicatorSpace() + 2 * (maxRadius + mNormalRadius * (count - 1)));
        int width = (int) (2 * maxRadius);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = config.getIndicatorSize();
        if (count <= 1) return;
        for (int i = 0; i < count; i++) {
            mPaint.setColor(config.getCurrentPosition() == i ? config.getSelectedColor() : config.getNormalColor());
            float radius = config.getCurrentPosition() == i ? mSelectedRadius : mNormalRadius;
            float y = maxRadius + (2 * mNormalRadius + config.getIndicatorSpace()) * i;
            canvas.drawCircle(maxRadius, y, radius, mPaint);
        }
    }
}
