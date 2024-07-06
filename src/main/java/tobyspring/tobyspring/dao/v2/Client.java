package tobyspring.tobyspring.dao.v2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.tobyspring.dao.v2.dao.UserDaoV2;
import tobyspring.tobyspring.domain.User;

public class Client {
    public static void main(String[] args) throws Exception {
        //UserDao를 사용하는 Clinet에서 어떤 커넥션을 사용할지 생성할 역할 또한 다른 관심사이기 때문에 DaoFactory 분리한다.
//       UserDaoV2 dao = new DaoFactory().userDao();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDaoV2 dao = context.getBean("userDao",UserDaoV2.class);

        User user = new User();
        user.setId("rudnf");
        user.setName("Jemok");
        user.setPassword("gkdlfn12");

        dao.add(user);

        User findUser = dao.get(user.getId());
        System.out.println("findUser = " + findUser);
        System.out.println(user == findUser);

        dao.deleteAll();
    }

}
