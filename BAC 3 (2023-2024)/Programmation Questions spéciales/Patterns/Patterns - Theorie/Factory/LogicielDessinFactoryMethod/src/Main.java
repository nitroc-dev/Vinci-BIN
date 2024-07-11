public class Main {

    public static void main(String[] args) {
        DrawCar drawCar = new DrawCarWithCircles();
        drawCar.draw(5);
        DrawCar drawCar2 = new DrawCarWithSquares();
        drawCar2.draw(5);
    }
}
