/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegopoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Importamos nuestras clases personalizadas
import personajes.*;

/**
 *
 * @author ab189
 */
public class JuegoPOO extends JFrame {
    // Componentes de la interfaz
    private JComboBox<String> comboJugador1, comboJugador2;
    private JButton btnIniciar, btnAtacar;
    private JTextArea areaBatalla;

    // Objetos de los personajes
    private Personaje jugador1, jugador2;

    // Control del turno
    private boolean turnoJugador1 = true;

    // Constructor del juego
    public JuegoPOO() {
        // Configuración básica del JFrame
        setTitle("Arena de Clases - Batalla POO");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Menús desplegables para elegir personajes
        comboJugador1 = new JComboBox<>(new String[]{"Guerrero", "Mago", "Arquero"});
        comboJugador2 = new JComboBox<>(new String[]{"Guerrero", "Mago", "Arquero"});

        // Botones para iniciar el juego y atacar
        btnIniciar = new JButton("Iniciar Batalla");
        btnAtacar = new JButton("Atacar");
        btnAtacar.setEnabled(false); // Desactivado al inicio

        // Área de texto para mostrar el combate
        areaBatalla = new JTextArea(10, 40);
        areaBatalla.setEditable(false); // Solo lectura

        // Agregamos todos los componentes a la ventana
        add(new JLabel("Jugador 1:"));
        add(comboJugador1);
        add(new JLabel("Jugador 2:"));
        add(comboJugador2);
        add(btnIniciar);
        add(btnAtacar);
        add(new JScrollPane(areaBatalla)); // Scroll para el área de texto

        // Acción al hacer clic en "Iniciar Batalla"
        btnIniciar.addActionListener(e -> iniciarBatalla());

        // Acción al hacer clic en "Atacar"
        btnAtacar.addActionListener(e -> realizarAtaque());

        setVisible(true); // Mostrar la ventana
    }

    // Inicializa los personajes y habilita el botón de ataque
    private void iniciarBatalla() {
        jugador1 = crearPersonaje(comboJugador1.getSelectedItem().toString(), "Jugador 1");
        jugador2 = crearPersonaje(comboJugador2.getSelectedItem().toString(), "Jugador 2");
        System.out.println("combox: " + comboJugador2.toString());
        System.out.println("comboy: " + comboJugador2.getSelectedItem());
        System.out.println("Jugador 2: " + jugador2);

        areaBatalla.setText("¡Batalla iniciada!\nTurno del Jugador 1\n");
        btnAtacar.setEnabled(true);
    }

    // Maneja el turno de ataque entre los jugadores
    private void realizarAtaque() {
        if (turnoJugador1) {
            jugador1.atacar(jugador2); // Jugador 1 ataca a 2
            areaBatalla.append(jugador1.getNombre() +" atacó a "+jugador2.getNombre()+". Vida restante: " + jugador2.getVida() + "\n");
        } else {
            jugador2.atacar(jugador1); // Jugador 2 ataca a 1
            areaBatalla.append(jugador2.getNombre()+" atacó a "+jugador1.getNombre()+". Vida restante: " + jugador1.getVida() + "\n");
        }

        // Si alguno pierde toda la vida, termina el juego
        if (!jugador1.estaVivo() || !jugador2.estaVivo()) {
            String ganador = jugador1.estaVivo() ? jugador1.getNombre() : jugador2.getNombre()  ;
            areaBatalla.append("¡Fin del juego! Ganador: " + ganador + "\n");
            btnAtacar.setEnabled(false); // Desactivar ataques
        }

        turnoJugador1 = !turnoJugador1; // Cambiar de turno
    }

    // Crea un personaje según el nombre del tipo seleccionado
    private Personaje crearPersonaje(String tipo, String nombre) {
        switch (tipo) {
            case "Guerrero": return new Guerrero("Guerrero");
            case "Mago": return new Mago("Mago");
            case "Arquero": return new Arquero("Arquero");
            
            default: return null;
        }
    }

    // Método main que inicia todo
    public static void main(String[] args) {
        new JuegoPOO(); // Crear la ventana
    }
    
}
