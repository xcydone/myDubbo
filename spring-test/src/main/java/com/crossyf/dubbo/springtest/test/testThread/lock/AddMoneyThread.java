package com.crossyf.dubbo.springtest.test.testThread.lock;

public class AddMoneyThread implements Runnable{
    private Bank2 bank;
    private double money;

    public AddMoneyThread(Bank2 bank,double money){
        this.bank = bank;
        this.money = money;
    }

    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            bank.addAmount(money);
        }
    }
}
