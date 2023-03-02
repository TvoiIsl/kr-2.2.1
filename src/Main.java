import Daily.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;

public class Main {

    private static final TaskService taskService = new TaskService();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    //    Task task = new Task();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        taskService.add( new OneTimeTask("title", "description", LocalDateTime.now().plusHours(1), Type.PERSONAL));
        taskService.add(new DailyTask("title", "description", LocalDateTime.now().plusHours(2), Type.PERSONAL));
        taskService.add(new WeeklyTask("title", "description", LocalDateTime.now().plusHours(3), Type.PERSONAL));
        taskService.add(new MonthlyTask("title", "description", LocalDateTime.now().plusHours(4), Type.PERSONAL));
        taskService.add(new YearlyTask("title", "description", LocalDateTime.now().plusHours(5), Type.PERSONAL));
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
                    System.out.println("До свидания");
            }

        }

    }
    public static void printMenu(){
        System.out.println(" 0 - Создать задачу");
        System.out.println(" 1 - Удалить задачу");
        System.out.println(" 2 - Получить список на определенный день");
        System.out.println(" 3 - Выход");

    }
    public static void addTask(Scanner scanner){
        String title = readTitle("Введите название задачи: " , scanner);
        String description = readDescription("Введите описание задачи: " , scanner);
        LocalDateTime localDateTime = readDateTime(scanner);
        Type type = readType(scanner);
        TaskTypeDay typeTask = readTaskTypeDay(scanner);

        Task task = switch (typeTask) {
            case ONETIME->
                    new OneTimeTask(title, description, localDateTime, type);
            case DAILY->
                    new DailyTask(title, description, localDateTime, type);
            case WEEKLY->
                    new WeeklyTask(title, description, localDateTime, type);
            case MONTHLY->
                    new MonthlyTask(title, description, localDateTime, type);
            case YEARLY->
                    new YearlyTask(title, description, localDateTime, type);
        };

        taskService.add(task);

    }
    private static Type readType( Scanner scanner){
        while (true) {
            try {
                System.out.println("Выберите тип:");
                for (Type type : Type.values()) {
                    System.out.println(type.ordinal() + ". " + localType(type));
                }
                System.out.println("Введите тип:");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return Type.values()[ordinal];
            }catch (NumberFormatException e){
                System.out.println("Введен неверный номер типа задачи");
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Тип задачи не найден");
            }
        }
    }

    private static TaskTypeDay readTaskTypeDay(Scanner scanner){
        while (true) {
            try {
                System.out.println("Выберите тип задачи: ");
                for (TaskTypeDay taskTypeDay : TaskTypeDay.values()) {
                    System.out.println(taskTypeDay.ordinal() + ". " + localTaskTypeDay(taskTypeDay));
                }
                System.out.println("Введите тип: ");
                String ordinalLine = scanner.nextLine();
                int ordinal = Integer.parseInt(ordinalLine);
                return TaskTypeDay.values()[ordinal];

            }catch (NumberFormatException e){
                System.out.println("Введен неверный номер типа задачи");
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Тип задачи не найден");
            }
        }
    }

    private static LocalDateTime readDateTime(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        LocalTime localTime = readTime(scanner);
        return localDate.atTime(localTime);

    }
    public static String readTitle(String message, Scanner scanner){
        while (true) {
            System.out.println(message);
            String readTitle = scanner.nextLine();
            if (readTitle == null || readTitle.isBlank()) {
                System.out.println("Вы ввели пустое значение!");
            } else {
                return readTitle;
            }
        }
    }
    private static void removeTask(Scanner scanner){
        System.out.println("Все задачи:");
        for (Task task : taskService.getAllTask()){
            System.out.println(
                    task.getId() + " " +
                            task.getTitle() + " " +
                            localTaskTypeDay(task.getTaskTypeDay())+ " " +
                            localType(task.getType()));
        }
        while (true){
            try {
                System.out.println("Выберите задачу для удаления");
                String idLine = scanner.nextLine();
                int id = Integer.parseInt(idLine);
                taskService.removeTask(id);
                break;
            }catch (NumberFormatException e ){
                System.out.println("Введен неверный id задачи");
            }catch (TaskNotFoundExeption e){
                System.out.println("Задачи для удаления не найдены");
            }
        }

    }
    private static void printTaskForDate(Scanner scanner) {
        LocalDate localDate = readDate(scanner);
        Collection<Task> taskForDate = taskService.getTaskForDate(localDate);
        System.out.println(" Задачи на "  + localDate.format(DATE_FORMATTER));
        for (Task task : taskForDate){
            System.out.println(
                    localType(task.getType()) + " " + task.getTitle() + " " + task.getDescription()  + " " + task.getDateTime().format(TIME_FORMATTER)
            );
        }
    }
    private static LocalTime readTime(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите дату задачи в формате ЧЧ:ММ:");
                String timeLine = scanner.next();
                return LocalTime.parse(timeLine, TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Введено время в неверном формате");
            }
        }

    }
    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите дату задачи в формате Д.ММ.ГГГГ:");
                String dateLine = scanner.next();
                return LocalDate.parse(dateLine, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Введена дата в неверном формате");
            }
        }
    }
    private static String localType(Type type){
        return switch (type){
            case WORK -> "Рабочая задача";
            case PERSONAL -> "Персональная задача";
        };
    }
    private static String localTaskTypeDay(TaskTypeDay taskTypeDay){
        return switch (taskTypeDay){
            case ONETIME -> "Одновременная";
            case DAILY -> "Ежедневная";
            case WEEKLY -> "Еженедельная";
            case MONTHLY -> "Ежемесячная";
            case YEARLY -> "Ежегодная";
        };
    }
    public static String readDescription(String message, Scanner scanner){
        while (true) {
            System.out.println(message);
            String readDescription = scanner.nextLine();
            if (readDescription == null || readDescription.isBlank()) {
                System.out.println("Вы ввели пустое значение!");
            } else {
                return readDescription;
            }
        }
    }
}


