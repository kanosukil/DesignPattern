package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 有一家快餐店经营良好，并逐渐发展壮大，为了适合不同地方人的饮食习惯，创建了两大系列（相当于产品族）快餐，
 * + 北方系列和南方系列。
 * <p>
 * 1）每个系列分别由一个大厨掌勺，要求给出快餐店类的关系图及代码。
 * 2）在快餐店案例中添加一个美国系列快餐，给出类图并说明如何添加。
 */

interface FastFood {
    void contain();
}

interface Cook {
    FastFood cookFood();
}

class WestFastFood implements FastFood {

    @Override
    public void contain() {
        System.out.println("HamBurger, HotDog, FrenchFries...");
    }
}

class EastFastFood implements FastFood {

    @Override
    public void contain() {
        System.out.println("Dumplings, Traditional Chinese Rice-pudding, Steamed Buns");
    }
}

class WestCook implements Cook {

    @Override
    public FastFood cookFood() {
        return new WestFastFood();
    }
}

class EastCook implements Cook {

    @Override
    public FastFood cookFood() {
        return new EastFastFood();
    }
}

class CookSelector {
    public static Cook whichCook(String type) {
        return switch (type) {
            case "west", "West", "WEST" -> new WestCook();
            case "east", "East", "EAST" -> new EastCook();
            default -> null;
        };
    }
}

public class AbstractFactoryTwo implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 5: ");
        CookSelector.whichCook("west").cookFood().contain();
        CookSelector.whichCook("east").cookFood().contain();
    }
}
