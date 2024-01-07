package View;

import Controllers.JogoController;
import Domain.Itens.Poção;
import Domain.Personagem.Entidade;
import Domain.Personagem.Heroi;
import Domain.Personagem.NPC;
import Domain.Vendedor;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class JogoView {

    /**
     * Menu inicial do jogo
     * @throws FileNotFoundException
     */
    public static void jogo() throws FileNotFoundException {
        int escolha;
        boolean personagemCriada = false;
        System.out.println("***Bem vindo ao Jogo***");

        Heroi heroi = JogoController.criarPersonagem("String nome", "cavaleiro", "String dificuldade", 0, 0);

        do {
            System.out.println("\nEscolha o que pretender fazer: ");
            System.out.println("1. Mudar/Criar personagem");
            System.out.println("2. Começar um mundo novo");
            System.out.println("3. Sair");
            Scanner input = new Scanner(System.in);
            escolha = input.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Vai Criar a sua personagem: \n");
                    heroi = menuPersonagem();
                    personagemCriada = true;
                    break;

                case 2:
                    if (personagemCriada) {
                        menuJogo(heroi);
                        break;
                    } else {
                        System.out.println("tem de criar uma personagem primeiro");
                        heroi = menuPersonagem();
                        personagemCriada = true;
                        menuJogo(heroi);
                        break;
                    }


                case 3:
                    System.out.println("Saiu do jogo");
                    break;

                default:
                    System.out.println("Escolha uma opção válida.");
                    break;
            }
        } while (escolha != 3);
    }


    /**
     * Menu para criar uma personagem
     * @return um heroi
     * @throws FileNotFoundException
     */
    public static Heroi menuPersonagem() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        JogoController jogoController = new JogoController();

        System.out.println("Que tipo de heroi deseja? Escreva um dos seguintes tipos\n");
        System.out.println("1. Cavaleiro");
        System.out.println("2. Feiticeiro");
        System.out.println("3. Arqueiro");
        String heroi = input.next();
        switch (heroi) {
            case "1" -> System.out.println("Escolheu o heroi cavaleiro\n");
            case "2" -> System.out.println("Escolheu o heroi Feiticeiro\n");
            case "3" -> System.out.println("Escolheu o heroi Arqueiro\n");
        }

        System.out.println("\nDiga o nome que quer para a personagem");
        String nome = input.next();


        System.out.println("\nDiga a dificuldade. Escolha a opção");
        String dificuldade;
        System.out.println("Facil");
        System.out.println("Dificil");
        dificuldade = input.next();
        while (!dificuldade.equals("facil") && !dificuldade.equals("Facil") && !dificuldade.equals("dificil") && !dificuldade.equals("Dificil")) {
            System.out.println("Diga novamente a diculdade");
            dificuldade = input.next();
        }
        System.out.println("\nEscolheu a dificuldade " + dificuldade);


        int pontos = 0;
        System.out.println("\nPode agora distribuir pontos pela personagem criada: ");
        if (dificuldade.equals("facil") || dificuldade.equals("Facil")) {
            pontos = 350;
        } else {
            pontos = 220;
        }
        System.out.println("\nComo escolheu a dificuldade " + dificuldade + ", tem direito a " + pontos + " pontos");

        System.out.println("\nCada ponto de vida consome um ponto e cada ponto de força consome 5 pontos.");
        int vida;
        int força;

        do {
            int pontosCriacao = pontos;
            System.out.println("Quantos pontos quer para a vida? Pontos disponívels: " + pontosCriacao);
            vida = input.nextInt();
            pontosCriacao -= vida;
            System.out.println("Quantos pontos quer para a força? Pontos diponiveis: " + pontosCriacao);
            força = input.nextInt();
            pontosCriacao -= força;
            if (vida + força > pontos) {
                System.out.println("Ultrapassou os pontos disponíveis para distruibuir. Escolha de novo");
            }
        } while (vida + força > pontos);
        força /= 5;
        System.out.println("\nA sua personagem " + nome + " tem de vida: " + vida + " e de força: " + força);

        System.out.println("\nA sua personagem ganhou ouro!");
        int ouro;
        if (dificuldade.equals("facil") || dificuldade.equals("Facil")) {
            ouro = 50;
        } else {
            ouro = 30;
        }
        System.out.println("Como escolheu a dificuldade " + dificuldade + ", tem direito a " + ouro + " de ouro");

        return JogoController.criarPersonagem(nome, heroi, dificuldade, vida, força);
    }

    /**
     * Menu do proprio Jogo
     * @param personagem heroi que foi criado no menu de personagem
     * @throws FileNotFoundException se não encontrar o ficheiro com os itens
     */
    public static void menuJogo(Heroi personagem) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        JogoController jogoController = new JogoController();

        Heroi personagemInical = personagem;
        NPC inimigoDragão = new NPC("Dragão", 110, 30, 500);
        NPC inimigo1Bando = new NPC("Bandido1", 90, 5, 50);
        NPC inimigo2Bando = new NPC("Bandido2", 95, 10, 60);
        NPC inimigo3Bando = new NPC("Bandido3", 100, 25, 70);
        NPC inimigoPrincipe = new NPC("Principe", 80, 20, 200);
        NPC inimigoHomemQueque = new NPC("Homem Queque", 60, 15, 35);

        NPC inimigoFadaMadrinha = new NPC("Fada Madrinha", 100, 25, 150);

        Vendedor vendedor = new Vendedor();

        System.out.println("\n*** Bem-Vindo ao jogo ***\n");
        System.out.println("A história começa na terra de Bue Bue longe...");
        System.out.println("Era uma vez um corajoso guerreiro chamado " + personagem.getNome() + " ...");
        System.out.println("\nEste guerreiro tinha como missão salvar uma princesa na torre mais alta do reino a pedido do Rei de Bue Bue longe");
        System.out.println("Antes de partir na aventura, o guerreiro juntou todas as suas poupanças de ouro: (" + personagem.getOuro() + ")");
        System.out.print(" e foi preparar-se para os possíveis desafios que pudessem aparecer na viagem.");
        System.out.println("Dirigiu-se a um vendedor que pudesse vender-lhe ferramentas para a missão...");

        System.out.println("\nO vendedor perguntou ao " + personagem.getNome() + "o que queria comprar para a viagem. Tenho estes itens-disse o vendedor");
        vendedor.loja(personagem);
        System.out.println("\nNo momento depois de sair da loja do vendedor o " + personagem.getNome() + " foi abordado pelo " + inimigoHomemQueque.getNome());

        if (jogoController.atacar(personagem, inimigoHomemQueque) instanceof Heroi) {
            System.out.println("O heroi " + personagem.getNome() + " derrotou o inimigo " + inimigoHomemQueque.getNome());
            jogoController.heroiVenceu(personagem, inimigoHomemQueque);
        } else {
            System.out.println("O heroi " + personagem.getNome() + " foi derrotado");
            System.out.println("\nPerdeste. Escolhe o que queres fazer");
            System.out.println("1. recomeçar com a mesma personagem");
            System.out.println("2. criar uma personagem nova");
            System.out.println("3. sair do jogo");
            int opção = input.nextInt();
            jogoController.heroiPerdeu(opção, personagemInical);
        }

        System.out.println("\n***");
        System.out.println("O " + personagem.getNome() + " pode usar uma poção:");
        jogoController.usarPoção(personagem);

        System.out.println("\nDepois de passar o vendedor e derrotar o Homem Queque, uns kilometros à frente, o " + personagem.getNome() + " dá de caras com uma biforcação entre:");
        System.out.println("1. VALE DOS BANDIDOS & ");
        System.out.println("2. MONTANHA ESPIRITUAL");
        System.out.println("\nQual caminho deseja? \ndigite 1 para vale dos mafiosos ou 2 para montanha espiritual");
        int escolha = input.nextInt();
        //Escolhe o vale
        if (escolha == 1) {
            System.out.println("\nEncontrou um grupo de bandidos no Vale");
            System.out.println("\nVou-vos derrotar- diz o " + personagem.getNome());
            System.out.println("\nDá-se a primeira batalha do " + personagem.getNome());
            if (jogoController.atacar(personagem, inimigo1Bando) instanceof Heroi) {
                System.out.println("O heroi " + personagem.getNome() + " derrotou o inimigo " + inimigo1Bando.getNome());
                jogoController.heroiVenceu(personagem, inimigo1Bando);
            } else {
                System.out.println("O heroi " + personagem.getNome() + " foi derrotado");
                System.out.println("\nPerdeste. Escolhe o que queres fazer");
                System.out.println("1. recomeçar com a mesma personagem");
                System.out.println("2. criar uma personagem nova");
                System.out.println("3. sair do jogo");
                int opção = input.nextInt();
                jogoController.heroiPerdeu(opção, personagemInical);
            }
            if (jogoController.atacar(personagem, inimigo2Bando) instanceof Heroi) {
                System.out.println("O heroi " + personagem.getNome() + " derrotou o inimigo " + inimigo2Bando.getNome());
                jogoController.heroiVenceu(personagem, inimigo2Bando);
            } else {
                System.out.println("O heroi " + personagem.getNome() + " foi derrotado");
                System.out.println("Perdeste. Escolhe o que queres fazer");
                System.out.println("1. recomeçar com a mesma personagem");
                System.out.println("2. criar uma personagem nova");
                System.out.println("3. menu inicial");
                int opção = input.nextInt();
                jogoController.heroiPerdeu(opção, personagemInical);
            }
            if (jogoController.atacar(personagem, inimigo3Bando) instanceof Heroi) {
                System.out.println("O heroi " + personagem.getNome() + "derrotou o inimigo " + inimigo3Bando.getNome());
                jogoController.heroiVenceu(personagem, inimigo3Bando);
            } else {
                System.out.println("O heroi " + personagem.getNome() + " foi derrotado");
                System.out.println("Perdeste. Escolhe o que queres fazer");
                System.out.println("1. recomeçar com a mesma personagem");
                System.out.println("2. criar uma personagem nova");
                System.out.println("3. sair do jogo");
                int opção = input.nextInt();
                jogoController.heroiPerdeu(opção, personagemInical);
            }
            System.out.println("\n***");
            System.out.println("O " + personagem.getNome() + " pode usar uma poção:");
            jogoController.usarPoção(personagem);

            System.out.println("Ao derrotar o grupo dos 3 bandidos, a personagem teve de escolher seguir em frente entre:");
            System.out.println("1. taberna do bosque");
            System.out.println("2. Estrada do Rei");
            System.out.println("\nQual caminho deseja? \ndigite 1 taberna do bosque ou 2 para Estrada do Rei");
            int caminho = input.nextInt();
            //Se escolheu o vale, escolhe a taberna do bosque
            if (caminho == 1) {
                System.out.println("O " + personagem.getNome() + "Escolheu ir pela taberna do bosque");
                System.out.println("Na taberna o " + personagem.getNome() + " pediu uma poção como refrigerante");
                Poção refrigerante = new Poção("bandida do Bosque", 2, 10, 15);
                personagem.addIventário(refrigerante);
                System.out.println("\nNa taberna havia um vendedor:");
                vendedor.loja(personagem);
                System.out.println("\nDentro da taberna foi desafiado pelo principe de Bue Bue longe, filho da fadra madrinha\n");
                System.out.println("que também queria salvar a princesa da torre");
                if (jogoController.atacar(personagem, inimigoPrincipe) instanceof Heroi) {
                    System.out.println("O heroi " + personagem.getNome() + " derrotou o inimigo " + inimigoPrincipe.getNome());
                    jogoController.heroiVenceu(personagem, inimigoPrincipe);
                } else {
                    System.out.println("O heroi " + personagem.getNome() + " foi derrotado");
                    System.out.println("Perdeste. Escolhe o que queres fazer");
                    System.out.println("1. recomeçar com a mesma personagem");
                    System.out.println("2. criar uma personagem nova");
                    System.out.println("3. menu inicial");
                    int opção = input.nextInt();
                    jogoController.heroiPerdeu(opção, personagemInical);
                }
                System.out.println("\n***");
                System.out.println("O " + personagem.getNome() + " pode usar uma poção:");
                jogoController.usarPoção(personagem);
            }
        } // Escolhe montanha espiritual
        else if (escolha == 2) {
            System.out.println("O " + personagem.getNome() + " foi pelo caminho da montanha espiritual");
            System.out.println("Nesta montanha o " + personagem.getNome() + " encontrou um tesouro surpresa. Teve de abrir um báu para saber que tesouro guardava");
            jogoController.tesouroSurpresa(personagem);
            System.out.println("\n***");
            System.out.println("O " + personagem.getNome() + " pode usar uma poção:");
            jogoController.usarPoção(personagem);

            System.out.println("De seguida, teve de escolher entre:");
            System.out.println("1. Floresta Negra ");
            System.out.println("2. Hotel do Reino");
            escolha = input.nextInt();
            //Se escolheu montanha espiritual, escolhe a Floresta Negra
            if (escolha == 1) {
                System.out.println("O " + personagem.getNome() + " foi pelo caminho da Floresta Negra");
                System.out.println("Na floresta Negra deparou-se com uma armadilha!!!");
                System.out.println("-25 de Vida");
                personagem.setVidaAtual(personagem.getVidaAtual() - 25);
                System.out.println("Teve também de enfrentar um enimigo poderoso...");
                if (jogoController.atacar(personagem, inimigoFadaMadrinha) instanceof Heroi) {
                    System.out.println("O heroi " + personagem.getNome() + " derrotou o inimigo " + inimigoFadaMadrinha.getNome());
                    jogoController.heroiVenceu(personagem, inimigoFadaMadrinha);
                } else {
                    System.out.println("O heroi " + personagem.getNome() + " foi derrotado");
                    System.out.println("\nPerdeste. Escolhe o que queres fazer");
                    System.out.println("1. recomeçar com a mesma personagem");
                    System.out.println("2. criar uma personagem nova");
                    System.out.println("3. sair do jogo");
                    int opção = input.nextInt();
                    jogoController.heroiPerdeu(opção, personagemInical);
                }
                System.out.println("\n***");
                System.out.println("O " + personagem.getNome() + " pode usar uma poção:");
                jogoController.usarPoção(personagem);

            }  //Se escolheu montanha espiritual, escolhe  Hotel do Reino
            else if (escolha == 2) {
                System.out.println("O " + personagem.getNome() + "foi pelo caminho do Hotel do Reino");
                System.out.println("Neste Hotel o " + personagem.getNome() + "descansou e por isso teve a sua vida e força regeneradas\n");
                System.out.println("Vida: + 25 & Força: + 10");
                jogoController.aumentarVida(personagem, 25);
                personagem.setForça(personagem.getForça() + 10);
                System.out.println("Neste hotel havia um vendedor:");
                vendedor.loja(personagem);

                System.out.println("\n***");
                System.out.println("O " + personagem.getNome() + " pode usar uma poção:");
                jogoController.usarPoção(personagem);
            }
        }

        //Caminho comum a todas as escolhas
        System.out.println("\nO caminho a seguir deu à estrada do rei");
        System.out.println("Já na estrada do rei, o " + personagem.getNome() + " sentiu que estava quase a recuperar a princesa que estava presa na torre mais alta do reino de Bue Bue Longe");
        System.out.println("Por fim, ao chegar à torre o " + personagem.getNome() + "lutou contra o temível dragão que guardava a princesa!");

        if (jogoController.atacar(personagem, inimigoDragão) instanceof Heroi) {
            System.out.println("O heroi " + personagem.getNome() + " derrotou o inimigo " + inimigoDragão.getNome());
            jogoController.heroiVenceu(personagem, inimigoDragão);
        } else {
            System.out.println("O heroi " + personagem.getNome() + " foi derrotado");
            System.out.println("\nPerdeste. Escolhe o que queres fazer");
            System.out.println("1. recomeçar com a mesma personagem");
            System.out.println("2. criar uma personagem nova");
            System.out.println("3. sair do jogo");
            int opção = input.nextInt();
            jogoController.heroiPerdeu(opção, personagemInical);
        }

        System.out.println("\nPor fim a princesa foi salva. O " + personagem.getNome() + " e a princesa casaram e foram felizes para todo o sempre!");
        System.out.println("\n***FIM***");

    }

}
