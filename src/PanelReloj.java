import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelReloj extends JPanel implements Runnable {
    private Image fondo;
    private int grados = 270;
    private int minutos = 270;
    private float horas = 270;

    public PanelReloj(Image fondo) {
        this.setPreferredSize(new Dimension(800, 600));

        this.fondo = fondo;
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, this);
        g.setColor(Color.BLACK);
        g.drawOval(300, 100, 200, 200);
        g.setColor(Color.WHITE);
        g.fillOval(295, 95, 210, 210);

        // Degradado para circulo
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(300, 100, Color.RED, 500, 300, Color.BLUE);
        g2d.setPaint(gradient);
        g2d.fillOval(300, 100, 200, 200);

        // Números
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("1", 440, 135);
        g.drawString("2", 465, 165);
        g.drawString("3", 480, 210);
        g.drawString("4", 465, 250);
        g.drawString("5", 440, 280);
        g.drawString("6", 395, 290);
        g.drawString("7", 350, 280);
        g.drawString("8", 315, 250);
        g.drawString("9", 305, 210);
        g.drawString("10", 310, 165);
        g.drawString("11", 340, 135);
        g.drawString("12", 390, 120);

        // Dibuja la manecilla del segundero
        g2d.setColor(Color.RED);
        double radianesSegundos = Math.toRadians(grados);
        int xSegundos = (int) (Math.cos(radianesSegundos) * 100);
        int ySegundos = (int) (Math.sin(radianesSegundos) * 100);
        g2d.drawLine(400, 200, 400 + xSegundos, 200 + ySegundos);

        // Dibuja la manecilla del minutero
        g2d.setColor(Color.BLACK);
        double radianesMinutos = Math.toRadians(minutos);
        int xMinutos = (int) (Math.cos(radianesMinutos) * 70);
        int yMinutos = (int) (Math.sin(radianesMinutos) * 70);
        g2d.drawLine(400, 200, 400 + xMinutos, 200 + yMinutos);

        // Dibuja la manecilla de las horas
        g2d.setColor(Color.BLACK);
        double radianesHoras = Math.toRadians(horas);
        int xHoras = (int) (Math.cos(radianesHoras) * 50);
        int yHoras = (int) (Math.sin(radianesHoras) * 50);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(400, 200, 400 + xHoras, 200 + yHoras);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                grados += 6;

                if (grados == 270) {
                    minutos += 6;
                    horas += 0.5f;
                    System.out.println("Minutos: " + minutos + " Horas: " + horas);
                }
                if(grados == 360)
                    grados = 0;

                repaint();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reloj");
        Image fondo = new ImageIcon("src/imagenes/fondo3.jpg").getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        PanelReloj panel = new PanelReloj(fondo);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Thread t = new Thread(panel);
        t.start();
    }
}
