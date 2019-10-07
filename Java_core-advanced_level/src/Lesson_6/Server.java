package Lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Server {
    private ServerSocket server = null;
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Сервер запущен!");
            socket = server.accept();
            System.out.println("Клиент подключился!");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            //поток ввода с консоли
            Thread consoleInputThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Введите сообщение");
                    while (true) {
                        try {
                            Scanner inScanner = new Scanner(System.in);
                            String msg = inScanner.nextLine();
                            out.writeUTF(msg);

                            if (msg.equals("/end")) {
                                System.out.println("Поток ввода с консоли завершен!");
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            //поток ожидания по сети
            Thread receiveNetworkThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String msg = in.readUTF();
                            //пришло с клиента, добавляб маску
                            System.out.println("Client: " + msg);

                            if (msg.equals("/end")) {
                                //отправляю клиенту для завершения работы  потока приема по сети
                                out.writeUTF("/end");
                                System.out.println("Поток ожидания по сети завершен!");
                                break;
                            }

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            consoleInputThread.setDaemon(true);
            consoleInputThread.start();
            receiveNetworkThread.start();

            try {
                //ставлю Daemon = true первому потоку, а join - второму, чтобы таким образом реализвовать корректный выход из обоих потоков по команде /end
                receiveNetworkThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
            socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
                System.out.println("Сервер отключен!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
