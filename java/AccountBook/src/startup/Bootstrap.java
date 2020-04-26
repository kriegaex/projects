package startup;

import gui.MainFrame;
import gui.panel.AnalysisPanel;
import gui.panel.ExpensePanel;
import gui.panel.MainPanel;
import gui.util.GUIUtil;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Bootstrap {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        GUIUtil.setSkin();
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance().setVisible(true);
                MainPanel.getInstance().getPanel().display(ExpensePanel.getInstance());
            }
        });
    }
}
