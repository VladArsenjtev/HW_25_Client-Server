package ua.ithilel.server.protocol;

import java.io.DataOutputStream;
import java.io.IOException;

public class AddSend {
    public void sendToAll(DataBaseClient dataBaseClient) throws IOException {
        for (int i = 0; i < dataBaseClient.getClientInfo().size(); i++) {
            DataOutputStream in = new DataOutputStream(dataBaseClient.
                    getClientInfo().get(i).
                    getSocket().getOutputStream());
            in.writeUTF(dataBaseClient.getClientInfo().get(dataBaseClient.getClientInfo().size() - 1).getNameClient() + " подключился");
        }
    }
}
