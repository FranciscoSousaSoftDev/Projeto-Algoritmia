package Domain.Itens;

import java.util.ArrayList;

public abstract class ItemHeroi {
    private String nome;
    private int preçoOuro;
    private ArrayList<String> heroisPermitidos;

    public ItemHeroi(String nome, int preçoOuro) {
        this.nome = nome;
        this.preçoOuro = preçoOuro;
        this.heroisPermitidos = new ArrayList<String>();
    }

    public String getNome() {
        return nome;
    }

    public int getPreçoOuro() {
        return preçoOuro;
    }



    public ArrayList<String> getHeroisPermitidos() {
        return heroisPermitidos;
    }

    public void mostrarDetalhes() {
        System.out.println("Detalhes do item: \nnome: " + this.nome + " | preço em ouro: " + this.preçoOuro);
        for (String heroiAtual : this.heroisPermitidos) {
            System.out.println(heroiAtual);
        }
    }

    public void addHeroiPermitido(ArrayList<String> heroisNovos) {
        this.heroisPermitidos.addAll(heroisNovos);
    }
    public void addHeroiPermitido(String heroiNovo){
        this.heroisPermitidos.add(heroiNovo);
    }
}