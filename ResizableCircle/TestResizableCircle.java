public class TestResizableCircle {

    public static void main(String[] args) {
        ResizableCircle c2 = new ResizableCircle(100);
        c2.resize(10);
        System.out.printf("The resized radius is %.2f \n", c2.getRadius());
        System.out.printf("Perimeter of the resized circle is %.2f \n", + c2.getPerimeter());
        System.out.printf("Area of the resized circle is %.2f \n", c2.getArea());
    }
}
