package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

/**
 * 某高校教学管理系统需要实现如下功能，
 * + 如果某个系的系名发生改变，则该系所有教师和学生的所属系名称也将发生改变。
 * 使用Java语言提供的观察者类和观察目标类实现该功能，绘制类图并编程实现。
 */

abstract class DepartObserver {
    protected Department department;

    public abstract void update();
}

class Department {
    private final List<DepartObserver> observers = new ArrayList<>();
    private String name;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void attach(DepartObserver observer) {
        observers.add(observer);
    }

    public void leave(DepartObserver observer) {
        observers.remove(observer);
    }

    private void notifyObserve() {
        observers.forEach(DepartObserver::update);
    }
}

class ObserveTeacher extends DepartObserver {
    private String name;

    public ObserveTeacher(Department department, String name) {
        this.name = name + "|" + department.getName();
        super.department = department;
        department.attach(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name + "|" + department.getName();
    }

    @Override
    public void update() {
        System.out.println("The name of department has changed.");
        setName(name.substring(0, name.indexOf('|')));
    }
}

class ObserveStudent extends DepartObserver {
    private String name;

    public ObserveStudent(Department department, String name) {
        this.name = name + "|" + department.getName();
        super.department = department;
        department.attach(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name + "|" + department.getName();
    }

    @Override
    public void update() {
        System.out.println("The name of department has changed.");
        setName(name.substring(0, name.indexOf('|')));
    }
}

public class Observer implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 6: ");
        Faker faker = new Faker();
        Department department = new Department(faker.pokemon().location());
        ObserveTeacher teacher = new ObserveTeacher(department, faker.pokemon().name());
        ObserveStudent student = new ObserveStudent(department, faker.pokemon().name());

        System.out.println(department.getName());
        System.out.println(teacher.getName());
        System.out.println(student.getName());

        department.setName(faker.pokemon().location());
        System.out.println(department.getName());
        System.out.println(teacher.getName());
        System.out.println(student.getName());

    }
}
