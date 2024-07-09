package tobyspring.tobyspring.dao.v2.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.tobyspring.dao.v2.DaoFactory;
import tobyspring.tobyspring.domain.User;

import static org.assertj.core.api.Assertions.*;

/**
 * 여러 테스트가 존재할 경우 어떤 순서대로 실행될지는 알 수 없음
 * 한 메소드를 의존하는 테스트를 만들지마라
 */
@SpringBootTest
class UserDaoTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    UserDao dao = context.getBean("userDaoMain", UserDao.class);
    @BeforeEach
    void setUp() throws Exception {
       dao.deleteAll();
    }
    @AfterEach
    void tearDown() throws Exception {
        dao.deleteAll();
    }

    @Test
    public void addAndGet() throws Exception{
        assertThat(dao.getCount()).isEqualTo(0);
        User user = new User();
        user.setName("Jemok");
        user.setId("rudnf");
        user.setPassword("123");

        dao.add(user);
        assertThat(dao.getCount()).isEqualTo(1);

        User findUser = dao.get(user.getId());
        assertThat(user.getName()).isEqualTo(findUser.getName());
        assertThat(user.getPassword()).isEqualTo(findUser.getPassword());
    }

    @Test
    public void count() throws Exception{
        assertThat(dao.getCount()).isEqualTo(0);
        User user1 = new User("a", "b", "c");
        User user2 = new User("aa", "bb", "cc");
        User user3 = new User("aaa", "bbb", "ccc");
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);
        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);
        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }
}