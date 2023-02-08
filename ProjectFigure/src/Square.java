

public class Square implements Figure {
    private double side;

    public Square(double side){
        this.side = side;
    }

    @Override
    public double perimeter() {
        return 4*side;
    }
    @Override
    public double square() {
        return side*side;
    }
}
