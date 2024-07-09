package tobyspring.tobyspring.dao.v2.dao;

import tobyspring.tobyspring.dao.v2.connection.ConnectionMaker;

public class AccountDao {
    private ConnectionMaker connectionMaker;

    public AccountDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
