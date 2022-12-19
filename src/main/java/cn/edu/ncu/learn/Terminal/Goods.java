package cn.edu.ncu.learn.Terminal;

import java.util.*;

/**
 * 商品种类
 */
class Category {
    private final String name;
    private final Staff manager;
    private final Set<Goods> contain = new HashSet<>();

    private Category(String name, Staff manager) {
        this.name = name;
        this.manager = manager;
    }

    private Category(String name, Staff manager, Set<Goods> goods) {
        this.name = name;
        this.manager = manager;
        this.contain.addAll(goods);
    }

    public static Category getInstance(String name, Staff manager, Goods goods) {
        return new Category(name, manager, new HashSet<>(1, 1f) {{
            add(goods);
        }});
    }

    public static Category getInstance(String name, Staff manager) {
        return new Category(name, manager);
    }

    public String getName() {
        return name;
    }

    public Staff getManager() {
        return manager;
    }

    private boolean checkRight(Staff applicant) {
        if (!Objects.deepEquals(applicant, manager)) {
            System.out.println("You don`t have right to operate.");
            return false;
        } else {
            return true;
        }
    }

    public void addGoods(Goods goods, Staff applicant) {
        if (checkRight(applicant)) {
            this.contain.add(goods);
        }
    }

    public boolean isExist(Goods goods) {
        return contain.contains(goods);
    }

    public Set<Goods> getContain() {
        return contain;
    }

    public boolean removeGoods(Goods goods, Staff applicant) {
        if (checkRight(applicant)) {
            return contain.remove(goods);
        } else {
            return false;
        }
    }
}

/*
(6)
 */

/**
 * 商品信息
 */
class GoodsInfo {
    private double price;
    private String originalArea;
    private String img;
    private String specifications;
    private String description;

    public GoodsInfo() {
    }

    public GoodsInfo(
            double price,
            String originalArea,
            String img,
            String specifications,
            String description) {
        this.price = price;
        this.originalArea = originalArea;
        this.img = img;
        this.specifications = specifications;
        this.description = description;
    }

    public String getOriginalArea() {
        return originalArea;
    }

    public void setOriginalArea(String originalArea) {
        this.originalArea = originalArea;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

/**
 * 商品实体
 */
class Goods {
    private final GoodsContext context;
    private Category category;
    private String id;
    private String name;
    private GoodsInfo info;

    public Goods() {
        this.context = new GoodsContext();
    }

    public Goods(String name) {
        this.name = name;
        this.context = new GoodsContext();
    }

    public GoodsInfo getInfo() {
        return info;
    }

    public void setInfo(GoodsInfo info) {
        this.info = info;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GoodsContext getContext() {
        return context;
    }

}

/*
(8)
 */

/**
 * 仓库实体
 */
class Repository {
    private final List<GoodsNumberObserver> observers = new ArrayList<>();
    private final Map<Goods, Integer> store = new HashMap<>();
    private String name;
    private Staff chargePerson;

    public Repository(String name, Staff manager) {
        this.name = name;
        this.chargePerson = manager;
    }

    public void attach(GoodsNumberObserver observer) {
        observers.add(observer);
    }

    public void leave(GoodsNumberObserver observer) {
        observers.remove(observer);
    }

    public void notifyObserver(Goods goods) {
        observers.forEach(it -> it.update(goods));
    }


    public String getName() {
        return name;
    }

    public void setName(String name, Staff applicant) {
        if (applicant != chargePerson) {
            System.out.println("You don`t have the right to change!");
            return;
        }
        this.name = name;
    }

    public Staff getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(Staff chargePerson, Staff applicant) {
        if (applicant != chargePerson) {
            System.out.println("You don`t have the right to change!");
            return;
        }
        this.chargePerson = chargePerson;
    }

    /**
     * Store Relation
     */
    public Integer getStock(Goods goods) {
        return store.get(goods);
    }

    public boolean sold(Goods goods, Staff applicant) {
        return sold(goods, 1, applicant);
    }

    public void setSoldOut(Goods goods, Staff applicant) {
        if (applicant.getDepartment() != Department.Sales) {
            System.out.println("You don`t have the right to change!");
            return;
        }
        goods.getContext().action();
    }


    public Map<Goods, Integer> getInStock() {
        Map<Goods, Integer> set = new HashMap<>();
        for (Goods goods : store.keySet()) {
            Integer number = store.get(goods);
            if (number > 0) {
                set.put(goods, number);
            }
        }
        return set;
    }

    public Map<Goods, Integer> getStock() {
        return store;
    }

    public boolean sold(Goods goods, int number, Staff applicant) {
        Integer num = store.get(goods);
        if (num == 0 || num < number) {
            return false;
        } else {
            addGoods(goods, num - number, applicant);
            if (num - number == 0) {
                notifyObserver(goods);
            }
            return true;
        }
    }

    public void updateNumber(Goods goods, int number, Staff applicant) {
        addGoods(goods, number, applicant);
    }

    public void addGoods(Goods goods, int number, Staff applicant) {
        if (applicant.getDepartment() != Department.Sales) {
            System.out.println("You don`t have the right to change!");
            return;
        }
        if (number > 0 && goods.getContext().getState() instanceof SoldOut) {
            System.out.println("Goods has bean Supply.");
            goods.getContext().action();
        }
        store.put(goods, number);
    }

    public boolean remove(Goods goods, Staff applicant) {
        if (applicant.getDepartment() != Department.Sales) {
            System.out.println("You don`t have the right to change!");
            return false;
        }
        Integer aDefault = store.getOrDefault(goods, -1);
        if (aDefault != -1) {
            return false;
        } else {
            return aDefault.equals(store.remove(goods));
        }
    }
}

/**
 * 商品存量观察者
 */
abstract class GoodsNumberObserver {
    protected List<Repository> repo;

    abstract void update(Goods goods);
}

