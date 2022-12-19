package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * 某系统需要提供一个命令集合（注：可使用ArrayList等集合对象实现），用于存储一些列命令对象，
 * 并通过该命令集合实现多次undo()和redo()操作，可使用计算器来模拟实现。
 */
interface Order {
    void execute();
}

class Undo implements Order {
    private final Actuator actuator;

    public Undo(Actuator actuator) {
        this.actuator = actuator;
    }

    @Override
    public void execute() {
        actuator.undo();
    }
}

class Redo implements Order {
    private final Actuator actuator;

    public Redo(Actuator actuator) {
        this.actuator = actuator;
    }

    @Override
    public void execute() {
        actuator.redo();
    }
}

class Actuator {
    public void undo() {
        System.out.println("undo <CTRL+Z>");
    }

    public void redo() {
        System.out.println("redo <CTRL+Y>");
    }
}

class Caller {
    private final List<Order> orders = new ArrayList<>();

    public void takeOrder(Order order) {
        orders.add(order);
    }

    public void placeOrder() {
        orders.forEach(Order::execute);
        orders.clear();
    }
}


public class Command implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 2: ");
        Actuator actuator = new Actuator();
        Redo redo = new Redo(actuator);
        Undo undo = new Undo(actuator);
        Caller caller = new Caller();
        caller.takeOrder(redo);
        caller.takeOrder(undo);
        caller.takeOrder(undo);
        caller.takeOrder(undo);
        caller.takeOrder(redo);
        caller.takeOrder(undo);
        caller.takeOrder(undo);
        caller.takeOrder(redo);
        caller.takeOrder(redo);
        caller.takeOrder(redo);
        caller.takeOrder(redo);
        caller.takeOrder(undo);
        caller.takeOrder(redo);
        caller.placeOrder();
    }
}
