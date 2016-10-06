package albert.practice.designpattern.command;

public class TurnLeftCommand implements Command {
    private Robot robot;

    public TurnLeftCommand(Robot robot) {
        super();
        this.robot = robot;
    }

    @Override
    public void execute() {
        this.robot.turnLeft();
    }

}
