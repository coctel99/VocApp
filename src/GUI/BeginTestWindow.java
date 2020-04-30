package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BeginTestWindow extends JFrame{
    private static final Dimension TestSizeSize=new Dimension(200,20);
    public BeginTestWindow() {
        // Frame Preferences
        super("Begin test window");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        // Element initialization
        final TestSizeField testSizeField = new TestSizeField();
    }

    private static class TestSizeField extends JTextField{
        TestSizeField(){
            super();
        }
    }

    private static class MarkList extends JCheckBox{
        MarkList(){
            super();
        }
    }
}
