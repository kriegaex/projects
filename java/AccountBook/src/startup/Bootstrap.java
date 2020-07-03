package startup;

import gui.MainFrame;
import gui.panel.ConnectionPanel;
import gui.panel.ExpensePanel;
import gui.MainPanel;
import gui.util.GUIUtil;
import service.ConnectionService;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class Bootstrap {
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        GUIUtil.setSkin();
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.getInstance().setVisible(true);
//                if (!ConnectionService.isConnected()){
//                    JOptionPane.showMessageDialog(null, "Set the configuration of database first");
//                    MainPanel.getInstance().getPanel().display(ConnectionPanel.getInstance());
//                }
                 MainPanel.getInstance().getPanel().display(ExpensePanel.getInstance());
            }
        });
    }
}
