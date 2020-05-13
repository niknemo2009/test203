package lab4;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class GUI extends JFrame{
    private JLabel goalLabel = new JLabel("Create your goal:");
    private JLabel showActionLable = new JLabel("Your Actions:");
    private JLabel actionInfoLable = new JLabel("");
    private JTextField actionNameTextField = new JTextField("Name");
    private JTextField actionTimeTextField = new JTextField("Time");
    private JTextField goalTextField = new JTextField("");
    private JTextField editActionNameTextField = new JTextField("Edit action name:");
    private JTextField editActionTimeTextField = new JTextField("Edit action time:");
    private JTextField editActionIndexTextField = new JTextField("Edit action at index:");
    private JTextField deleteActionIndexTextField = new JTextField("Delete action at index:");
    private JButton goalButton = new JButton("Add goal");
    private JButton actionButton = new JButton("Add action");
    private JButton editActionButton = new JButton("Edit action");
    private JButton deleteActionButton = new JButton("Delete action");
    private JButton deleteGoalButton = new JButton("Delete goal");
    private JComboBox goalsBox = new JComboBox();


    private String goalName;
    private String actionName;
    private double actionTime;
    private String oldGoalName;
    private String editedActionName;
    private double editedActionTime;
    Map<String, Goals> goalsMap = new HashMap<>();

    public GUI() {
        super("lab4");
        this.setBounds(100, 100, 250, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(0, 3));
        container.add(goalLabel);
        container.add(goalTextField);
        container.add(goalButton);
        container.add(goalsBox);
        container.add(deleteGoalButton);
        container.add(new JLabel(""));
        container.add(showActionLable);
        container.add(actionInfoLable);
        container.add(new JLabel(""));
        actionNameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                actionNameTextField.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {
                //do nothing
            }
        });
        container.add(actionNameTextField);
        actionTimeTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                actionTimeTextField.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        container.add(actionTimeTextField);
        container.add(actionButton);
        editActionNameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editActionNameTextField.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        container.add(editActionNameTextField);
        editActionTimeTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editActionTimeTextField.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        container.add(editActionTimeTextField);
        editActionIndexTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                editActionIndexTextField.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        container.add(editActionIndexTextField);
        container.add(editActionButton);
        container.add(new JLabel(""));
        container.add(new JLabel(""));
        deleteActionIndexTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                deleteActionIndexTextField.setText(null);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        container.add(deleteActionIndexTextField);
        container.add(deleteActionButton);
        deleteGoalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goalsMap.remove(goalsBox.getSelectedItem());
                goalsBox.removeItem(goalsBox.getSelectedItem());
            }
        });
        goalButton.addActionListener(new GButtonEventListener());
        goalsBox.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                if(goalsMap.get(cb.getSelectedItem()).getAct().size() != 0) {
                    actionInfoLable.setText(goalsMap.get(cb.getSelectedItem()).getAct().toString());
                }
                else
                {
                    actionInfoLable.setText("Your actions list is empty! Add something.");
                }
            }
        });
        actionButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionName = actionNameTextField.getText();
                actionTime = Double.parseDouble(actionTimeTextField.getText());
                goalsMap.get(goalsBox.getSelectedItem()).add_action(new Actions(actionName, actionTime));

            }
        });
        editActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editedActionName = editActionNameTextField.getText();
                editedActionTime = Double.parseDouble(editActionTimeTextField.getText());
                int editIndex = Integer.parseInt(editActionIndexTextField.getText());
                goalsMap.get(goalsBox.getSelectedItem()).edit_action(editIndex, editedActionName, editedActionTime);
            }
        });
        deleteActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int deleteActionIndex = Integer.parseInt(deleteActionIndexTextField.getText());
                String deleteMessage = "";
                goalsMap.get(goalsBox.getSelectedItem()).remove_action(deleteActionIndex);
                deleteMessage += "Action at index " + deleteActionIndex + " successfully deleted.";
                JOptionPane.showMessageDialog(null, deleteMessage, "Delete info", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }

    class GButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            goalName = goalTextField.getText();
//                actionName = actionNameTextField.getText();
//                actionTime = Double.parseDouble(actionTimeTextField.getText());
            //System.out.println(oldGoalName + " " + goalName);
            if(!goalName.equals(oldGoalName)) {
                goalsMap.put(goalName, new Goals(goalName));
                goalsBox.addItem(goalName);
            }
            //goalsMap.get(goalName).add_action(new Actions(actionName, actionTime));
            //System.out.println(goalsMap);
            //goalsMap.get(goalName).show_goal_actions();
            //oldActionName = actionName;
            oldGoalName = goalName;
        }
    }
}