package gui.listener;

import bean.Category;
import gui.panel.CategoryPanel;
import gui.service.CategoryService;

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
                JOptionPane.showMessageDialog(categoryPanel,
                        "Expense record under this category, deletion is not permitted");
            }
            else {
                new CategoryService().delete(selectedCategory.getID());
            }
        }

        else if (button == categoryPanel.getEditButton()){
            Category selectedCategory = categoryPanel.getSelectedCategory();
            String name = JOptionPane.showInputDialog("NAME of NEW CATEGORY");
            if (name.length() == 0){
                JOptionPane.showMessageDialog(null, "Name of Category cannot be epsilon");
                return ;
            }
            new CategoryService().update(selectedCategory.getID(), name);
        }

        else if (button == categoryPanel.getClearButton()){
            Category selectedCategory = categoryPanel.getSelectedCategory();
            int answer = JOptionPane.showConfirmDialog(null,
                    "clear all record under this category?");
            if (answer == JOptionPane.YES_OPTION){
                new CategoryService().deleteAll(selectedCategory);
            }
        }
        categoryPanel.updatePanel();
    }
}
