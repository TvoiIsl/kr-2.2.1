package Daily;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class TaskService {


    private Map<Integer,Task> taskMap = new HashMap<>();
    private Collection<Task> removedTask = new HashSet<>();


    public void add( Task task ){
        this.taskMap.put(task.getId(), task);
    }
    public Collection<Task> getAllTask(){
        return this.taskMap.values();
    }


    public Collection<Task> getTaskForDate(LocalDate localDate) {

        TreeSet<Task>  taskForDate = new TreeSet<>();
        for( Task task : taskMap.values()){
            if(task.appearsIn(localDate)){
                taskForDate.add(task);
            }
        }
        return taskForDate;
    }
    public void removeTask(int id) throws TaskNotFoundExeption{
        if(this.taskMap.containsKey(id)){
            this.taskMap.remove(id);
        }else {
            throw new TaskNotFoundExeption();
        }
    }

}
