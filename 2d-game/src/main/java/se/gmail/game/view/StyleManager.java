package se.gmail.game.view;


import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StyleManager {

    // Default font definitions
    public static final Font TITLE_FONT = new Font("serif", Font.BOLD, 24);
    public static final Font LABEL_FONT = new Font("serif", Font.PLAIN, 14);
    public static final Font BUTTON_FONT = new Font("serif", Font.BOLD, 16);
    public static final Font TEXT_FIELD_FONT = new Font("serif", Font.PLAIN, 14);

    // Default color definitions
    public static final Color PRIMARY_COLOR = new Color(0, 122, 255); // Blue
    public static final Color SECONDARY_COLOR = new Color(255, 255, 255); // White
    public static final Color BACKGROUND_COLOR = new Color(150, 150, 150); // Light gray
    public static final Color ERROR_COLOR = new Color(255, 0, 0); // Red
    
    // Add additional styles as needed
    public static final Border DEFAULT_BORDER = BorderFactory.createLineBorder(Color.GRAY, 1);

    // Utility method to set component styles
    public static void applyTitleStyle(JLabel label) {
        label.setFont(TITLE_FONT);
        label.setForeground(PRIMARY_COLOR);
    }

    public static void applyLabelStyle(JLabel label) {
        label.setFont(LABEL_FONT);
        label.setForeground(Color.BLACK);
    }

    public static void applyButtonStyle(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(SECONDARY_COLOR);
        button.setBorder(DEFAULT_BORDER);
    }

    public static void applyTextFieldStyle(JTextField textField) {
        textField.setFont(TEXT_FIELD_FONT);
        textField.setForeground(Color.BLACK);
        textField.setBackground(SECONDARY_COLOR);
        textField.setBorder(DEFAULT_BORDER);
    }

    // Utility methods for other common styles can be added here
}
