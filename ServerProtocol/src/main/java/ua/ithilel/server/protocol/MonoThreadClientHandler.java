package ua.ithilel.server.protocol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class MonoThreadClientHandler implements Runnable {

    private static Socket clientDialog;
    private static final Logger log = LogManager.getLogger(MonoThreadClientHandler.class);
    private SaveFile saveFile = new SaveFile();


    public MonoThreadClientHandler(Socket client) {
        clientDialog = client;
    }

    @Override
    public void run() {

        try (DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
             DataInputStream in = new DataInputStream(clientDialog.getInputStream())) {


            while (!clientDialog.isClosed()) {
                String entry = in.readUTF();
                if (entry.equalsIgnoreCase("-exit")) {
                    log.info("Клиент иницилиализирует выход");
                    out.writeUTF("Ответ сервера - " + entry + " - OK");
                    break;
                }
                if (entry.contains("-file")) {
                    saveFile.getFile(in);
                }

                out.writeUTF("Ответ сервера - " + entry + " - OK");
                log.info("Сервер написал клиенту.");
                out.flush();
            }
            log.info("Клиент отключается...");
            log.info("Закрытие соединений и каналов.");
            in.close();
            out.close();
            clientDialog.close();
            log.info("Закрытие соединений и каналов - ВЫПОЛНЕНО.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}