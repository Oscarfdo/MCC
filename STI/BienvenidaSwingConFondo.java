import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BienvenidaSwingConFondo extends JFrame {

    private JPanel panelCentral;
    private JButton botonInicio;
    private JLabel mensaje;
    private JLabel etiquetaPregunta;
    private JLabel etiquetaRespuestaIncorrecta;
    private JLabel etiquetaTimer; // Etiqueta para mostrar el cronómetro
    private JLabel etiquetaErrores; // Etiqueta para mostrar el contador de errores
    private JLabel etiquetaAyuda; // Etiqueta estilo hipervínculo para ayuda
    private int respuestaCorrecta;
    private Image imagenFondo;
    private Timer timer;
    private int segundosTranscurridos;
    private int errores;

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
        botonInicio.addActionListener(e -> mostrarPregunta());

        panelBoton.add(botonInicio);
        panelCentral.add(panelBoton);

        panelFondo.add(panelCentral);
        setVisible(true);
    }

    private void mostrarPregunta() {
        panelCentral.removeAll();

        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setOpaque(false);

        Random random = new Random();
        int numero1 = random.nextInt(10) + 1;
        int numero2 = random.nextInt(10) + 1;
        respuestaCorrecta = numero1 + numero2;

        String pregunta = "¿Cuánto es " + numero1 + " + " + numero2 + "?";
        etiquetaPregunta = new JLabel(pregunta);
        etiquetaPregunta.setFont(new Font("Arial", Font.BOLD, 20));
        etiquetaPregunta.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaPregunta.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelContenido.add(etiquetaPregunta);

        JTextField campoRespuesta = new JTextField();
        campoRespuesta.setMaximumSize(new Dimension(200, 30));
        campoRespuesta.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        campoRespuesta.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                limpiarMensajeError();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                limpiarMensajeError();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                limpiarMensajeError();
            }
        });

        panelContenido.add(campoRespuesta);

        etiquetaRespuestaIncorrecta = new JLabel(" ");
        etiquetaRespuestaIncorrecta.setForeground(Color.RED);
        etiquetaRespuestaIncorrecta.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaRespuestaIncorrecta.setPreferredSize(new Dimension(200, 20)); // Fijar tamaño de la etiqueta
        panelContenido.add(etiquetaRespuestaIncorrecta);

        panelContenido.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio adicional debajo del campo de respuesta

        JButton botonEnviar = new JButton("Enviar");
        botonEnviar.setFont(new Font("Arial", Font.PLAIN, 18));
        botonEnviar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonEnviar.addActionListener(ev -> verificarRespuesta(campoRespuesta.getText()));
        panelContenido.add(botonEnviar);

        panelCentral.add(panelContenido);

        // Panel para el cronómetro, la ayuda y el contador de errores en la parte inferior
        JPanel panelCronometroErrores = new JPanel();
        panelCronometroErrores.setLayout(new BoxLayout(panelCronometroErrores, BoxLayout.X_AXIS));
        panelCronometroErrores.setOpaque(false);

        // Cronómetro alineado a la izquierda
        etiquetaTimer = new JLabel("T: 0s");
        etiquetaTimer.setFont(new Font("Arial", Font.PLAIN, 14));
        panelCronometroErrores.add(etiquetaTimer);

        // Espacio flexible para centrar la "Ayuda"
        panelCronometroErrores.add(Box.createHorizontalGlue());

        // Crear la palabra "Ayuda" como hipervínculo sin un área de clic adicional
        etiquetaAyuda = new JLabel("<html><u>Ayuda</u></html>");
        etiquetaAyuda.setFont(new Font("Arial", Font.PLAIN, 14));
        etiquetaAyuda.setForeground(Color.BLUE);
        etiquetaAyuda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        etiquetaAyuda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mostrarAyuda();
            }
        });
        panelCronometroErrores.add(etiquetaAyuda);

        // Espacio flexible para centrar "Ayuda" entre cronómetro y errores
        panelCronometroErrores.add(Box.createHorizontalGlue());

        // Errores alineado a la derecha
        etiquetaErrores = new JLabel("Errores: 0");
        etiquetaErrores.setFont(new Font("Arial", Font.PLAIN, 14));
        panelCronometroErrores.add(etiquetaErrores);

        // Añadir el panel inferior al final del panel central
        panelCentral.add(Box.createVerticalGlue());
        panelCentral.add(panelCronometroErrores);

        iniciarCronometro();

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private void iniciarCronometro() {
        segundosTranscurridos = 0;
        errores = 0;
        actualizarEtiquetaTimer();
        actualizarEtiquetaErrores();

        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new Timer(1000, e -> {
            segundosTranscurridos++;
            actualizarEtiquetaTimer();
        });
        timer.start();
    }

    private void actualizarEtiquetaTimer() {
        etiquetaTimer.setText("T: " + segundosTranscurridos + "s");
    }

    private void actualizarEtiquetaErrores() {
        etiquetaErrores.setText("Errores: " + errores);
    }

    private void limpiarMensajeError() {
        etiquetaRespuestaIncorrecta.setText(" ");
    }

    private void verificarRespuesta(String respuestaUsuario) {
        try {
            int respuesta = Integer.parseInt(respuestaUsuario.trim());
            if (respuesta == respuestaCorrecta) {
                etiquetaPregunta.setText("¡Correcto!");
                etiquetaRespuestaIncorrecta.setText(" ");
                timer.stop();

                Timer delay = new Timer(3000, e -> mostrarPregunta());
                delay.setRepeats(false);
                delay.start();
            } else {
                errores++;
                actualizarEtiquetaErrores();
                etiquetaRespuestaIncorrecta.setText("Respuesta incorrecta. Intenta de nuevo.");
            }
        } catch (NumberFormatException e) {
            etiquetaRespuestaIncorrecta.setText("Por favor, ingresa un número válido.");
        }
    }

    private void mostrarAyuda() {
        JOptionPane.showMessageDialog(this, "Para resolver la suma, introduce tu respuesta en el campo y presiona 'Enviar'.\n"
                + "El cronómetro muestra el tiempo que has tomado para responder, y el contador muestra los errores que has cometido.", 
                "Ayuda", JOptionPane.INFORMATION_MESSAGE);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BienvenidaSwingConFondo());
    }
}
