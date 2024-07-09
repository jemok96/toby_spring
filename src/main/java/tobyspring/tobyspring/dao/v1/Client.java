package tobyspring.tobyspring.dao.v1;

import tobyspring.tobyspring.domain.User;

public class Client {
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
