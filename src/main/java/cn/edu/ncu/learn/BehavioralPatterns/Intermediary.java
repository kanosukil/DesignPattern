package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;
import cn.edu.ncu.learn.Util.RandomUtil;
import com.github.javafaker.Faker;


/**
 * 使用中介者模式来说明联合国的作用，要求绘制相应的类图并分析每个类的作用
 * （注：可以将联合国定义为抽象中介者类，
 * 联合国下属机构如WTO、WFC、WHO等作为具体中介者类，
 * 国家可以作为抽象同事类，
 * 而将中国、美国、日本、英国等国家作为具体同事类）。
 */

enum UN {
    WTO, WFC, WHO;

    private final String theme = this.name();

    public void action(Nation nation, String action) {
        System.out.println(nation.getName() + "[" + theme + "]" + action);
    }
}

enum Nation {
    China, America, Japan, England;
    private final String name = this.name();

    public String getName() {
        return name;
    }

    public void action(UN unit, String action) {
        unit.action(this, action);
    }
}


public class Intermediary implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 4: ");

        for (Nation nation : Nation.values()) {
            nation.action(get(), Faker.instance().pokemon().name());
        }
    }

    private UN get() {
        return UN.values()[RandomUtil.getInt(UN.values().length)];
    }
}
