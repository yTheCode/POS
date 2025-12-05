package POS;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CartTableModel extends AbstractTableModel {
    private final Cart cart;
    private final String[] columns = {"Item", "Qty", "Price", "Total", "Action"};

    public CartTableModel(Cart cart) {
        this.cart = cart;
    }

    @Override
    public int getRowCount() {
        return cart.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        List<CartItem> items = cart.getItems();
        if (rowIndex < 0 || rowIndex >= items.size()) return null;
        CartItem ci = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ci.getProduct().getName();
            case 1:
                return ci.getQuantity();
            case 2:
                return ci.getProduct().getPrice();
            case 3:
                return ci.getTotalPrice();
            case 4:
                return "Remove";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1 || columnIndex == 4;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        List<CartItem> items = cart.getItems();
        if (rowIndex < 0 || rowIndex >= items.size()) return;
        CartItem ci = items.get(rowIndex);
        if (columnIndex == 1) {
            try {
                int v;
                if (aValue instanceof Number) v = ((Number) aValue).intValue();
                else v = Integer.parseInt(aValue.toString());
                if (v <= 0) {
                    cart.removeProduct(ci.getProduct());
                    fireTableRowsDeleted(rowIndex, rowIndex);
                } else {
                    ci.setQuantity(v);
                    fireTableRowsUpdated(rowIndex, rowIndex);
                }
            } catch (NumberFormatException ignored) {}
        } else if (columnIndex == 4) {
            cart.removeProduct(ci.getProduct());
            fireTableRowsDeleted(rowIndex, rowIndex);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
            case 3:
                return Double.class;
            case 4:
                return String.class;
            default:
                return Object.class;
        }
    }
}
