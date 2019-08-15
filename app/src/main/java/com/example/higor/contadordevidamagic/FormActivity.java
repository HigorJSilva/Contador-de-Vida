package com.example.higor.contadordevidamagic;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


import static com.example.higor.contadordevidamagic.R.color.colorAccent;


public class FormActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button comecar;
    EditText[] textos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Bundle extras = getIntent().getExtras();
        int value =0;
        if (extras != null) {
             value = extras.getInt("nJogadores");
        }

        linearLayout = findViewById(R.id.linearLayout);
        textos= new  EditText[value];
        for (int i = 1; i <= value; i++) {

            EditText editText = new EditText(this);

           LinearLayout.LayoutParams editTextParams = new LinearLayout.LayoutParams(
                   LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            editText.setLayoutParams(editTextParams);

            editText.setTextSize(16);

            editText.setHint("Nome do "+i+"º"+" jogador");

            TextInputLayout textInputLayout = new TextInputLayout(this);
            textInputLayout.setId(View.generateViewId());
            LinearLayout.LayoutParams textInputLayoutParams = new LinearLayout.LayoutParams(
                   LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            textInputLayout.setLayoutParams(textInputLayoutParams);
            textInputLayout.setHintTextAppearance(R.style.texto);



            editText.setLayoutParams(editTextParams);
            textInputLayout.addView(editText, editTextParams);
            linearLayout.addView(textInputLayout, editTextParams);
            textos[i-1]=editText;
            editTextParams.setMargins(20,50,20,20);


        }
        comecar = new Button(getApplicationContext());
        comecar.setText("Começar");
        comecar.setBackground(getResources().getDrawable(R.drawable.bg_rounded_input_field));
        comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] jog = new String[textos.length];
                for (int i = 0; i <textos.length ; i++) {
                    jog[i] =  (   textos[i].getText().toString() );
                }
                Intent intent = new Intent(FormActivity.this, Lista.class);
                intent.putExtra("jogadores", jog);
                startActivity(intent);
                finish();
            }




        });




        linearLayout.addView(comecar);

    }
}

