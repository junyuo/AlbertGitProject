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
        log.debug(robotName + " 前進 !");
    }

    public void turnLeft() {
        log.debug(robotName + " 左轉 !");
    }

    public void turnRight() {
        log.debug(robotName + " 右轉 !");
    }

    public void turnBack() {
        log.debug(robotName + " 後退 !");
    }
}
