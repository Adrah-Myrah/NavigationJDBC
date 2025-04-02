import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;

public class NavigationDemo {
    JFrame frame1, frame2;
     DefaultListModel listModel;
    JList<String> recordList;
    JTextField fullNameField, dateOfBirthField, nationalityField;
    JComboBox<String> genderComboBox, countryComboBox, statusComboBox;
    JTextArea feedbackTextArea;

    static String fullName;

    static String date_of_birth;
    static String nationality;
    static String sex;
    static String country;
    static String marital_status;
    static String feedback;

     {
        this.prepareTheProgram();
    }

    
    public void prepareTheProgram(){
        createDataEntryFrame();
    }

    public JFrame createDataEntryFrame() {
        frame1 = new JFrame("Data Entry");
        frame1.setSize(400, 400);
        frame1.setLayout(new BorderLayout());
        frame1.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        JPanel formPanelContainer = new JPanel();
        formPanelContainer.setLayout(new GridBagLayout());
        formPanelContainer.setBackground(Color.decode("#87CEEB"));
        formPanelContainer.add(createFormPanel());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.decode("#87CEEB"));
        centerPanel.add(formPanelContainer, BorderLayout.CENTER);

        JPanel buttonPanelContainer = new JPanel();
        buttonPanelContainer.setLayout(new FlowLayout());
        buttonPanelContainer.setBackground(Color.decode("#87CEEB"));
        buttonPanelContainer.add(createButtonPanel());

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanelContainer, BorderLayout.SOUTH);

        frame1.add(mainPanel, BorderLayout.CENTER);
        frame1.setVisible(true);
        return frame1;
    }

    public JPanel createFormPanel() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2));
        formPanel.add(new JLabel("Full Name:"));
        fullNameField = new JTextField();
        formPanel.add(fullNameField);
        formPanel.add(new JLabel("Date of Birth:"));
        dateOfBirthField = new JTextField();
        formPanel.add(dateOfBirthField);
        formPanel.add(new JLabel("Nationality:"));
        nationalityField = new JTextField();
        formPanel.add(nationalityField);
        String[] gender = {"Male", "Female"};
        formPanel.add(new JLabel("Sex:"));
        genderComboBox = new JComboBox<>(gender);
        formPanel.add(genderComboBox);
        String[] country = {"Uganda", "Kenya","southsudan", "Tanzania", "Rwanda", "Congo", "Burundi"};
        formPanel.add(new JLabel("Country:"));
        countryComboBox = new JComboBox<>(country);
        formPanel.add(countryComboBox);
        String[] status = {"Single", "Married", "Widowed","taken for granted","single for life"};
        formPanel.add(new JLabel("Marital Status:"));
        statusComboBox = new JComboBox<>(status);
        formPanel.add(statusComboBox);
        formPanel.add(new JLabel("Feedback:"));
        feedbackTextArea = new JTextArea(5, 10);
        formPanel.add(feedbackTextArea);
        formPanel.setBackground(Color.BLACK);
        formPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        formPanel.setPreferredSize(new Dimension(600, 400));

        return formPanel;
    }

    public JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 fullName = fullNameField.getText();
                date_of_birth = dateOfBirthField.getText();
                 nationality = nationalityField.getText();
                 sex = (String) genderComboBox.getSelectedItem();
                country = (String) countryComboBox.getSelectedItem();
                marital_status = (String) statusComboBox.getSelectedItem();
                feedback = feedbackTextArea.getText();
                String record = "Full Name: " + fullName + "\nDate of Birth: " + date_of_birth + "\nNationality: " + nationality + "\nSex: " + sex + "\nCountry: " + country + "\nMarital Status: " + marital_status + "\nFeedback: " + feedback;
                userData.saveData();
                listModel = new DefaultListModel();
                listModel.addElement(record);
                createRecordsFrame().setVisible(true);
                frame1.setVisible(false);
            }
        });
        buttonPanel.add(submitButton);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return buttonPanel;
    }

       public JFrame createRecordsFrame() {
        frame2 = new JFrame("Records");
        frame2.setSize(400, 400);
        frame2.setLayout(new BorderLayout());
        frame2.setLocationRelativeTo(null);

        recordList = new JList<>(listModel);
        recordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel recordsPanelContainer = new JPanel();
        recordsPanelContainer.setLayout(new BorderLayout());
        recordsPanelContainer.add(new JScrollPane(recordList), BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.add(recordsPanelContainer, BorderLayout.CENTER);

        JPanel buttonPanelContainer = new JPanel();
        buttonPanelContainer.setLayout(new FlowLayout());

        JButton deleteButton = new JButton("Delete");
           deleteButton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   int selectedIndex = recordList.getSelectedIndex();
                   if (selectedIndex != -1) {
                       NavigationDemo.this.listModel.remove(selectedIndex);
                   }
               }
           });
        buttonPanelContainer.add(deleteButton);

        JButton navigateButton = new JButton("Navigate to Data Entry");
        navigateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDataEntryFrame().setVisible(true);
                frame2.setVisible(false);
            }
        });
        buttonPanelContainer.add(navigateButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanelContainer, BorderLayout.SOUTH);

        frame2.add(mainPanel, BorderLayout.CENTER);
        frame2.setVisible(true);
        return frame2;
    }


}
