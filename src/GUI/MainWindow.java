package GUI;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class MainWindow extends JFrame{
    private static final Dimension dictionarySize=new Dimension(200,300);
    private static final Dimension wordInfoSize=new Dimension(200,150);
    private static final Dimension wordSize=new Dimension(200,20);
    private static final Dimension definitionSize=new Dimension(200,20);
    private static final Dimension contextSize=new Dimension(400,60);
    private static final Dimension workWithTextSize=new Dimension(400,200);
    private static ProfileWindow profileWindow = new ProfileWindow();
    private static WordTextField wordTextField = new WordTextField();
    private static DefinitionTextField definitionTextField = new DefinitionTextField();
    private static ContextTextArea contextTextArea = new ContextTextArea();
    private static DictionaryList dictionaryList = new DictionaryList();
    private static JFrame errorFrame = new JFrame();
    private static JFrame errorFrame2 = new JFrame();

    public MainWindow() throws SQLException {
        // Frame Preferences
        super("VocApp");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        // Element initialization
        final WordInfoLabel wordInfoLabel = new WordInfoLabel();
        final SortButton sortButton = new SortButton();
        final FilterButton filterButton = new FilterButton();
        final WorkWithTextTextArea workWithTextTextArea=new WorkWithTextTextArea();
        final CustomMenuBar customMenuBar=new CustomMenuBar();


        // Element preferences
        wordTextField.setPreferredSize(wordSize);
        definitionTextField.setPreferredSize(definitionSize);
        contextTextArea.setPreferredSize(contextSize);
        workWithTextTextArea.setPreferredSize(workWithTextSize);

        // Container initialization
        final Container panel_0_hbox=this.getContentPane();
        final JPanel panel_00_vbox=new JPanel();
        final JPanel panel_000_border=new JPanel();
        final JPanel panel_0000_vbox=new JPanel();
        final JPanel panel_01_vbox=new JPanel();
        final JScrollPane panel_010_scroll=new JScrollPane(wordInfoLabel);
        final JPanel panel_011_hbox=new JPanel();
        final JScrollPane panel_012_scroll=new JScrollPane(dictionaryList);

        // Container Preferences
        panel_0_hbox.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.X_AXIS));

        panel_00_vbox.setLayout(new BoxLayout(panel_00_vbox,BoxLayout.Y_AXIS));
        panel_00_vbox.setAlignmentY(Component.TOP_ALIGNMENT);

        panel_000_border.setLayout(new BorderLayout());

        panel_0000_vbox.setLayout(new BoxLayout(panel_0000_vbox,BoxLayout.Y_AXIS));

        panel_01_vbox.setLayout(new BoxLayout(panel_01_vbox,BoxLayout.Y_AXIS));
        panel_01_vbox.setAlignmentY(Component.TOP_ALIGNMENT);

        panel_010_scroll.setPreferredSize(wordInfoSize);
        panel_010_scroll.setViewportView(wordInfoLabel);
        panel_010_scroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel_011_hbox.setLayout(new BoxLayout(panel_011_hbox,BoxLayout.X_AXIS));
        panel_011_hbox.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel_012_scroll.setPreferredSize(dictionarySize);
        panel_012_scroll.setViewportView(dictionaryList);
        panel_012_scroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Container Placement
        panel_0_hbox.add(Box.createHorizontalStrut(20));
        panel_0_hbox.add(panel_00_vbox);

        panel_00_vbox.add(panel_000_border);

        panel_000_border.add(panel_0000_vbox,BorderLayout.NORTH);

        panel_0000_vbox.add(Box.createVerticalStrut(10));
        panel_0000_vbox.add(wordTextField);
        panel_0000_vbox.add(Box.createVerticalStrut(10));
        panel_0000_vbox.add(definitionTextField);
        panel_0000_vbox.add(Box.createVerticalStrut(10));
        panel_0000_vbox.add(contextTextArea);

        panel_00_vbox.add(workWithTextTextArea);
        panel_00_vbox.add(Box.createVerticalStrut(10));

        panel_0_hbox.add(Box.createHorizontalStrut(20));
        panel_0_hbox.add(panel_01_vbox);

        panel_01_vbox.add(Box.createVerticalStrut(10));
        panel_01_vbox.add(panel_010_scroll);
        panel_01_vbox.add(Box.createVerticalStrut(10));
        panel_01_vbox.add(panel_011_hbox);

        panel_011_hbox.add(sortButton);
        panel_011_hbox.add(Box.createHorizontalStrut(10));
        panel_011_hbox.add(filterButton);

        panel_01_vbox.add(Box.createVerticalStrut(10));
        panel_01_vbox.add(panel_012_scroll);

        panel_01_vbox.add(Box.createVerticalStrut(10));

        panel_0_hbox.add(Box.createHorizontalStrut(20));

        wordTextField.setBorder(BorderFactory.createEtchedBorder());
        definitionTextField.setBorder(BorderFactory.createEtchedBorder());
        contextTextArea.setBorder(BorderFactory.createEtchedBorder());
        workWithTextTextArea.setBorder(BorderFactory.createEtchedBorder());
        panel_010_scroll.setBorder(BorderFactory.createEtchedBorder());
        panel_012_scroll.setBorder(BorderFactory.createEtchedBorder());

        errorFrame.add(new JLabel("Error. This word already exists"));
        errorFrame.setPreferredSize(new Dimension(200, 60));
        errorFrame.pack();
        errorFrame.setVisible(false);

        errorFrame2.add(new JLabel("Wrong input"));
        errorFrame2.setPreferredSize(new Dimension(200, 60));
        errorFrame2.pack();
        errorFrame2.setVisible(false);

        wordTextField.addKeyListener(enterPressedListener);
        definitionTextField.addKeyListener(enterPressedListener);
        contextTextArea.addKeyListener(enterPressedListener);

        this.setJMenuBar(customMenuBar);

        this.pack();
    }

    //-----------ELEMENTS-------------
    private static class DictionaryList extends JList{
        final DefaultListModel<String> listModel=new DefaultListModel();
        DictionaryList(){
            super();
            for(int i=0;i<50;i++){
                listModel.addElement("word"+i);
            }
            this.setModel(listModel);
        }
    }
    private class WordInfoLabel extends JLabel{
        WordInfoLabel(){
            super();
            this.setText("Word info here");
            this.setVerticalAlignment(JLabel.NORTH);
        }
    }
    private class SortButton extends JButton{
        SortButton() {
            super();
            this.setText("Sort");
            final JPopupMenu popupMenu = new JPopupMenu();
            final ButtonGroup buttonGroup=new ButtonGroup();
            final JRadioButtonMenuItem menuItem1=
                    new JRadioButtonMenuItem(new AbstractAction("Alphabetic order") {
                public void actionPerformed(ActionEvent e) {
                }
            });
            final JRadioButtonMenuItem menuItem2=new JRadioButtonMenuItem(new AbstractAction("By date added") {
                public void actionPerformed(ActionEvent e) {
                }
            });
            final JRadioButtonMenuItem menuItem3=new JRadioButtonMenuItem(new AbstractAction("By marker") {
                public void actionPerformed(ActionEvent e) {
                }
            });

            buttonGroup.add(menuItem1);
            buttonGroup.add(menuItem2);
            buttonGroup.add(menuItem3);
            popupMenu.add(menuItem1);
            popupMenu.add(menuItem2);
            popupMenu.add(menuItem3);

            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
        }
    }
    private class FilterButton extends JButton{
        FilterButton(){
            super();
            this.setText("Filter");
            final JPopupMenu popupMenu = new JPopupMenu();

            popupMenu.add(new JCheckBoxMenuItem(new AbstractAction("No Filter") {
                public void actionPerformed(ActionEvent e) {
                    ((JCheckBoxMenuItem)popupMenu.getComponent(1)).setState(false);
                    ((JCheckBoxMenuItem)popupMenu.getComponent(2)).setState(false);
                    ((JCheckBoxMenuItem)popupMenu.getComponent(3)).setState(false);
                    ((JCheckBoxMenuItem)popupMenu.getComponent(4)).setState(false);
                }
            }));
            popupMenu.add(new JCheckBoxMenuItem(new AbstractAction("Filter ignored words") {
                public void actionPerformed(ActionEvent e) {
                    ((JCheckBoxMenuItem)popupMenu.getComponent(0)).setState(false);
                }
            }));
            popupMenu.add(new JCheckBoxMenuItem(new AbstractAction("Filter words with green mark") {
                public void actionPerformed(ActionEvent e) {
                    ((JCheckBoxMenuItem)popupMenu.getComponent(0)).setState(false);
                }
            }));
            popupMenu.add(new JCheckBoxMenuItem(new AbstractAction("Filter words with yellow mark") {
                public void actionPerformed(ActionEvent e) {
                    ((JCheckBoxMenuItem)popupMenu.getComponent(0)).setState(false);
                }
            }));
            popupMenu.add(new JCheckBoxMenuItem(new AbstractAction("Filter words with red mark") {
                public void actionPerformed(ActionEvent e) {
                    ((JCheckBoxMenuItem)popupMenu.getComponent(0)).setState(false);
                }
            }));

            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            });
        }
    }
    private static class WordTextField extends JTextField{
        WordTextField() {
            super();
            addKeyListener(enterPressedListener);
        }
    }

    private static class DefinitionTextField extends JTextField{
        DefinitionTextField(){
            super();
            addKeyListener(enterPressedListener);
        }
    }
    private static class ContextTextArea extends JTextArea{
        ContextTextArea(){
            super();
            this.setLineWrap(true);
            this.setWrapStyleWord(true);
            addKeyListener(enterPressedListener);
        }
    }
    private class WorkWithTextTextArea extends JTextArea{
        WorkWithTextTextArea(){
            super();
            this.setLineWrap(true);
            this.setWrapStyleWord(true);
        }
    }
    private class CustomMenuBar extends JMenuBar{
        CustomMenuBar(){
            super();
            JMenu profileMenu=new JMenu("Profile");
            JMenuItem profileMenuItem0=new JMenuItem("Create/Switch user");
            profileMenuItem0.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profileWindow.setVisible(true);
                }
            });
            JMenu dictionaryMenu=new JMenu("Dictionary");
            JMenuItem dictionaryMenuItem0=new JMenuItem("Clear");
            JMenu testMenu=new JMenu("Test");
            JMenuItem testMenuItem0=new JMenuItem("New custom test");
            JMenuItem testMenuItem1=new JMenuItem("Repeat the last test");
            JMenu helpMenu=new JMenu("Help");
            JMenuItem helpMenuItem0=new JMenuItem("Guide");

            profileMenu.add(profileMenuItem0);
            dictionaryMenu.add(dictionaryMenuItem0);
            testMenu.add(testMenuItem0);
            testMenu.add(testMenuItem1);
            helpMenu.add(helpMenuItem0);

            this.add(profileMenu);
            this.add(dictionaryMenu);
            this.add(testMenu);
            this.add(helpMenu);
        }
    }

    private static KeyListener enterPressedListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                System.out.println("123");
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                //System.out.println(wordTextField.getText());
                boolean isNew = true;
                boolean isOk = true;
                if (wordTextField.getText().equals("") || definitionTextField.getText().equals("")){
                    errorFrame2.setVisible(true);
                    isOk = false;
                }
                for (int i=0;i<dictionaryList.listModel.size();i++) {
                    if (dictionaryList.listModel.getElementAt(i).equals(wordTextField.getText()) && isOk){
                        errorFrame.setVisible(true);
                        isNew = false;
                    }
                }
                if (isNew && isOk) {
                    dictionaryList.listModel.addElement(wordTextField.getText());
                    wordTextField.setText("");
                    definitionTextField.setText("");
                    contextTextArea.setText("");
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };
}
