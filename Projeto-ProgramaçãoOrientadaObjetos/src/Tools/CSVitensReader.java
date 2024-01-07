package Tools;

import Domain.Itens.ConsumivelCombate;
import Domain.Itens.ArmaPrincipal;
import Domain.Itens.ItemHeroi;
import Domain.Itens.Poção;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVitensReader {

    private String filePath;

    public CSVitensReader(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Método que separa os herois permitidos e os coloca num Array
     * @param heroisPermitidoFicheiro String com todos os herois permitidos de um certo item
     * @return ArrayList de Strings com os herois que sao permitidos comprarem o item
     */
    public static ArrayList<String> verificarHeroisPermitidos(String heroisPermitidoFicheiro) {

        String[] heroisString = heroisPermitidoFicheiro.split(",");
        ArrayList<String> heroisPermitidos = new ArrayList<>();
        for (int i = 0; i < heroisString.length; i++) {
            heroisPermitidos.add(heroisString[i]);
        }
        return heroisPermitidos;
    }

    /**
     * Método para ler o ficheiro CSV com os itens e passá-lo para o repositório
     * @return ArrayList dos itens do ficheiro
     * @throws FileNotFoundException se não encontrar o ficheiro
     */
    public ArrayList<ItemHeroi> readCSVtoRepository() throws FileNotFoundException {
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);

        String linha = scanner.nextLine();

        ArrayList<ItemHeroi> itensHeroi = new ArrayList<>();

        while (scanner.hasNextLine()) {
            linha = scanner.nextLine();
            String[] linhaDividida = linha.split(";");

            ItemHeroi item = null;
            String tipoItem = linhaDividida[0];
            String nome = linhaDividida[1];
            int preço = Integer.parseInt(linhaDividida[2]);
            String heroisPermitidos = linhaDividida[3];
            heroisPermitidos=heroisPermitidos.replace("[", "");
            heroisPermitidos=heroisPermitidos.replace("]", "");
            switch (tipoItem) {
                case "ArmaPrincipal":
                    int ataque = Integer.parseInt(linhaDividida[4]);
                    int ataqueEspecial = Integer.parseInt(linhaDividida[5]);

                    item = new ArmaPrincipal(nome, preço, ataque, ataqueEspecial);
                    break;

                case "ConsumivelCombate":
                    int ataqueInstantaneo = Integer.parseInt(linhaDividida[6]);
                    item = new ConsumivelCombate(nome, preço, ataqueInstantaneo);
                    break;

                case "Pocao":
                    int vida = Integer.parseInt(linhaDividida[7]);
                    int força = Integer.parseInt(linhaDividida[8]);
                    item = new Poção(nome, preço, vida, força);
            }

            itensHeroi.add(item);
            item.addHeroiPermitido(verificarHeroisPermitidos(heroisPermitidos));
        }

        return itensHeroi;

    }

}
