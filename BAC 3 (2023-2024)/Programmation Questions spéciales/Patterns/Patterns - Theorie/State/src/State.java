public abstract class State {

    public abstract void attack(Bear b);

    public void sleep(Bear b) {
        b.setState(new SleepingState());
    }

    public void wakeup(Bear b) {
        b.setState(new NotSleepingState());
    }
}
