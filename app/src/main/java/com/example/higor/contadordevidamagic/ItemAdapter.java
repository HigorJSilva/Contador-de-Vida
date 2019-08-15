package com.example.higor.contadordevidamagic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

import modelo.Jogador;

public class ItemAdapter extends ArrayAdapter<String>{
    Context activeContext;//adicionado v3
    public ItemAdapter(Context context, List<String> lista) {
        super(context, 0, lista);
        this.activeContext = context;// adicionado v3
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_adapter, parent, false);
        }

        TextView jogador = (TextView) itemView.findViewById(R.id.nomeJogador);
        jogador.setText(getItem(position));

        TextView tipoNome = (TextView) itemView.findViewById(R.id.HP);
        tipoNome.setText("20");

        return itemView;
    }
}
