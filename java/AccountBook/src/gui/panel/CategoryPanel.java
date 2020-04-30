package gui.panel;

import bean.Category;
import gui.listener.Category.CategoryListener;
import gui.listener.Category.CategoryService;
import gui.model.CategoryTableModel;
import gui.util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CategoryPanel extends JPanel{
    static{
        GUIUtil.setSkin();
    }

    private static CategoryPanel panel = new CategoryPanel();

    public static CategoryPanel getInstance() { return panel; }

//    private CategoryTableModel model = new CategoryTableModel(
//            new ArrayList<>(Arrays.asList("FOOD&DINING", "RENT includes BILLS", "CLOTHING",
//                    "HEALTH&INSURANCE", "AUTO&TRANSPORT", "EDUCATION", "OTHERS")));
    private  CategoryTableModel model = new CategoryTableModel();
    private JTable table = new JTable(model);
    private JButton bAdd = new JButton("ADD");
    public JButton getAddButton() { return bAdd; }
    private JButton bDelete = new JButton("DELETE");
    public JButton getDeleteButton() { return bDelete; }
    private JButton bEdit = new JButton("EDIT");
    public JButton getEditButton() { return bEdit; }

    public CategoryPanel(){
        this.setLayout(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        buttonPanel.add(bAdd, c);
        c.gridx = 1;
        buttonPanel.add(bEdit, c);
        c.gridx = 2;
        buttonPanel.add(bDelete, c);
        this.add(buttonPanel, BorderLayout.SOUTH);

        this.addListener();
    }

    public Category getSelectedCategory(){
        int index = table.getSelectedRow();
        return model.getList().get(index);
    }

    public void updateData(){
        model.setList(new CategoryService().list());
        table.updateUI();
        table.getSelectionModel().setSelectionInterval(0, 0);

        if (model.getList().size() == 0){
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        }
        else{
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    private void addListener(){
        bAdd.addActionListener(new CategoryListener());
        bEdit.addActionListener(new CategoryListener());
        bDelete.addActionListener(new CategoryListener());

    }
}
