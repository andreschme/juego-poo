package personajes;

/**
 * @author ACHOLOTIO
 * @version 1.0.0
 * @since 1.0.0
 * created on 3/08/26
 */
public class Hechicero extends Personaje {

    public Hechicero(String nombre) {
        super(nombre, 40, 20);
    }

    @Override
    public void atacar(Personaje enemigo) {
        System.out.println(nombre + " lanza un hechizo.");
        System.out.println(enemigo);
        enemigo.recibirDanio(ataque);
    }

}
