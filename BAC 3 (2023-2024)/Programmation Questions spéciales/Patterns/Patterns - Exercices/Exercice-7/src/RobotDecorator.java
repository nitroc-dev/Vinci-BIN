public class RobotDecorator implements Robot {
    private Robot robot;
    private int bonusHealth;
    private int bonusCanon;
    private int bonusShield;
    private int bonusFreq;

    public RobotDecorator(Robot robot) {
        this.robot = robot;
    }

    public int getCanon() {
        return robot.getCanon() + bonusCanon;
    }

    public int getShield() {
        return robot.getShield() + bonusShield;
    }

    public int getFreq() {
        return robot.getFreq() + bonusFreq;
    }

    public String getName() {
        return robot.getName();
    }

    public int diffLife(int i) {
        return robot.diffLife(i);
    }

    public Robot clone() throws CloneNotSupportedException {
        return (Robot) super.clone();
    }

    public void setBonusHealth(int bonusHealth) {
        this.bonusHealth = bonusHealth;
    }

    public void setBonusCanon(int bonusCanon) {
        this.bonusCanon = bonusCanon;
    }

    public void setBonusShield(int bonusShield) {
        this.bonusShield = bonusShield;
    }

    public void setBonusFreq(int bonusFreq) {
        this.bonusFreq = bonusFreq;
    }



}
