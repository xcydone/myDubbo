package com.crossyf.dubbo.springtest.test.testThread.lock;

public class Bank2 {
    private double amount;

    public Bank2(double amount){
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void addAmount(double money){
        synchronized(this){
            System.out.println("当前金额为： "+amount);
            amount = amount + money;
            System.out.println("增加"+money+"后的金额为："+amount);
        }
    }

    public void substract(double money){
        synchronized(this){
            System.out.println("当前金额为： "+amount);
            amount = amount - money;
            System.out.println("减少"+money+"后的金额为："+amount);
        }
    }
}
