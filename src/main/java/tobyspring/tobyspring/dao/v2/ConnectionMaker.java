package tobyspring.tobyspring.dao.v2;

import java.sql.Connection;

public interface ConnectionMaker {
    public Connection makeNewConnection() throws Exception;
}
