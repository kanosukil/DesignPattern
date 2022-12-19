package cn.edu.ncu.learn.StructuralPatterns;

import cn.edu.ncu.learn.Main;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用享元设计模式设计一个围棋软件，在系统中只存在一个白棋对象和一个黑棋对象，
 * 但是它们可以在棋盘的不同位置显示多次。要求使用简单工厂模式和单例模式实现享元工厂类的设计。
 */

enum Piece {
    BLACK, WHITE;

    public void submit(int x, int y) {
        System.out.printf("(%d, %d) -> %s", x, y, this.name());
    }
}

class SimplePieceFactory {
    private static final Map<String, Piece> table = new HashMap<>();

    public static boolean putPiece(int x, int y, Piece p) {
        try {
            table.put("(" + x + "," + y + ")", p);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static Piece getPiece(int x, int y) {
        return table.get("(" + x + "," + y + ")");
    }

}

public class Flyweight implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 6: ");
        SimplePieceFactory.putPiece(1, 1, Piece.BLACK);
        SimplePieceFactory.putPiece(1, 2, Piece.BLACK);
        SimplePieceFactory.putPiece(2, 1, Piece.WHITE);
        SimplePieceFactory.putPiece(2, 3, Piece.WHITE);
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 3; j++) {
                Piece piece = SimplePieceFactory.getPiece(j, i);
                System.out.print("|" + (piece == null ? "     " : piece.name()));
            }
            System.out.println("|");
        }
    }
}
