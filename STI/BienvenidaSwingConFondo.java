import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class BienvenidaSwingConFondo extends JFrame {

    private JPanel panelCentral;
    private JButton botonInicio;
    private JLabel mensaje;
    private JLabel etiquetaPregunta;
    private JLabel etiquetaRespuestaIncorrecta;
    private int respuestaCorrectaNumerador;
    private int respuestaCorrectaDenominador;
    private double respuestaCorrecta;
    private Image imagenFondo;
    private Timer timer;
    private int segundosTranscurridos;
    private int errores;
    private int ayudas;
    private int nivelActual = 0; // Nivel inicial (0: básico, 1: intermedio, 2: avanzado)
    private int respuestasConsecutivas = 0; // Contador para avanzar de nivel
    private int retrocesosConsecutivos = 0; // Contador para retroceder de nivel
    private JLabel etiquetaTimer;
    private JLabel etiquetaErrores; // Nueva etiqueta para mostrar el número de errores



    public BienvenidaSwingConFondo() {
        setTitle("Aprender a Sumar");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        imagenFondo = new ImageIcon("Recursos/fondo3.png").getImage();

        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panelFondo.setLayout(new GridBagLayout());
        add(panelFondo);

        panelCentral = new PanelRedondeado(30);
        panelCentral.setBackground(new Color(173, 216, 230, 230));
        panelCentral.setPreferredSize(new Dimension(400, 300));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mensaje = new JLabel("¡Vamos a aprender a sumar!");
        mensaje.setFont(new Font("Arial", Font.BOLD, 24));
        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(mensaje);

        

        botonInicio = new JButton("Inicio");
        botonInicio.setFont(new Font("Arial", Font.PLAIN, 18));
        botonInicio.setPreferredSize(new Dimension(100, 40));
        botonInicio.setBackground(new Color(255, 182, 193)); // Fondo rosado
        botonInicio.setFocusPainted(false); // Sin contorno al hacer clic
        botonInicio.addActionListener(e -> mostrarPregunta());
        panelCentral.add(Box.createRigidArea(new Dimension(0, 50)));
        panelCentral.add(botonInicio);

        panelFondo.add(panelCentral);
        setVisible(true);
    }

    private void mostrarPregunta() {
    panelCentral.removeAll(); // Limpiar panel central

    // Reiniciar errores al mostrar una nueva pregunta
    errores = 0;

    // Panel para los elementos de la pregunta
    JPanel panelCentro = new JPanel();
    panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS)); // Diseño vertical
    panelCentro.setOpaque(false);

    // Generar la pregunta basada en el nivel actual
    String pregunta;
    if (nivelActual == 0) {
        pregunta = generarPreguntaSimple(); // Nivel básico
    } else if (nivelActual == 1) {
        pregunta = generarPreguntaDecimal(); // Nivel intermedio
    } else {
        pregunta = generarPreguntaFraccion(); // Nivel avanzado
    }

    // Etiqueta de la pregunta
    etiquetaPregunta = new JLabel(pregunta);
    etiquetaPregunta.setFont(new Font("Arial", Font.BOLD, 20));
    etiquetaPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
    etiquetaPregunta.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0)); // Espaciado superior
    panelCentro.add(etiquetaPregunta);

    // Cuadro de texto para la respuesta
    JTextField campoRespuesta = new JTextField();
    campoRespuesta.setMaximumSize(new Dimension(200, 30));
    campoRespuesta.setAlignmentX(Component.CENTER_ALIGNMENT);
    campoRespuesta.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Borde visible
    panelCentro.add(campoRespuesta);

    // Listener para limpiar el mensaje de error si el usuario escribe algo
    campoRespuesta.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            limpiarMensajeError();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            limpiarMensajeError();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            limpiarMensajeError();
        }

        private void limpiarMensajeError() {
            etiquetaRespuestaIncorrecta.setText(" ");
        }
    });

    // Etiqueta para mostrar mensajes de error
    etiquetaRespuestaIncorrecta = new JLabel(" ");
    etiquetaRespuestaIncorrecta.setForeground(Color.RED);
    etiquetaRespuestaIncorrecta.setAlignmentX(Component.CENTER_ALIGNMENT);
    etiquetaRespuestaIncorrecta.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Espaciado inferior
    panelCentro.add(etiquetaRespuestaIncorrecta);

    // Etiqueta para mostrar el contador de errores
    etiquetaErrores = new JLabel("Errores: " + errores);
    etiquetaErrores.setFont(new Font("Arial", Font.PLAIN, 16));
    etiquetaErrores.setAlignmentX(Component.CENTER_ALIGNMENT);
    etiquetaErrores.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Espaciado inferior
    panelCentro.add(etiquetaErrores);

    // Etiqueta de ayuda interactiva
    JLabel etiquetaAyuda = new JLabel("Ayuda");
    etiquetaAyuda.setFont(new Font("Arial", Font.ITALIC, 14));
    etiquetaAyuda.setForeground(Color.BLUE);
    etiquetaAyuda.setAlignmentX(Component.CENTER_ALIGNMENT);
    etiquetaAyuda.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor al pasar por encima
    etiquetaAyuda.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            JOptionPane.showMessageDialog(panelCentral, generarPista(), "Pista", JOptionPane.INFORMATION_MESSAGE);
        }
    });
    panelCentro.add(etiquetaAyuda);

    // Botón de envío de respuesta
    JButton botonEnviar = new JButton("Enviar");
    botonEnviar.setFont(new Font("Arial", Font.PLAIN, 18));
    botonEnviar.setBackground(new Color(255, 182, 193));
    botonEnviar.setFocusPainted(false);
    botonEnviar.setAlignmentX(Component.CENTER_ALIGNMENT);
    botonEnviar.addActionListener(ev -> verificarRespuesta(campoRespuesta.getText())); // Validar la respuesta ingresada
    panelCentro.add(Box.createVerticalStrut(20)); // Espacio entre elementos
    panelCentro.add(botonEnviar);

    // Añadir los elementos al panel central
    panelCentral.add(panelCentro, BorderLayout.CENTER);

    // Temporizador debajo de todos los elementos
    etiquetaTimer = new JLabel("T:0S");
    etiquetaTimer.setFont(new Font("Arial", Font.PLAIN, 16));
    etiquetaTimer.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centrar temporizador
    panelInferior.setOpaque(false); // Fondo transparente
    panelInferior.add(etiquetaTimer);

    panelCentral.add(panelInferior, BorderLayout.SOUTH); // Añadir al final del panel

    iniciarCronometro(); // Reiniciar cronómetro

    // Actualizar el panel central
    panelCentral.revalidate();
    panelCentral.repaint();
}

private String generarPista() {
    if (nivelActual == 0) {
        return "Suma los números simples. Ejemplo: 5 + 3 = 8.";
    } else if (nivelActual == 1) {
        return "Suma números con decimales redondeando al siguiente número si es más fácil.";
    } else if (nivelActual == 2) {
        return "Convierte las fracciones a un denominador común antes de sumar.";
    }
    return "No hay pista disponible.";
}


    private String generarPreguntaSimple() {
        Random random = new Random();
        int numero1 = random.nextInt(20) + 1;
        int numero2 = random.nextInt(20) + 1;
        respuestaCorrecta = numero1 + numero2;
        return "¿Cuánto es " + numero1 + " + " + numero2 + "?";
    }

    private String generarPreguntaDecimal() {
    Random random = new Random();
    double numero1 = Math.round((random.nextDouble() * 10 + 1) * 10.0) / 10.0; // Redondear a 1 decimal
    double numero2 = Math.round((random.nextDouble() * 10 + 1) * 10.0) / 10.0; // Redondear a 1 decimal
    respuestaCorrecta = numero1 + numero2;
    return "¿Cuánto es " + numero1 + " + " + numero2 + "?";
}


    private String generarPreguntaFraccion() {
        Random random = new Random();
        int numerador1 = random.nextInt(10) + 1;
        int denominador1 = random.nextInt(10) + 1;
        int numerador2 = random.nextInt(10) + 1;
        int denominador2 = random.nextInt(10) + 1;

        respuestaCorrectaNumerador = numerador1 * denominador2 + numerador2 * denominador1;
        respuestaCorrectaDenominador = denominador1 * denominador2;

        return "¿Cuánto es " + numerador1 + "/" + denominador1 + " + " + numerador2 + "/" + denominador2 + "?";
    }

   private void verificarRespuesta(String respuestaUsuario) {
    try {
        double respuesta = Double.parseDouble(respuestaUsuario.trim());
        if (Math.abs(respuesta - respuestaCorrecta) < 0.01) {
            etiquetaPregunta.setText("¡Correcto!");
            etiquetaPregunta.setForeground(Color.GREEN);
            etiquetaRespuestaIncorrecta.setText(" ");

            // Evaluar sistema difuso y cambiar nivel
            double nivelDifuso = evaluarSistemaDifuso();
            manejarProgresion(nivelDifuso);

            // Mostrar una nueva pregunta basada en el nivel actualizado
            mostrarPregunta();
        } else {
            errores++; // Incrementar errores
            etiquetaRespuestaIncorrecta.setText("Respuesta incorrecta. Intenta de nuevo.");
            etiquetaRespuestaIncorrecta.setForeground(Color.RED);
            etiquetaErrores.setText("Errores: " + errores); // Actualizar el contador de errores
        }
    } catch (NumberFormatException e) {
        etiquetaRespuestaIncorrecta.setText("Por favor, ingresa un número válido.");
        etiquetaRespuestaIncorrecta.setForeground(Color.RED);
    }
}




    private double evaluarSistemaDifuso() {
    SistemaDifuso sistema = new SistemaDifuso();
    System.out.println("Entradas al sistema difuso (antes de enviar):");
    System.out.println("Tiempo acumulado: " + segundosTranscurridos);
    System.out.println("Errores acumulados: " + errores);
    System.out.println("Ayudas acumuladas: " + ayudas);

    double nivel = sistema.obtenerNivel(errores, segundosTranscurridos, ayudas);
    System.out.println("Nivel obtenido: " + nivel); // Depuración
    return nivel;
}


  private void iniciarCronometro() {
    segundosTranscurridos = 0; // Reinicia el contador
    if (timer != null && timer.isRunning()) {
        timer.stop();
    }
    timer = new Timer(1000, e -> {
        segundosTranscurridos++;
        etiquetaTimer.setText("T:" + segundosTranscurridos + "S");
    });
    timer.start();
}



   private void manejarProgresion(double nivel) {
    boolean avanzar = false;
    boolean retroceder = false;

    if (nivel > 5.0) { // Nivel alto: avanzar
        respuestasConsecutivas++;
        retrocesosConsecutivos = 0;
        if (respuestasConsecutivas >= 3) { // Avanzar si hay 3 respuestas correctas consecutivas
            if (nivelActual < 2) { // Máximo nivel es 2
                nivelActual++;
                avanzar = true;
            }
            respuestasConsecutivas = 0; // Reiniciar el contador
        }
    } else if (nivel < 2.5) { // Nivel bajo: retroceder
        retrocesosConsecutivos++;
        respuestasConsecutivas = 0;
        if (retrocesosConsecutivos >= 3) { // Retroceder si hay 3 incorrectas consecutivas
            if (nivelActual > 0) { // Mínimo nivel es 0
                nivelActual--;
                retroceder = true;
            }
            retrocesosConsecutivos = 0; // Reiniciar el contador
        }
    } else { // Mantener nivel
        respuestasConsecutivas = 0;
        retrocesosConsecutivos = 0;
    }

    // Depuración
    if (avanzar) {
        System.out.println("¡Avanzaste al siguiente nivel! Nivel actual: " + nivelActual);
    } else if (retroceder) {
        System.out.println("¡Retrocediste al nivel anterior! Nivel actual: " + nivelActual);
    } else {
        System.out.println("Te mantienes en el nivel actual: " + nivelActual);
    }
}



    public static void main(String[] args) {
        SwingUtilities.invokeLater(BienvenidaSwingConFondo::new);
    }

    class PanelRedondeado extends JPanel {
        private int radio;

        public PanelRedondeado(int radio) {
            this.radio = radio;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
        }
    }
}
