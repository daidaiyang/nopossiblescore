package com.nopossiblescore.customview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 */

public class LineChartView extends View {

    private boolean isBezierLine = false;
    private boolean isInitialized = false;
    private boolean isPlayAnim = false;
    private ValueAnimator valueAnimator;
    private float currentValue = 0f;
    private boolean isAnimating = false;
    private int mWidth, mHeight;
    private List<Data> dataList = new ArrayList<>();
    private List<String> data = new ArrayList<>();
    private Point[] linePoints;
    private int stepStart;
    private int stepEnd;
    private int stepSpace;
    private int stepSpaceDefault = 50;
    private int stepSpaceDP = stepSpaceDefault;//item宽度默认dp
    private int topSpace, bottomSpace;
    private int tablePadding;
    private int tablePaddingDP = 20;//view四周padding默认dp
    private int maxValue, minValue;
    private int rulerValuePadding;//刻度单位与轴的间距
    private int rulerValuePaddingDP = 20;//刻度单位与轴的间距默认dp
    private float heightPercent = 0.618f;
    private int pointColor = Color.parseColor("#66ccff");//锚点颜色
    private float pointWidthDefault = 8f;
    private float pointWidthDP = pointWidthDefault;//锚点宽度dp







    private Paint linePaint;//曲线画笔
    private Paint pointPaint;//曲线上锚点画笔
    private Paint textPointPaint;//曲线上锚点文本画笔
    private Path linePath;//曲线路径
    private Path linePath2;//曲线路径2

    private int lineColor = Color.parseColor("#000000");//曲线颜色
    private float lineWidthDP = 8.0f;//曲线宽度dp

    private int pointTextColor = Color.parseColor("#ffffff");//锚点文本颜色
    private float pointTextSizeSP = 8f;//锚点文本大小

    private boolean isTwoPath = false;

    public void setTwoPath(boolean twoPath) {
        isTwoPath = twoPath;
    }


    public LineChartView(Context context) {
        this(context, null);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView();
    }


    private void setupView() {
        //画笔-线
        linePaint = new Paint();
        linePaint.setAntiAlias(true);//抗锯齿
        linePaint.setStyle(Paint.Style.STROKE);//STROKE描边FILL填充
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(dip2px(lineWidthDP));//边框宽度
        //画笔-点
        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStyle(Paint.Style.FILL);
        pointPaint.setColor(pointColor);
        pointPaint.setStrokeWidth(dip2px(pointWidthDP));

        //画笔-文本
        textPointPaint = new Paint();
        textPointPaint.setAntiAlias(true);
        textPointPaint.setStyle(Paint.Style.FILL);
        textPointPaint.setTextAlign(Paint.Align.CENTER);
        textPointPaint.setColor(pointTextColor);//文本颜色
        textPointPaint.setTextSize(sp2px(pointTextSizeSP));//字体大小
        //线-路径
        linePath = new Path();
        linePath2 = new Path();
        resetParam();
    }

    private void initAnim() {
        valueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(dataList.size() * 150);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                currentValue = 0f;
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                currentValue = 1f;
                isAnimating = false;
                isPlayAnim = false;
            }
        });
        valueAnimator.setStartDelay(500);
    }

    private void resetParam() {
        linePath.reset();
        linePath2.reset();
        stepSpace = dip2px(stepSpaceDP);
        tablePadding = dip2px(tablePaddingDP);
        rulerValuePadding = dip2px(rulerValuePaddingDP);
        stepStart = tablePadding;
        stepEnd = stepStart + stepSpace * (dataList.size() - 1);
        topSpace = bottomSpace = tablePadding;
        linePoints = new Point[dataList.size()];
        initAnim();
        isInitialized = false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = tablePadding + getTableEnd() + getPaddingLeft() + getPaddingRight();//计算自己的宽度
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);//父类期望的高度
        if (MeasureSpec.EXACTLY == heightMode) {
            height = getPaddingTop() + getPaddingBottom() + height;
        }
        setMeasuredDimension(width, height);//设置自己的宽度和高度
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);//绘制背景颜色
        canvas.translate(0f, mHeight / 2f + (getViewDrawHeight() + topSpace + bottomSpace) / 2f);//设置画布中心点垂直居中
        if (!isInitialized) {
            setupLine();
        }
        drawLine(canvas);//绘制曲线
        drawLinePoints(canvas);//绘制曲线上的点
    }

    private void drawText(Canvas canvas, Paint textPaint, String text,String text2, float x, float y) {

        canvas.drawText(text, x, y, textPaint);
        canvas.drawText(text2,x,dip2px(20),textPaint);
    }


    /**
     * 绘制曲线上锚点文本
     *
     * @param canvas
     * @param text
     * @param x
     * @param y
     */
    private void drawLinePointText(Canvas canvas, String text,String text2, float x, float y) {
        textPointPaint.setTextAlign(Paint.Align.CENTER);
        float newY = y+8f;
        drawText(canvas, textPointPaint, text,text2, x, newY);
    }

    private int getTableStart() {
        return stepStart;
    }

    private int getTableEnd() {
        return stepEnd;
    }



    /**
     * 绘制曲线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        Shader mShader = new LinearGradient(linePoints[0].x, linePoints[0].y, linePoints[linePoints.length-1].x, linePoints[linePoints.length-1].y,
                new int[] { Color.parseColor("#C7DE8A"), Color.parseColor("#16E3E1") },
                null, Shader.TileMode.CLAMP);
        linePaint.setShader(mShader);
        linePaint.setStrokeJoin(Paint.Join.ROUND);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        if (isPlayAnim) {
            Path dst = new Path();
            PathMeasure measure = new PathMeasure(linePath, false);
            measure.getSegment(0, currentValue * measure.getLength(), dst, true);
            canvas.drawPath(dst, linePaint);
        } else {
            canvas.drawPath(linePath, linePaint);
        }
    }

    /**
     * 绘制曲线上的锚点
     *
     * @param canvas
     */
    private void drawLinePoints(Canvas canvas) {
        if (linePoints == null) return;

        float pointWidth = dip2px(pointWidthDP) / 2;
        int pointCount = linePoints.length;
        if (isPlayAnim) {
            pointCount = Math.round(currentValue * linePoints.length);
        }
        for (int i = 0; i < pointCount; i++) {
            Point point = linePoints[i];
            if (point == null) break;
            canvas.drawRect(point.x-1.5f,point.y,point.x+1.5f,0,pointPaint);
            canvas.drawCircle(point.x,0,pointWidth,pointPaint);
            //绘制点的文本
            drawLinePointText(canvas, String.valueOf(dataList.get(i).getValue()),String.valueOf(data.get(i)), point.x, point.y);
        }
    }

    /**
     * 获取value值所占的view高度
     *
     * @param value
     * @return
     */
    private int getValueHeight(int value) {
        float valuePercent = Math.abs(value - minValue) * 100f / (Math.abs(maxValue - minValue) * 100f);//计算value所占百分比
        return (int) (getViewDrawHeight() * valuePercent + bottomSpace + 0.5f);//底部加上间隔
    }

    /**
     * 获取绘制区域高度
     *
     * @return
     */
    private float getViewDrawHeight() {
        return getMeasuredHeight() * heightPercent;
    }

    /**
     * 初始化曲线数据
     */
    private void setupLine() {
        if (dataList.isEmpty()) return;
        int stepTemp = getTableStart();
        Point pre = new Point();
        pre.set(stepTemp, -getValueHeight(dataList.get(0).getValue()));//坐标系从0,0默认在第四象限绘制
        linePoints[0] = pre;
        linePath.moveTo(pre.x, pre.y);

        if (dataList.size() == 1) {
            isInitialized = true;
            return;
        }

        for (int i = 1; i < dataList.size(); i++) {
            Data data = dataList.get(i);
            Point next = new Point();
            next.set(stepTemp += stepSpace, -getValueHeight(data.getValue()));

            if (isBezierLine) {
                int cW = pre.x + stepSpace / 2;

                Point p1 = new Point();//控制点1
                p1.set(cW, pre.y);

                Point p2 = new Point();//控制点2
                p2.set(cW, next.y);

                linePath.cubicTo(p1.x, p1.y, p2.x, p2.y, next.x, next.y);//创建三阶贝塞尔曲线
            } else {
                linePath.lineTo(next.x, next.y);
            }

            pre = next;
            linePoints[i] = next;
        }

        isInitialized = true;
    }

    private int dip2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    private void refreshLayout() {
        resetParam();
        requestLayout();
        postInvalidate();
    }

    /*-------------可操作方法---------------*/

    /**
     * 设置数据
     *
     * @param dataList
     */
    public void setData(List<Data> dataList,List<String> data) {
        if (dataList == null||data == null) {
            throw new RuntimeException("dataList cannot is null!");
        }
        if (dataList.isEmpty()||data.isEmpty()) return;
        this.dataList.clear();
        this.dataList.addAll(dataList);
        this.data.clear();
        this.data.addAll(data);

        maxValue = Collections.max(this.dataList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getValue() - o2.getValue();
            }
        }).getValue();

        minValue = Collections.min(this.dataList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getValue() - o2.getValue();
            }
        }).getValue();

        refreshLayout();
    }

    /**
     * 设置是否是贝塞尔曲线
     *
     * @param isBezier
     */
    public void setBezierLine(boolean isBezier) {
        isBezierLine = isBezier;
        refreshLayout();
    }



    /**
     * 设置曲线点的间距，标尺x轴间距
     *
     * @param dp
     */
    public void setStepSpace(int dp) {
        if (dp < stepSpaceDefault) {
            dp = stepSpaceDefault;
        }
        this.stepSpaceDP = dp;
        refreshLayout();
    }

    /**
     * 设置锚点尺寸
     *
     * @param dp
     */
    public void setPointWidth(float dp) {
        if (dp <= 0) {
            dp = pointWidthDefault;
        }
        this.pointWidthDP = dp;
        refreshLayout();
    }

    /**
     * 播放动画
     */
    public void playAnim() {
        this.isPlayAnim = true;
        if (isAnimating) return;
        if (valueAnimator != null) {
            valueAnimator.start();
        }
    }

    public static class Data {

        int value;

        public Data(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
}
