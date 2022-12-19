package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

/**
 * 如果网上商店中商品（product）在名称（name）、价格（price）等方面有变化，系统能自动通知会员，将是网上商店区别传统商店的一大特色。
 * 如何设计实现？说明你所选择的设计模式，画出类关系图并指明各个类的角色。
 */
class Product {
    private final List<GoodsObserver> obs = new ArrayList<>();

    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyOb();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        notifyOb();
    }

    public void attach(GoodsObserver obs) {
        this.obs.add(obs);
    }

    public void leave(GoodsObserver ob) {
        obs.remove(ob);
    }

    private void notifyOb() {
        obs.forEach(it ->
                it.update(this)
        );
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

abstract class GoodsObserver {
    protected Product goods;

    public abstract void update(Product product);
}

class VIP extends GoodsObserver {
    private final String flg;

    public VIP(Product goods, String flag) {
        this.flg = flag;
        this.goods = goods;
        goods.attach(this);
    }

    @Override
    public void update(Product product) {
        System.out.println("VIP[" + flg + "]: " +
                "The Goods` Information has updated." +
                product);
    }
}

public class ObserverTwo implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 9: ");
        Faker faker = Faker.instance();
        Product product = new Product(faker.name().fullName(), faker.number().numberBetween(0, 10000000));
        new VIP(product, "v1");
        new VIP(product, "v2");
        new VIP(product, "v3");

        for (int i = 0; i < 10; i++) {
            product.setName(faker.name().username());
            product.setPrice(faker.number().numberBetween(0, 10000000));
        }
    }
}
