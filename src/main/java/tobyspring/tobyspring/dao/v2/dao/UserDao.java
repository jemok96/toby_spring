package tobyspring.tobyspring.dao.v2.dao;


import lombok.Setter;
import tobyspring.tobyspring.domain.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@Setter
public  class UserDao {
    private DataSource dataSource;

    public void add(User user) throws Exception{
        Connection c = dataSource.getConnection();
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
        Connection c = dataSource.getConnection();
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
        Connection c = dataSource.getConnection();
        String sql = "DELETE FROM users";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.executeUpdate();

        ps.close();
        c.close();
    }
    public int getCount() throws Exception{
        Connection c = dataSource.getConnection();
        String sql = "SELECT COUNT(*) FROM users";
        PreparedStatement ps = c.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();
        rs.next();
        int result = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();
        return result;
    }

}
