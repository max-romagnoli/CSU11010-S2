public class TestCircle {

    public static void main(String[] args) {
        Circle c1 = new Circle(100);
        System.out.printf("The radius is %.2f \n", c1.getRadius());
        System.out.printf("Perimeter of the circle is %.2f \n", + c1.getPerimeter());
        System.out.printf("Area of the circle is %.2f \n", c1.getArea());
    }
}
