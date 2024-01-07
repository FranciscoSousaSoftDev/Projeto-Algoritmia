package Domain.Personagem;

public class Feiticeiro extends Heroi {

    public Feiticeiro(String nome, int vidaMax, int força, int nivel, int ouro) {
        super(nome, vidaMax, força, nivel, ouro);
    }

    @Override
    public void usarPoção() {
    }

}
