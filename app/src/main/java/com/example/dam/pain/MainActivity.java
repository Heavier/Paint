package com.example.dam.pain;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private Vista vista;
    private android.widget.ImageButton btBlue;
    private android.widget.ImageButton btGreen;
    private android.widget.ImageButton btRed;
    private android.widget.ImageButton ibColor;
    private android.widget.RelativeLayout view;
    private boolean openColor,openForma, mostrar;
    private ImageButton ibFormas;
    private ImageButton ibCuadrado;
    private ImageButton ibCirculo;
    private ImageButton ibLibre;
    private ImageButton ibGuardar;
    private android.widget.SeekBar sbGrosor;
    private ImageButton ibMostrar;
    private ImageButton ibImportar;
    private ImageButton ibLimpiar;
    private ImageButton ibFill;
    private ImageButton ibFillStroke;
    private ImageButton ibStroke;
    private int grosor = 10;
    private Uri uri;
    private Bitmap bitmap;
    private android.widget.ImageView ivImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ivImagen = (ImageView) findViewById(R.id.ivImagen);
        this.ibStroke = (ImageButton) findViewById(R.id.ibStroke);
        this.ibFillStroke = (ImageButton) findViewById(R.id.ibFillStroke);
        this.ibFill = (ImageButton) findViewById(R.id.ibFill);
        this.ibLimpiar = (ImageButton) findViewById(R.id.ibLimpiar);
        this.ibImportar = (ImageButton) findViewById(R.id.ibImportar);
        this.ibMostrar = (ImageButton) findViewById(R.id.ibMostrar);
        this.sbGrosor = (SeekBar) findViewById(R.id.sbGrosor);
        this.ibGuardar = (ImageButton) findViewById(R.id.ibGuardar);
        this.ibLibre = (ImageButton) findViewById(R.id.ibLibre);
        this.ibCirculo = (ImageButton) findViewById(R.id.ibCirculo);
        this.ibCuadrado = (ImageButton) findViewById(R.id.ibCuadrado);
        this.ibFormas = (ImageButton) findViewById(R.id.ibFormas);
        this.view = (RelativeLayout) findViewById(R.id.view);
        this.ibColor = (ImageButton) findViewById(R.id.ibColor);
        this.btRed = (ImageButton) findViewById(R.id.btRed);
        this.btGreen = (ImageButton) findViewById(R.id.btGreen);
        this.btBlue = (ImageButton) findViewById(R.id.btBlue);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inicia el booleano de los botones.
        openColor = true;
        openForma = true;
        mostrar = true;

        vista = (Vista) findViewById(R.id.vista);

        sbGrosor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                grosor = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vista.setGrosor(grosor);
            }
        });

        Uri original = getIntent().getData();
        if (original != null) {
            ivImagen.setImageURI(original);
            vista.importar(bitmap);
        }
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

    public void mostrar(View view) {
        if (mostrar) {
            ibGuardar.setVisibility(View.VISIBLE);
            ivImagen.setVisibility(View.VISIBLE);
            ibLimpiar.setVisibility(View.VISIBLE);
            ibImportar.setVisibility(View.VISIBLE);
            ibColor.setVisibility(View.VISIBLE);
            ibFill.setVisibility(View.VISIBLE);
            ibFillStroke.setVisibility(View.VISIBLE);
            ibStroke.setVisibility(View.VISIBLE);
            sbGrosor.setVisibility(View.VISIBLE);
            ibFormas.setVisibility(View.VISIBLE);
            mostrar = false;
        }else{
            ibGuardar.setVisibility(View.GONE);
            ivImagen.setVisibility(View.GONE);
            ibLimpiar.setVisibility(View.GONE);
            ibImportar.setVisibility(View.GONE);
            ibColor.setVisibility(View.GONE);
            ibFill.setVisibility(View.GONE);
            ibFillStroke.setVisibility(View.GONE);
            ibStroke.setVisibility(View.GONE);
            sbGrosor.setVisibility(View.GONE);
            ibFormas.setVisibility(View.GONE);
            mostrar = true;
        }
    }

    public void importar(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {

            uri = data.getData();
            if (uri != null) {
                ivImagen.setImageURI(uri);
                bitmap = ((BitmapDrawable)ivImagen.getDrawable()).getBitmap();
                vista.importar(bitmap);
            }
        }
    }

    public void limpiar(View view) {
        vista.limpiar();
    }

    public void fill(View view) {
        vista.fill();
    }

    public void fillStroke(View view) {
        vista.fillStroke();
    }

    public void stroke(View view) {
        vista.stroke();
    }

}
