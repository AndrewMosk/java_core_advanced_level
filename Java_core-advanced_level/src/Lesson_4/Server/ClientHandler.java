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

    String getNick() {
        return nick;
    }

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
                                if (currentNick!=null) {
                                    if (serv.checkNick(currentNick)) {
                                        sendMessage("/authOk " + currentNick);
                                        nick = currentNick;
                                        serv.subscribe(ClientHandler.this);
                                        break;
                                    }else {
                                        sendMessage("Попытка повторного входа");
                                    }
                                }else {
                                    sendMessage("Ошибка аутентификаци");
                                }
                            }else if (str.startsWith("/reg")){
                                String[] tokens = str.split(" ");
                                boolean validRegData = AuthService.checkLoginAndNick(tokens[1], tokens[2]);
                                if (validRegData){
                                    if (AuthService.regNewUser(tokens[1], tokens[2], tokens[3])){
                                        sendMessage("Регистрация прошла успешно");
                                    }else {
                                        sendMessage("Регистрация закончилась неудачей");
                                    }
                                }else {
                                    sendMessage("Регистрация отклонена");
                                }
                            }
                        }

                        while (true){
                            String msg = in.readUTF();
                            if (msg.equalsIgnoreCase("/end")) {
                                sendMessage("/clientClose");
                                break;
                            }else if (msg.startsWith("/w")){
                                //сообщение конкретному клиенту
                                String[] tokens = msg.split(" ");
                                serv.singleMessage(buildMessage(tokens), nick, tokens[1]);
                            }else {
                                serv.broadcastMessage(nick + ": " + msg);
                            }
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

    private String buildMessage(String[] tokens){
        String result = "";
        for (int j = 2; j < tokens.length; j++) {
            result = result + tokens[j] + " ";
        }
        return result.substring(0, result.length() - 1);
    }

    void sendMessage(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
