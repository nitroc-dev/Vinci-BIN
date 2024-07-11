public class DrawCarWithCircles extends DrawCar {

    @Override
    protected Shape createShape(int i, Point point) {
        return new Circle(i, point);
    }
}
