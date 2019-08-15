package com.example.higor.contadordevidamagic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;

public class Lista extends AppCompatActivity {

    private ArrayList<String> nomes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);



        Bundle extras = getIntent().getExtras();
        String[] jogadores = new String[8];
        if (extras != null) {
            jogadores = extras.getStringArray("jogadores");
        }
        nomes.addAll(Arrays.asList(jogadores));
        initRecyclerView();

    }

    private void initRecyclerView(){

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = null;


        try {
           /* RecyclerView */recyclerView = findViewById(R.id.iv2);
        }catch (Exception e) {
            Log.e("tag","aa",e);
        }

        Log.e("erro1","inicioy");

        //recyclerView.setLayoutManager(llm);
        recyclerView.setLayoutManager( new GridLayoutManager(Lista.this,2));
        RCAdapter adapter = new RCAdapter(nomes,this);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        return;
    }

    }
