package Repository;

import Domain.Itens.ItemHeroi;
import Tools.CSVitensReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ItensRepository {
    private ArrayList<ItemHeroi> itensHeroi;

    /**
     * método para ler o ficheiro do vendedor
     * @param filePath caminho do ficheiro
     * @throws FileNotFoundException se não encontrar o ficheiro
     */
    public ItensRepository(String filePath) throws FileNotFoundException {
         CSVitensReader csVitensReader = new CSVitensReader(filePath);
        this.itensHeroi = csVitensReader.readCSVtoRepository();
    }

    public ArrayList<ItemHeroi> getItensHeroi() {
        return itensHeroi;
    }
}
