package albert.practice.designpattern.command;

public class TurnBackCommand implements Command {
    private Robot robot;

    public TurnBackCommand(Robot robot) {
        super();
        this.robot = robot;
    }

    @Override
    public void execute() {
        this.robot.turnBack();
    }

}
