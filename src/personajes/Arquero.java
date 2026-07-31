package personajes;

/**
 * @author ACHOLOTIO
 * @version 1.0.0
 * @since 1.0.0
 * created on 25/07/26
 */
public class Arquero extends Personaje{

    public Arquero(String nombre) {
        super(nombre, 50, 15);
    }

    @Override
    public void atacar(Personaje enemigo) {
        System.out.println(nombre + " lanza una bola de fuego.");
        System.out.println(enemigo);
        enemigo.recibirDanio(ataque);
    }
}
