package tobyspring.tobyspring.main;

import tobyspring.tobyspring.dao.v1.NUserDao;
import tobyspring.tobyspring.dao.v1.UserDaoV1;
import tobyspring.tobyspring.domain.User;

public class MainV1 {
    public static void main(String[] args) throws Exception {
        UserDaoV1 userDao = new NUserDao();

        User user = new User();
        user.setId("rudnf");
        user.setName("Jemok");
        user.setPassword("gkdlfn12");

        userDao.add(user);

        User findUser = userDao.get(user.getId());
        System.out.println("findUser = " + findUser);
        System.out.println(user == findUser);

        userDao.deleteAll();
    }

}
