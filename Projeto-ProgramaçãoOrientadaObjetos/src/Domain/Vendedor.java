package Domain;

import Domain.Itens.ArmaPrincipal;
import Domain.Itens.Consumivel;
import Domain.Itens.ItemHeroi;
import Domain.Personagem.Heroi;
import Repository.ItensRepository;
import Tools.CSVitensReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Vendedor {
    ArrayList<ItemHeroi> loja;


    public Vendedor() throws FileNotFoundException {
        ItensRepository itensRepository = new ItensRepository("Ficheiros/ItensHeroiRPG.csv");
        this.loja = itensRepository.getItensHeroi();
    }

    public ArrayList<ItemHeroi> getLoja() {
        return loja;
    }

    public static boolean validarPermissao(Heroi jogador, ItemHeroi itemComprar) {
        for (String heroiPermitidoAtual : itemComprar.getHeroisPermitidos()) {
            if (jogador.getClass().getSimpleName().equals(heroiPermitidoAtual)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Método Loja onde é atualizada a loja do vendedor
     * @param heroi recebe o heroi para verificar se pode comprar algum tipo de item
     */
    public void loja(Heroi heroi) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();


        boolean flag = false;
        int a = 0;
        boolean comprouItem = false;
        boolean segundoItem = false;
        int ouroInicial = 0;
        ArrayList<Integer> arrayIndexAleatorio;
        arrayIndexAleatorio = new ArrayList<>();
        ArrayList<ItemHeroi> itensComprados;
        itensComprados = new ArrayList<>();
        while (arrayIndexAleatorio.size() < 10) {
            int indexAleatorio = random.nextInt(0, this.loja.size());
            if (!arrayIndexAleatorio.contains(indexAleatorio)) {
                arrayIndexAleatorio.add(indexAleatorio);
            }
        }
        for (int i = 0; i < arrayIndexAleatorio.size(); i++) {
            System.out.println("\nItem: " + i + ": ");
            this.loja.get(arrayIndexAleatorio.get(i)).mostrarDetalhes();
        }
        String resposta;
        do {
            System.out.println("Quer comprar algum item? S/N\n");
            resposta = input.next();
            resposta = resposta.toLowerCase();
            if (resposta.equals("s")) {
                System.out.println("Que item quer comprar?\n");
                int item = input.nextInt();
                for (int i = 0; i < arrayIndexAleatorio.size(); i++) {
                    if (i == item) {
                        ItemHeroi itemHeroi = this.loja.get(arrayIndexAleatorio.get(item));
                        ouroInicial = heroi.getOuro();
                        if (validarPermissao(heroi, itemHeroi)) {
                            if (heroi.getOuro() >= itemHeroi.getPreçoOuro()) {
                                if (a != item) {
                                    if (itemHeroi instanceof ArmaPrincipal) {
                                        heroi.setArmaPrincipal((ArmaPrincipal) itemHeroi);
                                        heroi.setOuro(heroi.getOuro() - itemHeroi.getPreçoOuro());
                                        System.out.println("Comprou a ArmaPrincipal: " + itemHeroi.getNome() + "\n");
                                        itensComprados.add(itemHeroi);
                                        //this.loja.remove(itemHeroi);
                                        a = item;
                                        System.out.println("Vou remover da minha loja: " + itemHeroi.getNome() + " disse o vendedor\n");
                                    } else if (itemHeroi instanceof Consumivel) {
                                        heroi.getInventário().add((Consumivel) itemHeroi);
                                        heroi.setOuro(heroi.getOuro() - itemHeroi.getPreçoOuro());
                                        System.out.println("Comprou o consumível: " + itemHeroi.getNome() + "\n");
                                        itensComprados.add(itemHeroi);
                                        a = item;
                                        System.out.println("Vou remover da minha loja:" + itemHeroi.getNome() + " disse o vendedor\n");
                                    }
                                } else {
                                    System.out.println("Já Comprou esse item. Escolha outro");
                                }
                            } else {
                                System.out.println("Não tem ouro suficiente. Escolha outro item\n");

                            }
                        } else {
                            System.out.println("Não é possível comprar o item porque o seu heroi não é compatível\n");

                        }
                        System.out.println("O seu ouro: " + ouroInicial + "| Custo: " + itemHeroi.getPreçoOuro() + "\n");
                    }

                }
            } else {
                System.out.println("Não comprou nada, " + heroi.getNome() + ". Volte sempre\n");
                return;
            }
        } while (resposta.equals("s"));

        for (ItemHeroi itemComprado : itensComprados) {
            this.loja.remove(itemComprado);
        }

    }
}
