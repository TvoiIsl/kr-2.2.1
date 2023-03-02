package Daily;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneTimeTask extends Task {


    public OneTimeTask(String title,String description, LocalDateTime dateTime, Type type ) {
        super(title,description,dateTime, type );
    }
    @Override
    public TaskTypeDay getTaskTypeDay() {
        return TaskTypeDay.ONETIME;
    }
    @Override
    public boolean appearsIn(LocalDate localDate) {
        LocalDate taskDate = this.getDateTime().toLocalDate();
        return localDate.equals(taskDate) || localDate.isAfter(taskDate);
    }
}
