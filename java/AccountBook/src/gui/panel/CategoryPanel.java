package gui.panel;

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

    private CategoryTableModel model = new CategoryTableModel(
            new ArrayList<>(Arrays.asList("FOOD&DINING", "RENT includes BILLS", "CLOTHING",
                    "HEALTH&INSURANCE", "AUTO&TRANSPORT", "EDUCATION", "OTHERS")));
    private JTable table = new JTable(model);
    private JButton bAdd = new JButton("ADD");
    private JButton bDelete = new JButton("DELETE");
    private JButton bEdit = new JButton("EDIT");

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
    }
}
