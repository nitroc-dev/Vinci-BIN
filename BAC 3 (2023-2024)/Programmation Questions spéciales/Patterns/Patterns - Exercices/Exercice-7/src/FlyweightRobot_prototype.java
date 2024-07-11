import java.util.HashMap;
import java.util.Map;

public class FlyweightRobot_prototype {
    private Map<String, Robot> robots = new HashMap<>();

    public void addRobot(String name, Robot robot) {
        robots.put(name, robot);
    }

    public Robot createRobot(String name) throws CloneNotSupportedException {
        return robots.get(name).clone();
    }
}
