import Controllers.JogoController;
import Domain.Itens.ArmaPrincipal;
import Domain.Personagem.Cavaleiro;
import Domain.Personagem.Heroi;
import Domain.Personagem.NPC;

import java.io.FileNotFoundException;

public class mainAtaque {
    public static void main(String[] args) throws FileNotFoundException {

        Cavaleiro cavaleiroTeste = new Cavaleiro("Francisco", 150, 20, 1, 30);

        ArmaPrincipal espada = new ArmaPrincipal("Espada Fixe", 10, 5, 12);
        espada.addHeroiPermitido("Cavaleiro");

        cavaleiroTeste.setArmaPrincipal(espada);

        NPC dragao = new NPC("Dragao Forte", 120, 5, 500);

        JogoController jogoController = new JogoController();
        jogoController.atacar(cavaleiroTeste, dragao);
    }
}
