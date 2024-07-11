public class Tank implements AbstractFactory {
    @Override
    public Robot createRobot() {
        return new RobotImpl.RobotBuilder()
                .setHealth(500)
                .setCanon(5)
                .setShield(10)
                .setFreq(150)
                .setName("Tank")
                .buildRobot();
    }
}
