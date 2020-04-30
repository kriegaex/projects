package gui.listener.Category;

import bean.Category;
import gui.panel.CategoryPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel categoryPanel = CategoryPanel.getInstance();
        JButton button = (JButton)e.getSource();
        if (button == categoryPanel.getAddButton()) {
            String name = JOptionPane.showInputDialog("NAME of NEW CATEGORY");
            if (name.length() == 0){
                JOptionPane.showMessageDialog(null, "Name of Category cannot be epsilon");
                return ;
            }
            else{
                new CategoryService().add(name);
            }

        }

        else if (button == categoryPanel.getDeleteButton()){
            Category selectedCategory = categoryPanel.getSelectedCategory();
            if (selectedCategory.getRecordNumber() != 0){
                int reply = JOptionPane.showConfirmDialog(categoryPanel,
                        "Expense record under this category, still delete?",
                        "Delete a record",
                        JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION){
                    new CategoryService().delete(selectedCategory.getID());
                }
            }
            else {
                new CategoryService().delete(selectedCategory.getID());
            }
        }

        else {
            Category selectedCategory = categoryPanel.getSelectedCategory();
            String name = JOptionPane.showInputDialog("NAME of NEW CATEGORY");
            if (name.length() == 0){
                JOptionPane.showMessageDialog(null, "Name of Category cannot be epsilon");
                return ;
            }
            new CategoryService().update(selectedCategory.getID(), name);
        }
        categoryPanel.updateData();
    }
}
