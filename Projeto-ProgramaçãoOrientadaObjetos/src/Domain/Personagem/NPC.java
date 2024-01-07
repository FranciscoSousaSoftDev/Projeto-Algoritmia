package Domain.Personagem;

import Domain.Personagem.Entidade;

public class NPC extends Entidade {
    private int ouro;

    public NPC(String nome, int vidaMax, int força, int ouro) {
        super(nome, vidaMax, força);
        this.ouro = ouro;
    }

    public int getOuro() {
        return ouro;
    }
}
