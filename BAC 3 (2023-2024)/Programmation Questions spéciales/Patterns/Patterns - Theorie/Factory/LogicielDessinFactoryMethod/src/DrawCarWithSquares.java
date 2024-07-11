public class DrawCarWithSquares extends DrawCar {

    @Override
    protected Shape createShape(int i, Point point) {
        return new Square(i, point);
    }
}
