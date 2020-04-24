package gui.panel;

import gui.model.RecordComboBoxModel;
import gui.util.ColourUtil;
import gui.util.GUIUtil;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RecordPanel extends JPanel {
    static{
        GUIUtil.setSkin();
    }

    private static RecordPanel panel = new RecordPanel();

    public static RecordPanel getInstance() { return panel; }

    // The code below describes the jcomponents on the upper panel
    private JLabel lExpense = new JLabel("EXPENSE");
    private JTextField tExpense = new JTextField();

    private JLabel lCategory = new JLabel("CATEGORY");
    private RecordComboBoxModel catergoryModel =
            new RecordComboBoxModel(new ArrayList<> (Arrays.asList("FOOD&DINING", "RENT includes BILLS", "CLOTHING",
                    "HEALTH&INSURANCE", "AUTO&TRANSPORT", "EDUCATION", "OTHERS")));
    private JComboBox<String> boxes = new JComboBox<>(catergoryModel);

    private JLabel lPayment = new JLabel("PAYMENT");
    private RecordComboBoxModel paymentModel =
            new RecordComboBoxModel(new ArrayList<>(Arrays.asList("VISA", "CASH", "DEBIT", "LOAN")));
    private JComboBox<String> payments = new JComboBox<>(paymentModel);

    private JLabel lDescription = new JLabel("DESCRIPTION");
    private JTextArea description = new JTextArea();
    private JScrollPane des = new JScrollPane(description);

    private JLabel lDate = new JLabel("DATE");
    private JXDatePicker date = new JXDatePicker();


    public RecordPanel(){
        this.setLayout(new BorderLayout());
        this.add(centerPanel(), BorderLayout.CENTER);
        this.add(southPanel(), BorderLayout.SOUTH);
    }

    private JPanel centerPanel(){
        JPanel nPanel = new JPanel();
        nPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        des.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridheight = 5;
        c.weightx = 0.5;
        c.weighty = 0.2;
        c.gridx = 0;
        c.gridy = 0;
        nPanel.add(lExpense, c);
        c.gridx = 1;
        c.gridy = 0;
        nPanel.add(tExpense, c);

        c.gridx = 0;
        c.gridy = 5;
        nPanel.add(lCategory, c);
        c.gridx = 1;
        c.gridy = 5;
        nPanel.add(boxes, c);

        c.gridx = 0;
        c.gridy = 10;
        nPanel.add(lPayment, c);
        c.gridx = 1;
        c.gridy = 10;
        nPanel.add(payments, c);

        c.gridx = 0;
        c.gridy = 15;
        nPanel.add(lDescription, c);
        c.ipady = 70;
        c.gridx = 1;
        c.gridy = 15;
        nPanel.add(des, c);

        c.ipady = 5;
        c.gridx = 0;
        c.gridy = 20;
        nPanel.add(lDate, c);
        c.gridx = 1;
        c.gridy = 20;
        nPanel.add(date, c);
        return nPanel;
    }

    private JPanel southPanel(){
        JButton submitButton = new JButton("SUBMIT");
        GUIUtil.setColour(ColourUtil.blueColor, submitButton);
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new BorderLayout());
        cPanel.add(submitButton, BorderLayout.SOUTH);
        return cPanel;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecordPanel.getInstance());
    }
}
