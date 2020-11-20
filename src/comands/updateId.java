package comands;

import main.Connect;

import java.io.IOException;
import java.util.Scanner;

public class updateId {
    Connect connect = new Connect();
    public boolean work=false;
    int idToRemove=0;
    public void update() throws IOException {
        connect.sendComand("update_id");

        idToRemove=0;
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
    public void vybor(boolean w) throws IOException {
        work=w;
        String pole;
        if(!work){
            System.out.println("Сначала введите поле");
            pole=Scan("str");
            switch (pole) {
                case "id":
                    System.err.println("У Вас недостаточно прав для изменения id");
                    break;
                case "name":
                    System.out.print("На что меняем: ");
                    connect.sendComand("name:"+Scan("str")+":");
                    break;
                case "coordinates.x":
                    System.out.print("На что меняем: ");
                    connect.sendComand("coordinates.x:"+Scan("long")+":");

                    break;
                case "coordinates.y":
                    System.out.print("На что меняем: ");
                    connect.sendComand("coordinates.y:"+Scan("long")+":");

                    break;
                case "creationDate":
                    System.err.println("У Вас недостаточно прав для изменения даты создания (creationDate)");
                    break;
                case "minimalPoint":
                    System.out.print("На что меняем: ");
                    connect.sendComand("minimalPoint:"+Scan("float")+":");
                    break;
                case "personalQualitiesMinimum":
                    System.out.print("На что меняем: ");
                    connect.sendComand("personalQualitiesMinimum:"+Scan("double")+":");

                    break;
                case "difficulty":
                    System.out.print("На что меняем: ");
                    connect.sendComand("difficulty:"+Scan("str")+":");

                    break;
                case "author.name":
                    System.out.print("На что меняем: ");
                    connect.sendComand("author.name:"+Scan("str")+":");

                    break;
                case "author.birthday":
                    System.out.print("На что меняем: ");
                    connect.sendComand("author.birthday:"+Scan("str")+":");
                    break;
                case "author.height":
                    System.out.print("На что меняем: ");
                    connect.sendComand("author.height:"+Scan("float")+":");
                    break;
                case "author.nationality":
                    System.out.print("На что меняем: ");
                    connect.sendComand("author.nationality:"+Scan("str")+":");
                    break;
                default:
                    System.err.println("Такого поля не существует");
                    break;
            }
        }
    }


    }
