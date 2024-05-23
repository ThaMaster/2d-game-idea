package se.gmail.game.view;

import javax.swing.ImageIcon;

public class GuiUtil {

    public static String createStockToolTipText(ImageIcon icon, String stockName, String desc) {
        return "<html><body><img src='" + icon + "' width='50' height='50'><br><b>" + stockName + "<hr>" + desc + "</body></html>";
    }
}
