package Palette;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author mahar
 */
public class JPanelGradient extends JPanel {

    private Color colorstart;
    private Color colorend;

    // Konstruktor default (warna default sesuai gambar yang diinginkan)
    public JPanelGradient() {
        this.colorstart = new Color(137, 207, 240); // #89CFF0 (Biru Muda)
        this.colorend = new Color(86, 82, 119);    // #565277 (Ungu Tua)
        setOpaque(false); // Hilangkan latar belakang default
    }

    // Konstruktor dengan parameter warna
    public JPanelGradient(Color colorstart, Color colorend) {
        this.colorstart = colorstart;
        this.colorend = colorend;
        setOpaque(false);
    }

    public Color getColorstart() {
        return colorstart;
    }

    public void setColorstart(Color colorstart) {
        this.colorstart = colorstart;
        repaint(); // Perbarui tampilan setelah mengubah warna
    }

    public Color getColorend() {
        return colorend;
    }

    public void setColorend(Color colorend) {
        this.colorend = colorend;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Aktifkan rendering hints untuk gradasi lebih halus
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Terapkan gradasi
        GradientPaint gradient = new GradientPaint(0, 0, colorstart, getWidth(), getHeight(), colorend);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
