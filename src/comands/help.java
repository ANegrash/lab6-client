package comands;

import main.Connect;

import java.io.IOException;

public class help {
    Connect connect = new Connect();
    public String Help() throws IOException {
        connect.sendComand("help");
        return "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, количество элементов, вес файла и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update_id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id {id} : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "history : вывести последние 12 команд (без их аргументов)\n" +
                "count_less_than_minimal_point {minimalPoint} : вывести количество элементов, значение поля minimalPoint которых меньше заданного\n" +
                "filter_by_minimal_point {minimalPoint} : вывести элементы, значение поля minimalPoint которых равно заданному\n" +
                "filter_less_than_personal_qualities_minimum {personalQualitiesMinimum} : вывести элементы, значение поля personalQualitiesMinimum которых меньше заданного";

    }

}
