package com.iss.dao.impl;

import com.iss.dao.IUserDAO;
import com.iss.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDAOImpl implements IUserDAO{
    private Connection conn;  //需要利用Connection对象操作
    private PreparedStatement pstmt;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    public boolean doCreate(User pojo) throws SQLException {
        String sql="INSERT INTO tb_user (username,password,role,phone,photo) VALUES (?,?,?,?,?)";
        this.pstmt=this.conn.prepareStatement(sql);
        this.pstmt.setString(1,pojo.getUsername());
        this.pstmt.setString(2,pojo.getPassword());
        this.pstmt.setInt(3,pojo.getRole());
        this.pstmt.setInt(4,pojo.getPhone());
        this.pstmt.setString(5,pojo.getPhoto());
        return this.pstmt.executeUpdate()>0;
    }

    public boolean doUpdate(User pojo) throws SQLException {
        String sql="UPDATE tb_user SET username=?,password=?,role=?,phone=?,photo WHERE id=?";
        this.pstmt=this.conn.prepareStatement(sql);
        this.pstmt.setString(1,pojo.getUsername());
        this.pstmt.setString(2,pojo.getPassword());
        this.pstmt.setInt(3,pojo.getRole());
        this.pstmt.setInt(4,pojo.getPhone());
        this.pstmt.setString(5,pojo.getPhoto());
        this.pstmt.setInt(6,pojo.getId());
        return pstmt.executeUpdate()>0;
    }

    public boolean doRemoveBatch(Set<Integer> ids) throws SQLException {
        return false;
    }

    public User findById(Integer id) throws SQLException {
        User pojo=null;
        String sql="SELECT * FROM tb_user WHERE id=?";
        this.pstmt=this.conn.prepareStatement(sql);
        this.pstmt.setInt(1,id);
        ResultSet rs=pstmt.executeQuery();//执行查询，返回的仍然是ResultSet对象
        while (rs.next()){
            pojo=new User();
            pojo.setId(rs.getInt(1));
        }
        return pojo;
    }


    public List<User> findAll() throws SQLException {
        List<User> all=new ArrayList<User>();
        String sql="SELECT * FROM tb_user";
        this.pstmt=this.conn.prepareStatement(sql);
        ResultSet rs=this.pstmt.executeQuery();
        while (rs.next()){
            User pojo=new User();
        }
        return all;
    }

    public List<User> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException {
        return null;
    }

    public Integer getAllCount(String column, String keyWord) throws SQLException {
        return null;
    }
}
