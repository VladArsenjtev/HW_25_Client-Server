package ua.ithilel.server.protocol;

import java.net.Socket;

public class ClientInfo {
    private String nameClient;
    private String time;
    private Socket socket;

    public ClientInfo(String nameClient, String time, Socket socket) {
        this.nameClient = nameClient;
        this.time = time;
        this.socket = socket;
    }

    public String getNameClient() {
        return nameClient;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public String toString() {
        return "ClientInfo{" +
                "nameClient='" + nameClient + '\'' +
                ", time='" + time + '\'' +
                ", socket=" + socket +
                '}';
    }
}
