package comands;

import main.Connect;

import java.io.IOException;

public class show {
    Connect connect = new Connect();
    public void showCollection() throws IOException {
        connect.sendComand("show");
        //connect.waitForResponse();
    }
}

