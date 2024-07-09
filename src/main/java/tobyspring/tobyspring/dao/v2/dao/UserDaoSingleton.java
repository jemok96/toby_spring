package tobyspring.tobyspring.dao.v2.dao;

import tobyspring.tobyspring.dao.v2.connection.ConnectionMaker;
import tobyspring.tobyspring.dao.v2.connection.DConnectionMaker;
import tobyspring.tobyspring.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * SQL생성하고, 이를 실행하는 데만 집중함
 * Connection을 가져오는 방벙은 자신이 선택하지 않는다.
 */


public  class UserDaoSingleton {

    private ConnectionMaker connectionMaker;
    private static UserDaoSingleton INSTANCE;
    private UserDaoSingleton(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
    public static UserDaoSingleton getInstance() {
        if(INSTANCE==null) {
            INSTANCE = new UserDaoSingleton(new DConnectionMaker());
        }
        return INSTANCE;
    }

    public void add(User user) throws Exception{
        Connection c = connectionMaker.makeNewConnection();
        String sql = "insert into users(id,name,password) values(?,?,?)";
        PreparedStatement ps = c.prepareStatement(sql);

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }
    public User get(String id) throws Exception{
        Connection c = connectionMaker.makeNewConnection();
        String sql = "SELEcT * FROM USERS WHERE ID = ?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();
        return user;

    }
    public void deleteAll() throws Exception{
        Connection c = connectionMaker.makeNewConnection();
        String sql = "DELETE FROM users";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.executeUpdate();

        ps.close();
        c.close();
    }

}
