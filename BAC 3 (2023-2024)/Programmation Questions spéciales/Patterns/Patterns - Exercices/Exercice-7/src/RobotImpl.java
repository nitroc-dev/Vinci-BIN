public class RobotImpl implements Robot {

    private int health;
    private final int canon;
    private final int shield;
    private final int freq;
    private final String name;

    private RobotImpl(RobotBuilder builder) {
        this.health = builder.health;
        this.canon = builder.canon;
        this.shield = builder.shield;
        this.freq = builder.freq;
        this.name = builder.name;
    }

    public int getHealth() {
        return health;
    }
    @Override
    public int getCanon() {
        return canon;
    }

    @Override
    public int getShield() {
        return shield;
    }

    @Override
    public int getFreq() {
        return freq;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int diffLife(int i) {
        return health += (i);
    }

    @Override
    public Robot clone() throws CloneNotSupportedException {
        return (Robot) super.clone();
    }

    //builder pattern
    public static class RobotBuilder {
        private int health = 100;
        private int canon = 1;
        private int shield = 1;
        private int freq = 100;
        private String name;

        public RobotBuilder setHealth(int health) {
            this.health = health;
            return this;
        }
        public RobotBuilder setCanon(int canon) {
            this.canon = canon;
            return this;
        }

        public RobotBuilder setShield(int shield) {
            this.shield = shield;
            return this;
        }

        public RobotBuilder setFreq(int freq) {
            this.freq = freq;
            return this;
        }

        public RobotBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public RobotImpl buildRobot() {
            return new RobotImpl(this);
        }
    }

}
