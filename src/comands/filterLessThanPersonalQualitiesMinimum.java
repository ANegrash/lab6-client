package comands;

import main.Connect;

import java.io.IOException;
import java.util.Scanner;

public class filterLessThanPersonalQualitiesMinimum {
    Connect connect = new Connect();
    double minimalPointEnter=-1;

    public void doFilter() throws IOException {
        connect.sendComand("filter_less_than_personal_qualities_minimum");
        minimalPointEnter=-1;

        while(minimalPointEnter<0){
            minimalPointEnter=Float.parseFloat(Scan("double"));
        }

        connect.sendPQMToFilter(minimalPointEnter);
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
