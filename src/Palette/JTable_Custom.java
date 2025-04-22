package Palette;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JTable_Custom extends JTable {

    private int selectedRow = -1;
    private int hoveredRow = -1;

    public JTable_Custom() {
        setShowHorizontalLines(true);
        setShowVerticalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Setting untuk header tabel dengan warna [0,153,153] dan teks putih
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                TablezHeader header = new TablezHeader(value.toString());
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });

        // Renderer default untuk sel dengan warna baris putih & abu-abu bergantian
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                com.setFont(new Font("SansSerif", Font.PLAIN, 12));

                if (isSelected) {
                    com.setBackground(new Color(204, 229, 255)); // Warna biru muda saat dipilih
                    com.setForeground(new Color(0, 76, 153));
                } else if (row == hoveredRow) {
                    com.setBackground(new Color(245, 245, 245)); // Warna saat hover
                    com.setForeground(new Color(0, 0, 0)); // Teks tetap hitam
                } else {
                    // Warna bergantian putih & abu-abu
                    if (row % 2 == 0) {
                        com.setBackground(Color.WHITE); // Putih
                    } else {
                        com.setBackground(new Color(220, 220, 220)); // Abu-abu terang
                    }
                    com.setForeground(Color.BLACK); // Teks hitam untuk semua baris
                }

                setBorder(noFocusBorder);
                return com;
            }
        });

        // Event listener untuk efek hover dan klik
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = getSelectedRow();
                if (row == selectedRow) {
                    clearSelection();
                    selectedRow = -1;
                } else {
                    selectedRow = row;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hoveredRow = -1;
                repaint();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = rowAtPoint(e.getPoint());
                if (row != hoveredRow) {
                    hoveredRow = row;
                    repaint();
                }
            }
        });
    }

    // Fungsi untuk menambah baris
    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

    // Custom header class dengan warna [0,153,153] dan teks putih
    private class TablezHeader extends JLabel {

        public TablezHeader(String text) {
            super(text);
            setOpaque(true);
            setBackground(new Color(102, 204, 255)); // Warna header
            setFont(new Font("SansSerif", Font.BOLD, 13));
            setForeground(Color.WHITE); // Teks putih
            setBorder(new EmptyBorder(10, 5, 10, 5));
            setHorizontalAlignment(CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(new Color(204, 204, 240)); // Warna garis bawah header
            g.fillRect(0, getHeight() - 2, getWidth(), 2);
        }
    }
}
