package gui.model;

/**
 * This class works as the data model for the combobox in recordPanel
 */

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class RecordComboBoxModel implements ComboBoxModel<String> {
    private ArrayList<String> comboBoxes = new ArrayList<>();
    private String displaying;
    public RecordComboBoxModel(ArrayList<String> categories){
        for (String category : categories){
            comboBoxes.add(category);
        }

        displaying = comboBoxes.get(0);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        displaying = (String)anItem;
    }

    @Override
    public Object getSelectedItem(){
        return displaying;
    }

    @Override
    public int getSize() {
        return comboBoxes.size();
    }

    @Override
    public String getElementAt(int index) {
        return comboBoxes.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
