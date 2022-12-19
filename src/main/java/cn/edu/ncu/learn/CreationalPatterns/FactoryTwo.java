package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 现需要设计一个程序来读取多种不同类型的图片格式，针对每一种图片格式设计一个图片读取器（ImageReader）, 如:
 * 1. GIF图片读取器（GifReader）用于读取GIF格式的图片，
 * 2. JPEG图片读取器（JpgReader）用于读取JPEG格式的图片。
 * 图片读取器对象通过图片读取器工厂ImageReaderFactory来创建，
 * <p>
 * ImageReaderFactory是一个抽象类，用于定义创建图片读取器的工厂方法，
 * 其子类GifReaderFactory和JpgReaderFactory用于创建具体的图片读取器对象。
 */

interface ImageReader {
    void readImg();
}

abstract class ImageReaderFactory {
    abstract ImageReader getImgReader();
}

class GifReader implements ImageReader {

    @Override
    public void readImg() {
        System.out.println("Read GIF");
    }
}

class JpgReader implements ImageReader {

    @Override
    public void readImg() {
        System.out.println("Read JPG");
    }
}

class GifReaderFactory extends ImageReaderFactory {

    @Override
    public ImageReader getImgReader() {
        return new GifReader();
    }
}

class JpgReaderFactory extends ImageReaderFactory {

    @Override
    public ImageReader getImgReader() {
        return new JpgReader();
    }
}

public class FactoryTwo implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 3: ");
        new GifReaderFactory().getImgReader().readImg();
        new JpgReaderFactory().getImgReader().readImg();
    }
}
