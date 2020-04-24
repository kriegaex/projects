package gui.panel;
/**
 * Main panel, contains the toolbar and nothing else
 */

import gui.util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    static {
        GUIUtil.setSkin();
    }

    public static MainPanel instance = new MainPanel();
    public JToolBar topBar = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    private MainPanel() {

        GUIUtil.setImageIcon(bSpend, "img/home.png", "EXPENSES");
        GUIUtil.setImageIcon(bRecord, "img/record.png", "RECORD");
        GUIUtil.setImageIcon(bCategory, "img/category2.png", "CATEGORY");
        GUIUtil.setImageIcon(bReport, "img/report.png", "MONTH REPORT");
        GUIUtil.setImageIcon(bConfig, "img/config.png", "CONGIG");
        GUIUtil.setImageIcon(bBackup, "img/backup.png", "BACKUP");
        GUIUtil.setImageIcon(bRecover, "img/restore.png", "RESTORE");

        topBar.add(bSpend);
        topBar.add(bRecord);
        topBar.add(bCategory);
        topBar.add(bReport);
        topBar.add(bConfig);
        topBar.add(bBackup);
        topBar.add(bRecover);
        topBar.setFloatable(false); // true for the user to move the tool bar.

        setLayout(new BorderLayout());
        add(topBar, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(MainPanel.instance, 1);
    }
}
