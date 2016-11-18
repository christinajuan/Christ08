package com.example.user.christ08;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2016/11/18.
 */

public class GameView extends View{
    private Resources res;
    private Context context;
    private int viewW,viewH;
    private boolean isInit;
    private Bitmap bmpBall;
    private Matrix matrix;
    private float ballW, ballH,ballX, ballY;
    Timer timer;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        res = context.getResources();
        matrix = new Matrix();
        timer = new Timer();
    }

    private void init(){
        viewW = getWidth(); viewH = getHeight();
        Log.v("brad", viewW + "x" +viewH);

        bmpBall = BitmapFactory.decodeResource(res,R.drawable.b1);
        ballW = viewW / 12f; ballH = ballW;
        bmpBall = resizeBmp(bmpBall,ballW,ballH);

        timer.schedule(new BallTask(),1000,62);

        isInit = true;
    }

    private class BallTask extends TimerTask{
        @Override
        public void run() {
            ballX +=10;ballY+=10;
            postInvalidate();
        }
    }

    Bitmap resizeBmp(Bitmap src, float width, float height){
        matrix.reset();
        matrix.postScale(width/src.getWidth(),height/src.getHeight());
        return  Bitmap.createBitmap(bmpBall,0,0,bmpBall.getWidth(),bmpBall.getHeight(),matrix,false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInit) init();

        canvas.drawBitmap(bmpBall,ballX,ballY,null);
    }
}
