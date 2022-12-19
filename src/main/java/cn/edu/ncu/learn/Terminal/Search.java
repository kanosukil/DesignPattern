package cn.edu.ncu.learn.Terminal;

import java.util.HashMap;
import java.util.Map;

/**
 * 搜索总入口(后续扩展)[外观模式]
 */
class Search {
    public static int inventoryOfGoods(Goods goods) {
        return GoodsSearch.searchInventory(goods);
    }

    public static Map<Goods, Integer> goodsWithInventory() {
        return GoodsSearch.searchByRepo(false);
    }

    public static Map<Category, Integer> categoryWithGoodsNumber() {
        return CategorySearch.statisticsByCategory();
    }

    public static Map<Goods, Integer> goodsInSpecialRepo(Repository repo) {
        return RepositorySearch.getSpecifiedInventory(repo);
    }
}

/*
(10)
 */

/**
 * 商品查询(按库存 & 按分类)
 */
class GoodsSearch {

    public static int searchInventory(Goods goods) {
        for (Repository repository : RepoManager.getAll()) {
            Integer stock = repository.getStock(goods);
            if (stock != null) {
                return stock;
            }
        }
        return -1;
    }

    public static Map<Goods, Integer> searchByRepo(boolean has) {
        Map<Goods, Integer> goods = new HashMap<>();
        for (Repository repository : RepoManager.getAll()) {
            goods.putAll(
                    has ?
                            repository.getInStock() :
                            repository.getStock()
            );
        }
        return goods;
    }

}

/**
 * 仓库搜索
 */
class RepositorySearch {
    public static Map<Goods, Integer> getSpecifiedInventory(Repository repo) {
        return repo.getStock();
    }
}

/**
 * 类别搜索
 */
class CategorySearch {
    public static Map<Category, Integer> statisticsByCategory() {
        HashMap<Category, Integer> map = new HashMap<>();
        for (Category category : CategoryManager.getAll()) {
            map.put(category, category.getContain().size());
        }
        return map;
    }
}
