package Domain.Personagem;

import Domain.Itens.ArmaPrincipal;

public class Arqueiro extends Heroi {

    public Arqueiro(String nome, int vidaMax, int força, int nivel, int ouro) {
        super(nome, vidaMax, força, nivel, ouro);
    }

    @Override
    public void usarPoção() {

    }

}
