package Controllers;

import Domain.Itens.Consumivel;
import Domain.Itens.ConsumivelCombate;
import Domain.Itens.ItemHeroi;
import Domain.Itens.Poção;
import Domain.Personagem.*;
import Domain.Vendedor;
import Repository.ItensRepository;
import View.JogoView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class JogoController {

    public JogoController() {
    }


    public void imprimirLoja() throws FileNotFoundException {
        Vendedor vendedor = new Vendedor();
        for (ItemHeroi itemAtual : vendedor.getLoja()) {
            itemAtual.mostrarDetalhes();
        }
    }

    /**
     * Método para criara personagen
     * @param nome
     * @param heroi
     * @param dificuldade
     * @param vida
     * @param forca
     * @return o heroi criado
     * @throws FileNotFoundException se não encontrar o ficheiro
     */
    public static Heroi criarPersonagem(String nome, String heroi, String dificuldade, int vida, int forca) throws FileNotFoundException {
        int ouro;
        heroi = heroi.toLowerCase();
        if (dificuldade.equals("facil") || dificuldade.equals("Facil")) {
            ouro = 70;
        } else {
            ouro = 30;
        }
        switch (heroi) {
            case "cavaleiro", "1":
                return new Cavaleiro(nome, vida, forca, 1, ouro);

            case "feiticeiro", "2":
                return new Feiticeiro(nome, vida, forca, 1, ouro);

            case "arqueiro", "3":
                return new Arqueiro(nome, vida, forca, 1, ouro);
        }
        return null;
    }

    /**
     * Método para usar uma poção
     * @param heroi para usar a poção que está no inventário do heroi
     */
    public void usarPoção(Heroi heroi) {
        Scanner input = new Scanner(System.in);
        int cont = 0;
        ArrayList<Integer> contador = new ArrayList<>();
        for (Consumivel consumivelAtual : heroi.getInventário()) {
            if (consumivelAtual instanceof Poção) {
                System.out.println(cont + ".");
                contador.add(cont);
                consumivelAtual.mostrarDetalhes();
            }
            cont++;
        }
        int poção;
        do {
            System.out.println("\nQue Poção quer usar? Digite -1 para não usar nenhuma poção");
            poção = input.nextInt();


            if (poção == -1) {
                System.out.println("não usou poção");
                return;
            }
        } while (!contador.contains(poção));

        Poção consumivelPoção = (Poção) heroi.getInventário().get(poção);

        if (heroi.getVidaAtual() + consumivelPoção.getCurarVida() <= heroi.getVidaMax()) {
            heroi.setVidaAtual(heroi.getVidaAtual() + consumivelPoção.getCurarVida());
            System.out.println("Aumentou a vida em " + consumivelPoção.getCurarVida());
            heroi.setForça(heroi.getForça() + consumivelPoção.getAumentarForça());
            System.out.println("Aumentou a força em " + consumivelPoção.getAumentarForça());
            heroi.getInventário().remove(consumivelPoção);

        } else {
            System.out.println("A sua vida atual é" + heroi.getVidaAtual() + " e a sua vida máxima é " + heroi.getVidaMax());
            System.out.println("Se decidir confirmar a regeneração " + consumivelPoção.getCurarVida() + "de vida, a vida máxima continuará a ser " + heroi.getVidaMax() + ". Digite s/n");
            String opcao = input.next();
            opcao = opcao.toLowerCase();
            if (opcao.equals("s")) {
                heroi.setVidaAtual(heroi.getVidaMax());
                heroi.setForça(heroi.getForça() + consumivelPoção.getAumentarForça());
                System.out.println("Aumentou a força em " + consumivelPoção.getAumentarForça());
                System.out.println("Usou a poção " + consumivelPoção.getNome());
                heroi.getInventário().remove(consumivelPoção);
            } else {
                System.out.println("Não usou a poção");
            }
        }


    }

    /**
     * Método que permite atacar
     * @param heroi em jogo
     * @param npc que está a combater
     * @return quem ganhar
     */
    public Entidade atacar(Heroi heroi, NPC npc) {
        Scanner input = new Scanner(System.in);
        boolean flag = false;
        do {
            int ataque;
            System.out.println("Escolha o Ataque do Herói: ");
            System.out.println("1. Ataque Normal");
            System.out.println("2. Ataque Especial");
            System.out.println("3. Ataque Consumível");
            ataque = input.nextInt();
            switch (ataque) {
                case 1:
                    if (heroi instanceof Arqueiro) {
                        System.out.println("O heroi " + heroi.getNome() + " atacou: " + heroi.getForça() + heroi.getArmaPrincipal().getAtaque() + " de dano");
                        npc.setVidaAtual(npc.getVidaAtual() - (heroi.getForça() + heroi.getArmaPrincipal().getAtaque()));
                        System.out.println("O bandido " + npc.getNome() + " atacou " + npc.getForça() + (npc.getForça() * 0.10) + " de dano");
                        heroi.setVidaAtual((int) (heroi.getVidaAtual() - (npc.getForça() + (npc.getForça() * 0.10))));

                    } else if (heroi instanceof Cavaleiro) {
                        System.out.println("O bandido " + npc.getNome() + " atacou: " + (npc.getForça() * 0.80) + " de dano");
                        heroi.setVidaAtual((int) (heroi.getVidaAtual() - (npc.getForça() * 0.80)));
                        System.out.println("O heroi " + heroi.getNome() + " atacou: " + heroi.getForça() + heroi.getArmaPrincipal().getAtaque() + " de dano");
                        npc.setVidaAtual(npc.getVidaAtual() - (heroi.getForça() + heroi.getArmaPrincipal().getAtaque()));
                    } else if (heroi instanceof Feiticeiro) {
                        System.out.println("O heroi " + heroi.getNome() + " atacou " + heroi.getForça() + heroi.getArmaPrincipal().getAtaque() + " de dano");
                        npc.setVidaAtual(npc.getVidaAtual() - (heroi.getForça() + heroi.getArmaPrincipal().getAtaque()));
                        System.out.println("O bandido " + npc.getNome() + " atacou: " + npc.getForça() + " de dano");
                        heroi.setVidaAtual(npc.getForça());
                    }
                    break;

                case 2:
                    if (!flag) {
                        if (heroi instanceof Arqueiro) {
                            System.out.println("O heroi " + heroi.getNome() + " atacou " + heroi.getForça() + heroi.getArmaPrincipal().getAtaque() + " de dano");
                            npc.setVidaAtual(npc.getVidaAtual() - (npc.getForça() + heroi.getArmaPrincipal().getAtaqueEspecial()));
                            System.out.println("O bandido " + npc.getNome() + " atacou " + npc.getForça() + (npc.getForça() * 0.10) + " de dano");
                            heroi.setVidaAtual((int) (heroi.getVidaAtual() - (npc.getForça() + (npc.getForça() * 0.10))));
                        } else if (heroi instanceof Cavaleiro) {
                            System.out.println("O bandido " + npc.getNome() + " atacou: " + (npc.getForça() * 0.80) + " de dano");
                            heroi.setVidaAtual((int) (heroi.getVidaAtual() - (npc.getForça() * 0.80)));
                            System.out.println("O heroi " + heroi.getNome() + " atacou " + heroi.getForça() + heroi.getArmaPrincipal().getAtaque() + " de dano");
                            npc.setVidaAtual(npc.getVidaAtual() - (heroi.getForça() + heroi.getArmaPrincipal().getAtaqueEspecial()));
                        } else if (heroi instanceof Feiticeiro) {
                            System.out.println("O heroi " + heroi.getNome() + " atacou " + heroi.getForça() + heroi.getArmaPrincipal().getAtaque() + " de dano");
                            npc.setVidaAtual(npc.getVidaAtual() - (heroi.getForça() + heroi.getArmaPrincipal().getAtaqueEspecial()));
                            System.out.println("O bandido " + npc.getNome() + " atacou: " + npc.getForça() + " de dano");
                            heroi.setVidaAtual(heroi.getVidaAtual() - npc.getForça());
                        }
                        flag = true;
                    }
                    break;

                case 3:
                    int cont = 0;
                    ArrayList<Integer> contador = new ArrayList<>();
                    for (Consumivel consumivelAtual : heroi.getInventário()) {

                        if (consumivelAtual instanceof ConsumivelCombate) {
                            System.out.println(cont + ".");
                            contador.add(cont);
                            consumivelAtual.mostrarDetalhes();
                        }
                        cont++;
                    }

                    int consumivel;
                    do {
                        System.out.println("\nQue consumível de combate quer usar? Digite -1 para sair");
                        consumivel = input.nextInt();


                        if (consumivel != -1) {
                            ConsumivelCombate consumivelCombate = (ConsumivelCombate) heroi.getInventário().get(consumivel);
                            npc.setVidaAtual(npc.getVidaAtual() - consumivelCombate.getAtaqueInstantaneo());
                            System.out.println("usou " + consumivelCombate.getNome());
                            heroi.getInventário().remove(consumivelCombate);
                        }
                    } while (!contador.contains(consumivel) && consumivel != -1);
                    break;
            }
        } while (heroi.getVidaAtual() > 0 && npc.getVidaAtual() > 0);
        if (heroi.getVidaAtual() <= 0) {
            return npc;
        } else
            return heroi;
    }


    /**
     * Método para melhorar o heroi em caso de vencer um ataque
     * @param heroi
     * @param inimigo
     */
    public void heroiVenceu(Heroi heroi, NPC inimigo) {
        heroi.setVidaMax(heroi.getVidaMax() + 10);
        heroi.setForça(heroi.getForça() + 1);
        heroi.setNivel(heroi.getNivel() + 1);
        heroi.setOuro(heroi.getOuro() + inimigo.getOuro());
        System.out.println("O heroi ganhou " + inimigo.getOuro() + " ouro");
    }

    public void heroiPerdeu(int opção, Heroi personagemInicial) throws FileNotFoundException {
        switch (opção) {
            case 1 -> JogoView.menuJogo(personagemInicial);

            case 2 -> JogoView.menuJogo(JogoView.menuPersonagem());

            case 3 -> JogoView.jogo();
        }
    }

    /**
     * método de adicionar uma poção
     * @param heroi
     * @throws FileNotFoundException
     */
    public void tesouroSurpresa(Heroi heroi) throws FileNotFoundException {
        Random random = new Random();
        int indexAleatorio = random.nextInt(1, 2);
        System.out.println("\nSurpresa! Encontrou um consumível ");
        if (indexAleatorio == 1) {
            Poção poção = new Poção("Poção Surpresa Regenerativa", 15, 50, 25);
            heroi.addIventário(poção);
            System.out.println("Poção Surpresa Regenerativa");
        } else if (indexAleatorio == 2) {
            Poção poção = new Poção("Poção Surpresa Desagradavel", 15, -5, 0);
            heroi.addIventário(poção);
            System.out.println("Poção Surpresa Desagradavel");
        }
    }

    /**
     * Método para aumentar a vida do heroi
     * @param heroi
     * @param vidaregenerada
     */
    public void aumentarVida(Heroi heroi, int vidaregenerada) {
        Scanner input = new Scanner(System.in);
        if (heroi.getVidaAtual() + vidaregenerada <= heroi.getVidaMax()) {
            heroi.setVidaAtual(heroi.getVidaAtual() + vidaregenerada);
            System.out.println("Aumentou a vida em " + vidaregenerada);
        } else {
            System.out.println("A sua vida atual é" + heroi.getVidaAtual() + " e a sua vida máxima é " + heroi.getVidaMax());
            System.out.println("Se decidir confirmar a regeneração " + vidaregenerada + "de vida, a vida máxima continuará a ser " + heroi.getVidaMax() + ". Digite s/n");
            String opcao = input.next();
            opcao = opcao.toLowerCase();
            if (opcao.equals("s")) {
                heroi.setVidaAtual(heroi.getVidaMax());
            }
        }
    }


}
