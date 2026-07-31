/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personajes;

/**
 *
 * @author ab189
 */
// Subclase Guerrero que hereda de Personaje (Herencia)
public class Guerrero extends Personaje {

    // Constructor específico del Guerrero con valores predeterminados
    public Guerrero(String nombre) {
        super(nombre, 100, 20); // Vida: 100, Ataque: 20
    }

    // Implementación del método atacar (Polimorfismo)
    @Override
    public void atacar(Personaje enemigo) {
        System.out.println(nombre + " ataca con su espada.");
        System.out.println(enemigo);
        enemigo.recibirDanio(ataque); // El enemigo pierde vida
    }
}
