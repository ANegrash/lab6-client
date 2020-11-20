package comands;

import main.Connect;

import java.io.IOException;

public class clear {
    Connect connect = new Connect();
    public void doClean() throws IOException {
        connect.sendComand("clear");
    }

}
