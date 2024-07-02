package tobyspring.tobyspring.dao.v1;

import tobyspring.tobyspring.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public abstract class UserDaoV1 {
    abstract Connection getConnection() throws Exception;

    public void add(User user) throws Exception{
        Connection c = getConnection();
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
        Connection c = getConnection();
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
        Connection c = getConnection();
        String sql = "DELETE FROM users";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.executeUpdate();

        ps.close();
        c.close();
    }

}
