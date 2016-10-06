package albert.practice.designpattern.command;

public class GoAheadCommand implements Command {

    private Robot robot;

    public GoAheadCommand(Robot robot) {
        super();
        this.robot = robot;
    }

    @Override
    public void execute() {
        this.robot.goAhead();
    }

}
