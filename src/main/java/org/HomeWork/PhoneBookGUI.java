package org.HomeWork;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class PhoneBookGUI extends JFrame {

    private Map<String, String> phoneBook = new HashMap<>();

    public PhoneBookGUI() {
        setTitle("Phone Book");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JTextArea outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);

        JButton findShortestNumberButton = new JButton("Find Shortest Number");
        findShortestNumberButton.addActionListener(e -> {
            String shortestNumber = findShortestNumber();
            outputTextArea.setText(phoneBook.get(shortestNumber) + " -> " + shortestNumber);
        });

        JButton findLongestNameButton = new JButton("Find Longest Name");
        findLongestNameButton.addActionListener(e -> {
            String longestName = findLongestName();
            List<String> phones = findPhonesByValue(longestName);
            StringBuilder sb = new StringBuilder();
            for (String phone : phones) {
                sb.append(longestName).append(" -> ").append(phone).append("\n");
            }
            outputTextArea.setText(sb.toString());
        });

        JTextField nameField = new JTextField();
        JTextField phoneField = new JTextField();
        JButton addContactButton = new JButton("Add Contact");
        addContactButton.addActionListener(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            phoneBook.put(phone, name);
            outputTextArea.setText("Contact added: " + name + " -> " + phone);
        });

        JButton deleteContactButton = new JButton("Delete Contact");
        deleteContactButton.addActionListener(e -> {
            String phone = JOptionPane.showInputDialog("Enter the phone number to delete:");
            if (phoneBook.containsKey(phone)) {
                String name = phoneBook.get(phone);
                phoneBook.remove(phone);
                outputTextArea.setText("Contact deleted: " + name + " -> " + phone);
            } else {
                outputTextArea.setText("Contact with phone number " + phone + " not found.");
            }
        });

        panel.add(findShortestNumberButton);
        panel.add(findLongestNameButton);
        panel.add(nameField);
        panel.add(phoneField);
        panel.add(addContactButton);
        panel.add(deleteContactButton);
        panel.add(scrollPane);

        add(panel);
    }

    private String findShortestNumber() {
        return Collections.min(phoneBook.keySet(), Comparator.comparing(String::length));
    }

    private String findLongestName() {
        return Collections.max(phoneBook.values(), Comparator.comparing(String::length));
    }

    private List<String> findPhonesByValue(String value) {
        List<String> phones = new ArrayList<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (value.equals(entry.getValue())) {
                phones.add(entry.getKey());
            }
        }
        return phones;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PhoneBookGUI phoneBookGUI = new PhoneBookGUI();
            phoneBookGUI.setVisible(true);
        });
    }
}
