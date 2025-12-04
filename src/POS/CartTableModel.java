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
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("setValueAt not implemented");
    }
}
