package Palette;

import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalTextFieldUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.EmptyBorder;

public class JTextfieldRounded extends JTextField {

    private TextFieldUI textUI;

    public JTextfieldRounded() {
        textUI = new TextFieldUI(this);
        setUI(textUI);
    }

    public void setBorderSize(int borderSize) {
        textUI.setBorderSize(borderSize);
        repaint();
    }

    private class TextFieldUI extends MetalTextFieldUI {
        
        private JTextField textfield;
        private Border border;
        private int round = 20; // Ukuran lengkungan border lebih besar
        private int borderSize = 3; // Ukuran ketebalan border default

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
            border.setRound(round);
            textfield.repaint();
        }

        public void setBorderSize(int size) {
            this.borderSize = size;
            border.setBorderSize(size);
            textfield.repaint();
        }

        public TextFieldUI(JTextField textfield) {
            this.textfield = textfield;
            border = new Border(8, borderSize);
            border.setRound(round);
            textfield.setBorder(border);
            textfield.setOpaque(false);
            textfield.setSelectionColor(new Color(100, 149, 237)); // Warna biru lebih lembut
            textfield.setSelectedTextColor(Color.white);
            textfield.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    border.setColor(new Color(30, 144, 255)); // Warna biru saat fokus
                    textfield.repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    border.setColor(new Color(70, 130, 180)); // Warna biru stabil saat tidak fokus
                    textfield.repaint();
                }
            });
        }

        @Override
        protected void paintBackground(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(textfield.getBackground());
            g2.fillRoundRect(0, 0, textfield.getWidth(), textfield.getHeight(), round, round);
            g2.dispose();
        }

        private class Border extends EmptyBorder {
            
            private Color color = new Color(70, 130, 180); // Warna biru sebagai default
            private int round;
            private int borderSize;

            public Color getColor() {
                return color;
            }

            public void setColor(Color color) {
                this.color = color;
            }

            public int getRound() {
                return round;
            }

            public void setRound(int round) {
                this.round = round;
            }

            public void setBorderSize(int size) {
                this.borderSize = size;
            }

            public Border(int border, int borderSize) {
                super(border, border, border, border);
                this.borderSize = borderSize;
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(color);
                for (int i = 0; i < borderSize; i++) {
                    g2.drawRoundRect(x + i, y + i, width - 1 - (2 * i), height - 1 - (2 * i), round, round);
                }
                g2.dispose();
            }
        }
    }
}
