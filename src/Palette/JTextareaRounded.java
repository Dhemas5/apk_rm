package Palette;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class JTextareaRounded extends JPanel {

    private Color topLineColor = new Color(255, 165, 0); // Warna garis atas (kuning)

    public JTextareaRounded() {
        setOpaque(false); // Biar bisa di-custom
        setLayout(null);  // Atur posisi komponen secara bebas (opsional)
    }

    public void setTopLineColor(Color color) {
        this.topLineColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Penting! Agar komponen anak tetap tampil

        Graphics2D g2d = (Graphics2D) g;

        // Latar putih
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Garis atas kuning
        g2d.setColor(topLineColor);
        g2d.fillRect(0, 0, getWidth(), 3); // Garis setebal 3 piksel
    }
}
