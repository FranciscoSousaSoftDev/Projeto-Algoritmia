package Domain.Personagem;

import Domain.Itens.ArmaPrincipal;
import Domain.Itens.Consumivel;

import java.util.ArrayList;

public abstract class Heroi extends Entidade {
    private int nivel;
    private int ouro;
    private ArmaPrincipal armaPrincipal;
    private ArrayList<Consumivel> inventário;

    public Heroi(String nome, int vidaMax, int força, int nivel, int ouro) {
        super(nome, vidaMax, força);
        this.nivel = nivel;
        this.ouro = ouro;
        this.armaPrincipal = new ArmaPrincipal("Punhos", 0, 0, 1);
        this.inventário = new ArrayList<Consumivel>();
    }

    public int getNivel() {
        return nivel;
    }

    public int getOuro() {
        return ouro;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }


    public void setOuro(int ouro) {
        this.ouro = ouro;
    }

    public ArmaPrincipal getArmaPrincipal() {
        return armaPrincipal;
    }

    public ArrayList<Consumivel> getInventário() {
        return inventário;
    }

    public void setArmaPrincipal(ArmaPrincipal armaPrincipal) {
        this.armaPrincipal = armaPrincipal;
    }

    public void setInventário(ArrayList<Consumivel> inventário) {
        this.inventário = inventário;
    }

    public void addIventário(Consumivel consumivel) {
        this.inventário.add(consumivel);
    }

    public abstract void usarPoção();

}
