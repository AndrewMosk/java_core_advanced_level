package Lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class Client {
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    Client(String server, int port) {
        try {
            socket = new Socket(server, port);
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
                            //пришло с сервера, добавляю маску
                            System.out.println("Server: " + msg);

                            if (msg.equals("/end")) {
                                //отправляю серверу для завершения работы потока приема по сети
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
        }finally {
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
                System.out.println("Клиент отключен!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
