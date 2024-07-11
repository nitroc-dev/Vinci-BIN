public class SquareStrategy implements Strategy {

    @Override
    public Shape createShape(int size, Point center) {
        return new Square(size, center);
    }
}
