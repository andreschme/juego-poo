/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package juegopoo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

// Importamos nuestras clases personalizadas
import personajes.*;

/**
 *
 * @author ab189
 */
public class JuegoPOO extends JFrame {
    // Componentes de la interfaz
    private JComboBox<String> comboJugador1, comboJugador2;
    private JLabel lblImgJugador1, lblImgJugador2;
    private JButton btnIniciar, btnAtacar;
    private JTextArea areaBatalla;

    // Objetos de los personajes
    private Personaje jugador1, jugador2;

    // Control del turno
    private boolean turnoJugador1 = true;
    Random random = new Random();

    // Constructor del juego
    public JuegoPOO() {
        // Configuración básica del JFrame
        setTitle("Arena de Clases - Batalla POO");
        setSize(550, 450); // Aumentado un poco el tamaño para acomodar las imágenes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Menús desplegables para elegir personajes
        comboJugador1 = new JComboBox<>(new String[]{"Guerrero", "Mago", "Arquero", "Hechicero", "Soldado"});
        comboJugador2 = new JComboBox<>(new String[]{"Guerrero", "Mago", "Arquero", "Hechicero", "Soldado"});

        // Botones para iniciar el juego y atacar
        btnIniciar = new JButton("Iniciar Batalla");
        btnAtacar = new JButton("Atacar");
        btnAtacar.setEnabled(false); // Desactivado al inicio

        // Área de texto para mostrar el combate
        areaBatalla = new JTextArea(10, 40);
        areaBatalla.setEditable(false); // Solo lectura

        // Labels para las imágenes
        lblImgJugador1 = new JLabel();
        lblImgJugador1.setHorizontalAlignment(SwingConstants.CENTER);
        lblImgJugador1.setPreferredSize(new Dimension(120, 120));
        
        lblImgJugador2 = new JLabel();
        lblImgJugador2.setHorizontalAlignment(SwingConstants.CENTER);
        lblImgJugador2.setPreferredSize(new Dimension(120, 120));

        // Paneles para agrupar selección e imagen
        JPanel panelJ1 = new JPanel(new BorderLayout());
        JPanel panelSuperiorJ1 = new JPanel();
        panelSuperiorJ1.add(new JLabel("Jugador 1:"));
        panelSuperiorJ1.add(comboJugador1);
        panelJ1.add(panelSuperiorJ1, BorderLayout.NORTH);
        panelJ1.add(lblImgJugador1, BorderLayout.CENTER);

        JPanel panelJ2 = new JPanel(new BorderLayout());
        JPanel panelSuperiorJ2 = new JPanel();
        panelSuperiorJ2.add(new JLabel("Jugador 2:"));
        panelSuperiorJ2.add(comboJugador2);
        panelJ2.add(panelSuperiorJ2, BorderLayout.NORTH);
        panelJ2.add(lblImgJugador2, BorderLayout.CENTER);

        // Agregamos todos los componentes a la ventana
        add(panelJ1);
        add(panelJ2);
        add(btnIniciar);
        add(btnAtacar);
        add(new JScrollPane(areaBatalla)); // Scroll para el área de texto

        // Configurar imágenes iniciales
        actualizarImagen(comboJugador1, lblImgJugador1);
        actualizarImagen(comboJugador2, lblImgJugador2);

        // Eventos para cambiar imagen dinámicamente al seleccionar otro personaje
        comboJugador1.addActionListener(e -> actualizarImagen(comboJugador1, lblImgJugador1));
        comboJugador2.addActionListener(e -> actualizarImagen(comboJugador2, lblImgJugador2));

        // Acción al hacer clic en "Iniciar Batalla"
        btnIniciar.addActionListener(e -> iniciarBatalla());

        // Acción al hacer clic en "Atacar"
        btnAtacar.addActionListener(e -> realizarAtaque());

        setVisible(true); // Mostrar la ventana
    }

    private void actualizarImagen(JComboBox<String> combo, JLabel label) {
        String seleccion = (String) combo.getSelectedItem();
        ImageIcon icon = null;

        java.net.URL imgURL = getClass().getResource("/imagenes/" + seleccion + ".png");
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        } else {
            java.io.File file = new java.io.File("src/imagenes/" + seleccion + ".png");
            if (file.exists()) {
                icon = new ImageIcon(file.getAbsolutePath());
            }
        }
        
        if (icon != null) {
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            label.setText(""); // Quitar texto si hay imagen
        } else {
            label.setIcon(null);
            label.setText("[" + seleccion + "]");
        }
    }

    // Inicializa los personajes y habilita el botón de ataque
    private void iniciarBatalla() {
        int numeroAleatorio = random.nextInt(2) ;
        turnoJugador1 = (numeroAleatorio== 0) ? true : false;
        jugador1 = crearPersonaje(comboJugador1.getSelectedItem().toString(), "Jugador 1");
        jugador2 = crearPersonaje(comboJugador2.getSelectedItem().toString(), "Jugador 2");
        if (jugador1.getNombre() != jugador2.getNombre()) {
            System.out.println("combox: " + comboJugador2.toString());
            System.out.println("comboy: " + comboJugador2.getSelectedItem());
            System.out.println("Jugador 2: " + jugador2);

            areaBatalla.setText("¡Batalla iniciada!\nTurno del Jugador "+(numeroAleatorio+1)+"\n");
            btnAtacar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "No puedes jugar con el mismo personaje.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Maneja el turno de ataque entre los jugadores
    private void realizarAtaque() {
        int numeroAleatorio = random.nextInt(2) ;
        turnoJugador1 = (numeroAleatorio== 0) ? true : false;
        if (turnoJugador1) {
            jugador1.atacar(jugador2); // Jugador 1 ataca a 2
            areaBatalla.append(jugador1.getNombre() +" atacó a "+jugador2.getNombre()+". Vida restante: " + jugador2.getVida() + "\n" + "\n");
        } else {
            jugador2.atacar(jugador1); // Jugador 2 ataca a 1
            areaBatalla.append(jugador2.getNombre()+" atacó a "+jugador1.getNombre()+". Vida restante: " + jugador1.getVida() + "\n" + "\n");
        }

        // Si alguno pierde toda la vida, termina el juego
        if (!jugador1.estaVivo() || !jugador2.estaVivo()) {
            String ganador = jugador1.estaVivo() ? jugador1.getNombre() : jugador2.getNombre()  ;
            areaBatalla.append("¡Fin del juego! Ganador: " + ganador + "\n");
            btnAtacar.setEnabled(false); // Desactivar ataques
            //MUESTRA GANADOR
            ImageIcon iconoJugador = new ImageIcon(
                    getClass().getResource("/imagenes/" + ganador + ".png")
            );
            Image imagenReescalada = iconoJugador.getImage().getScaledInstance(
                    50,
                    50,
                    Image.SCALE_SMOOTH // Usamos SCALE_SMOOTH para mejor calidad visual
            );

            // 3. Crear un NUEVO ImageIcon con la imagen de tamaño reducido
            ImageIcon iconoRedimensionado = new ImageIcon(imagenReescalada);

            JOptionPane.showMessageDialog(this, "Fin del juego, el ganador es: "+ ganador, "Ganador", JOptionPane.INFORMATION_MESSAGE, iconoRedimensionado);
        }


        //turnoJugador1 = !turnoJugador1; // Cambiar de turno
    }

    // Crea un personaje según el nombre del tipo seleccionado
    private Personaje crearPersonaje(String tipo, String nombre) {
        switch (tipo) {
            case "Guerrero": return new Guerrero("Guerrero");
            case "Mago": return new Mago("Mago");
            case "Arquero": return new Arquero("Arquero");
            case "Hechicero": return new Hechicero("Hechicero");
            case "Soldado": return new Soldado("Soldado");
            default: return null;
        }
    }

    // Método main que inicia todo
    public static void main(String[] args) {
        new JuegoPOO(); // Crear la ventana
    }
    
}
