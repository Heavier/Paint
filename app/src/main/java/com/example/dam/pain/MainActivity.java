package com.example.dam.pain;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Vista vista;
    private android.widget.ImageButton btBlue;
    private android.widget.ImageButton btGreen;
    private android.widget.ImageButton btRed;
    private android.widget.ImageButton btColor;
    private android.widget.RelativeLayout view;

    private boolean openColor;
    private ImageButton ibFormas;
    private ImageButton ibCuadrado;
    private ImageButton ibCirculo;
    private ImageButton ibLibre;
    private boolean openForma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ibLibre = (ImageButton) findViewById(R.id.ibLibre);
        this.ibCirculo = (ImageButton) findViewById(R.id.ibCirculo);
        this.ibCuadrado = (ImageButton) findViewById(R.id.ibCuadrado);
        this.ibFormas = (ImageButton) findViewById(R.id.ibFormas);
        this.view = (RelativeLayout) findViewById(R.id.view);
        this.btColor = (ImageButton) findViewById(R.id.btColor);
        this.btRed = (ImageButton) findViewById(R.id.btRed);
        this.btGreen = (ImageButton) findViewById(R.id.btGreen);
        this.btBlue = (ImageButton) findViewById(R.id.btBlue);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inicia el booleano de los botones.
        openColor = true;
        openForma = true;

        vista = (Vista) findViewById(R.id.vista);
    }


    public void showColors(View view) {
        if(openColor) {
            btRed.setVisibility(View.VISIBLE);
            btGreen.setVisibility(View.VISIBLE);
            btBlue.setVisibility(View.VISIBLE);
            openColor = false;
        }else{
            btRed.setVisibility(View.INVISIBLE);
            btGreen.setVisibility(View.INVISIBLE);
            btBlue.setVisibility(View.INVISIBLE);
            openColor = true;
        }
    }

    public void setRed(View view) {
        vista.setRed();
    }

    public void setGreen(View view) {
        vista.setGreen();
    }

    public void setBlue(View view) {
        vista.setBlue();
    }

    public void showFormas(View view) {
        if(openForma) {
            ibCuadrado.setVisibility(View.VISIBLE);
            ibCirculo.setVisibility(View.VISIBLE);
            ibLibre.setVisibility(View.VISIBLE);
            openForma = false;
        }else{
            ibCuadrado.setVisibility(View.INVISIBLE);
            ibCirculo.setVisibility(View.INVISIBLE);
            ibLibre.setVisibility(View.INVISIBLE);
            openForma = true;
        }
    }
    public void cuadrado(View view) {
        vista.cuadrado();
    }
    public void circulo(View view) {
        vista.circulo();
    }
    public void libre(View view) {
        vista.libre();
    }

    public void guardar(View view) {
        vista.guardar(this);
    }
}
