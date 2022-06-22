package ua.ithilel.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static final Logger log = LogManager.getLogger(Client.class);
    SendFile sendFile = new SendFile();

    public Client() {
        log.info("Клиент запускается...");
        try (Socket socket = new Socket("localhost", 8080);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());) {
            log.info("Клиент подключился к серверу.");


            while (!socket.isOutputShutdown()) {
                if (ois.available() != 0) {
                    String in = ois.readUTF();
                    System.out.println(in);
                }

                if (br.ready()) {
                    String clientCommand = br.readLine();
                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("Client sent message " + clientCommand + " to server.");

                    if (clientCommand.equalsIgnoreCase("-exit")) {
                        System.out.println("Client kill connections");
                        if (ois.available() != 0) {
                            System.out.println("reading...");
                            String in = ois.readUTF();
                            System.out.println(in);
                        }
                        break;
                    }
                    if (clientCommand.contains("-file")) {
                        String[] split = clientCommand.split(" ");
                        String pathName = split[1];
                        oos.writeUTF(clientCommand);
                        sendFile.sendClientFile(pathName, oos);
                    }

                    System.out.println("Client sent message & start waiting for data from server...");

                    if (ois.available() != 0) {
                        String in = ois.readUTF();
                        System.out.println(in);
                    }
                }
            }
            System.out.println("Closing connections & channels on clientSide - DONE.");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}