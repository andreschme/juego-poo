/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personajes;

/**
 *
 * @author ab189
 */
// Clase abstracta que representa a un personaje genérico
public abstract class Personaje {
    protected String nombre;
    protected int vida;
    protected int ataque;

    // Constructor que inicializa los atributos del personaje
    public Personaje(String nombre, int vida, int ataque) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
    }

    // Método abstracto que se implementará de forma distinta en cada subclase (Polimorfismo)
    public abstract void atacar(Personaje enemigo);

    // Método para recibir daño. Se resta a la vida del personaje.
    public void recibirDanio(int danio) {
        vida -= danio;
    }

    // Método getter para obtener el nombre del personaje (Encapsulamiento)
    public String getNombre() {
        return nombre;
    }

    // Método getter para obtener la vida del personaje
    public int getVida() {
        return vida;
    }

    // Método que verifica si el personaje sigue vivo
    public boolean estaVivo() {
        return vida > 0;
    }
}
