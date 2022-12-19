package cn.edu.ncu.learn.Terminal;

import java.util.*;

/**
 * 商品管理者
 */
class GoodsManager {
    private static final Map<String, Goods> map = new HashMap<>();

    public static Goods createGoods(String name, Staff applicant) {
        if (applicant.getDepartment() != Department.Sales) {
            System.out.println("You can`t do this operation.");
            return null;
        }
        Goods goods = map.getOrDefault(name, null);
        if (goods == null) {
            return map.put(name, new Goods(name));
        } else {
            System.out.println("The Goods has bean created.");
            return goods;
        }
    }

    public static Goods getGoods(String name) {
        return map.get(name);
    }

    public static Set<Goods> getAll() {
        Set<Goods> ans = new HashSet<>();
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }

    public static boolean removeGoods(String name, Staff applicant) {
        return removeGoods(map.get(name), applicant);
    }

    public static boolean removeGoods(Goods goods, Staff applicant) {
        if (goods == null ||
                applicant.getDepartment() != Department.Sales) {
            System.out.println("You can`t do this operation.");
            return false;
        }
        return map.remove(goods.getName(), goods);
    }


}

/*
(7)
 */

/**
 * 类别管理者
 */
class CategoryManager {
    private static final Map<String, Category> map = new HashMap<>();

    private static boolean checkRight(Staff applicant) {
        if (applicant.getDepartment() != Department.Sales &&
                applicant.getLevel() != Level.DepartmentManager) {
            System.out.println("You don`t have right to operate.");
            return false;
        } else {
            return true;
        }
    }

    public static Category createCategory(String name, Staff applicant) {
        if (checkRight(applicant)) {
            Category aDefault = map.getOrDefault(name, null);
            if (aDefault == null) {
                return map.put(name, Category.getInstance(name, applicant));
            } else {
                System.out.println("The Category has bean created.");
                return aDefault;
            }
        } else {
            return null;
        }
    }

    public static Category get(String name) {
        return map.get(name);
    }

    public static List<Category> getAll() {
        return new ArrayList<>(map.values().size()) {{
            addAll(map.values());
        }};
    }

    public static boolean removeCategory(String name, Staff applicant) {
        return removeCategory(map.get(name), applicant);
    }

    public static boolean removeCategory(Category category, Staff applicant) {
        if (category != null && checkRight(applicant)) {
            return Objects.deepEquals(map.remove(category.getName()), category);
        } else {
            return false;
        }
    }
}

/**
 * 仓库管理者
 */
class RepoManager {
    private static final Map<String, Repository> map = new HashMap<>();

    public static Repository createRepo(Staff applicant, String name) {
        if (applicant.getDepartment() == Department.Sales
                && applicant.getLevel() == Level.DepartmentManager) {
            Repository aDefault = map.getOrDefault(name, null);
            if (aDefault != null) {
                System.out.println("The Repo has bean created!");
                return aDefault;
            } else {
                map.put(name, new Repository(name, applicant));
                return map.get(name);
            }
        } else {
            System.out.println("Lack of permission.");
            return null;
        }
    }

    public static Repository getRepo(String name) {
        return map.get(name);
    }

    public static Set<Repository> getAll() {
        Set<Repository> ans = new HashSet<>();
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        return ans;
    }

    public static boolean removeRepo(Staff applicant, String name) {
        return removeRepo(applicant, map.get(name));
    }

    public static boolean removeRepo(Staff applicant, Repository repo) {
        try {
            if (repo == null) {
                System.out.println("Repo is not exist.");
                throw new NullPointerException("Repo is null.");
            }
            if (!Objects.deepEquals(repo.getChargePerson(), applicant)) {
                System.out.println("You don`t have right to operate.");
                throw new IllegalStateException("No permission");
            }
            return map.remove(repo.getName(), repo);
        } catch (Exception ex) {
            System.out.println("Remove Repo Exception." + ex.getMessage());
            return false;
        }
    }
}


/*
(3)
 */

/**
 * 用户(登录/注册)管理者
 */
class LoginManager {
    private static final Map<String, Map<String, Staff>> existence = new HashMap<>();

    public static Staff loginUp(String account, String password) {
        try {
            Staff value = new Staff(account, password);
            existence.put(account, Collections.singletonMap(password, value));
            return value;
        } catch (Exception ex) {
            System.out.println("User login up exception.");
            return null;
        }
    }

    public static Staff loginIn(String account, String password) {
        try {
            return existence.get(account).getOrDefault(password, null);
        } catch (Exception ex) {
            System.out.println("User login in exception.");
            return null;
        }
    }
}
