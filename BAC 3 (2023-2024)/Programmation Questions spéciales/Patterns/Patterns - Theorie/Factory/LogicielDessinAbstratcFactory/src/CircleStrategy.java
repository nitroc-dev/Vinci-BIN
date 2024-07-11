public class CircleStrategy implements Strategy {

    @Override
    public Shape createShape(int size, Point center) {
        return new Circle(size, center);
    }
}
