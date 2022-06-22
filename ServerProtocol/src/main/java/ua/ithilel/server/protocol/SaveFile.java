package ua.ithilel.server.protocol;

import java.io.*;

public class SaveFile {

    public void getFile(DataInputStream in) throws IOException {
        String str;
        try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter("ServerFile.txt"))) {
            while ((str = in.readUTF()) != null) {
                buffWrite.append(str);
                buffWrite.newLine();
            }
        }
    }
}