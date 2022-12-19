package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 程序中经常有这样的要求，整个程序运行时只有一个实例被使用。
 * 比如：数据库连接池，系统参数配置，Java API 中的 Runtime, Calendar 等等。
 * 如何实现这种需求是一个值得讨论的问题。
 * <p>
 * 以往的做法，是
 * 1. 在程序的某个类里面（比如是GlobalObject）建立一个这个此种类的实例，
 * 2. 然后规定所有需要用到此类的，都从GlobalObject那里获得，
 * <p>
 * 但这样做有如下缺点：
 * 1. 其他人可能调用ConnectionPoolManager的构造函数自己建立一个数据库连接池，导致程序中存在多个ConnectionPoolManager，
 * 2. 人为的规定只创建一个数据库连接池往往不会很好的执行。
 * <p>
 * 如何利用单件模式来改进设计，保证系统只建立唯一的连接池，并完善代码。
 */

class ConnectionPoolManager {
    private static final ConnectionPoolManager instance = new ConnectionPoolManager();

    private ConnectionPoolManager() {
        if (instance != null) {
            throw new RuntimeException("The Instance of ConnectionPoolManager has been created.");
        }
    }

    public static ConnectionPoolManager getInstance() {
        return instance;
    }
}

class GlobalObject {
    private static GlobalObject instance = null;

    private GlobalObject() {
        if (instance != null) {
            throw new RuntimeException("The Instance of GlobalObject has been created.");
        }
    }

    public static GlobalObject getInstance() {
        if (instance == null) {
            instance = new GlobalObject();
        }
        return instance;
    }
}

class QueryFunctions {
    private QueryFunctions() {
        // 静态内部类将报错(静态内部类初始化后于构造函数)
//        throw new RuntimeException("The Instance of QueryFunctions has been created.");
    }

    public static QueryFunctions getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final QueryFunctions INSTANCE = new QueryFunctions();
    }
}


public class SingleInstance implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 6: ");
        System.out.println("ConnectionPoolManager ->" + ConnectionPoolManager.getInstance().getClass());
        System.out.println("GlobalObject ->" + GlobalObject.getInstance().getClass());
        System.out.println("QueryFunctions ->" + QueryFunctions.getInstance().getClass());
    }
}
