package comands;

import collectionClasses.*;
import main.Connect;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class addIfMax {
    String nameEnter="";
    long xEnter=-1, yEnter=-1;
    float minimalPointEnter=-1;
    double personalQalityEnter=-1;
    Difficulty dificultyEnter;
    String authornameEnter="", authorbirthEnter="";
    float heightEnter=0;
    Country nationEnter;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Connect connect = new Connect();
    public void compare() throws IOException {
            System.out.println("Добавление нового элемента в коллекцию:");
            connect.sendComand("add_if_max");
            System.out.println("Введите имя:");
            while(nameEnter.isEmpty()){
                nameEnter=Scan("str");
                if(nameEnter.isEmpty()) System.err.println("Ошибка: Пустая строка");
            }

            System.out.println("Введите координату X (тип Long, больше или равно 0):");
            while(xEnter<=0){
                xEnter=Long.parseLong(Scan("long"));
            }

            System.out.println("Введите координату Y (тип Long, больше или равно 0):");
            while(yEnter<0){
                yEnter=Long.parseLong(Scan("long"));
            }

            System.out.println("Введите значение Minimal Point (тип Float, больше или равно 0):");
            while(minimalPointEnter<0){
                minimalPointEnter=Float.parseFloat(Scan("float"));
            }

            System.out.println("Введите значение Personal Qualities Minimum (тип Double, больше или равно 0):");
            while(personalQalityEnter<0){
                personalQalityEnter=Double.parseDouble(Scan("double"));
            }

            boolean exists = true;
            System.out.println("Введите значение сложности (NORMAL, HARD, IMPOSSIBLE, TERRIBLE):");
            while(exists) {
                exists = false;
                String wtfDifficulty = Scan("str");
                try {
                    Difficulty.valueOf(wtfDifficulty);
                } catch (IllegalArgumentException e) {
                    exists = true;
                }
                if (!exists) {
                    dificultyEnter = Difficulty.valueOf(wtfDifficulty);
                } else {
                    System.err.println("Ошибка: введённого значения не существует");
                }
            }

            System.out.println("Введите своё имя (автора):");
            while(authornameEnter.isEmpty()){
                authornameEnter=Scan("str");
                if(authornameEnter.isEmpty()) System.err.println("Ошибка: Пустая строка");
            }

            System.out.println("Вводим свою дату рождения (автора):");
            System.out.println("Введите год (1900-2019):");
            int yearBA=0;
            while (yearBA<1900 || yearBA>=2020){
                yearBA=Integer.parseInt(Scan("int"));
            }
            authorbirthEnter=yearBA+"-";

            System.out.println("Введите месяц (1-12):");
            int monthBA=0;
            while (monthBA>=13 || monthBA<=0){
                monthBA=Integer.parseInt(Scan("int"));
            }
            if(monthBA<10) authorbirthEnter=authorbirthEnter+"0"+monthBA+"-"; else authorbirthEnter=authorbirthEnter+monthBA+"-";

            System.out.println("Введите день (1-31):");
            int dayBA=0;
            while (dayBA>=32 || dayBA<=0){
                dayBA=Integer.parseInt(Scan("int"));
            }
            if(dayBA<10) authorbirthEnter=authorbirthEnter+"0"+dayBA; else authorbirthEnter=authorbirthEnter+dayBA;

            LocalDate birthday = LocalDate.parse(authorbirthEnter, formatter);
            System.out.println("Введите свой рост (автора, тип Float, больше 0, меньше 3):");
            while(heightEnter<=0){
                heightEnter=Float.parseFloat(Scan("float"));
            }

            exists = true;
            System.out.println("Введите свою национальность (FRANCE, INDIA, ITALY, NORTH_KOREA):");
            while(exists) {
                exists = false;
                String wtfNationality = Scan("str");
                try {
                    Country.valueOf(wtfNationality);
                } catch (IllegalArgumentException e) {
                    exists = true;
                }
                if (!exists) {
                    nationEnter = Country.valueOf(wtfNationality);
                } else {
                    System.err.println("Ошибка: введённого значения не существует");
                }
            }

            ZonedDateTime creationDate = ZonedDateTime.now();

            LabWork lw = new LabWork(1, nameEnter, new Coordinates(xEnter, yEnter), creationDate, minimalPointEnter, personalQalityEnter, dificultyEnter, new Person(authornameEnter, birthday, heightEnter, nationEnter));

            connect.sendObj(lw);

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
