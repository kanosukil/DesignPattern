package cn.edu.ncu.learn;

import cn.edu.ncu.learn.StructuralPatterns.Facade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Main");
        DoAction pattern = new Facade();

        pattern.method();
    }

    public interface DoAction {
        void method();
    }
}