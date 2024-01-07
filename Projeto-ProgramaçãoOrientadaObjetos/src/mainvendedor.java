import Domain.Itens.ArmaPrincipal;
import Domain.Personagem.Cavaleiro;
import Domain.Vendedor;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class mainvendedor {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Vendedor vendedor = new Vendedor();

        Cavaleiro cavaleiroTeste = new Cavaleiro("Francisco", 150, 20, 1, 30);

        ArmaPrincipal espada = new ArmaPrincipal("Espada Fixe", 10, 5, 12);
        espada.addHeroiPermitido("Cavaleiro");

        cavaleiroTeste.setArmaPrincipal(espada);
        int item;
        vendedor.loja(cavaleiroTeste);

    }
}
