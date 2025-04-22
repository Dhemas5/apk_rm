package Palette;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicPasswordFieldUI;

public class Custom_JPasswordFieldRounded extends JPasswordField {

    private PasswordFieldUI textUI;

    public Custom_JPasswordFieldRounded() {
        textUI = new PasswordFieldUI(this);
        setUI(textUI);
    }

    public void setBorderSize(int borderSize) {
        textUI.setBorderSize(borderSize);
        repaint();
    }

    private class PasswordFieldUI extends BasicPasswordFieldUI {

        private JPasswordField passwordField;
        private Border border;
        private int round = 20;
        private int borderSize = 3;

        public int getRound() {
            return round;
        }

        public void setRound(int round) {
            this.round = round;
            border.setRound(round);
            passwordField.repaint();
        }

        public void setBorderSize(int size) {
            this.borderSize = size;
            border.setBorderSize(size);
            passwordField.repaint();
        }

        private PasswordFieldUI(JPasswordField passwordField) {
            this.passwordField = passwordField;
            border = new Border(8, borderSize);
            border.setRound(round);
            passwordField.setBorder(border);
            passwordField.setOpaque(false);
            passwordField.setSelectionColor(new Color(100, 149, 237));
            passwordField.setSelectedTextColor(Color.white);
            passwordField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    border.setColor(new Color(30, 144, 255));
                    passwordField.repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    border.setColor(new Color(70, 130, 180));
                    passwordField.repaint();
                }
            });
        }

        @Override
        protected void paintBackground(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(passwordField.getBackground());
            g2.fillRoundRect(0, 0, passwordField.getWidth(), passwordField.getHeight(), round, round);
            g2.dispose();
        }

        private class Border extends EmptyBorder {

            private Color color = new Color(70, 130, 180);
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
