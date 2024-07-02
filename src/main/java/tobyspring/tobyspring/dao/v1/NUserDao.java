package tobyspring.tobyspring.dao.v1;

import lombok.extern.slf4j.Slf4j;
import tobyspring.Constant;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class NUserDao extends UserDaoV1 {

    @Override
    Connection getConnection() throws Exception {
        log.info("NUser");
        Class.forName(Constant.DRIVER);
        return  DriverManager.getConnection(Constant.URL, Constant.USER,Constant.PW);
    }
}
