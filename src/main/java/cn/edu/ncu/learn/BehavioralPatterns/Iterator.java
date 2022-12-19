package cn.edu.ncu.learn.BehavioralPatterns;

import cn.edu.ncu.learn.Main;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * 某教务管理系统中一个班级(Class)包含多个学生(Student)，使用Java内置迭代器实现对学生信息的遍历，
 * 要求按学生由大到小的次序输出学生信息。用Java实现该过程。
 */
class Student {
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Class {
    private final List<Student> students = new ArrayList<>();
    private int index = 0;

    public Class() {
    }

    public Class(List<Student> students) {
        this.students.addAll(students);
    }

    private void sort() {
        students.sort(Comparator.comparingInt(Student::getAge));
    }

    public void setStudents(List<Student> students) {
        this.students.addAll(students);
    }

    public boolean hasNext() {
        return index != students.size();
    }

    public Student next() {
        return students.get(index++);
    }
}


public class Iterator implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 3: ");
        Faker faker = new Faker(Locale.CHINA);

        Class cl = new Class(new ArrayList<>() {{
            for (int i = 0; i < 10; i++) {
                add(
                        new Student(
                                faker.name().fullName(),
                                faker.number().randomDigitNotZero()
                        )
                );
            }
        }});

        while (cl.hasNext()) {
            System.out.println(cl.next().toString());
        }
    }
}
