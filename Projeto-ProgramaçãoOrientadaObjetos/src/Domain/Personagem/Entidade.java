package Domain.Personagem;

public abstract class Entidade {
    private String nome;
    private int vidaMax;
    private int vidaAtual;
    private int força;

    public Entidade(String nome, int vidaMax, int força) {
        this.nome = nome;
        this.vidaMax = vidaMax;
        this.vidaAtual = this.vidaMax;
        this.força = força;
    }

    public void exibirDetalhea() {
        System.out.println("Detalhes do boneco: \nnome:" + this.nome + " | vida máxima: " + this.vidaMax + " | vida atual: " + this.vidaAtual + " | força: " + this.força);
    }

    public String getNome() {
        return nome;
    }

    public int getVidaMax() {
        return vidaMax;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getForça() {
        return força;
    }

    public void setVidaMax(int vidaMax) {
        this.vidaMax = vidaMax;
    }

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = vidaAtual;
    }

    public void setForça(int força) {
        this.força = força;
    }
}
