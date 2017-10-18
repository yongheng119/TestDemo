package com.example.yongh.testfortextdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by yongh on 2017/10/15.
 */

public class NewFlagTextView extends android.support.v7.widget.AppCompatTextView {

  private Paint newPointPaint;
  private Paint newPointTextPaint;

  private int newPointRadius = 20;
  private int newPointTextPadding = 10;

  public NewFlagTextView(Context context) {
    super(context);
    initPaint();
  }

  public NewFlagTextView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    initPaint();
  }

  public NewFlagTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initPaint();
  }

  private void initPaint() {
    newPointPaint = new Paint();
    newPointPaint.setColor(Color.RED);
    newPointPaint.setAntiAlias(true);

    newPointTextPaint = new TextPaint();
    newPointTextPaint.setColor(Color.WHITE);
    newPointTextPaint.setTextSize(40);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    TextPaint textPaint = getPaint();
    Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
    float textBaseLine =
        getHeight() / 2 + ((fontMetrics.descent - fontMetrics.ascent) - fontMetrics.bottom) / 2;
    float textTop = textBaseLine + fontMetrics.ascent;
    Log.i("xsj", "textBaseLine : " + textBaseLine);
    Paint baseLinePaint = new Paint();
    baseLinePaint.setColor(Color.RED);
    baseLinePaint.setStrokeWidth(5);
    //canvas.drawLine(0, textBaseLine, getWidth(), textBaseLine, baseLinePaint);
    //canvas.drawLine(0, textTop, getWidth(), textTop, baseLinePaint);

    //drawNewPoint(canvas, textTop);
    drawNewPointNumber(canvas, textTop);
  }

  private void drawNewPointNumber(Canvas canvas, float textTop) {
    TextPaint textPaint = getPaint();
    float textWidth = textPaint.measureText(getText().toString());

    String drawText = "18";
    // 计算标新数字的宽度
    float pointTextWidth = newPointTextPaint.measureText(drawText);

    // 计算红点的半径：数字的宽度加再padding
    float circleRadius = pointTextWidth / 2 + newPointTextPadding;
    float drawX = getPaddingLeft() + textWidth + circleRadius;
    float drawY = textTop - circleRadius;
    canvas.drawCircle(drawX, drawY, pointTextWidth / 2 + newPointTextPadding, newPointPaint);

    Paint.FontMetrics fontMetrics = newPointTextPaint.getFontMetrics();
    // 计算数字的左端x值
    float drawTextX = drawX - circleRadius + newPointTextPadding;
    // 计算数字的baseline y值
    float drawTextY = drawY - circleRadius
        + circleRadius
        + ((fontMetrics.descent - fontMetrics.ascent) - fontMetrics.bottom) / 2;
    canvas.drawText(drawText, drawTextX, drawTextY, newPointTextPaint);
  }

  private void drawNewPoint(Canvas canvas, float textTop) {
    TextPaint textPaint = getPaint();
    float textWidth = textPaint.measureText(getText().toString());

    // 计算小红点的圆心x距离：Text的右端+小红点半径
    float drawX = getPaddingLeft() + textWidth + newPointRadius;
    // 计算小红点的圆心y距离：Text的顶端-小红点半径
    float drawY = textTop - newPointRadius;

    canvas.drawCircle(drawX, drawY, newPointRadius, newPointPaint);
  }
}
