package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 某软件公司欲开发一个音频和视频播放软件，
 * 为了给用户使用提供方便，该播放软件提供了多种界面显示模式，如:
 * 1. 完整模式
 * 2. 精简模式
 * 3. 记忆模式
 * 4. 网络模式
 * ...
 * <p>
 * 在不同的显示模式下主界面的组成元素有所差异, 如:
 * 1. 在完整模式下将显示菜单、播放列表、主窗口、控制条等，
 * 2. 在精简模式下只显示主窗口和控制条，
 * 3. 在记忆模式下将显示主窗口、控制条、收藏列表等。
 * <p>
 * 现使用建造者模式设计该软件。
 */

interface Menu {
    void showMenu();
}

interface PlayList {
    void showList();
}

interface MainWindow {
    void showWin();
}

interface ControlBar {
    void showBar();
}

interface ViewMode {
    void show();

}

abstract class ModeBuilder {
    protected final AbsViewMode instance;

    protected ModeBuilder(AbsViewMode instance) {
        this.instance = instance;
    }

    public ModeBuilder setMenu(Menu menu) {
        instance.setMenu(menu);
        return this;
    }

    public ModeBuilder setPlayList(PlayList list) {
        instance.setList(list);
        return this;
    }

    public ModeBuilder setControlBar(ControlBar bar) {
        instance.setBar(bar);
        return this;
    }

    public ModeBuilder setMainWindow(MainWindow win) {
        instance.setWindow(win);
        return this;
    }

    public ViewMode build() {
        return instance;
    }
}

abstract class AbsViewMode implements ViewMode {
    protected Menu menu = null;
    protected PlayList list = null;
    protected ControlBar bar = null;
    protected MainWindow window = null;

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setList(PlayList list) {
        this.list = list;
    }

    public void setBar(ControlBar bar) {
        this.bar = bar;
    }

    public void setWindow(MainWindow window) {
        this.window = window;
    }
}

class FullMenu implements Menu {

    @Override
    public void showMenu() {
        System.out.println("Full Menu");
    }
}

class FullList implements PlayList {
    @Override
    public void showList() {
        System.out.println("Full Playlist");
    }
}

class CollectedList implements PlayList {

    @Override
    public void showList() {
        System.out.println("Memory Collected List");
    }
}

class FullBar implements ControlBar {

    @Override
    public void showBar() {
        System.out.println("Full Control Bar");
    }
}

class SimpleBar implements ControlBar {
    @Override
    public void showBar() {
        System.out.println("Simple Control Bar");
    }
}

class MemoryBar implements ControlBar {

    @Override
    public void showBar() {
        System.out.println("Memory Control Bar");
    }
}

class FullWin implements MainWindow {

    @Override
    public void showWin() {
        System.out.println("Full Main Window");
    }
}

class SimpleWin implements MainWindow {

    @Override
    public void showWin() {
        System.out.println("Simple Main Window");
    }
}

class MemoryWin implements MainWindow {
    @Override
    public void showWin() {
        System.out.println("Memory Main Window");
    }
}


class FullMode extends AbsViewMode {

    public static ModeBuilder builder() {
        return new FullModeBuilder(new FullMode());
    }

    @Override
    public void show() {
        if (menu == null || list == null || bar == null || window == null) {
            throw new RuntimeException("The Full Mode Component has not Initialized.");
        } else {
            System.out.println("This is Full Mode");
            menu.showMenu();
            list.showList();
            bar.showBar();
            window.showWin();
        }
    }

    static class FullModeBuilder extends ModeBuilder {
        private FullModeBuilder(FullMode instance) {
            super(instance);
        }
    }
}

class SimpleMode extends AbsViewMode {

    public static ModeBuilder builder() {
        return new SimpleModeBuilder(new SimpleMode());
    }

    @Override
    public void setMenu(Menu menu) {
        System.out.println("This is SimpleMode. Forbidden create Menu.");
    }

    @Override
    public void setList(PlayList list) {
        System.out.println("This is SimpleMode. Forbidden create PlayList.");
    }

    @Override
    public void show() {
        if (bar == null || window == null) {
            throw new RuntimeException("The Simple Mode Component has not Initialized.");
        } else {
            System.out.println("This is Simple Mode");
            bar.showBar();
            window.showWin();
        }
    }

    static class SimpleModeBuilder extends ModeBuilder {
        private SimpleModeBuilder(SimpleMode instance) {
            super(instance);
        }
    }

}

class MemoryMode extends AbsViewMode {
    public static ModeBuilder builder() {
        return new MemoryModeBuilder(new MemoryMode());
    }

    @Override
    public void setMenu(Menu menu) {
        System.out.println("This is MemoryMode. Forbidden create Menu.");
    }

    @Override
    public void show() {
        if (bar == null || window == null || list == null) {
            throw new RuntimeException("The Memory Mode Component has not Initialized.");
        } else {
            System.out.println("This is Memory Mode");
            list.showList();
            bar.showBar();
            window.showWin();
        }
    }

    static class MemoryModeBuilder extends ModeBuilder {
        private MemoryModeBuilder(MemoryMode instance) {
            super(instance);
        }
    }
}

class ViewModeDirector {
    private final ModeBuilder builder;

    public ViewModeDirector(ModeBuilder builder) {
        this.builder = builder;
    }

    private ViewMode full() {
        return builder
                .setMenu(new FullMenu())
                .setPlayList(new FullList())
                .setControlBar(new FullBar())
                .setMainWindow(new FullWin())
                .build();
    }

    private ViewMode simple() {
        return builder
                .setControlBar(new SimpleBar())
                .setMainWindow(new SimpleWin())
                .build();
    }

    private ViewMode memory() {
        return builder
                .setPlayList(new CollectedList())
                .setControlBar(new MemoryBar())
                .setMainWindow(new MemoryWin())
                .build();
    }

    public ViewMode construct(String type) {
        return switch (type) {
            case "full", "Full", "FULL", "F", "f" -> full();
            case "simple", "Simple", "SIMPLE", "S", "s" -> simple();
            case "memory", "Memory", "MEMORY", "M", "m" -> memory();
            default -> null;
        };
    }
}

public class BuilderTwo implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 8: ");
        ViewModeDirector fullDirector = new ViewModeDirector(FullMode.builder());
        ViewModeDirector simpleDirector = new ViewModeDirector(SimpleMode.builder());
        ViewModeDirector memoryDirector = new ViewModeDirector(MemoryMode.builder());

        fullDirector.construct("f").show();
        System.out.println();
        fullDirector.construct("s").show();
        System.out.println();
        fullDirector.construct("m").show();

        System.out.println("-------------------------------------------------------");
        simpleDirector.construct("f").show();
        System.out.println();
        simpleDirector.construct("s").show();
        System.out.println();
        simpleDirector.construct("m").show();

        System.out.println("------------------------------------------------------");
        memoryDirector.construct("f").show();
        System.out.println();
        memoryDirector.construct("s").show();
        System.out.println();
        memoryDirector.construct("m").show();
    }
}
