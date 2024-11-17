import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class BienvenidaSwingConFondo extends JFrame {

    private JPanel panelCentral;
    private JButton botonInicio;
    private JLabel mensaje;
    private JLabel etiquetaPregunta;
    private JLabel etiquetaRespuestaIncorrecta;
    private JLabel etiquetaTimer;
    private int respuestaCorrectaNumerador;
    private int respuestaCorrectaDenominador;
    private double respuestaCorrecta;
    private Image imagenFondo;
    private Timer timer;
    private int segundosTranscurridos;
    private int errores;
    private int ayudas;

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

        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        mensaje = new JLabel("¡Vamos a aprender a sumar!");
        mensaje.setFont(new Font("Arial", Font.BOLD, 24));
        mensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(mensaje);

        panelCentral.add(Box.createRigidArea(new Dimension(0, 50)));

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panelBoton.setOpaque(false);

        botonInicio = new JButton("Inicio");
        botonInicio.setFont(new Font("Arial", Font.PLAIN, 18));
        botonInicio.setPreferredSize(new Dimension(100, 40));
        botonInicio.setBackground(new Color(255, 182, 193));
        botonInicio.setFocusPainted(false);
        botonInicio.addActionListener(e -> mostrarPregunta(0)); // Nivel inicial bajo

        panelBoton.add(botonInicio);
        panelCentral.add(panelBoton);

        panelFondo.add(panelCentral);
        setVisible(true);
    }

    private void mostrarPregunta(double nivel) {
        panelCentral.removeAll();

        iniciarCronometro();

        String pregunta = "";

        // Decidir el tipo de pregunta según el nivel
        if (nivel < 3) {
            pregunta = generarPreguntaSimple();
        } else if (nivel < 6) {
            pregunta = generarPreguntaDecimal();
        } else {
            pregunta = generarPreguntaFraccion();
        }

        etiquetaPregunta = new JLabel(pregunta);
        etiquetaPregunta.setFont(new Font("Arial", Font.BOLD, 20));
        etiquetaPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaPregunta.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelCentral.add(etiquetaPregunta);

        JTextField campoRespuesta = new JTextField();
        campoRespuesta.setMaximumSize(new Dimension(200, 30));
        campoRespuesta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        panelCentral.add(campoRespuesta);

        etiquetaRespuestaIncorrecta = new JLabel(" ");
        etiquetaRespuestaIncorrecta.setForeground(Color.RED);
        etiquetaRespuestaIncorrecta.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaRespuestaIncorrecta.setPreferredSize(new Dimension(200, 20));
        panelCentral.add(etiquetaRespuestaIncorrecta);

        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setFont(new Font("Arial", Font.PLAIN, 18));
        botonEnviar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonEnviar.addActionListener(ev -> {
            verificarRespuesta(campoRespuesta.getText());
            double nuevoNivel = evaluarSistemaDifuso();
            mostrarPregunta(nuevoNivel); // Mostrar nueva pregunta con el nivel actualizado
        });
        panelCentral.add(botonEnviar);

        panelCentral.revalidate();
        panelCentral.repaint();
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
        double numero1 = Math.round((random.nextDouble() * 10 + 1) * 100.0) / 100.0;
        double numero2 = Math.round((random.nextDouble() * 10 + 1) * 100.0) / 100.0;
        respuestaCorrecta = numero1 + numero2;
        return "¿Cuánto es " + numero1 + " + " + numero2 + "?";
    }

    private String generarPreguntaFraccion() {
        Random random = new Random();
        int numerador1 = random.nextInt(10) + 1;
        int denominador1 = random.nextInt(10) + 1;
        int numerador2 = random.nextInt(10) + 1;
        int denominador2 = random.nextInt(10) + 1;

        // Calcular la respuesta correcta
        respuestaCorrectaNumerador = numerador1 * denominador2 + numerador2 * denominador1;
        respuestaCorrectaDenominador = denominador1 * denominador2;

        return "¿Cuánto es " + numerador1 + "/" + denominador1 + " + " + numerador2 + "/" + denominador2 + "?";
    }

    private void verificarRespuesta(String respuestaUsuario) {
        try {
            double respuesta = Double.parseDouble(respuestaUsuario.trim());
            if (Math.abs(respuesta - respuestaCorrecta) < 0.01) {
                etiquetaPregunta.setText("¡Correcto!");
                etiquetaRespuestaIncorrecta.setText(" ");
            } else {
                errores++;
                etiquetaRespuestaIncorrecta.setText("Respuesta incorrecta. Intenta de nuevo.");
            }
        } catch (NumberFormatException e) {
            etiquetaRespuestaIncorrecta.setText("Por favor, ingresa un número válido.");
        }
    }

    private double evaluarSistemaDifuso() {
        SistemaDifuso sistema = new SistemaDifuso();
        System.out.println("Entradas al sistema difuso (antes de enviar):");
        System.out.println("Tiempo acumulado: " + segundosTranscurridos);
        System.out.println("Errores acumulados: " + errores);
        System.out.println("Ayudas acumuladas: " + ayudas);

        double nivel = sistema.obtenerNivel(errores, segundosTranscurridos, ayudas);
        System.out.println("Nivel obtenido: " + nivel);
        return nivel;
    }

    private void iniciarCronometro() {
        segundosTranscurridos = 0;
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        timer = new Timer(1000, e -> segundosTranscurridos++);
        timer.start();
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
