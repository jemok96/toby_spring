package tobyspring.tobyspring.dao.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import tobyspring.tobyspring.dao.v2.connection.ConnectionMaker;
import tobyspring.tobyspring.dao.v2.connection.CountingConnectionMaker;
import tobyspring.tobyspring.dao.v2.connection.DConnectionMaker;
import tobyspring.tobyspring.dao.v2.dao.AccountDao;
import tobyspring.tobyspring.dao.v2.dao.MessageDao;
import tobyspring.tobyspring.dao.v2.dao.UserDaoV2;
import tobyspring.tobyspring.dao.v2.dao.UserDao;
import tobyspring.tobyspring.domain.Constant;

import javax.sql.DataSource;


@Configuration
public class DaoFactory {
    @Bean
    public UserDaoV2 userDao(){
        return new UserDaoV2(connectionMaker());
    }
    @Bean
    public UserDaoV2 userDao2(){
        return new UserDaoV2(getConnectionMaker());
    }
    @Bean
    public UserDao userDaoMain() throws ClassNotFoundException {
        UserDao dao = new UserDao();
        dao.setDataSource(dataSource());
        return dao;
    }
    @Bean
    public ConnectionMaker connectionMaker(){
        return new CountingConnectionMaker(getConnectionMaker());
    }
    @Bean
    public AccountDao accountDao(){
        return new AccountDao(getConnectionMaker());
    }
    @Bean
    public MessageDao messageDao(){
        return new MessageDao(getConnectionMaker());
    }
    @Bean
    private static ConnectionMaker getConnectionMaker() {
        return new DConnectionMaker();
    }

    @Bean
    public DataSource dataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl(Constant.URL);
        dataSource.setUsername(Constant.USER);
        dataSource.setPassword(Constant.PW);
        return dataSource;
    }
}
