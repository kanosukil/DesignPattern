package cn.edu.ncu.learn.Terminal;


/**
 * 单个商品价格策略
 */
interface GoodsPriceStrategy {
    double showFinalPrice(Goods goods);
}

/**
 * 销售展示
 */
interface SaleShow {
    void getResult(Repository repo);
}

/*
(9)
 */

/**
 * 展示工厂
 */
interface ShowFactory {
    SaleShow getShow();
}

/**
 * 节日1优惠价格
 */
class Festival1Price implements GoodsPriceStrategy {
    private final double discount;

    public Festival1Price() {
        this.discount = 0.9;
    }

    @Override
    public double showFinalPrice(Goods goods) {
        return goods.getInfo().getPrice() * discount;
    }
}

/**
 * 节日2优惠价格
 */
class Festival2Price implements GoodsPriceStrategy {
    private final double discount;

    public Festival2Price() {
        this.discount = 0.5;
    }

    @Override
    public double showFinalPrice(Goods goods) {
        return goods.getInfo().getPrice() * discount;
    }
}

/**
 * 周年庆优惠价格
 */
class AnniversaryPrice implements GoodsPriceStrategy {
    private final double discount;

    public AnniversaryPrice() {
        this.discount = 0.1;
    }

    @Override
    public double showFinalPrice(Goods goods) {
        return goods.getInfo().getPrice() * discount;
    }

}

/**
 * 单个商品策略执行器
 */
class PSContext {
    private GoodsPriceStrategy strategy;

    public PSContext(GoodsPriceStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(GoodsPriceStrategy strategy) {
        this.strategy = strategy;
    }

    public double payable(Goods goods) {
        return strategy.showFinalPrice(goods);
    }
}

/**
 * 通过 Web List 展示
 */
class WebListShow implements SaleShow {
    @Override
    public void getResult(Repository repo) {
        System.out.println("Web presentation of sales data."
                + repo.getName() + ": "
                + Search.goodsInSpecialRepo(repo));
    }
}

/**
 * 通过 Excel 展示
 */
class ExcelShow implements SaleShow {
    @Override
    public void getResult(Repository repo) {
        System.out.println("Excel presentation of sales data."
                + repo.getName() + ": "
                + Search.goodsInSpecialRepo(repo));
    }
}

/**
 * 通过柱状图展示
 */
class BarChartShow implements SaleShow {
    @Override
    public void getResult(Repository repo) {
        System.out.println("Bar chart presentation of sales data."
                + repo.getName() + ": "
                + Search.goodsInSpecialRepo(repo));
    }
}

/**
 * WebList 展示工厂
 */
class WebFactory implements ShowFactory {
    @Override
    public SaleShow getShow() {
        return new WebListShow();
    }
}

/**
 * Excel 展示工厂
 */
class ExcelFactory implements ShowFactory {
    @Override
    public SaleShow getShow() {
        return new ExcelShow();
    }
}

/**
 * 柱状图展示工厂
 */
class BarFactory implements ShowFactory {
    @Override
    public SaleShow getShow() {
        return new BarChartShow();
    }
}

/**
 * 展示类型选择器
 */
class ShowSelector {
    public static SaleShow getShow(String type) {
        return switch (type.toLowerCase()) {
            case "web", "w", "weblist", "web list", "wl" -> new WebFactory().getShow();
            case "excel", "e" -> new ExcelFactory().getShow();
            case "bar", "barchart", "bar chart", "b", "bc" -> new BarFactory().getShow();
            default -> null;
        };
    }
}

/**
 * 展示总出口(外观模式)
 */
class Show {
    private static PSContext context = null;

    public static void SaleShow(String type, Repository repo) {
        ShowSelector.getShow(type).getResult(repo);
    }

    public static void setPSContext(GoodsPriceStrategy strategy) {
        if (context == null) {
            context = new PSContext(strategy);
        } else {
            context.setStrategy(strategy);
        }
    }

    public static double PriceShow(Goods goods) {
        if (context == null) {
            Level.GeneralManager.response(BusinessType.CONFERENCE);
            return -1;
        } else {
            return context.payable(goods);
        }
    }
}
