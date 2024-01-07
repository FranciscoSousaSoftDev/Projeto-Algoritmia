package Domain.Personagem;

import Domain.Itens.ArmaPrincipal;
import Repository.ItensRepository;

import java.io.FileNotFoundException;

public class Cavaleiro extends Heroi {


    public Cavaleiro(String nome, int vidaMax, int força, int nivel, int ouro) throws FileNotFoundException {
        super(nome, vidaMax, força, nivel, ouro);
        ItensRepository itensRepository = new ItensRepository("Ficheiros/ItensHeroiRPG.csv");

    }

    @Override
    public void usarPoção() {
    }
}
