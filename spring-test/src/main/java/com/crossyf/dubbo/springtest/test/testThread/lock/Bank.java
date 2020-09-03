package com.crossyf.dubbo.springtest.test.testThread.lock;

public class Bank {
    private double amount;

    public Bank(double amount){
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public synchronized void addAmount(double money){
        System.out.println("当前金额为： "+amount);
        amount = amount + money;
        System.out.println("增加"+money+"后的金额为："+amount);
    }

    public synchronized void substract(double money){
        System.out.println("当前金额为： "+amount);
        amount = amount - money;
        System.out.println("减少"+money+"后的金额为："+amount);
    }
}
