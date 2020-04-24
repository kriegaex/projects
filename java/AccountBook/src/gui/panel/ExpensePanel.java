package gui.panel;

/**
 * This class constitutes the expense panel
 */

import gui.util.CircleProgressBar;
import gui.util.ColourUtil;
import gui.util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ExpensePanel extends JPanel {
    static{
        GUIUtil.setSkin();
    }

    private JLabel lMonthSpend = new JLabel("Month Spend");
    private JLabel lTodaySpend = new JLabel("Daily Spend");
    private JLabel lAvgSpend = new JLabel("Average Spend");
    private JLabel lMonthLeft = new JLabel("Money Available in this Month");
    private JLabel lDayLeft = new JLabel("Money Available today");
    private JLabel lTimeLeft = new JLabel("Days Left of this Month");

    private JLabel vMonthSpend = new JLabel("$2300");
    private JLabel vTodaySpend = new JLabel("$25");
    private JLabel vAvgSpendPerDay = new JLabel("$120");
    private JLabel vMonthAvailable = new JLabel("$2084");
    private JLabel vDayAvgAvailable = new JLabel("$389");
    private JLabel vTimeLeft = new JLabel("15 Days");

    public void setvMonthSpend(String content) {
        this.vMonthSpend.setText(content);
    }

    public void setvTodaySpend(String content) {
        this.vTodaySpend.setText(content);;
    }

    public void setvAvgSpendPerDay(String content) {
        this.vAvgSpendPerDay.setText(content);;
    }

    public void setvMonthAvailable(String content) {
        this.vMonthAvailable.setText(content);;
    }

    public void setvDayAvgAvailable(String content) {
        this.vDayAvgAvailable.setText(content);;
    }

    public void setvMonthLeftDay(String content) {
        this.vTimeLeft.setText(content);;
    }

    private static ExpensePanel panel = new ExpensePanel();

    public static ExpensePanel getInstance(){ return panel; }

    public static ExpensePanel instance = new ExpensePanel();

    CircleProgressBar bar;

    public ExpensePanel() {
        this.setLayout(new BorderLayout());
        bar = new CircleProgressBar();
        bar.setBackgroundColor(ColourUtil.blueColor);

        GUIUtil.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16), lMonthSpend, lTodaySpend,
                lAvgSpend, lMonthLeft, lDayLeft, lTimeLeft, vAvgSpendPerDay, vMonthAvailable,
                vDayAvgAvailable, vTimeLeft);
        GUIUtil.setFont(new Font("Impact", Font.BOLD, 23), vMonthSpend, vTodaySpend);

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);

    }

    private JPanel center() {
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(west(), BorderLayout.WEST);
        p.add(center2(),BorderLayout.CENTER);

        return p;
    }

    private Component center2() {
        return bar;
    }

    private Component west() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        return p;
    }

    private JPanel south() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 4));

        p.add(lAvgSpend);
        p.add(lMonthLeft);
        p.add(lDayLeft);
        p.add(lTimeLeft);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vTimeLeft);

        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(ExpensePanel.instance);
    }
}
