package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;
import cn.edu.ncu.learn.Util.RandomUtil;

/**
 * 在计算机主机（Mainframe）中，只需要按下主机的开机按钮（on()），即可调用其他硬件设备和软件的启动方法, 如:
 * 1. 内存（Memory）的自检（check()）、
 * 2. CPU的运行（run()）、
 * 3. 硬盘（HardDisk）的读取（read()）、
 * 4. 操作系统（OS）的载入（load()）等，
 * <p>
 * 如果某一个过程发生错误则计算机启动失败。使用外观模式模拟该过程。
 */

interface Memory {
    boolean check();
}

interface CPU {
    boolean run();
}

interface HardDisk {
    boolean read();
}

interface OS {
    boolean load();
}

class StandardMemory implements Memory {
    @Override
    public boolean check() {
        if (RandomUtil.getBoolean()) {
            System.out.println("Stand Memory is checked Successfully.");
            return true;
        } else {
            System.out.println("Stand Memory is checked in failure.");
            return false;
        }
    }
}


class StandardDisk implements HardDisk {
    @Override
    public boolean read() {
        if (RandomUtil.getBoolean()) {
            System.out.println("Stand Hard Disk is read Successfully.");
            return true;
        } else {
            System.out.println("Stand Hard Disk is read in failure.");
            return false;
        }
    }
}

class StandardCPU implements CPU {
    @Override
    public boolean run() {
        if (RandomUtil.getBoolean()) {
            System.out.println("Stand CPU is run Successfully.");
            return true;
        } else {
            System.out.println("Stand CPU is run in failure.");
            return false;
        }
    }
}

class StandardOS implements OS {
    @Override
    public boolean load() {
        if (RandomUtil.getBoolean()) {
            System.out.println("Stand OS is loaded Successfully.");
            return true;
        } else {
            System.out.println("Stand OS is loaded in failure.");
            return false;
        }
    }
}


abstract class MainFrame {
    private final HardDisk disk;
    private final Memory memory;
    private final CPU cpu;
    private final OS os;
    protected boolean isOn = false;

    protected MainFrame(HardDisk disk, Memory memory, CPU cpu, OS os) {
        this.disk = disk;
        this.memory = memory;
        this.cpu = cpu;
        this.os = os;
    }

    protected HardDisk getDisk() {
        return disk;
    }

    protected Memory getMemory() {
        return memory;
    }

    protected CPU getCPU() {
        return cpu;
    }

    protected OS getOS() {
        return os;
    }

    public void on() {
        if (disk.read() && memory.check() && cpu.run() && os.load()) {
            System.out.println("MainFrame is successfully started.");
            isOn = true;
        } else {
            System.out.println("MainFrame is started in failure.");
            isOn = false;
        }
    }
}

class StandardPC extends MainFrame {
    public StandardPC() {
        super(
                new StandardDisk(),
                new StandardMemory(),
                new StandardCPU(),
                new StandardOS()
        );
    }
}

public class Facade implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 5: ");
        StandardPC pc = new StandardPC();
        for (int i = 0; i < 10; i++) {
            pc.on();
        }
    }
}
