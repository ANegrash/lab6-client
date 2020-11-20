package comands;

import main.Connect;

import java.io.IOException;

public class info {
    Connect connect = new Connect();
    public void getInfo() throws IOException {
        connect.sendComand("info");
    }
}
