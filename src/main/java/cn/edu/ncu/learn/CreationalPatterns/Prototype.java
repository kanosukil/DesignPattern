package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 设计一个客户类Customer，其中客户地址存储在地址类Address中，用浅克隆和深克隆分别实现Customer对象的复制并比较这两种克隆方式的异同。
 */

class Address {
    private String address;

    public Address() {
    }

    public Address(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

class Customer implements Cloneable {
    private String name;
    private Address address;

    public Customer() {
    }

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public Customer clone() {
        try {
            return (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    public Customer deepClone() {
        return new Customer(this.name, this.address);
    }

    public Customer deepCloneII() {
        return new Customer(this.name, new Address(this.address.getAddress()));
    }
}

public class Prototype implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 9: ");
        Customer original = new Customer("Hello", new Address("World"));
        Customer copy_shallow = original.clone();
        Customer copy_deep = original.deepClone();
        Customer copy_deepII = original.deepCloneII();

        System.out.println("Original == ShallowCopy ->" + (original == copy_shallow));
        System.out.println("Original == DeepCopy ->" + (original == copy_deep));
        System.out.println("Original == DeepCopyII ->" + (original == copy_deepII));

    }
}
