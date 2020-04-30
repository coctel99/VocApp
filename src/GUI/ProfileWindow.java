package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfileWindow extends JFrame{
    private static final Dimension profileNameSize=new Dimension(200,20);
    private static final Dimension profileListSize=new Dimension(100,300);
    private static ProfileNameField profileNameField=new ProfileNameField();
    private static ProfileList profileList = new ProfileList();
    private static JFrame errorFrame = new JFrame();
    private static JFrame errorFrame2 = new JFrame();
    private static JFrame errorFrame3 = new JFrame();

    public ProfileWindow() {
        // Frame Preferences
        super("Profile window");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);
        this.setResizable(false);


        // Element preferences
        profileNameField.setPreferredSize(profileNameSize);
        profileList.setPreferredSize(profileListSize);

        // Container initialization
        final Container mainPanel=this.getContentPane();
        final JPanel westPanel=new JPanel();
        final JPanel eastPanel=new JPanel();
        final JPanel profileNamePanel=new JPanel();
        final JScrollPane profileListPanelScroll=new JScrollPane(profileList);
        final CreateButton createButton = new CreateButton();
        final SelectButton selectButton = new SelectButton();
        final DeleteButton deleteButton = new DeleteButton();

        // Container Preferences
        mainPanel.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        profileNamePanel.setLayout(new BoxLayout(profileNamePanel, BoxLayout.X_AXIS));
        profileNamePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        profileListPanelScroll.setPreferredSize(profileListSize);
        profileListPanelScroll.setViewportView(profileList);
        profileListPanelScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Container Placement
        mainPanel.add(Box.createHorizontalStrut(20));
        mainPanel.add(westPanel);
        mainPanel.add(Box.createHorizontalStrut(20));
        mainPanel.add(eastPanel);
        mainPanel.add(Box.createHorizontalStrut(20));

        westPanel.add(Box.createVerticalStrut(10));
        westPanel.add(profileNamePanel);
        westPanel.add(Box.createVerticalStrut(20));
        westPanel.add(profileListPanelScroll);
        westPanel.add(Box.createVerticalStrut(10));

        profileNamePanel.add(profileNameField);

        eastPanel.add(Box.createVerticalStrut(7));
        eastPanel.add(createButton);
        eastPanel.add(Box.createVerticalStrut(20));
        eastPanel.add(selectButton);
        eastPanel.add(Box.createVerticalStrut(10));
        eastPanel.add(deleteButton);

        errorFrame.add(new JLabel("Name field is empty"));
        errorFrame.setPreferredSize(new Dimension(200, 60));
        errorFrame.pack();
        errorFrame.setVisible(false);

        errorFrame2.add(new JLabel("Profile with that name already exists"));
        errorFrame2.pack();
        errorFrame2.setVisible(false);

        errorFrame3.add(new JLabel("This profile does not exist"));
        errorFrame3.pack();
        errorFrame3.setVisible(false);

        createButton.addActionListener(addListener);
        deleteButton.addActionListener(deleteListener);

        profileList.addMouseListener(mouseListener);

        this.pack();
    }

    private static class ProfileList extends JList{
        final DefaultListModel<String> listModel=new DefaultListModel();
        ProfileList(){
            super();
            //final DefaultListModel<String> listModel=new DefaultListModel();
            for(int i=0;i<5;i++){
                listModel.addElement("Profile"+i);
            }
            this.setModel(listModel);
        }
    }

    public static class ProfileNameField extends JTextField{
        ProfileNameField(){
            super();
        }
    }


    public static class CreateButton extends JButton{

        CreateButton() {
            super();
            this.setText("Create");
        }
    }

    private class SelectButton extends JButton{
        SelectButton() {
            super();
            this.setText("Select");
        }
    }

    private class DeleteButton extends JButton{
        DeleteButton() {
            super();
            this.setText("Delete");
        }
    }

    private static ActionListener addListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            boolean isOk = true;
            boolean isNew = true;
            if(profileNameField.getText().equals("")){
                errorFrame.setVisible(true);
                isOk = false;
            }
            for (int i=0;i<profileList.listModel.size();i++){
                if(profileList.listModel.getElementAt(i).equals(profileNameField.getText()) && isOk){
                    errorFrame2.setVisible(true);
                    isNew = false;
                }
            }
            if (isOk && isNew) {
                profileList.listModel.addElement(profileNameField.getText());
                profileNameField.setText("");
            }
        }
    };

    private static ActionListener deleteListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            boolean isOk = true;
            boolean exists = false;
            if(profileNameField.getText().equals("")){
                errorFrame.setVisible(true);
                isOk = false;
            } else {
                for (int i = 0; i < profileList.listModel.size(); i++) {
                    if (profileList.listModel.getElementAt(i).equals(profileNameField.getText())) {
                        exists = true;
                    }
                }
                if(!exists){
                    errorFrame3.setVisible(true);
                }
            }
            if (isOk && exists) {
                for (int i=0;i<profileList.listModel.size();i++) {
                    if (profileList.listModel.getElementAt(i).equals(profileNameField.getText())) {
                        profileList.listModel.removeElementAt(i);
                        profileNameField.setText("");
                    }
                }
            }
        }
    };

    private static MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            profileNameField.setText(profileList.getSelectedValue().toString());
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };
}

