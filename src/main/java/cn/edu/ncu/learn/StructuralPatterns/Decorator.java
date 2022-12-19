package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;


/**
 * 某图书管理系统中，书籍类（Book）具有借书方法borrowBook（）和还书方法returnBook（）。
 * 现需要动态给书籍对象添加冻结方法freeze（）和遗失方法lose（）。使用装饰模式设计该系统。
 */
interface Book {
    boolean borrowBook(String username);

    boolean returnBook(String username);
}

class BookImpl implements Book {
    private final String name;

    public BookImpl(String name) {
        this.name = name;
    }

    @Override
    public boolean borrowBook(String username) {
        System.out.printf("%s borrowed %s\n", username, name);
        return true;
    }

    @Override
    public boolean returnBook(String username) {
        System.out.printf("%s return %s\n", username, name);
        return true;
    }
}

abstract class ExtensionBook implements Book {
    private final Book book;
    protected boolean isFreeze = false;
    protected boolean isLose = false;

    protected ExtensionBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean borrowBook(String username) {
        return book.borrowBook(username);
    }

    @Override
    public boolean returnBook(String username) {
        return book.returnBook(username);
    }
}

class ExtensionBookImpl extends ExtensionBook {

    public ExtensionBookImpl(String bookName) {
        super(new BookImpl(bookName));
    }

    @Override
    public boolean borrowBook(String username) {
        if (!isFreeze ^ !isLose) {
            if (super.borrowBook(username)) {
                freeze();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean returnBook(String username) {
        if (isFreeze ^ isLose) {
            if (super.returnBook(username)) {
                lose();
                return true;
            }
        }
        return false;
    }


    private void freeze() {
        isFreeze = !isFreeze;
    }

    private void lose() {
        isLose = !isLose;
    }
}


public class Decorator implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 4: ");

        Book b1 = new BookImpl("book1");
        Book b2 = new ExtensionBookImpl("book2");

        b1.borrowBook("a1");
        b1.borrowBook("a2");
        b1.returnBook("a3");
        b1.returnBook("a4");

        b2.borrowBook("a1");
        b2.borrowBook("a2");
        b2.returnBook("a3");
        b2.returnBook("a4");

    }
}
