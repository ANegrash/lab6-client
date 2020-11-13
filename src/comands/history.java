package comands;

import main.Connect;

import java.io.IOException;

public class history {
    Connect connect = new Connect();
    public void getHistory() throws IOException {
        connect.sendComand("history");
        connect.waitForResponse();
    }
}
