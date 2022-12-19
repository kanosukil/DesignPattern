package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 一杯咖啡，有中杯和大杯之分，同时还有加奶和不加奶之分。
 * 如果用单纯的继承，这四个具体实现(中杯大杯加奶不加奶)之间有概念重叠，
 * 因为有中杯加奶，也有中杯不加奶，如果再在中杯这一层再实现两个继承,很显然混乱，扩展性极差。请使用桥接模式进行设计。
 */

interface CoffeeAPI {
    void drink();
}

interface Coffee {
    void drink();

}

abstract class AbsCoffee implements CoffeeAPI {
    private final boolean hasMilk;
    private final boolean isBig;

    protected AbsCoffee(boolean hasMilk, boolean isBig) {
        this.hasMilk = hasMilk;
        this.isBig = isBig;
    }

    @Override
    public void drink() {
        System.out.println("This is a " + (isBig ? "big" : "middle") + " coffee" + (hasMilk ? " with milk" : ""));
    }
}

class BMCoffee extends AbsCoffee {
    public BMCoffee() {
        super(true, true);
    }
}

class MMCoffee extends AbsCoffee {
    public MMCoffee() {
        super(true, false);
    }
}

class BNCoffee extends AbsCoffee {
    public BNCoffee() {
        super(false, true);
    }
}

class MNCoffee extends AbsCoffee {
    public MNCoffee() {
        super(false, false);
    }
}

class CoffeeImpl implements Coffee {
    private final CoffeeAPI api;

    public CoffeeImpl(CoffeeAPI api) {
        this.api = api;
    }

    public void drink() {
        api.drink();
    }

}

public class BridgeTwo implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 10: ");
        Coffee bm = new CoffeeImpl(new BMCoffee());
        Coffee bn = new CoffeeImpl(new BNCoffee());
        Coffee mm = new CoffeeImpl(new MMCoffee());
        Coffee mn = new CoffeeImpl(new MNCoffee());

        bm.drink();
        bn.drink();
        mm.drink();
        mn.drink();
    }
}
