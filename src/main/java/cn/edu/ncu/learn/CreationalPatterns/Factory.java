package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 假设我们要开发一个绘图程序，用来绘制简单的几何图形，这个软件应该能够处理下面的几种几何对象：
 * + 圆形（Circle）
 * + 矩形（Rectangle）
 * + 正方形（Square）。
 * 除了各自特有的属性和方法之外，所有的几何图形几乎都可以抽象出绘制（draw）和擦除（erase）两个公共方法，
 * 利用简单工厂模型进行设计，画出结构图并指明类之间的关系，然后用工厂方法模式将其改进。
 */

interface Shape {
    void draw();

    void erase();
}

interface Generator {
    Shape getShape();
}

class Circle implements Shape {
    private final double radius;

    public Circle() {
        radius = 1.0;
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Draw a Circle -> Radius=" + radius);
    }

    @Override
    public void erase() {
        System.out.println("Erase a Circle");
    }
}

class Rectangle implements Shape {
    private final double edge1;
    private final double edge2;
    private final double edge3;

    public Rectangle() {
        edge1 = 1.0;
        edge2 = 1.0;
        edge3 = 1.0;
    }

    public Rectangle(double edge) {
        edge1 = edge;
        edge2 = edge;
        edge3 = edge;
    }

    public Rectangle(double edge1, double edge2, double edge3)
            throws Exception {
        this.edge1 = edge1;
        this.edge2 = edge2;
        this.edge3 = edge3;
        if (check()) {
            throw new Exception("These edge can`t combine a rectangle. Edge(1,2,3)=(" + edge1 + "," + edge2 + "," + edge3 + ")");
        }
    }

    private boolean check() {
        double v1 = edge1 * edge1;
        double v2 = edge2 * edge2;
        double v3 = edge3 * edge3;
        return !(v1 < v2 + v3) && !(v2 < v1 + v3) && !(v3 < v1 + v2);
    }

    @Override
    public void draw() {
        System.out.println("Draw a Rectangle: -> Edge(1,2,3)=(" + edge1 + "," + edge2 + "," + edge3 + ")");
    }

    @Override
    public void erase() {
        System.out.println("Erase a Rectangle");
    }
}

class Square implements Shape {
    private final double width;
    private final double height;

    public Square() {
        width = 1.0;
        height = 1.0;
    }

    public Square(double edge) {
        width = edge;
        height = edge;
    }

    public Square(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("Draw a Square: -> width=" + width + " height=" + height);
    }

    @Override
    public void erase() {
        System.out.println("Erase a Square");
    }
}

class ShapeSelector {
    public static Shape getShape(String shape) {
        return switch (shape) {
            case "circle", "Circle", "CIRCLE" -> new Circle();
            case "rectangle", "Rectangle", "RECTANGLE" -> new Rectangle();
            case "square", "Square", "SQUARE" -> new Square();
            default -> null;
        };
    }
}

class CircleGenerator implements Generator {

    @Override
    public Shape getShape() {
        return new Circle();
    }
}

class RectangleGenerator implements Generator {
    @Override
    public Shape getShape() {
        return new Rectangle();
    }
}

class SquareGenerator implements Generator {

    @Override
    public Shape getShape() {
        return new Square();
    }
}


public class Factory implements Main.DoAction {

    @Override
    public void method() {
        System.out.println("Question 2: ");
        // Simple Factory
        Shape circleSimple = ShapeSelector.getShape("circle");
        Shape rectangleSimple = ShapeSelector.getShape("rectangle");
        Shape squareSimple = ShapeSelector.getShape("square");

        System.out.println("Simple Factory Obj Print Draw & Erase: ");

        circleSimple.draw();
        rectangleSimple.draw();
        squareSimple.draw();

        circleSimple.erase();
        rectangleSimple.erase();
        squareSimple.erase();

        Shape circle = new CircleGenerator().getShape();
        Shape rectangle = new RectangleGenerator().getShape();
        Shape square = new SquareGenerator().getShape();

        System.out.println("Factory Obj Print Draw & Erase: ");

        circle.draw();
        rectangle.draw();
        square.draw();

        circle.erase();
        rectangle.erase();
        square.erase();
    }
}