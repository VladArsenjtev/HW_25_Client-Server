package ua.ithilel.server.protocol;

public class CreationClientName {
    private static int count = 0;

    public String name() {
        count++;
        return "Client " + count;
    }
}
