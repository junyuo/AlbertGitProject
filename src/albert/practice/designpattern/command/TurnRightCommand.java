package albert.practice.designpattern.command;

public class TurnRightCommand implements Command {
    private Robot robot;

    public TurnRightCommand(Robot robot) {
        super();
        this.robot = robot;
    }

    @Override
    public void execute() {
        this.robot.turnRight();
    }

}
