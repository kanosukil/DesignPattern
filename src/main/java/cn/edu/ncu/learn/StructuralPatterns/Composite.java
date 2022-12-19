package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用组合设计模式设计一个杀毒软件（AntiVirus）的框架，该软件
 * 1. 既可以对某个文件夹（Folder）杀毒，
 * 2. 也可以对某个指定的文件（File）进行杀毒，
 * <p>
 * 文件种类包括文本文件TextFile、图片文件ImageFile、视频文件VideoFile。
 */

class Folder {
    private final List<File> files = new ArrayList<>();

    public void add(File file) {
        files.add(file);
    }

    public void remove(File file) {
        files.remove(file);
    }

    public int count() {
        return files.size();
    }

    public File get(int i) {
        return files.get(i);
    }

}

abstract class File {
    protected final String TYPE;
    protected final String PATH;

    protected File(String type, String path) {
        TYPE = type;
        PATH = path;
    }

    public String read() {
        return String.format("Read File{type:%s;path:%s}\n", TYPE, PATH);
    }
}

class TextFile extends File {
    public TextFile(String path) {
        super("Text", path);
    }

}

class ImageFile extends File {
    public ImageFile(String path) {
        super("Image", path);
    }
}

class VideoFile extends File {
    public VideoFile(String path) {
        super("Video", path);
    }
}

class AntiVirus {
    private Folder folder = null;

    public AntiVirus() {
    }

    public void setFile(File file) {
        if (folder == null) {
            folder = new Folder();
        }
        folder.add(file);
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    private void txtAnti(TextFile file) {
        System.out.println("Anti Text: File=" + file.read());
    }

    private void imgAnti(ImageFile file) {
        System.out.println("Anti Image: File=" + file.read());
    }

    private void vdoAnti(VideoFile file) {
        System.out.println("Anti Video: File=" + file.read());
    }

    public void start() {
        if (folder != null) {
            for (int i = 0; i < folder.count(); i++) {
                File file = folder.get(i);
                if (file instanceof TextFile) {
                    txtAnti((TextFile) file);
                } else if (file instanceof ImageFile) {
                    imgAnti((ImageFile) file);
                } else if (file instanceof VideoFile) {
                    vdoAnti((VideoFile) file);
                }
            }
        }
    }
}

public class Composite implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 3: ");
        AntiVirus anti = new AntiVirus();
        anti.setFolder(new Folder() {{
            add(new TextFile("/a/b/c/d1.txt"));
            add(new TextFile("/a/b/c/d2.cpp"));
            add(new TextFile("/a/b/c/d3.java"));
            add(new TextFile("/a/b/c/d4.py"));
            add(new TextFile("/a/b/c/d1.jpg"));
            add(new TextFile("/a/b/c/d2.gif"));
            add(new TextFile("/a/b/c/d3.png"));
            add(new TextFile("/a/b/c/d4.jpg"));
            add(new TextFile("/a/b/c/d1.avi"));
            add(new TextFile("/a/b/c/d2.mp4"));
            add(new TextFile("/a/b/c/d3.avi"));
            add(new TextFile("/a/b/c/d4.mp4"));
        }});
        anti.start();
    }
}
