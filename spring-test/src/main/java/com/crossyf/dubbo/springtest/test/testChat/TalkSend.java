package com.crossyf.dubbo.springtest.test.testChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class TalkSend implements Runnable {

    DatagramSocket datagramSocket = null;
    BufferedReader bufferedReader = null;

    private  int fromPort;
    private String toIP;
    private int toPort;

    public TalkSend(int fromPort,String toIP,int toPort){

        this.fromPort = fromPort;
        this.toIP = toIP;
        this.toPort = toPort;

        try {
            datagramSocket = new DatagramSocket();
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入消息。。。");


        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public void run() {
        while (true){

            try {
              String  data = bufferedReader.readLine();
              byte[] datas = data.getBytes();
              DatagramPacket datagramPacket = new DatagramPacket(datas,0,datas.length,new InetSocketAddress(this.toIP,this.toPort));
              datagramSocket.send(datagramPacket);
                if (data.equals("bye")){
                    System.out.println("退出聊天...");
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        datagramSocket.close();
    }
}
