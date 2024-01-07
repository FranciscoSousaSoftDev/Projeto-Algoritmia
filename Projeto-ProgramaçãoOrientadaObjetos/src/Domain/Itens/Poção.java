package Domain.Itens;

import Domain.Itens.Consumivel;

public class Poção extends Consumivel {
    private int curarVida;
    private int aumentarForça;

    public Poção(String nome, int preçoOuro, int curarVida, int aumentarForça) {
        super(nome, preçoOuro);
        this.curarVida = curarVida;
        this.aumentarForça = aumentarForça;
    }

    public int getCurarVida() {
        return curarVida;
    }

    public int getAumentarForça() {
        return aumentarForça;
    }

    @Override
    public void mostrarDetalhes(){
        super.mostrarDetalhes();
        System.out.println("Vida a curar: " + this.curarVida + " | força a aumentar: " + this.aumentarForça);
    }

}
