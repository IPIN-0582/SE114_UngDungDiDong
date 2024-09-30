// Truong Quang Huy - 22520582
import java.util.Random;

abstract class Shape {
    private static int idCounter = 0;
    private int id;

    public Shape() {
        idCounter++;
        this.id = idCounter;
    }

    public abstract double calculateArea();

    public int getId() {
        return id;
    }
}

class Ellipse extends Shape {
    private double a;
    private double b;

    public Ellipse(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double calculateArea() {
        return Math.PI * a * b;
    }
}

class Circle extends Ellipse {
    private double r;

    public Circle(double r) {
        super(r, r);
        this.r = r;
    }

    public double calculateArea() {
        return Math.PI * r * r;
    }
}

class Rectangle extends Shape {
    private double a;
    private double b;

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double calculateArea() {
        return a * b;
    }
}

class Square extends Rectangle {
    private double a;

    public Square(double a) {
        super(a, a);
        this.a = a;
    }

    public double calculateArea() {
        return a * a;
    }
}

class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double calculateArea() {
        double p = (a + b + c)/2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Shape[] shapes = new Shape[100];

        for (int i = 0; i < 100; i++) {
            int shapeType = random.nextInt(5);  // 0: Ellipse, 1: Circle, 2: Rectangle, 3: Square, 4: Triangle
            switch (shapeType) {
                case 0:
                    shapes[i] = new Ellipse(random.nextDouble() * 10 + 1, random.nextDouble() * 10 + 1);
                    break;
                case 1:
                    shapes[i] = new Circle(random.nextDouble() * 10 + 1);
                    break;
                case 2:
                    shapes[i] = new Rectangle(random.nextDouble() * 10 + 1, random.nextDouble() * 10 + 1);
                    break;
                case 3:
                    shapes[i] = new Square(random.nextDouble() * 10 + 1);
                    break;
                case 4:
                    shapes[i] = new Triangle(random.nextDouble() * 10 + 1, random.nextDouble() * 10 + 1, random.nextDouble() *10 +1);
                    break;
            }
        }
        
        Shape largestShape= shapes[0];
        for (int i = 1; i < 100; i++){
              if (shapes[i].calculateArea() > largestShape.calculateArea())
                largestShape = shapes[i];
        }
        
        System.out.println("Hinh co dien tich lon nhat:");
        System.out.println("ID: " + largestShape.getId() + ", dien tich: " + largestShape.calculateArea());
    }
}