package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfileWindow extends JFrame{
    private static final Dimension profileNameSize=new Dimension(200,20);
    private static final Dimension profileListSize=new Dimension(100,300);

    public ProfileWindow() {
        // Frame Preferences
        super("Profile window");
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        // Element initialization
        final ProfileNameField profileNameField = new ProfileNameField();
        final ProfileList profileList = new ProfileList();

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
        this.pack();
    }

    private static class ProfileList extends JList{
        final static DefaultListModel<String> listModel=new DefaultListModel();
        ProfileList(){
            super();
            //final DefaultListModel<String> listModel=new DefaultListModel();
            for(int i=0;i<5;i++){
                //listModel.addElement("Profile"+i);
                this.add("Profile"+i);
            }
            this.setModel(listModel);
        }

        public static void add(String name){
            listModel.addElement(name);
        }

        public static void remove(String name){
            listModel.removeElement(name);
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
            this.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProfileList.add("Test");
                }
            });
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
}

