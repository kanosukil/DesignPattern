package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 现在，有会飞的汽车，请给该汽车的对象动态添加会飞的功能。
 */

final class Car {
    public void move() {
        System.out.println("The Car can move on the land!");
    }
}

class FlyCar {
    private final Car car;

    public FlyCar(Car car) {
        this.car = car;
    }

    public void move() {
        car.move();
        fly();
    }

    private void fly() {
        System.out.println("The Car can Fly in the Sky!");
    }
}


public class DecoratorTwo implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 9: ");
        FlyCar car = new FlyCar(new Car());
        car.move();
    }
}
