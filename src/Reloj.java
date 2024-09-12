import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Reloj extends JFrame {
    private Image fondo;
    private PanelReloj panelReloj;

    public Reloj() {
        this.setSize(800, 600);
        this.setTitle("Reloj");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.fondo = new ImageIcon("src/imagenes/fondo.jpg").getImage();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.panelReloj = new PanelReloj(this.fondo);
        this.add(panelReloj);
        this.pack();
        this.setVisible(true);
        SwingUtilities.invokeLater(this.panelReloj);
    }



    public static void main(String[] args) {
        new Reloj();
    }
}
