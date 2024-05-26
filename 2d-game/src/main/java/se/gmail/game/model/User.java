package se.gmail.game.model;


public class User {

    private double userMoney;
    private String userName;

    public User(String name) {
        this.userMoney = 10000.0;
        this.userName = name;
    }

    public double getMoney() {
        return this.userMoney;
    }
    
    public void setMoney(double money) {
        this.userMoney = money;
    }
    
    public boolean canAfford(double money) {
        return (this.userMoney >= money);
    }

    public void decreaseMoney(double money) {
        this.userMoney -= money;
    }

    public void increaseMoney(double money) {
        this.userMoney += money;
    }
    
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

}
