package se.gmail.game.view;

public class GuiUtil {

    public static String createStockToolTipText(String stockName, String desc) {
        String toolTipString = 
            "<html><head><style>" +
            "body { width: 200px; }" +
            "p { word-wrap: break-word; }" +
            "</style></head><body>" +
            "<b>" + stockName + "</b><br>" + 
            "<p>" + desc + "</p>" + 
            "</body></html>";
        return toolTipString;
    }
}
