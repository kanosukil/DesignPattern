package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 使用合理的模式模拟女娲(Nvwa)造人(Person)，
 * 如果传入参数M，则返回一个Man对象，
 * 如果传入参数W，则返回一个Woman对象，
 * 用Java语言模拟实现该场景。
 * <p>
 * 现需要增加一个新的Robot类，如果传入参数R，则返回一个Robot对象，
 * 对代码进行修改并注意“女娲”的变化。
 */

interface Person {
    void canDo();
}

class Man implements Person {
    @Override
    public void canDo() {
        System.out.println("I`m the Man");
    }
}

class Woman implements Person {
    @Override
    public void canDo() {
        System.out.println("I`m the Woman");
    }
}

class Nvwa {
    public static Person getHumanLike(char arg) {
        return switch (arg) {
            case 'M', 'm' -> new Man();
            case 'W', 'w' -> new Woman();
            // New
            case 'R', 'r' -> new Robot();
            default -> null;
        };
    }
}

// New
class Robot implements Person {
    @Override
    public void canDo() {
        System.out.println("I`m the Robot");
    }
}

public class SimpleFactory implements Main.DoAction {
    public void method() {
        System.out.println("Question 1: ");

        Nvwa.getHumanLike('w').canDo();
        Nvwa.getHumanLike('m').canDo();
        // New
        Nvwa.getHumanLike('r').canDo();
    }
}
