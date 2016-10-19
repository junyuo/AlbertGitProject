package albert.practice.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

// http://blog.tonycube.com/2015/10/java-java8-4-optional.html
@Slf4j
public class OptionalTest {

    public static void main(String[] args) {
        OptionalTest test = new OptionalTest();

//        Project test1 = test.getProjectByName("test");
//        if (test1 != null) {
//            log.debug("test1 = " + test1.toString());
//        } else {
//            throw new RuntimeException("無資料");
//        }

//        Project test2 = test.getProjectByNameWithOptional("test").get();
//        log.debug("test2 = " + test2.toString());
        
        test.checkUncompletedSubtasks();
    }
    

    private Project getProjectByName(String name) {
        return getProjects().stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
    }

    private Optional<Project> getProjectByNameWithOptional(String name) {
        Optional<Project> project = Optional.ofNullable(getProjects().stream()
                .filter(p -> p.getName().equals(name)).findAny().orElse(null));
        project.orElseThrow(() -> new RuntimeException("查無資料"));
        return project;
    }

    private List<Project> getProjects() {
        Project fms = new Project(1, "FMS");
        Project nss = new Project(1, "NSS");
        Project dbm = new Project(1, "DBM");

        return Arrays.asList(fms, nss, dbm);
    }

    @Data
    @AllArgsConstructor
    @ToString
    private class Project {
        private Integer id;
        private String name;
    }
    
    public void checkUncompletedSubtasks() {
        Optional<List<Subtask>> subtasks = getSubtasks();
        Optional<Subtask> uncompletedTask = subtasks.get().stream().filter(s -> Boolean.FALSE.equals(s.getIsCompleted())).findAny();
        if(uncompletedTask.isPresent()) {
            throw new RuntimeException("還有尚未完成的子任務");
        }
    }
    
    private Optional<List<Subtask>> getSubtasks(){
        Subtask task1 = new Subtask("檢查網路線", Boolean.TRUE);
        Subtask task2 = new Subtask("檢查資料庫", Boolean.TRUE);
        Subtask task3 = new Subtask("檢查伺服器", Boolean.FALSE);
        return Optional.ofNullable(Arrays.asList(task1, task2, task3));
    }
    
    @Data
    @AllArgsConstructor
    @ToString
    private class Subtask {
        private String taskName;
        private Boolean isCompleted;
    }

}
