package tobyspring.tobyspring.dao.v2.connection;

import lombok.Getter;

import java.sql.Connection;

@Getter
public class CountingConnectionMaker implements ConnectionMaker{

    int count = 0;
    private ConnectionMaker connectionMaker;

    public CountingConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    @Override
    public Connection makeNewConnection() throws Exception {
        this.count++;
        return connectionMaker.makeNewConnection();
    }

}
