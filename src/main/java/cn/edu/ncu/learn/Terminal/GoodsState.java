package cn.edu.ncu.learn.Terminal;

/*
(11)
 */

/**
 * 事务类型
 */
enum BusinessType {
    VACATE, MEETING, CONFERENCE, OFFICE
}

/**
 * 请求处理者
 */
interface ApplyProcessor {
    void response(BusinessType type);
}

/**
 * 申请者
 */
interface Applicant {
    void request(BusinessType type);
}

/*
(8)
 */

/**
 * 商品状态
 */
interface GoodsState {
    void change(GoodsContext context);
}


/**
 * 售罄状态
 */
class SoldOut implements GoodsState {
    @Override
    public void change(GoodsContext context) {
        System.out.println("The Goods status has changed. Sold Out -> Plenty");
        context.setState(new Plenty());
    }

    @Override
    public String toString() {
        return "Goods Sold Out.";
    }
}

/**
 * 充足状态
 */
class Plenty implements GoodsState {

    @Override
    public void change(GoodsContext context) {
        System.out.println("The Goods status has changed. Plenty -> Sold Out");
        context.setState(new SoldOut());
    }

    @Override
    public String toString() {
        return "Goods Plenty.";
    }
}

/**
 * 状态管理(商品)
 */
class GoodsContext {
    private GoodsState state;

    public GoodsContext() {
        state = new Plenty();
    }

    public GoodsState getState() {
        return state;
    }

    public void setState(GoodsState state) {
        this.state = state;
    }

    public void action() {
        state.change(this);
    }
}