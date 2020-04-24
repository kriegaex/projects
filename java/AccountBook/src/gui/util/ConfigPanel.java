package gui.util;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel{
    static{
        GUIUtil.setSkin();
    }

    public static ConfigPanel instance = new ConfigPanel();

    JLabel lBudget = new JLabel("BUDGET of this MONTH ($)");
    public JTextField tfBudget = new JTextField("0");

    JLabel lMysql = new JLabel("DIRECTORY of INSTALLATION");
    public JTextField tfMysqlPath = new JTextField("");
    JButton bBrowse = new JButton("BROWSE");

    JButton bSubmit = new JButton("SUBMIT");

    public ConfigPanel() {
        GUIUtil.setColour(ColourUtil.grayColor, lBudget,lMysql);
        GUIUtil.setColour(ColourUtil.blueColor, bSubmit);

        JPanel pInput = new JPanel(new GridBagLayout());
        JPanel pSubmit = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

        c.weighty = 0.1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        pInput.add(lBudget, c);
        c.ipadx = 300;
        c.gridy = 1;
        pInput.add(tfBudget, c);
        c.ipadx = 0;
        c.gridwidth = 1;
        c.gridy = 2;
        pInput.add(lMysql, c);
        c.weighty = 0.05;
        c.gridy = 3;
        pInput.add(bBrowse, c);
        c.ipadx = 300;
        c.gridy = 4;
        pInput.add(tfMysqlPath, c);
        this.setLayout(new BorderLayout());
        this.add(pInput,BorderLayout.CENTER);

        pSubmit.add(bSubmit);
        this.add(pSubmit,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ConfigPanel.instance);
    }

}
