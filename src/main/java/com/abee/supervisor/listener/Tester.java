package com.abee.supervisor.listener;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class Tester {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 6; i++) {
            DatagramSocket datagramSocket = new DatagramSocket(6643 + i);
            byte[] buffer = "ACT;1234567890".getBytes();

            DatagramPacket packet = new DatagramPacket(
                    buffer, buffer.length,
                    InetAddress.getByName("localhost"), 12334);
            datagramSocket.send(packet);
        }

        //DatagramPacket rPacket = new DatagramPacket(buffer, buffer.length);
        //datagramSocket.receive(rPacket);
        //System.out.println(Arrays.toString(rPacket.getData()));
        //System.out.println(rPacket.getAddress() + ":" + rPacket.getPort());
    }
}
