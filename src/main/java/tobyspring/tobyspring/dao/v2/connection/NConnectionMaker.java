package tobyspring.tobyspring.dao.v2.connection;

import lombok.extern.slf4j.Slf4j;
import tobyspring.tobyspring.domain.Constant;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class NConnectionMaker implements ConnectionMaker{
    @Override
    public Connection makeNewConnection() throws Exception {


        Class.forName(Constant.DRIVER);
        return  DriverManager.getConnection(Constant.URL, Constant.USER,Constant.PW);
    }
}
