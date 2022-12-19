package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 使用Java语言实现一个双向适配器实例，使得猫可以学狗叫，够可以学猫抓老鼠。
 */
interface Learn {
    void action(boolean isLearned);
}

interface Animal {
    void action();
}

class Cat implements Animal {
    @Override
    public void action() {
        System.out.println("Cat can catch the mice.");
    }
}

class Dog implements Animal {
    @Override
    public void action() {
        System.out.println("Dog can bark");
    }
}

class CatLearn implements Learn {
    private final Animal dog;

    public CatLearn() {
        dog = new Dog();
    }

    @Override
    public void action(boolean isLearned) {
        dog.action();
    }
}

class DogLearn implements Learn {
    private final Animal cat;

    public DogLearn() {
        cat = new Cat();
    }

    @Override
    public void action(boolean isLearned) {
        cat.action();
    }
}

class AfterLearningDog extends Dog implements Learn {
    private final DogLearn dog;

    public AfterLearningDog() {
        dog = new DogLearn();
    }

    @Override
    public void action(boolean isLearned) {
        if (isLearned) {
            dog.action(true);
        } else {
            action();
        }
    }
}

class AfterLearningCat extends Cat implements Learn {
    private final CatLearn cat;

    public AfterLearningCat() {
        cat = new CatLearn();
    }

    @Override
    public void action(boolean isLearned) {
        if (isLearned) {
            cat.action(true);
        } else {
            action();
        }
    }
}

public class Adapter implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 1: ");
        Animal dog = new Dog();
        Animal cat = new Cat();
        Learn dogLearn = new AfterLearningDog();
        Learn catLearn = new AfterLearningCat();

        // Original
        dog.action();
        cat.action();
        // After Dog
        dogLearn.action(true);
        dogLearn.action(false);
        // After Cat
        catLearn.action(true);
        catLearn.action(false);
    }
}
