package com.awt;

import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {
    // Create components
    TextField display;
    Button[] numberButtons = new Button[10];
    Button addButton, subButton, mulButton, divButton, eqButton, clrButton;
    Panel panel;

    // Variables to hold the values and operator
    String operator = "";
    double num1, num2, result;

    public Calculator() {
        // Create the frame
        setTitle("Calculator");
        setSize(400, 500);
        setLayout(new BorderLayout());

        // Create display
        display = new TextField();
        display.setFont(new Font("Arial", Font.PLAIN, 64));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        // Create buttons
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }
        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        eqButton = new Button("=");
        clrButton = new Button("C");

        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        eqButton.addActionListener(this);
        clrButton.addActionListener(this);

        // Add buttons to panel
        panel = new Panel();
        panel.setLayout(new GridLayout(4, 4, 15, 15));
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(clrButton);
        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);

        add(panel, BorderLayout.CENTER);

        // Window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            display.setText(display.getText() + command);
        } else if (command.equals("+")) {
            num1 = Double.parseDouble(display.getText());
            operator = "+";
            display.setText("");
        } else if (command.equals("-")) {
            num1 = Double.parseDouble(display.getText());
            operator = "-";
            display.setText("");
        } else if (command.equals("*")) {
            num1 = Double.parseDouble(display.getText());
            operator = "*";
            display.setText("");
        } else if (command.equals("/")) {
            num1 = Double.parseDouble(display.getText());
            operator = "/";
            display.setText("");
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
            }

            display.setText(String.valueOf(result));
            operator = "";
        } else if (command.equals("C")) {
            display.setText("");
            operator = "";
            num1 = num2 = result = 0;
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
