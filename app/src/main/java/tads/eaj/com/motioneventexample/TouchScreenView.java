package tads.eaj.com.motioneventexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * CREATED BY RICARDO LECHETA
 */

public class TouchScreenView extends View {

    private Drawable img;
    int x, y;
    private boolean selecionou;
    private int larguraTela;
    private int alturaTela;
    private int larguraImg;
    private int alturaImg;

    public TouchScreenView(Context context, AttributeSet attrs){
        super (context, null);
        img = ContextCompat.getDrawable(context, R.mipmap.ic_launcher);
        larguraImg = img.getIntrinsicWidth();
        alturaImg = img.getIntrinsicHeight();
        setFocusable(true);
    }


    public TouchScreenView(Context context){
        this(context, null);

    }
    protected void onSizeChanged(int width, int height, int oldw, int oldh){
        super.onSizeChanged(width, height, oldw, oldh);
        this.larguraTela = width;
        this.alturaTela = height;
        x = width / 2 - (larguraImg / 2);
        y = height / 2 - (alturaImg / 2);
    }
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint pincel = new Paint();
        pincel.setColor(Color.WHITE);
        canvas.drawRect(0,0,larguraTela,alturaTela,pincel);
        img.setBounds(x, y, x + larguraImg, y+alturaImg);
        img.draw(canvas);
    }
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                selecionou = img.copyBounds().contains((int)x, (int) y);
                break;
            case MotionEvent.ACTION_MOVE:
                if (selecionou){
                    this.x = (int) x - (larguraImg / 2);
                    this.y = (int) y - (alturaImg / 2);
                }
                break;
            case MotionEvent.ACTION_UP:
                selecionou = false;
                break;
        }
        invalidate(); //Redesenhe a tela
        return true;
    }
}
