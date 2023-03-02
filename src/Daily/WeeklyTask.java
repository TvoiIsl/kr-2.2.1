package Daily;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, String description, LocalDateTime dateTime, Type type) {
        super(title,description,dateTime, type);
    }

    @Override
    public TaskTypeDay getTaskTypeDay() {
        return TaskTypeDay.WEEKLY;
    }
    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return localDate.equals(taskDate) || localDate.isAfter(taskDate);
    }
}
