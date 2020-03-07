package cm.offline.tv.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.nineoldandroids.view.ViewHelper;

import cm.offline.tv.R;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: ImagesTemplates
 * Author: wangdakuan
 * Date: 2020-03-06 19:46
 * Description: 自定义diy区域控件
 * History:
 * version：1.0.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class ImagesTemplates extends FrameLayout {

    private FrameLayout mFlRoot;
    private ImageView mIvBg;
    private FrameLayout mFlZone;

    private float centerX,centerX0;
    private float centerY,centerY0;

    public ImagesTemplates(Context context) {
        super(context);
        initView();
    }

    public ImagesTemplates(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ImagesTemplates(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImagesTemplates(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        View view = View.inflate(getContext(), R.layout.view_images_templates, this);
        mFlRoot = (FrameLayout) findViewById(R.id.fl_root);
        mIvBg = (ImageView) findViewById(R.id.iv_bg);
        mFlZone = (FrameLayout) findViewById(R.id.fl_zone);
        initBg(30, 90, 800, 1200);//背景起点 长宽
        initEditZone(30, 30, 600, 800);

        initEditImage();
    }

    /**
     * 背景布局
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void initBg(int x, int y, int width, int height) {
        mIvBg.setX(x);
        mIvBg.setY(y);
        mIvBg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mIvBg.setImageResource(R.mipmap.bg_v4_release);
        ViewGroup.LayoutParams layoutParams = mIvBg.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        mIvBg.setLayoutParams(layoutParams);
    }

    /**
     * 可编辑区域
     * @param x
     * @param y
     * @param width
     * @param height
     */
    private void initEditZone(final int x, final int y, final int width, final int height) {

        ViewTreeObserver vto = mIvBg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                //监听一次马上结束

                if (Build.VERSION.SDK_INT < 16) {
                    mIvBg.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    mIvBg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                Bitmap bitmap = Bitmap.createBitmap(mIvBg.getWidth(), mIvBg.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                canvas.drawColor(Color.WHITE);
                mIvBg.draw(canvas);


                Paint p = new Paint();
                p.setStyle(Paint.Style.STROKE);
                //设置虚线效果
                p.setPathEffect(new DashPathEffect(new float[]{10, 5}, 0));
                p.setStrokeWidth(3);
                p.setColor(Color.parseColor("#D0104C"));

                Path path = new Path();
                path.moveTo(x, y);
                path.lineTo(x + width, y);
                path.lineTo(x + width, y + height);
                path.lineTo(x, y + height);
                path.close();
                canvas.drawPath(path, p);

                mIvBg.setImageBitmap(bitmap);

                //显示编辑区域范围
                mFlZone.setX(x + mIvBg.getX());
                mFlZone.setY(y + mIvBg.getY());
                FrameLayout.LayoutParams layoutParams = (LayoutParams) mFlZone.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = height;

                mFlZone.setLayoutParams(layoutParams);

            }
        });
    }

    private void initEditImage() {

        final View flEditImage = View.inflate(getContext(), R.layout.add_img_item, null);
        mFlZone.addView(flEditImage);

        final ImageView iv = (ImageView) flEditImage.findViewById(R.id.iv);
        final ImageView ivLeft = (ImageView) flEditImage.findViewById(R.id.ivLeft);
        final ImageView ivRight = (ImageView) flEditImage.findViewById(R.id.ivRight);
//        final ImageView ivBLeft = (ImageView) flEditImage.findViewById(R.id.ivBLeft);
        final ImageView ivBRight = (ImageView) flEditImage.findViewById(R.id.ivBRight);
        iv.setImageResource(R.mipmap.shaosiming);
        flEditImage.setTag(iv);


        ViewTreeObserver vto = flEditImage.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                                          @Override
                                          public void onGlobalLayout() {

                                              //监听一次马上结束

                                              if (Build.VERSION.SDK_INT < 16) {
                                                  flEditImage.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                              } else {
                                                  flEditImage.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                              }
                                              int[] location = new int[2];
                                              flEditImage.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
                                              centerX0 = location[0] + flEditImage.getWidth() / 2;
                                              centerY0 = location[1] + flEditImage.getHeight() / 2;
                                              centerX = centerX0;
                                              centerY = centerY0;
                                          }
                                      }

        );

        //平移
        flEditImage.setOnTouchListener(new OnTouchListener() {

            private boolean isMove;
            private int downY;
            private int downX;

            private ImageView iv;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                FrameLayout flview = (FrameLayout) view;
                iv = (ImageView) view.getTag();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downX = (int) event.getRawX();
                    downY = (int) event.getRawY();
                    isMove = false;
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    isMove = true;
                    int moveX = (int) event.getRawX();
                    int moveY = (int) event.getRawY();
//                Log.d(TAG,"moveX   "+moveX+"  moveY  "+moveY);
                    int dx = moveX - downX;
                    int dy = moveY - downY;
                    int i1 = dx + (int) ViewHelper.getTranslationX(flview);
                    int i2 = dy + (int) ViewHelper.getTranslationY(flview);
                    ViewHelper.setTranslationX(flview, i1);
                    ViewHelper.setTranslationY(flview, i2);
                    downX = moveX;
                    downY = moveY;
                    mFlRoot.setClipChildren(false);//是否限制子View在其范围内*（注意这个属性必须放在拖动布局的爷爷布局才能生效）

                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    centerX = centerX0 + flEditImage.getTranslationX();
                    centerY = centerY0 + flEditImage.getTranslationY();
                    mFlRoot.setClipChildren(true);
                    mFlRoot.invalidate();

                    if (CheckIsOut(iv, flview)) {
                        ViewGroup parent = (ViewGroup) flview.getParent();
                        parent.removeView(flview);

//                    flZone.removeView(flview);
                        Toast.makeText(getContext(), "超出范围已移除", Toast.LENGTH_SHORT).show();
                    }

                    if (!isMove) {
//                    Log.i("rex", "i 点击事件 ");
                    }
//                    centerX = (flEditImage.getLeft() + flEditImage.getWidth()) / 2;
//                    centerY = (flEditImage.getTop() + flEditImage.getHeight()) / 2;

                }

                return true;
            }
        });

        //删除
        ivLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                mFlZone.removeView(flEditImage);
            }
        });

        //镜像
//        ivBLeft.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Bitmap bitmap = Bitmap.createBitmap(iv.getWidth(), iv.getHeight(), Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                canvas.drawColor(Color.WHITE);
//                iv.draw(canvas);
//                iv.setImageBitmap(flip(bitmap, false));
//
//            }
//        });
        //旋转
        ivRight.setOnTouchListener(new OnTouchListener() {

            private float mDownY;
            private float mDownX;


//
//            float centerX = flEditImage.getWidth() / 2;
//            float centerY = flEditImage.getHeight() / 2;

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:


                        mDownX = event.getRawX();
                        mDownY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getRawX();
                        float moveY = event.getRawY();
                        float rotate = flEditImage.getRotation();
                        float angle = getDegress(moveX, moveY) - getDegress(mDownX, mDownY);
//                        float angle = getDegress(mDownX, mDownY) - getDegress(moveX, moveY);


                        flEditImage.setRotation((rotate + angle) % 360);

                        mDownX = moveX;
                        mDownY = moveY;
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }
                return true;

            }

            private float getDegress(float newX, float newY) {
                float x = newX - centerX;
                float y = newY - centerY;
                return (float) Math.toDegrees(Math.atan2(y, x));
            }
        });

        //缩放
        ivBRight.setOnTouchListener(new OnTouchListener() {


            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        mDownX = event.getRawX();
//                        mDownY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveX = event.getRawX();
                        float moveY = event.getRawY();

                        float bili = getBili(moveX, moveY);
                        flEditImage.setScaleX(bili);
                        flEditImage.setScaleY(bili);
                        correct4ButtonSize(1 / bili);

//                        mDownX = moveX;
//                        mDownY = moveY;
                        break;
                    case MotionEvent.ACTION_UP:
                        centerX = centerX0 + flEditImage.getTranslationX();
                        centerY = centerY0 + flEditImage.getTranslationY();
                        break;
                }
                return true;

            }

            /**
             *
             * @param bili
             */
            private void correct4ButtonSize(float bili) {

                ivLeft.setScaleX(bili);
                ivLeft.setScaleY(bili);

                ivRight.setScaleX(bili);
                ivRight.setScaleY(bili);
//
//                ivBLeft.setScaleX(bili);
//                ivBLeft.setScaleY(bili);

                ivBRight.setScaleX(bili);
                ivBRight.setScaleY(bili);
                ;
            }

            private float getBili(float moveX, float moveY) {

                int[] location = new int[2];
//                flEditImage.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
                flEditImage.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
                float radius = ivBRight.getWidth();//不精确

                float oldLine = (float) Math.sqrt(flEditImage.getWidth() * flEditImage.getWidth() + flEditImage.getHeight() * flEditImage.getHeight());
                float newLine = (float) Math.sqrt((moveX - location[0] + radius) * (moveX - location[0] + radius) + (moveY - location[1] + radius) * (moveY - location[1] + radius));

                float scale = newLine / oldLine;//此处有微量误差
                return scale;
            }
        });

    }

    /**
     * 判断是否超出
     * @param iv
     * @param fl
     * @return
     */
    private boolean CheckIsOut(ImageView iv, FrameLayout fl) {
        int maxX = mFlZone.getWidth();
        int maxY = mFlZone.getHeight();

        int a = fl.getWidth();
        int a2 = (int) fl.getX();
        int a3 = iv.getLeft();
        int a4 = iv.getRight();
        int x1 = (int) (fl.getX() + iv.getLeft());
        int x2 = (int) (fl.getX() + fl.getWidth() - iv.getLeft());
        int y1 = (int) (fl.getY() + iv.getTop());
        int y2 = (int) (fl.getY() + fl.getHeight() - iv.getTop());


        if (x1 > maxX || x2 < 0 || y1 > maxY || y2 < 0) {
            return true;
        }
        return false;
    }


    public static Bitmap flip(Bitmap bitmap, boolean isVer) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        if (isVer)
            matrix.postScale(1, -1);//镜像垂直翻转
        else
            matrix.postScale(-1, 1); //镜像水平翻转
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        bitmap.recycle();
        return newBitmap;
    }
}
