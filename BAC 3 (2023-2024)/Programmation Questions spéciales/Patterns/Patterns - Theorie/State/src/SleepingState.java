public class SleepingState extends State {

    @Override
    public void attack(Bear b) {
        System.out.println("Zzzzzz");
    }

    @Override
    public void sleep(Bear b) {
        throw new RuntimeException("already sleeping");
    }
}
