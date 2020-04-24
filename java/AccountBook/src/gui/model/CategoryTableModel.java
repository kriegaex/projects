package gui.model;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;

public class CategoryTableModel implements TableModel {
    String[] columnNames = new String[] {"CATEGORIES", "TIMES"};
    ArrayList<String> categories = new ArrayList<>();

    public CategoryTableModel(ArrayList<String> categories){
        for (String category : categories){
            this.categories.add(category);
        }
    }

    @Override
    public int getRowCount() {
        return categories.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0){
            return categories.get(rowIndex);
        }
        if (columnIndex == 1){
         // TODO: RETURN count of expense
         return 0;
        }
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        this.categories.set(rowIndex, (String)aValue);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
