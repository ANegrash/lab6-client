package main;

import comands.*;

import java.io.IOException;
import java.util.Scanner;

public class Work {
    public Work() throws IOException {

        System.out.println("Добро пожаловать!");

        Connect connect = new Connect();
        connect.connectServer();

        help h = new help();
        addElement add = new addElement();
        addIfMax addifmax = new addIfMax();
        clear clean = new clear();
        countLessThanMinimalPoint cltmp = new countLessThanMinimalPoint();
        removeById rbi = new removeById();
        removeLower rl = new removeLower();
        updateId updId = new updateId();
        info inf = new info();
        history hst = new history();

        while (true){
            Scanner scanner = new Scanner(System.in);
            String comand = scanner.nextLine();
            switch (comand){
                case "help": System.out.println(h.Help());break;
                case "add": add.insertData();break;
                case "add_if_max": addifmax.compare();break;
                case "clear": clean.doClean();break;
                case "count_less_than_minimal_point": cltmp.counting();break;
                case "exit": System.exit(0);
                case "filter_by_minimal_point": h.Help();break; //TODO
                case "filter_less_than_personal_qualities_minimum": h.Help();break; //TODO
                case "history": hst.getHistory();break; //TODO
                case "info": inf.getInfo();break;
                case "remove_by_id": rbi.removing();break;
                case "remove_lower": rl.compare();break;
                case "show": System.out.println(h.Help());break; //TODO
                case "update_id": updId.update();break; //DO
            }
        }

    }
}
