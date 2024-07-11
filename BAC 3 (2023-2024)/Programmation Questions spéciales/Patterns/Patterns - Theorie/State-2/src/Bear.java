
public class Bear {

    public enum State {
        SLEEPING {
            @Override
            public void attack(Bear b) {
                System.out.println("Zzzzzz");
            }

            @Override
            public void sleep(Bear b) {
                throw new RuntimeException("already sleeping");
            }
        }, NOT_SLEEPING{
            @Override
            public void attack(Bear b) {
                System.out.println("GroaaR");
            }

            @Override
            public void wakeup(Bear b) {
                throw new RuntimeException("already not sleeping");
            }
        };

        public abstract void attack(Bear b);

        public void sleep(Bear b) {
            b.setState(State.SLEEPING);
        }

        public void wakeup(Bear b) {
            b.setState(State.NOT_SLEEPING);
        }
    }

    private State state;

    public Bear() {
        setState(State.NOT_SLEEPING);
    }

    public void attack() {
        state.attack(this);
    }

    public void sleep() {
        state.sleep(this);
    }

    public void wakeup() {
        state.wakeup(this);
    }

    public void setState(State state) {
        this.state= state;
    }

    public static void main(String[] args) {
        Bear b= new Bear();
        b.attack();
        b.sleep();
        b.attack();
    }
}
