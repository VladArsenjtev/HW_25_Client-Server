package ua.ithilel.server.protocol;

import java.util.ArrayList;
import java.util.List;

public class DataBaseClient {
    private final List<ClientInfo> clientInfo = new ArrayList<>();

    public void addClientDataBase(ClientInfo str) {
        clientInfo.add(str);
    }

    public List<ClientInfo> getClientInfo() {
        return clientInfo;
    }

    @Override
    public String toString() {
        return "DataBaseClient{" +
                "clientInfo=" + clientInfo +
                '}';
    }
}
