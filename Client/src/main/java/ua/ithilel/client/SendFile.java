package ua.ithilel.client;

import java.io.*;

public class SendFile {


    public void sendClientFile(String namePath, DataOutputStream os) {
        File fileClient = new File(namePath);
        String str;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileClient))) {
            while ((str = bufferedReader.readLine()) != null) {
                os.writeUTF(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
