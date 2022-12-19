package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;
import cn.edu.ncu.learn.Util.RandomUtil;

/**
 * 在游戏中，要求设计一个程序来画小人（person），要求小人有头（head）、身体（body）、两手（arm）、两脚（leg）就可以了，
 * 小人有高、矮之分，利用建造者模式进行设计，并画出其类关系图。
 */

interface BPBuilder {
    BPBuilder setHead(Head head);

    BPBuilder setBody(Body body);

    BPBuilder setArm(Arm arm);

    BPBuilder setArm(Arm arm1, Arm arm2);

    BPBuilder setLeg(Leg leg);

    BPBuilder setLeg(Leg leg1, Leg leg2);

    BPerson build();
}


abstract class Head {
    public static final double standard = 22.0;
    protected double headLength = 0.0;

    abstract double getHeadLength();
}

abstract class Body {
    public static final double standard = 43.0;
    protected double bodyLength = 0.0;

    abstract double getBodyLength();
}

abstract class Arm {
    public static final double standard = 69.0;
    protected double armLength = 0.0;

    abstract double getArmLength();
}

abstract class Leg {
    public static final double standard = 106.0;
    protected double legLength = 0.0;

    abstract double getLegLength();
}

class THead extends Head {
    public THead(double headLength) {
        if (headLength <= standard) {
            throw new RuntimeException("Illegal head length <= " + standard);
        }
        this.headLength = headLength;
    }

    @Override
    public double getHeadLength() {
        return headLength;
    }

    @Override
    public String toString() {
        return "Long Head (len:" + headLength + " )";
    }
}

class SHead extends Head {
    public SHead(double headLength) {
        if (headLength > standard) {
            throw new RuntimeException("Illegal head length > " + standard);
        }
        this.headLength = headLength;
    }

    @Override
    public double getHeadLength() {
        return headLength;
    }

    @Override
    public String toString() {
        return "Short Head (len:" + headLength + " )";
    }
}

class TBody extends Body {

    public TBody(double bodyLength) {
        if (bodyLength <= standard) {
            throw new RuntimeException("Illegal body length <= " + standard);
        }
        this.bodyLength = bodyLength;
    }

    @Override
    public double getBodyLength() {
        return bodyLength;
    }

    @Override
    public String toString() {
        return "Long Body (len:" + bodyLength + " )";
    }
}

class SBody extends Body {

    public SBody(double bodyLength) {
        if (bodyLength > standard) {
            throw new RuntimeException("Illegal body length > " + standard);
        }
        this.bodyLength = bodyLength;
    }

    @Override
    public double getBodyLength() {
        return bodyLength;
    }

    @Override
    public String toString() {
        return "Short Body (len:" + bodyLength + " )";
    }
}

class TArm extends Arm {

    public TArm(double armLength) {
        if (armLength <= standard) {
            throw new RuntimeException("Illegal body length <= " + standard);
        }
        this.armLength = armLength;
    }

    @Override
    double getArmLength() {
        return armLength;
    }

    @Override
    public String toString() {
        return "Long Arm (len:" + armLength + " )";
    }
}

class SArm extends Arm {

    public SArm(double armLength) {
        if (armLength > standard) {
            throw new RuntimeException("Illegal body length > " + standard);
        }
        this.armLength = armLength;
    }

    @Override
    double getArmLength() {
        return armLength;
    }

    @Override
    public String toString() {
        return "Short Arm (len:" + armLength + " )";
    }
}

class TLeg extends Leg {

    public TLeg(double legLength) {
        if (legLength <= standard) {
            throw new RuntimeException("Illegal body length <= " + standard);
        }
        this.legLength = legLength;
    }

    @Override
    double getLegLength() {
        return legLength;
    }

    @Override
    public String toString() {
        return "Long Leg (len:" + legLength + " )";
    }
}

class SLeg extends Leg {

    public SLeg(double legLength) {
        if (legLength > standard) {
            throw new RuntimeException("Illegal body length > " + standard);
        }
        this.legLength = legLength;
    }

    @Override
    double getLegLength() {
        return legLength;
    }

    @Override
    public String toString() {
        return "Short Leg (len:" + legLength + " )";
    }
}

class BPerson {
    private Head head = null;
    private Body body = null;
    private Arm arm1 = null;
    private Arm arm2 = null;
    private Leg leg1 = null;
    private Leg leg2 = null;

    public static BPBuilder builder() {
        return new BPBuilderImpl();
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setArm1(Arm arm1) {
        this.arm1 = arm1;
    }

    public void setArm2(Arm arm2) {
        this.arm2 = arm2;
    }

    public void setLeg1(Leg leg1) {
        this.leg1 = leg1;
    }

    public void setLeg2(Leg leg2) {
        this.leg2 = leg2;
    }

    public void showInfo() {
        if (head == null || body == null ||
                arm1 == null || arm2 == null ||
                leg1 == null || leg2 == null) {
            throw new RuntimeException("The Person has not Initialization");
        } else {
            System.out.println(
                    "The Person has" + head + ", " + body + ",\n" +
                            "one " + arm1 + ", one " + arm2 + "\n" +
                            "one " + leg1 + ", one " + leg2
            );
        }
    }

    static class BPBuilderImpl implements BPBuilder {
        private final BPerson person = new BPerson();

        private BPBuilderImpl() {
        }

        @Override
        public BPBuilder setHead(Head head) {
            person.setHead(head);
            return this;
        }

        @Override
        public BPBuilder setBody(Body body) {
            person.setBody(body);
            return this;
        }

        @Override
        public BPBuilder setArm(Arm arm) {
            person.setArm1(arm);
            person.setArm2(arm);
            return this;
        }

        @Override
        public BPBuilder setArm(Arm arm1, Arm arm2) {
            person.setArm1(arm1);
            person.setArm2(arm2);
            return this;
        }

        @Override
        public BPBuilder setLeg(Leg leg) {
            person.setLeg1(leg);
            person.setLeg2(leg);
            return this;
        }

        @Override
        public BPBuilder setLeg(Leg leg1, Leg leg2) {
            person.setLeg1(leg1);
            person.setLeg2(leg2);
            return this;
        }

        @Override
        public BPerson build() {
            return person;
        }
    }
}

class BPersonDirector {
    private BPBuilder person = null;

    public BPersonDirector(BPBuilder builder) {
        person = builder;
    }

    public BPerson construct(String type) {
        return switch (type.toLowerCase()) {
            case "tall", "long" -> sp();
            case "short" -> tp();
            default -> {
                throw new RuntimeException("Illegal Input BPerson Type");
            }
        };
    }

    private BPerson sp() {
        return person
                .setHead(new THead(RandomUtil.getDouble(Head.standard, Head.standard + 5.0)))
                .setBody(new TBody(RandomUtil.getDouble(Body.standard, Body.standard + 10.0)))
                .setArm(new TArm(RandomUtil.getDouble(Arm.standard, Arm.standard + 15.0)))
                .setLeg(new TLeg(RandomUtil.getDouble(Leg.standard, Leg.standard + 20.0)))
                .build();
    }

    private BPerson tp() {
        return person
                .setHead(new SHead(RandomUtil.getDouble(Head.standard - 5.0, Head.standard)))
                .setBody(new SBody(RandomUtil.getDouble(Body.standard - 10.0, Body.standard)))
                .setArm(new SArm(RandomUtil.getDouble(Arm.standard - 15.0, Arm.standard)))
                .setLeg(new SLeg(RandomUtil.getDouble(Leg.standard - 20.0, Leg.standard)))
                .build();
    }
}

public class Builder implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 7: ");
        BPersonDirector director = new BPersonDirector(BPerson.builder());
        director.construct("tall").showInfo();
        director.construct("short").showInfo();
    }
}
