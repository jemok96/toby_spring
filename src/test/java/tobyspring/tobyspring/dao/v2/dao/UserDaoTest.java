package tobyspring.tobyspring.dao.v2.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import tobyspring.tobyspring.dao.v2.DaoFactory;
import tobyspring.tobyspring.domain.User;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

/**
 * 여러 테스트가 존재할 경우 어떤 순서대로 실행될지는 알 수 없음
 * 한 메소드를 의존하는 테스트를 만들지마라
 */
@SpringBootTest
class UserDaoTest {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
    UserDao dao = context.getBean("userDaoMain", UserDao.class);

    @BeforeEach// @Test메소드가 실행되기 전에 먼저 실행돼야 하는 메소드, junit4의 Before
    void setUp() throws Exception {
       dao.deleteAll();
    }
    @AfterEach // @Test메소드가 실행된 후 실행돼야 하는 메소드, junit4의 After
    void tearDown() throws Exception {
        dao.deleteAll();
    }

    @Test
    public void addAndGet() throws Exception{
        assertThat(dao.getCount()).isEqualTo(0);
        User user1 = new User("aa","bb","cc");
        User user2 = new User("aaa","bbb","ccc");

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User findUser1 = dao.get(user1.getId());
        assertThat(findUser1.getId()).isEqualTo(user1.getId());
        assertThat(findUser1.getPassword()).isEqualTo(user1.getPassword());

        User findUser2 = dao.get(user2.getId());
        assertThat(findUser2.getId()).isEqualTo(user2.getId());
        assertThat(findUser2.getPassword()).isEqualTo(user2.getPassword());
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
    @Test
    public void getUserFailure() throws Exception{
        assertThat(dao.getCount()).isEqualTo(0);

        assertThatThrownBy(()->
                dao.get("Unknown_id")).isInstanceOf(EmptyResultDataAccessException.class);

    }
}