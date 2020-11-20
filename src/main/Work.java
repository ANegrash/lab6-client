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
        show sh = new show();
        filterByMinimalPoint fbm=new filterByMinimalPoint();
        filterLessThanPersonalQualitiesMinimum flt = new filterLessThanPersonalQualitiesMinimum();

        while (true){
            Scanner scanner = new Scanner(System.in);
            String comand = scanner.nextLine();
            switch (comand){
                case "help": h.Help();break;
                case "add": add.insertData();break;
                case "add_if_max": addifmax.compare();break;
                case "clear": clean.doClean();break;
                case "count_less_than_minimal_point": cltmp.counting();break;
                case "exit": System.exit(0);
                case "filter_by_minimal_point": fbm.doFilter();break;
                case "filter_less_than_personal_qualities_minimum": flt.doFilter();break;
                case "history": hst.getHistory();break;
                case "info": inf.getInfo();break;
                case "remove_by_id": rbi.removing();break;
                case "remove_lower": rl.compare();break;
                case "show": sh.showCollection();break;
                case "update_id": updId.update();break;
                default:connect.sendComand(comand);break;
            }
        }

    }
}
