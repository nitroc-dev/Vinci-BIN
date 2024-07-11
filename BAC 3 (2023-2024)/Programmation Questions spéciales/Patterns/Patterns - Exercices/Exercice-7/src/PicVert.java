public class PicVert implements AbstractFactory {
    @Override
    public Robot createRobot() {
        return new RobotImpl.RobotBuilder()
                .setHealth(100)
                .setCanon(6)
                .setShield(1)
                .setFreq(50)
                .setName("PicVert")
                .buildRobot();
    }
}
