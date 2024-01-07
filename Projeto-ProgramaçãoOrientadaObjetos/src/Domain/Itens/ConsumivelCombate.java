package Domain.Itens;

import Domain.Itens.Consumivel;

public class ConsumivelCombate extends Consumivel {
    private int ataqueInstantaneo;

    public ConsumivelCombate(String nome, int preçoOuro, int ataqueInstantaneo) {
        super(nome, preçoOuro);
        this.ataqueInstantaneo = ataqueInstantaneo;
    }

    @Override
    public void mostrarDetalhes(){
        super.mostrarDetalhes();
        System.out.println("Ataque intantaneo aumentando em: " + this.ataqueInstantaneo );
    }

    public int getAtaqueInstantaneo() {
        return this.ataqueInstantaneo;
    }


}
