package Domain.Itens;

public class ArmaPrincipal extends ItemHeroi {
    private int ataque;
    private int ataqueEspecial;

    public ArmaPrincipal(String nome, int preçoOuro, int ataque, int ataqueEspecial) {
        super(nome, preçoOuro);
        this.ataque = ataque;
        this.ataqueEspecial = ataqueEspecial;
    }
    @Override
    public void mostrarDetalhes(){
        super.mostrarDetalhes();
        System.out.println("Dano de ataque: " + this.ataque + " | dano de ataque especial: " + this.ataqueEspecial);
    }

    public int getAtaque() {
        return ataque;
    }

    public int getAtaqueEspecial() {
        return ataqueEspecial;
    }
}
