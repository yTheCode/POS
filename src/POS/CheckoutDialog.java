package POS;

import javax.swing.*;
import java.awt.*;

// Checkout
public class CheckoutDialog extends JDialog {
    private final Cart cart;
    private final double taxRate;

    public CheckoutDialog(Frame owner, Cart cart, double taxRate) {
        super(owner, "Checkout", true);
        this.cart = cart;
        this.taxRate = taxRate;
        initUI();
        setLocationRelativeTo(owner);
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // header 
        JPanel header = new JPanel(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 50));
        JLabel title = new JLabel("Checkout", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));
        header.add(title, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);

        // center
        JPanel center = new JPanel(new BorderLayout());
        center.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // items list
        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        center.add(scroll, BorderLayout.CENTER);

        // text content
        java.util.List<CartItem> items = cart.getItems();
        double subtotal = cart.getSubtotal();
        double tax = subtotal * taxRate;
        double total = subtotal + tax;

        if (items.isEmpty()) {
            area.setText("Your cart is empty.\n");
        } else {
            StringBuilder sb = new StringBuilder();
            for (CartItem ci : items) {
                sb.append(ci.getProduct().getName())
                  .append("  x")
                  .append(ci.getQuantity())
                  .append("  = ₱")
                  .append(String.format("%.2f", ci.getTotalPrice()))
                  .append("\n");
            }
            area.setText(sb.toString());
        }

        // totals panel
        JPanel totals = new JPanel(new GridLayout(3, 2));

        totals.add(new JLabel("Subtotal:"));
        totals.add(new JLabel(String.format("₱%.2f", subtotal)));

        totals.add(new JLabel("Tax:"));
        totals.add(new JLabel(String.format("₱%.2f", tax)));

        totals.add(new JLabel("Total:"));
        totals.add(new JLabel(String.format("₱%.2f", total)));

        center.add(totals, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        // south (confirm + close)
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton confirm = new JButton("Confirm");
        JButton close = new JButton("Close");

        south.add(close);
        south.add(confirm);
        add(south, BorderLayout.SOUTH);

        // confirm action
        confirm.addActionListener(e -> {
            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "You did not select any product to buy", 
                    "Empty Cart", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, 
                "Payment complete", 
                "Success", 
                JOptionPane.INFORMATION_MESSAGE);

            dispose();
        });

        // close
        close.addActionListener(e -> dispose());

        setSize(400, 420);
    }
}
