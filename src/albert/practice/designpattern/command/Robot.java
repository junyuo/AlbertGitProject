package albert.practice.designpattern.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Data
@Slf4j
public class Robot {

    private String robotName;

    public void goAhead() {
        log.debug(robotName + " �e�i !");
    }

    public void turnLeft() {
        log.debug(robotName + " ���� !");
    }

    public void turnRight() {
        log.debug(robotName + " �k�� !");
    }

    public void turnBack() {
        log.debug(robotName + " ��h !");
    }
}
