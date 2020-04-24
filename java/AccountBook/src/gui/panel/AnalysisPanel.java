package gui.panel;

import gui.util.ChartUtil;
import gui.util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class AnalysisPanel extends JPanel {
    static {
        GUIUtil.setSkin();
    }

    public static AnalysisPanel instance = new AnalysisPanel();

    public JLabel l = new JLabel();

    public AnalysisPanel() {
        this.setLayout(new BorderLayout());
        ChartUtil chart = new ChartUtil("AnalysisPanel");
        Image i = chart.getImage();
        ImageIcon icon= new ImageIcon(i);
        l.setIcon(icon);
        this.add(l);
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(AnalysisPanel.instance);
    }

}

