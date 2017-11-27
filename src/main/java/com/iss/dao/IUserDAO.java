package com.iss.dao;

import com.iss.pojo.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface IUserDAO extends IDAO<Integer,User> {

    boolean doCreate(User pojo) throws SQLException;

    boolean doUpdate(User pojo) throws SQLException;

    boolean doRemoveBatch(Set<Integer> ids) throws SQLException;

    User findById(Integer id) throws SQLException;

    List<User> findAll() throws SQLException;

    List<User> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException;

    Integer getAllCount(String column, String keyWord) throws SQLException;
}
