package com.example.chen.guigup2p.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chen.guigup2p.R;

/**
 * 自定义圆形进度条
 * 1. 创建画布
 * 2. 初始化画笔
 * 3. 获取视图宽高-> 圆环半径/宽度/圆心-> 绘制圆环
 *
 */

public class RoundProgress extends View {
    private Paint paint ;

/* 使用自定义属性:替代
    private int roundColor = Color.GRAY;
    private int ProgressColor = Color.RED;
    private int textColor = Color.BLUE;


    private int textSize = UIUtils.px2dp(80);//文字大小 使用px->dp
    private int roundWidth = UIUtils.px2dp(40); //圆环宽度 10px->dp

    //进度大小
    private float roundMax = 100;
    private float roundProgress = 50;
*/

    private int roundColor;
    private int ProgressColor ;
    private int textColor ;


    private float textSize ;//= UIUtils.px2dp(80);//文字大小 使用px->dp
    private float roundWidth ;//= UIUtils.px2dp(40); //圆环宽度 10px->dp

    //进度大小
    private float roundMax ;//= 100;
    private float roundProgress;// = 50;



    private int with ;//当前视图的宽度(高度)


    public RoundProgress(Context context) {
         this(context,null);
     }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint.setAntiAlias(true);//pain 去除锯齿


        /*提取自定义属性*/
        //1. 获取所有资源数据
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);


        //2. 为资源数据赋值
        roundColor = typedArray.getColor(R.styleable.RoundProgress_roundColor, Color.GRAY);
        ProgressColor = typedArray.getColor(R.styleable.RoundProgress_ProgressColor,Color.RED);
        textColor = typedArray.getColor(R.styleable.RoundProgress_textColor,Color.BLUE);

        textSize = typedArray.getDimension(R.styleable.RoundProgress_textSize,40);
        roundWidth =  typedArray.getDimension(R.styleable.RoundProgress_roundWidth,40);

        roundMax = typedArray.getFloat(R.styleable.RoundProgress_roundMax,100);
        roundProgress = typedArray.getFloat(R.styleable.RoundProgress_roundProgress,0);

        //3.释放资源数据
        typedArray.recycle();
    }


    //测量视图
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        with = this.getMeasuredWidth();
    }


    //创建画布 绘制圆环
    @Override
    protected void onDraw(Canvas canvas) {
        //1. 获取圆心坐标 圆环半径 圆环宽度-->绘制圆环
        int cx = with/2;
        int cy = with/2;

        float radios = with/2 - roundWidth/2; //圆环中心半径


        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);//描边FILL(填充)  STROKE(描边) FILL_AND_STROKE(填充+描边)

        paint.setStrokeWidth(roundWidth);
        canvas.drawCircle(cx,cy,radios,paint);


        //2.绘制圆环

        RectF oval = new RectF(roundWidth/2, roundWidth/2,with-roundWidth/2,with-roundWidth/2);//圆弧的外切矩形

        float startAngle = 0;
        float sweepAngle = roundProgress/roundMax *360;

        paint.setColor(ProgressColor);
        canvas.drawArc(oval,startAngle,sweepAngle,false,paint);


        //3. 绘制文字



        String text = roundProgress+"%";

        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        paint.setColor(textColor);

        Rect rectText = new Rect();

        paint.getTextBounds(text,0,text.length(),rectText); //使用矩形框包裹文本，获取矩形框的宽高


        //文本区域的左下角
        int textX = cx-rectText.width()/2;
        int textY = cy+rectText.height()/2;
        canvas.drawText(text,textX,textY,paint);

    }


    public float getRoundMax() {
        return roundMax;
    }

    public void setRoundMax(float roundMax) {
        this.roundMax = roundMax;
    }

    public float getRoundProgress() {
        return roundProgress;
    }

    public void setRoundProgress(float roundProgress) {
        this.roundProgress = roundProgress;
    }
}
