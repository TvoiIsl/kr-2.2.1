package Daily;

import java.time.LocalDate;
import java.time.LocalDateTime;


public abstract class Task {

    private int idGenerator = 0;
    private String title;
    private Type type;
    private TaskTypeDay taskTypeDay;
    private  int id;
    private LocalDateTime dateTime;
    private String description;

    public Task(String title,String description,LocalDateTime dateTime, Type type ) {
        idGenerator++;
        id = idGenerator;
        this.title = title;
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
    }

    public abstract TaskTypeDay getTaskTypeDay();

    public abstract boolean appearsIn (LocalDate localDate);


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }
}
