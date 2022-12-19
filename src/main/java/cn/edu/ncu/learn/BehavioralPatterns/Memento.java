package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;
import com.alibaba.fastjson2.JSON;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

/**
 * 改进“用户信息操作撤销”实例，使得系统可以实现多次撤销操作（可以使用集合对象如HashMap、ArrayList等来实现）。
 */

class Memo {
    private final String state;

    public Memo(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}


class User {
    private String name;
    private int age;
    private String pokemon;

    public User(String name, int age, String pokemon) {
        this.name = name;
        this.age = age;
        this.pokemon = pokemon;
    }

    public Memo save() {
        return new Memo(JSON.toJSONString(this));
    }

    public void reload(Memo memo) {
        User reloadUser = JSON.parseObject(memo.getState(), this.getClass());
        this.name = reloadUser.name;
        this.age = reloadUser.age;
        this.pokemon = reloadUser.pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPokemon() {
        return pokemon;
    }

    public void setPokemon(String pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pokemon='" + pokemon + '\'' +
                '}';
    }
}

class MemoTaker {
    private final List<Memo> memos = new ArrayList<>();

    public int getCount() {
        return memos.size();
    }

    public void add(Memo memo) {
        memos.add(memo);
    }

    public Memo get(int index) {
        return memos.get(index);
    }
}

public class Memento implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 5: ");
        Faker faker = new Faker();
        User user = new User(
                faker.name().fullName(),
                faker.number().numberBetween(1, 100),
                faker.pokemon().name());
        MemoTaker taker = new MemoTaker();

        System.out.println(user);
        taker.add(user.save());

        user.setPokemon(faker.pokemon().name());
        System.out.println(user);
        taker.add(user.save());

        user.setName(faker.name().fullName());
        System.out.println(user);
        taker.add(user.save());

        user.reload(taker.get(taker.getCount() - 2));
        System.out.println(user);
        taker.add(user.save());
    }
}
