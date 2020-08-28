package com.crossyf.dubbo.springtest.test;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatReceive {
    public static void main(String[] args) throws Exception {

        DatagramSocket datagramSocket = new DatagramSocket(6666);

        while (true){
            byte[] container = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(container,0,container.length);
            datagramSocket.receive(datagramPacket);

            byte[] data = datagramPacket.getData();

            System.out.println(new String(data, 0, data.length));
            if (new String(data, 0, data.length).equals("bye")){
                System.out.println("退出聊天...");
                break;
            }
        }

        datagramSocket.close();
    }
}
