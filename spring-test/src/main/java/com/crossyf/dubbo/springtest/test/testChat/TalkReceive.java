package com.crossyf.dubbo.springtest.test.testChat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TalkReceive implements Runnable {

    DatagramSocket datagramSocket = null;
    private int port;
    private String msgFrom;

    public  TalkReceive(int port,String msgFrom){
         this.port = port;
         this.msgFrom = msgFrom;
        try {
            datagramSocket = new DatagramSocket(port);

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public void run() {

        while (true){

            try {

                byte[] container = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(container,0,container.length);
                datagramSocket.receive(datagramPacket);

                byte[] data = datagramPacket.getData();
                String datas = new String(data, 0, data.length);
                System.out.println(this.msgFrom+": " +datas);
                if (new String(data, 0, data.length).equals("bye")){
                    System.out.println("退出聊天...");
                    break;
                }
            }catch (Exception e){

            }

        }

        datagramSocket.close();

    }
}
