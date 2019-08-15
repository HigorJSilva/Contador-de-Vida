package modelo;

import java.util.ArrayList;

public class Jogador {
    private String nome;
    private ArrayList<Integer> vida;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Integer> getVida() {
        return vida;
    }

    public void setVida(ArrayList<Integer> vida) {
        this.vida = vida;
    }
}
