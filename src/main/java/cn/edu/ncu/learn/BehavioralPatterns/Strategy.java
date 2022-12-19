package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;
import com.github.javafaker.Faker;

/**
 * 设计一个网上书店，
 * 该系统中所有的计算机类图书(ComputerBook)每本都有10%的折扣，
 * 所有的语言类图书(LanguageBook)每本都有2元的折扣，
 * 小说类图书(NovelBook)每100元有10元的折扣。
 * <p>
 * 现使用策略模式来设计该系统，绘制类图并编程实现。
 */

abstract class DiscountBook {
    public abstract void showPrice(String name, int price);
}

class ComputerBook extends DiscountBook {
    @Override
    public void showPrice(String name, int price) {
        var discount = price * 0.1;
        System.out.println(name + ":" + (price - discount));
    }
}

class LanguageBook extends DiscountBook {
    @Override
    public void showPrice(String name, int price) {
        var discount = 2;
        System.out.println(name + ":" + (price - discount));
    }
}

class NovelBook extends DiscountBook {
    @Override
    public void showPrice(String name, int price) {
        var discount = 10 * (price / 100);
        System.out.println(name + ":" + (price - discount));
    }
}

class SContext {
    private DiscountBook book;

    public SContext(DiscountBook book) {
        this.book = book;
    }

    public void setBook(DiscountBook book) {
        this.book = book;
    }

    public void execute(String name, int price) {
        book.showPrice(name, price);
    }
}

public class Strategy implements Main.DoAction {
    private final Faker faker = Faker.instance();

    @Override
    public void method() {
        System.out.println("Question 8: ");
        SContext context = new SContext(new ComputerBook());
        doo(context);
        context.setBook(new LanguageBook());
        doo(context);
        context.setBook(new NovelBook());
        doo(context);
    }

    private void doo(SContext context) {
        for (int i = 0; i < 5; i++) {
            context.execute(faker.book().title(), faker.number().numberBetween(10, 100000));
        }
    }
}
