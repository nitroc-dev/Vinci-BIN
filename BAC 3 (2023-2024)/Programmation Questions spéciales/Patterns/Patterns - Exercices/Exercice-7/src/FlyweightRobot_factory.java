import java.util.HashMap;
import java.util.Map;

public class FlyweightRobot_factory {
    private Map<String, AbstractFactory> robots = new HashMap<>();

    public void addRobot(String name, AbstractFactory factory) {
        robots.put(name, factory);
    }

    public Robot createRobot(String name) {
        return robots.get(name).createRobot();
    }
}
