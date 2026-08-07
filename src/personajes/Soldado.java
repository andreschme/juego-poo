package personajes;

public class Soldado extends Personaje {

    public Soldado(String nombre) {
        super(nombre, 70, 30);
    }

    @Override
    public void atacar(Personaje enemigo) {
        System.out.println(nombre + " dispara con una escopeta.");
        System.out.println(enemigo);
        enemigo.recibirDanio(ataque);
    }
}
