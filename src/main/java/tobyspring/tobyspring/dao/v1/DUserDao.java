package tobyspring.tobyspring.dao.v1;

import lombok.extern.slf4j.Slf4j;
import tobyspring.tobyspring.domain.Constant;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class DUserDao extends UserDaoV1 {
    @Override
    Connection getConnection() throws Exception {
        log.info("DUser");

        Class.forName(Constant.DRIVER);
        return  DriverManager.getConnection(Constant.URL, Constant.USER,Constant.PW);
    }
}
