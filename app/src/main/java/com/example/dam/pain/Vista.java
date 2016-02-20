package com.example.dam.pain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Vista extends View {
    private int w, h;
    private float x0, y0, x1, y1, x2, y2;
    private Paint pincel;
    private Bitmap mapaDeBits;
    private Canvas lienzoDeFondo;
    private Path path = new Path();
    private int figura;

    public Vista(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        pincel = new Paint();
        setRed();
        stroke();
        pincel.setAntiAlias(true);
        pincel.setStrokeWidth(10);
        figura = 1; //Libre
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        mapaDeBits = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        lienzoDeFondo = new Canvas(mapaDeBits);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mapaDeBits, 0, 0, null);
        canvas.drawPath(path, pincel);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x0 = x2 = x;
                y0 = y2 = y;
                path.moveTo(x0, y0);
                break;
            case MotionEvent.ACTION_MOVE:
                x1 = x;
                y1 = y;
                switch (figura) {
                    case 1: // Libre
                        lienzoDeFondo.drawPath(path, pincel);
                        path.quadTo(x0, y0, (x + x1) / 2, (y + y1) / 2);
                        break;
                    case 2: // Cuadrado
                        lienzoDeFondo.drawRect(Math.min(x2, x1), Math.min(y2, y1), Math.max(x2, x1), Math.max(y2, y1), pincel);
                        break;
                    case 3: // Circulo
                        float radio = (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)); // Pitágoras
                        lienzoDeFondo.drawCircle(x2, y2, radio, pincel);
                        break;
                }
                x0 = x1;
                y0 = y1;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                x1 = x;
                y1 = y;
                switch (figura) {
                    case 1: // Libre
                        lienzoDeFondo.drawPath(path, pincel);
                        path.reset();
                        break;
                    case 2: // Cuadrado
                        lienzoDeFondo.drawRect(Math.min(x2, x1), Math.min(y2, y1), Math.max(x2, x1), Math.max(y2, y1), pincel);
                        path.reset();
                        break;
                    case 3: // Circulo
                        float radio = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); // Pitágoras
                        lienzoDeFondo.drawCircle(x2, y2, radio, pincel);
                        path.reset();
                        break;
                }
                invalidate();
                break;
        }
        return true;
    }

    public void setRed() {
        pincel.setColor(Color.rgb(244, 67, 54));
    }

    public void setGreen() {
        pincel.setColor(Color.rgb(76, 175, 80));
    }

    public void setBlue() {
        pincel.setColor(Color.rgb(33, 150, 243));
    }

    public void libre() {
        figura = 1; // Libre
    }

    public void cuadrado() {
        figura = 2; // Cuadrado
    }

    public void circulo() {
        figura = 3; // Circulo
    }

    public void guardar(Context context){
        View content = this;
        content.setDrawingCacheEnabled(true);
        content.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = content.getDrawingCache();
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        File file = new File(path+"/image.png");
        FileOutputStream ostream;
        try {
            file.createNewFile();
            ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            ostream.flush();
            ostream.close();
            Toast.makeText(context, R.string.guardado, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiar() {
        mapaDeBits = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        lienzoDeFondo = new Canvas(mapaDeBits);
    }

    public void fill() {
        pincel.setStyle(Paint.Style.FILL);
    }

    public void fillStroke() {
        pincel.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void stroke() {
        pincel.setStyle(Paint.Style.STROKE);
    }

    public void setGrosor(int grosor) {
        pincel.setStrokeWidth(grosor);
    }

    public void importar(Bitmap bitmap) {
        /**
         * Qué es un bitmap mutable?
         * Permite editar el bitmap una vez creado, no me refiero a la manera habitual sino, por ejemplo
         * la forma en que trabaja StringBuilder con las cadenas de texto.
         * También ahorra memoria.
         */
        Bitmap workingBitmap = Bitmap.createBitmap(bitmap); // Inmutable
        mapaDeBits = workingBitmap.copy(Bitmap.Config.ARGB_8888, true); // Mutable
        lienzoDeFondo = new Canvas(mapaDeBits);
    }
}
