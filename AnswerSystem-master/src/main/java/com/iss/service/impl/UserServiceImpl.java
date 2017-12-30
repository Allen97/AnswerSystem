package com.iss.service.impl;


import com.iss.dbutils.DBUtils;
import com.iss.factory.DAOFactory;
import com.iss.pojo.User;
import com.iss.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserServiceImpl implements IUserService{
    //在这个类的对象内部就提供有一个数据库连接类的实例化对象
    private DBUtils dbUtils = new DBUtils();
    public boolean insert(User pojo) throws Exception {
        try {
            //要雇佣的雇员编号如果不存在，则findById()返回的结果就是null,null表示可以进行新雇员数据的保存
            if (DAOFactory.getIUserDAOInstance(this.dbUtils.getConnection()).findById(pojo.getId()) == null) {
                return DAOFactory.getIUserDAOInstance(this.dbUtils.getConnection()).doCreate(pojo);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbUtils.release();
        }
        return false;
    }

    public boolean update(User pojo) throws Exception {
        try {
            return DAOFactory.getIUserDAOInstance(this.dbUtils.getConnection()).doUpdate(pojo);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbUtils.release();
        }

    }

    public boolean delete(Set<Integer> ids) throws Exception {
        try {
            return DAOFactory.getIUserDAOInstance(this.dbUtils.getConnection()).doRemoveBatch(ids);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbUtils.release();
        }

    }

    public User get(int ids) throws Exception {
        try {
            return DAOFactory.getIUserDAOInstance(this.dbUtils.getConnection()).findById(ids);
        }catch (Exception e){
            throw e;
        }finally {
            this.dbUtils.release();
        }

    }

    public List<User> list() throws Exception {
        try {
            return DAOFactory.getIUserDAOInstance(this.dbUtils.getConnection()).findAll();
        }catch (Exception e){
            throw e;
        }finally {
            this.dbUtils.release();

        }
    }

    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allUsers", DAOFactory.getIUserDAOInstance(this.dbUtils.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
            map.put("userCount", DAOFactory.getIUserDAOInstance(this.dbUtils.getConnection()).getAllCount(column, keyWord));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbUtils.release();
        }
    }
}
