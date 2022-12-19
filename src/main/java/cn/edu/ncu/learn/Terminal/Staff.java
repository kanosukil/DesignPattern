package cn.edu.ncu.learn.Terminal;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/*
(1)(2)
 */

/**
 * 部门
 */
enum Department {
    Information, Personnel, Sales;
    private final Set<Staff> Employees = new HashSet<>();

    /**
     * 添加员工
     *
     * @param addStaff  需要添加的员工
     * @param yourStaff 人事部门员工
     */
    public static void addEmployee(Staff addStaff, Staff yourStaff) {
        if (yourStaff.getDepartment() == Department.Personnel) {
            Level addStaffLevel = addStaff.getLevel();
            if (addStaffLevel == Level.Employee
                    || addStaffLevel == Level.AreaManager) {
                addToEmployee(addStaff);
            } else {
                if (yourStaff.getLevel() != Level.DepartmentManager) {
                    System.out.println("Lack of permission!");
                } else {
                    addToEmployee(addStaff);
                }
            }
        } else {
            System.out.println("Please apply first!");
        }
    }

    private static void addToEmployee(Staff staff) {
        switch (staff.getDepartment()) {
            case Information -> Information.Employees.add(staff);
            case Personnel -> Personnel.Employees.add(staff);
            case Sales -> Sales.Employees.add(staff);
        }
    }

    /**
     * 移除员工
     *
     * @param removeStaff 需要移除的员工
     * @param yourStaff   人事部门员工
     */
    public static void removeEmployee(Staff removeStaff, Staff yourStaff) {
        if (yourStaff.getDepartment() == Department.Personnel) {
            Level addStaffLevel = removeStaff.getLevel();
            if (addStaffLevel == Level.Employee
                    || addStaffLevel == Level.AreaManager) {
                removeFromEmployee(removeStaff);
            } else {
                if (yourStaff.getLevel() != Level.DepartmentManager) {
                    System.out.println("Lack of permission!");
                } else {
                    removeFromEmployee(removeStaff);
                }
            }
        } else {
            System.out.println("Please apply first!");
        }
    }

    private static void removeFromEmployee(Staff staff) {
        switch (staff.getDepartment()) {
            case Information -> Information.Employees.remove(staff);
            case Personnel -> Personnel.Employees.remove(staff);
            case Sales -> Sales.Employees.remove(staff);
        }
    }

    /**
     * 查找员工
     *
     * @param account 查找员工名
     * @return 查找结果
     */
    public static Staff findEmployee(String account) {
        AtomicReference<Staff> ans = new AtomicReference<>(null);
        Information.Employees.parallelStream().forEach(it -> {
            if (ans.get() == null && Objects.equals(it.getAccount(), account)) {
                ans.set(it);
            }
        });
        Personnel.Employees.parallelStream().forEach(it -> {
            if (ans.get() == null && Objects.equals(it.getAccount(), account)) {
                ans.set(it);
            }
        });
        Sales.Employees.parallelStream().forEach(it -> {
            if (ans.get() == null && Objects.equals(it.getAccount(), account)) {
                ans.set(it);
            }
        });
        return ans.get();
    }

}

/**
 * 员工等级
 */
enum Level implements ApplyProcessor {
    GeneralManager, DepartmentManager, AreaManager, Employee;

    /**
     * 所有员工都可以执行的
     *
     * @param account 员工名
     * @return 员工实体信息
     */
    public Staff read(String account) {
        return Department.findEmployee(account);
    }


    @Override
    public void response(BusinessType type) {
        if (this == Employee) {
            System.out.println("Can`t operate this request.");
            return;
        }
        switch (type) {
            case OFFICE -> System.out.println("Notice the " + this.name() + " with the office appliance request");
            case CONFERENCE -> System.out.println("Notice the " + this.name() + " with the hold a meeting request");
            case MEETING -> System.out.println("Notice the " + this.name() + " with the conference room request");
            case VACATE -> System.out.println("Notice the " + this.name() + " with the leave request");
        }
    }
}

/*
(4)
 */

/**
 * 联络地址
 */
class Contact {
    private String phone;
    private String qq;
    private String address;

    public Contact(String phone) {
        this.phone = phone;
    }

    public Contact(String phone, String qq) {
        this.phone = phone;
        this.qq = qq;
    }

    public Contact(String phone, String qq, String address) {
        this.phone = phone;
        this.qq = qq;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

/**
 * 员工信息
 */
class StaffInfo {
    private boolean male;
    private Date birth;
    private Date signUp;
    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getSignUp() {
        return signUp;
    }

    public void setSignUp(Date signUp) {
        this.signUp = signUp;
    }
}

/**
 * 员工实体
 */
class Staff extends GoodsNumberObserver implements Cloneable, Applicant {
    private final List<ApplyProcessor> apObserver = new ArrayList<>();
    private String account;
    private String password;
    private Department department;
    private Level level;
    private StaffInfo info;

    public Staff(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void knowTheAP(ApplyProcessor ap) {
        apObserver.add(ap);
    }

    public void deleteTheAP(ApplyProcessor ap) {
        apObserver.remove(ap);
    }

    public void notifyAP(BusinessType type) {
        for (ApplyProcessor ap : apObserver) {
            ap.response(type);
        }
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public StaffInfo getInfo() {
        return info;
    }

    public void setInfo(StaffInfo info) {
        this.info = info;
    }

    public void addRepo(Repository repo) {
        if (this.repo == null) {
            this.repo = new ArrayList<>();
        }
        this.repo.add(repo);
        if (department == Department.Sales) {
            repo.attach(this);
        }
    }

    public boolean removeRepo(Repository repo) {
        if (this.repo != null) {
            repo.leave(this);
            return this.repo.remove(repo);
        } else {
            return false;
        }
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StaffInfo getStaffInfo() {
        return info;
    }

    @Override
    public Staff clone() {
        try {
            return (Staff) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * (5)
     */
    public Staff deepClone() {
        try {
            Staff staff = new Staff(account, password);
            staff.department = department;
            staff.level = level;
            staff.info = new StaffInfo();
            staff.info.setMale(info.isMale());
            staff.info.setBirth(info.getBirth());
            Contact contact = info.getContact();
            staff.info.setContact(new Contact(contact.getPhone(), contact.getQq(), contact.getAddress()));
            staff.info.setSignUp(info.getSignUp());
            return staff;
        } catch (Exception ex) {
            throw new IllegalStateException("Deep Clone Exception");
        }
    }

    @Override
    void update(Goods goods) {
        if (department == Department.Sales) {
            System.out.println("The Goods has bean sold out.");
            goods.getContext().action();
        }
    }

    @Override
    public void request(BusinessType type) {
        notifyAP(type);
    }
}

