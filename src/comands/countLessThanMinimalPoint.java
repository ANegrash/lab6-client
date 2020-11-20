package comands;

import main.Connect;

import java.io.IOException;
import java.util.Scanner;

public class countLessThanMinimalPoint {
    Connect connect = new Connect();
    float minimalPointEnter=-1;

    public void counting() throws IOException {
        connect.sendComand("count_less_than_minimal_point");
        minimalPointEnter=-1;

        while(minimalPointEnter<0){
            minimalPointEnter=Float.parseFloat(Scan("float"));
        }

        connect.sendMinimalPoint(minimalPointEnter);
    }
    public String Scan(String type){
        Scanner sc = new Scanner(System.in);
        switch (type){
            case "str": return sc.nextLine();
            case "long": if (sc.hasNextLong()) return String.valueOf(sc.nextLong()); else return "0";
            case "float": if (sc.hasNextFloat()) return String.valueOf(sc.nextFloat()); else return "0";
            case "double": if (sc.hasNextDouble()) return String.valueOf(sc.nextDouble()); else return "0";
            case "int": if (sc.hasNextInt()) return String.valueOf(sc.nextInt()); else return "0";
            default: return "";
        }

    }
}
