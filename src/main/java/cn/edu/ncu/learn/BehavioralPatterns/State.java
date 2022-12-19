package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;
import cn.edu.ncu.learn.Util.RandomUtil;

/**
 * 某纸牌游戏软件中，人物角色具有
 * 1. 入门级(Primary)、
 * 2. 熟练级(Secondary)、
 * 3. 高手级(Professional)
 * 4. 骨灰级(Final)
 * 四种等级
 * <p>
 * 角色的等级与其积分对应，游戏胜利将增加积分，失败则扣除积分。
 * 1. 入门级具有最基本的游戏功能play()，
 * 2. 熟练级增加了游戏胜利积分加倍功能doubleScore()，
 * 3. 高手级在熟练级基础上再增加换牌功能changeCards()，
 * 4. 骨灰级在高手级基础上再添加偷看他人的牌功能peekCards()。
 * <p>
 * 现使用状态模式来设计该系统，绘制类图并编程实现。
 */

abstract class Level {
    public static final int PRIMARY = 0;
    public static final int SECONDARY = 10;
    public static final int PROFESSIONAL = 50;
    public static final int FINAL = 500;
    protected int points;

    protected Level(int points) {
        this.points = points;
    }

    public boolean play(Context context) {
        if (points < PRIMARY) {
            throw new IllegalStateException("Ban Games");
        }

        boolean ans = RandomUtil.getBoolean();
        if (ans) {
            points += 5;
        } else {
            points -= 5;
        }

        if (points < SECONDARY) {
            context.setState(new Primary(points));
        } else if (points < PROFESSIONAL) {
            context.setState(new Secondary(points));
        } else if (points < FINAL) {
            context.setState(new Professional(points));
        } else {
            context.setState(new Final(points));
        }
        return ans;
    }
}

class Context {
    private Level state;

    public Context() {
        state = new Primary(0);
    }

    public Level getState() {
        return state;
    }

    public void setState(Level state) {
        this.state = state;
    }

    public void action() {
        state.play(this);
    }
}

class Primary extends Level {

    public Primary(int points) {
        super(points);
    }

    @Override
    public boolean play(Context context) {
        return super.play(context);
    }

    @Override
    public String toString() {
        return "Primary: " + points;
    }
}

class Secondary extends Level {

    public Secondary(int points) {
        super(points);
    }

    @Override
    public boolean play(Context context) {
        boolean ans = super.play(context);
        if (ans) {
            int i = context.getState().points + 5;
            context.setState(i < PROFESSIONAL ? new Secondary(i) : new Professional(i));
        }
        return ans;
    }


    @Override
    public String toString() {
        return "Secondary: " + points;
    }
}

class Professional extends Level {
    public Professional(int points) {
        super(points);
    }

    @Override
    public boolean play(Context context) {
        System.out.println("You can change the cards.");
        boolean ans = super.play(context);
        if (ans) {
            int i = context.getState().points + 5;
            context.setState(i < FINAL ? new Professional(i) : new Final(i));
        }
        return ans;
    }

    @Override
    public String toString() {
        return "Professional: " + points;
    }
}

class Final extends Level {
    public Final(int points) {
        super(points);
    }

    @Override
    public boolean play(Context context) {
        System.out.println("You can change the cards.");
        System.out.println("You can peek the others cards");
        boolean ans = super.play(context);
        if (ans) {
            context.setState(new Final(context.getState().points + 5));
        }
        return ans;
    }

    @Override
    public String toString() {
        return "Final: " + points;
    }
}

public class State implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 7: ");
        Context context = new Context();
        try {
            for (int i = 0; i < 100; i++) {
                context.action();
                System.out.println(context.getState().toString());
            }
        } catch (IllegalStateException ise) {
            System.out.println("You don't have any points.");
        }

    }
}
