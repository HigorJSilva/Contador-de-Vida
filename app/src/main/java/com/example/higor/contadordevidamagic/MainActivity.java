package com.example.higor.contadordevidamagic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import modelo.Jogador;

public class MainActivity extends AppCompatActivity {
    
    ImageView logo;
    TextView nJogadores;
    Button iniciar;
    TextInputLayout til;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nJogadores = findViewById(R.id.numeroJogadores);
        til =findViewById(R.id.til);
        logo = findViewById(R.id.logoID);
        setFadein();
        iniciar = findViewById(R.id.btnIniciar);
        til.setError(null);


        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( nJogadores.getText().toString().equals("") ||  nJogadores.getText().toString().equals("") ){
                    til.setError("Insira o número de jogadores");
                }
                else if( nJogadores.getText().toString().equals("1") ||  Integer.parseInt(nJogadores.getText().toString()) > 8 ){
                   til.setError("Insira um número maior que 1 e menor que 8 ");

                }else
                    {
                    try {
                        criarJogadores(Integer.parseInt(nJogadores.getText().toString()));
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });



    }

    private void criarJogadores(Integer jogadores) {
        Intent i = new Intent(getBaseContext(), FormActivity.class);
        i.putExtra("nJogadores", jogadores);
        startActivity(i);

    }


    private void setFadein() {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(5000);
        logo.startAnimation(fadeIn);
    }
}
