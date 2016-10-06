package albert.practice.designpattern.command;

// https://code.tutsplus.com/tutorials/design-patterns-the-command-pattern--cms-22942
public class RobotTestClient {

    public static void main(String[] args) {
        Robot robot = new Robot("¥Ë¤O");

//        RobotTestClient client = new RobotTestClient();
//        client.executeRobot(robot, CommandEnum.GO_HEAD);
//        client.executeRobot(robot, CommandEnum.TURN_LEFT);
//        client.executeRobot(robot, CommandEnum.TURN_RIGHT);
        
        new GoAheadCommand(robot).execute();
        new TurnLeftCommand(robot).execute();
        new TurnRightCommand(robot).execute();
    }

    public void executeRobot(Robot robot, CommandEnum command) {
        if (CommandEnum.GO_HEAD == command) {
            robot.goAhead();
        } else if (CommandEnum.TURN_BACK == command) {
            robot.turnBack();
        } else if (CommandEnum.TURN_LEFT == command) {
            robot.turnLeft();
        } else if (CommandEnum.TURN_RIGHT == command) {
            robot.turnRight();
        }
    }

}
