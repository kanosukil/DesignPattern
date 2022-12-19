package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;
import cn.edu.ncu.learn.Util.RandomUtil;

/**
 * 某物资管理系统中物资采购需要分级审批，
 * 1. 主任可以审批1万元及以下的采购单，
 * 2. 部门经理可以审批5万元及以下的采购单，
 * 3. 副总经理可以审批10万元及以下的采购单，
 * 4. 总经理可以审批20万元及以下的采购单，
 * 5. 20万元以上的采购单需要开会确定。
 * <p>
 * 现使用职责链模式设计该系统，绘制类图并编程实现。
 */

abstract class ChargeLeader {
    protected final int level;
    protected ChargeLeader nextLeader;

    protected ChargeLeader(int level, ChargeLeader leader) {
        this.level = level;
        nextLeader = leader;
    }

    public void judge(int number) {
        int canUseNumber = levelToNumber();
        if (number <= canUseNumber) {
            pass();
        } else {
            if (nextLeader != null) {
                nextLeader.judge(number);
            } else {
                throw new IllegalStateException("Out of Max Boss Can Judge Number.");
            }
        }
    }

    private int levelToNumber() {
        return switch (level) {
            case 1 -> 1;
            case 2 -> 5;
            case 3 -> 10;
            case 4 -> 20;
            default -> throw new IllegalStateException("Unexpected value: " + level);
        };
    }

    abstract protected void pass();
}

class Leader extends ChargeLeader {

    public Leader(int level, ChargeLeader leader) {
        super(level, leader);
    }

    @Override
    protected void pass() {
        System.out.println("The Leader(level:" + level + ") pass the number.");
    }
}

public class ResponsibilityChain implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 1: ");
        ChargeLeader gManager = new Leader(4, null);
        ChargeLeader dgManager = new Leader(3, gManager);
        ChargeLeader dManager = new Leader(2, dgManager);
        ChargeLeader director = new Leader(1, dManager);

        try {
            for (int i = 0; i < 10; i++) {
                director.judge(RandomUtil.getInt(25));
            }
        } catch (IllegalStateException ise) {
            System.out.println("Need to have a meeting to judge.");
        }
    }
}
