package Daily;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(String title,String description, LocalDateTime dateTime, Type type ) {
        super(title,description,dateTime, type );
    }

    @Override
    public TaskTypeDay getTaskTypeDay() {
        return TaskTypeDay.YEARLY;
    }
    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return localDate.equals(taskDate) || localDate.getDayOfYear() == localDate.getDayOfYear();
    }
}
