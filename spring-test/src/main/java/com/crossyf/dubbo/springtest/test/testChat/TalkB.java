package com.crossyf.dubbo.springtest.test.testChat;

public class TalkB {
    public static void main(String[] args) {
        new Thread(new TalkSend(7777,"132.126.3.54",9999)).start();
        new Thread(new TalkReceive(8888,"蔡")).start();
    }
}
