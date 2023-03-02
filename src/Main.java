import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

import Task.*;

public class Main {

    private static final TaskService taskService = new TaskService();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    //    Task task = new Task();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        taskService.add( new OneTimeTask("title", "description", LocalDateTime.now().plusHours(1), Type.PERSON));
        taskService.add(new DailyTask("title", "description", LocalDateTime.now().plusHours(2), Type.PERSON));
        taskService.add(new WeeklyTask("title", "description", LocalDateTime.now().plusHours(3), Type.PERSON));
        taskService.add(new MonthlyTask("title", "description", LocalDateTime.now().plusHours(4), Type.PERSON));
        taskService.add(new YearlyTask("title", "description", LocalDateTime.now().plusHours(5), Type.PERSON));
        int menu = 0 ;
        while (menu!=4) {
            System.out.println("Выберите пункт меню: ");
            printMenu();
            menu = scanner.nextInt();
            switch (menu) {
                case 0:
                    addTask(scanner);
                    break;
                case 1:
                    removeTask(scanner);
                    break;
                case 2:
                    printTaskForDate(scanner);
                    break;
                default:
                    System.out.println("До свидания!");
            }

        }

    }
    public static void printMenu(){
        System.out.println(" 0 - Занести задачу");
        System.out.println(" 1 - Удалить задачу");
        System.out.println(" 2 - Получить список на определенный день");
        System.out.println(" 3 - Выход");


