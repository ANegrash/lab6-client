package comands;

import main.Connect;

import java.io.IOException;

public class updateId {

    Connect connect = new Connect();
    public void update() throws IOException {
        connect.sendComand("update_id");


    }
}
