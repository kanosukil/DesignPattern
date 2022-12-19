package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 应用软件所提供的桌面快捷方式是快速启动应用程序的代理，桌面快捷方式一般使用一张小图片（Picture）来表示，
 * 通过调用快捷方式的run（）方法将调用应用软件（Application）的run（）方法。使用代理模式模拟该过程。
 */

interface Application {
    void run();
}

class StandardApplication implements Application {
    @Override
    public void run() {
        System.out.println("Standard Application is running");
    }
}

class SAProxy implements Application {

    private final StandardApplication sa;

    public SAProxy(StandardApplication sa) {
        this.sa = sa;
    }

    @Override
    public void run() {
        System.out.println("Before calling Standard Application run()");
        sa.run();
        System.out.println("After calling Standard Application run()");
    }
}


public class Proxy implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 7: ");
        SAProxy proxy = new SAProxy(new StandardApplication());
        proxy.run();
    }
}
