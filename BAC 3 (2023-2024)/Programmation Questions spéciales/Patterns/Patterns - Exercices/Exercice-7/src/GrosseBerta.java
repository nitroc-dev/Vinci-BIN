public class GrosseBerta implements AbstractFactory {
    @Override
    public Robot createRobot() {
        return new RobotImpl.RobotBuilder()
                .setHealth(100)
                .setCanon(45)
                .setShield(3)
                .setFreq(300)
                .setName("GrosseBerta")
                .buildRobot();
    }
}
