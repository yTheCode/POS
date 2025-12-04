package POS;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

// App
public class Main {
    // Window
    private final JFrame frame = new JFrame("Simple POS");

    // Model
    private final Cart cart = new Cart();
    private final CartTableModel tableModel = new CartTableModel(cart);

    // Totals
    private final JLabel lblSubtotal = new JLabel("Subtotal");
    private final JLabel lblTax = new JLabel("Tax");
    private final JLabel lblTotal = new JLabel("Total");
    private final JLabel lblSubtotalAmt = new JLabel("₱0.00", SwingConstants.CENTER);
    private final JLabel lblTaxAmt = new JLabel("₱0.00", SwingConstants.CENTER);
    private final JLabel lblTotalAmt = new JLabel("₱0.00", SwingConstants.CENTER);

    // Tax
    private final double TAX_RATE = 0.12; // 12%

    // Catalog
    private final List<Product> catalog = new ArrayList<>();
    // tiles
    private JPanel tileSubtotal;
    private JPanel tileTax;
    private JPanel tileTotal;
    // header phase
    private float headerPhase = 0f;
    // Flash
    private JTable cartTable;
    private int flashRow = -1;
    private float flashPhase = 0f;
    private Timer flashTimer;


    public Main() {
        createSampleCatalog();
        initUI();
    }

    // Options
    private void createSampleCatalog() {
        catalog.add(new FoodItem("Burger", 5.99));
        catalog.add(new FoodItem("Fries", 2.49));
        catalog.add(new FoodItem("Hotdog", 3.25));
        catalog.add(new DrinkItem("Coke", 1.50));
        catalog.add(new DrinkItem("Coffee", 2.25));
        catalog.add(new DrinkItem("Water", 1.00));
    }

    // UI
    private void initUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // root panel
        JPanel root = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                // gradient
                g2.setPaint(new GradientPaint(0, 0, new Color(255, 250, 245), w, h, new Color(245, 255, 255)));
                g2.fillRect(0, 0, w, h);
                // watermark
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 72f));
                g2.setColor(new Color(0, 0, 0, 12));
                FontMetrics fm = g2.getFontMetrics();
                String wm = "POS";
                int sx = (w - fm.stringWidth(wm)) / 2;
                int sy = h / 2 + fm.getAscent() / 2 - 30;
                g2.drawString(wm, sx, sy);
                // sketch
                int crw = 120, crh = 64;
                int cx = w - crw - 24;
                int cy = h - crh - 24;
                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(cx, cy, crw, crh, 8, 8);
                g2.setColor(new Color(255, 255, 255, 40));
                g2.fillRect(cx + 10, cy + 10, crw - 20, 28);
            }
        };
        frame.setContentPane(root);
    }
}