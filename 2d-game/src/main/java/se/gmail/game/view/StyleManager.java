package se.gmail.game.view;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class StyleManager {

    public static Font DEFAULT_FONT;

    static {
        InputStream is = null;
        try {
            // Load the font from resources
            is = StyleManager.class.getResourceAsStream("/fonts/yoster.ttf");
            if (is == null) {
                throw new IOException("Font resource not found");
            }
            DEFAULT_FONT = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        } catch (Exception e) {
            e.printStackTrace();
            // Provide a fallback font or handle the exception as needed
            DEFAULT_FONT = new Font("Serif", Font.PLAIN, 12);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Default font definitions
    public static final Font TITLE_FONT = DEFAULT_FONT.deriveFont(Font.BOLD).deriveFont(24f);
    public static final Font LABEL_FONT = DEFAULT_FONT.deriveFont(Font.PLAIN).deriveFont(14f);
    public static final Font BUTTON_FONT = DEFAULT_FONT.deriveFont(Font.BOLD).deriveFont(16f);
    public static final Font TEXT_FIELD_FONT = DEFAULT_FONT.deriveFont(Font.PLAIN).deriveFont(14f);

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
