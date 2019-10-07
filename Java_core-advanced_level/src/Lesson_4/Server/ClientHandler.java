package Lesson_4.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MainServ serv;
    private String nick;

    ClientHandler(MainServ serv, Socket socket) {
        try {
            this.socket = socket;
            this.serv = serv;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            String str = in.readUTF();
                            if (str.startsWith("/auth")){
                                String[] tokens = str.split(" ");
                                String currentNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (currentNick!=null){
                                    sendMessage("/authok");
                                    nick = currentNick;
                                    serv.subscribe(ClientHandler.this);
                                    break;
                                }else {
                                    sendMessage("неверный логин/пароль");
                                }
                            }
                        }

                        while (true){
                            String msg = in.readUTF();
                            if (msg.equalsIgnoreCase("/end")) {
                                sendMessage("/clientClose");
                                break;
                            }
                            //выходить из потока нужно по закрытию окна клиента, но реализовать не смог :-(
                            //if (msg.equalsIgnoreCase("/end")) break;
                            serv.broadcastMessage(nick + ": " +msg);
                        }
                    }catch (IOException | SQLException e){
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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    serv.unsubscribe(ClientHandler.this);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessage(String msg){
        try {
            System.out.println(msg);
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
