package albert.practice.optional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        Project test2 = test.getProjectByNameWithOptional("test").get();
        log.debug("test2 = " + test2.toString());
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

}
