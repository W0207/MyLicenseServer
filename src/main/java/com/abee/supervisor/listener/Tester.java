package com.abee.supervisor.listener;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

public class Tester {

    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket(12555);
        byte[] buffer = "ACT;1234567890".getBytes();

        DatagramPacket packet = new DatagramPacket(
                buffer, buffer.length,
                InetAddress.getByName("localhost"), 12334);
        datagramSocket.send(packet);
        datagramSocket.receive(packet);
        System.out.println(Arrays.toString(packet.getData()));
    }
}
