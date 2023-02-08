

public class Circle implements Figure {
    private double radius;

    public  Circle(double radius){
        this.radius = radius;
    }
    @Override
    public double perimeter() {
        return 2*Math.PI*radius;
    }
    @Override
    public double square() {
        return Math.PI*radius*radius;
    }

    public double diameter() { return 2*radius; }

    public int intPerimeter() { return (int)(2*Math.PI*radius); }

    private double getRadius() {return radius;}
}
