package tobyspring.tobyspring.dao.v2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tobyspring.tobyspring.dao.v2.connection.ConnectionMaker;
import tobyspring.tobyspring.dao.v2.connection.DConnectionMaker;
import tobyspring.tobyspring.dao.v2.connection.NConnectionMaker;
import tobyspring.tobyspring.dao.v2.dao.AccountDao;
import tobyspring.tobyspring.dao.v2.dao.MessageDao;
import tobyspring.tobyspring.dao.v2.dao.UserDaoV2;

@Configuration
public class DaoFactory {
    @Bean
    public UserDaoV2 userDao(){
        return new UserDaoV2(getConnectionMaker());
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

}
