package se.gmail.game.model;

public class User {
    private static double userMoney = 0.0;

    private static String userName;

    public User(String name) {
        userName = name;
    }

    public static double getMoney() {
        return userMoney;
    }
    public static void setMoney(double money) {
        userMoney = money;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String name) {
        userName = name;
    }

}
