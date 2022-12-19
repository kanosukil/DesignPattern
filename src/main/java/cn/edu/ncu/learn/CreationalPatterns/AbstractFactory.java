package cn.edu.ncu.learn.CreationalPatterns;

import cn.edu.ncu.learn.Main;

/**
 * 某系统为了改进数据库操作的性能，自定义数据库连接对象Connection和语句对象Statement，
 * 可针对不同类型的数据库提供不同的连接对象和语句对象，
 * <p>
 * 如提供Oracle或MySQL专用连接类和语句类，而且用户可以通过配置文件等方式根据实际需要动态更换系统数据库。
 */

interface SQLConnection {
    void connection();
}

interface SQLStatement {
    void getStatement();

    void commit();
}

interface DataBase {
    SQLConnection getConnection();

    SQLStatement getStatement();
}

class OracleDB implements DataBase {

    @Override
    public SQLConnection getConnection() {
        return new OracleConnection();
    }

    @Override
    public SQLStatement getStatement() {
        return new OracleStatement();
    }
}

class MySQLDB implements DataBase {

    @Override
    public SQLConnection getConnection() {
        return new MySQLConnection();
    }

    @Override
    public SQLStatement getStatement() {
        return new MySQLStatement();
    }
}

class OracleConnection implements SQLConnection {

    @Override
    public void connection() {
        System.out.println("Connect to Oracle DB");
    }
}

class MySQLConnection implements SQLConnection {

    @Override
    public void connection() {
        System.out.println("Connect to MySQL DB");
    }
}

class OracleStatement implements SQLStatement {

    @Override
    public void getStatement() {
        System.out.println("Get Oracle SQL Statement");
    }

    @Override
    public void commit() {
        System.out.println("Commit Oracle SQL");
    }
}

class MySQLStatement implements SQLStatement {

    @Override
    public void getStatement() {
        System.out.println("Get MySQL SQL Statement");
    }

    @Override
    public void commit() {
        System.out.println("Commit MySQL SQL");
    }
}

class DBSelector {
    public static DataBase getFactory(String dbms) {
        return switch (dbms) {
            case "oracle", "Oracle", "ORACLE" -> new OracleDB();
            case "mysql", "MySQL", "MYSQL" -> new MySQLDB();
            default -> null;
        };
    }
}

public class AbstractFactory implements Main.DoAction {
    @Override
    public void method() {
        System.out.println("Question 4: ");
        DataBase oracle = DBSelector.getFactory("Oracle");
        SQLConnection oConn = oracle.getConnection();
        SQLStatement oStat = oracle.getStatement();
        oConn.connection();
        oStat.getStatement();
        oStat.commit();

        DataBase mySQL = DBSelector.getFactory("MySQL");
        SQLConnection mConn = mySQL.getConnection();
        SQLStatement mStat = mySQL.getStatement();
        mConn.connection();
        mStat.getStatement();
        mStat.commit();
    }
}
