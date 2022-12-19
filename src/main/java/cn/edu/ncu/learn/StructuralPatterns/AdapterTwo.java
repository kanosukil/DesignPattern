package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 要在DrawCircle中调用DrawAngle的方法，可是类已经分发出去了，不能修改，如何用适配器模式解决这个问题，画出类关系图并说明
 */
interface Draw {
    void insert(String msg);
}

class DrawCircle implements Draw {
    public DrawCircle() {
    }

    @Override
    public void insert(String msg) {
        System.out.println("Draw Circle insert(): " + msg);
    }
}

class DrawAngle implements Draw {
    public DrawAngle() {
    }

    @Override
    public void insert(String msg) {
        System.out.println("Draw Angle insert(): " + msg);
    }
}

class AngleAdapter implements Draw {
    private final DrawAngle angle;

    public AngleAdapter(DrawAngle angle) {
        this.angle = angle;
    }

    @Override
    public void insert(String msg) {
        angle.insert(msg);
    }

}

class CircleAngle extends DrawCircle {
    private final AngleAdapter adapter;

    public CircleAngle(AngleAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void insert(String msg) {
        super.insert(msg);
        adapter.insert(msg);
    }
}


public class AdapterTwo implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 8: ");

        CircleAngle circleAngle = new CircleAngle(new AngleAdapter(new DrawAngle()));
        circleAngle.insert("Hello, World!");
    }
}
