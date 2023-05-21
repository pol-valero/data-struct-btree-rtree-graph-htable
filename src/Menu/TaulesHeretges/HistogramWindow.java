package Menu.TaulesHeretges;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;

public class HistogramWindow {

    public HistogramWindow(){
        JFrame frame = new JFrame("Histogram Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel(new BorderLayout());

        JInternalFrame internalFrame1 = new JInternalFrame("Map Legend", true, true, true, true);
        internalFrame1.setSize(300, 200);
        internalFrame1.setVisible(true);
        panel.add(internalFrame1);

        JInternalFrame internalFrame2 = new JInternalFrame("Histogram", true, true, true, true);
        internalFrame2.setSize(300, 200);
        internalFrame2.setLocation(320, 0);
        internalFrame2.setVisible(true);
        panel.add(internalFrame2);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
