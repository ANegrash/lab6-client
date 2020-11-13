package comands;

import main.Connect;

import java.io.IOException;
import java.util.Scanner;

public class removeById {
    Connect connect = new Connect();
    int idToRemove=0;

    public void removing() throws IOException {
        connect.sendComand("remove_by_id");

        System.out.println("Введите id элемента, который нужно удалить (целое число, больше 0):");
        while(idToRemove<=0){
            idToRemove=Integer.parseInt(Scan("int"));
        }

        connect.sendIdToRemove(idToRemove);
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
