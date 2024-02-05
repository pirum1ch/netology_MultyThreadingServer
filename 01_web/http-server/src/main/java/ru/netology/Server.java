package ru.netology;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {

    private final int port = 9999;
    private boolean isRunning = true;
    private ExecutorService threadPool = Executors.newFixedThreadPool(64);

    public void startServer() {
        try (final var serverSocket = new ServerSocket(port)) {
            while (isRunning) {
                Socket socket = serverSocket.accept();
                threadPool.execute(new ResponceHandler(socket));
                System.out.println(Thread.currentThread().getName());
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

}
