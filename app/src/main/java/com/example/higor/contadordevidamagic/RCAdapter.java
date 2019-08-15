package com.example.higor.contadordevidamagic;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.ViewHolder> {

    private ArrayList<String> nomes = new ArrayList<>();
    private ArrayList<Integer> vida = new ArrayList<>();
    private Context context;
    private Integer pontos;


    public RCAdapter(ArrayList<String> nomes, Context context) {
        this.nomes = nomes;
        this.context = context;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
       
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);

        return new ViewHolder(view);
    }

    public void removeAt(int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Um jogador não está mais entre nós");
        alert.setMessage("Jogador "+nomes.get(position)+" morreu");
        alert.setCancelable(true);
        alert.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();


        nomes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, nomes.size());
        if(nomes.size()==1){
            mostraGanhador();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.jogador.setText(nomes.get(i));
        final View botaoMais = viewHolder.botaoMais;
        final View botaoMenos = viewHolder.botaoMenos;
        final TextView ptsVida = viewHolder.pontosVida;

        final AlertDialog.Builder add = new AlertDialog.Builder(viewHolder.itemView.getContext());
        add.setCancelable(true);


        botaoMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final EditText input = new EditText(viewHolder.itemView.getContext());
                add.setView(input);
                add.setMessage("Quantos pontos de vida você deseja adicionar?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               Integer soma = Integer.valueOf(ptsVida.getText().toString()) + Integer.valueOf(input.getText().toString());
                                ptsVida.setText(String.valueOf(soma));

                            }
                        });
                final Dialog dialog = add.create();
                dialog.show();
            }
        });
        botaoMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              final  EditText input = new EditText(viewHolder.itemView.getContext());
                add.setView(input);
                add.setMessage("Quantos pontos de vida você deseja remover?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Integer sub = Integer.valueOf(ptsVida.getText().toString()) - Integer.valueOf(input.getText().toString());
                                ptsVida.setText(String.valueOf(sub));
                                if(checaMorte(ptsVida.getText().toString())) {
                                    removeAt(i);
                                }
                            }
                        });
               final Dialog dialog = add.create();
                dialog.show();
            }
        });


    }

    private void mostraGanhador(){
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Temos um ganhador");
        alert.setMessage("Jogador "+nomes.get(0)+" foi o campeão");
        alert.setCancelable(true);
        alert.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }

    private boolean checaMorte(String vida ){
        if(Integer.valueOf(vida) <=0){
            return  true;
        }
        return  false;
    }

    @Override
    public int getItemCount() {
        return nomes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView jogador, pontosVida;
        private ImageButton botaoMais, botaoMenos;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);
            jogador = itemView.findViewById(R.id.nomeJogador);
            pontosVida = itemView.findViewById(R.id.HP);
            botaoMais = itemView.findViewById(R.id.botaoMais);
            botaoMenos = itemView.findViewById(R.id.botaoMenos);
        }

    }
}
