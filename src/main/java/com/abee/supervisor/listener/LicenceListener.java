package com.abee.supervisor.listener;

import com.abee.supervisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.*;

@WebListener
public class LicenceListener implements ServletContextListener {

    @Autowired
    private UserService userService;

    private final int port = 12334;

    private DatagramSocket socket;

    /**
     * Depends on the fixed size of {@Link com.abee.supervisor.listener.Message}
     */
    public static final int MESSAGE_SIZE = 14;

    private DatagramPacket packet;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            new Thread(new LicenceServer()).start();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private class LicenceServer implements Runnable {

        public LicenceServer() throws SocketException {
            socket = new DatagramSocket(port);
        }

        @Override
        public void run() {
            byte[] buffer = new byte[MESSAGE_SIZE];
            packet = new DatagramPacket(buffer, buffer.length);

            /**
             * Thread pool.
             */
            ExecutorService threadPool=new ThreadPoolExecutor(
                    2,5,
                    2L, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(32),
                    Executors.defaultThreadFactory(),
                    new ThreadPoolExecutor.AbortPolicy());

            while (true) {
                try {
                    synchronized (this) {
                        socket.receive(packet);
                        threadPool.execute(new LicenceHandler(packet));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class LicenceHandler implements Runnable {

        private String ip;

        private Integer port;

        private Message message;

        public LicenceHandler(DatagramPacket packet) {
            this.ip = packet.getAddress().getHostAddress();
            this.port = packet.getPort();
            this.message = Message.toMessage(packet.getData());
        }

        @Override
        public void run() {
            boolean success = false;
            switch (message.getType()) {
                case ACT:
                    success = userService.activate(ip, port, message.getSerialNumber());
                    break;
                case FOR:
                    success = userService.forbid(ip, port);
                    break;
                default:
            }

            byte[] result;
            if (success) {
                result = message.toByteArray();
            } else {
                result = new byte[MESSAGE_SIZE];
            }

            DatagramPacket resultPacket;
            try {
                resultPacket = new DatagramPacket(
                        result, result.length, InetAddress.getByName(ip), port
                );
                socket.send(resultPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
