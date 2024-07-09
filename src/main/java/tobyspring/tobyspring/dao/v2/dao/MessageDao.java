package tobyspring.tobyspring.dao.v2.dao;

import tobyspring.tobyspring.dao.v2.connection.ConnectionMaker;

public class MessageDao {
    private ConnectionMaker connectionMaker;

    public MessageDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}
