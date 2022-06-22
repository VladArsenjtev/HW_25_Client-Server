package ua.ithilel.server.protocol;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    private static final Logger log = LogManager.getLogger(Server.class);
    private AddSend addSend = new AddSend();
    private CreationClientName creationClientName = new CreationClientName();
    private DataBaseClient dataBaseClient = new DataBaseClient();
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm_dd.MM.yyyy");
    private String dateString = dateFormat.format(date);


    public Server() {
        try (ServerSocket server = new ServerSocket(8080)) {
            log.info("Поднят сервер");


            while (!server.isClosed()) {
                addSend.sendToAll(dataBaseClient);
                Socket client = server.accept();
                dataBaseClient.addClientDataBase(new ClientInfo(creationClientName.name(), dateString, client));
                executeIt.execute(new MonoThreadClientHandler(client));
                log.info("Клиент успешно подключился");
                System.out.println(dataBaseClient.getClientInfo());
            }
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}