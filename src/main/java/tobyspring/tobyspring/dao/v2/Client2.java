package tobyspring.tobyspring.dao.v2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import tobyspring.tobyspring.dao.v2.connection.CountingConnectionMaker;
import tobyspring.tobyspring.dao.v2.dao.MessageDao;
import tobyspring.tobyspring.dao.v2.dao.UserDaoV2;
import tobyspring.tobyspring.dao.v2.dao.UserDaoV3;
import tobyspring.tobyspring.domain.User;

public class Client2 {
    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDaoV3 dao = context.getBean("userDao3", UserDaoV3.class);
        MessageDao messageDao = context.getBean("messageDao", MessageDao.class);
        System.out.println("messageDao = " + messageDao);
        User user = new User();
        user.setId("rudnf");
        user.setName("Jemok");
        user.setPassword("gkdlfn12");

        dao.add(user);

        User findUser = dao.get(user.getId());
        System.out.println("findUser = " + findUser);
        System.out.println(user == findUser);

        dao.deleteAll();

        CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);

        System.out.println("ccm = " + ccm.getCount());

    }

}
