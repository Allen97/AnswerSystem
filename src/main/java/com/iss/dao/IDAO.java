package com.iss.dao;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * 定义公共的DAO接口标准，基本功能包括：增加，删除全部，删除数据，根据编号查询，查询全部，分页显示，数据统计
 * @param <K> 表示要操作的主键类型，由子接口实现
 * @param <V> 表示要操作的pojo类型，由子接口实现
 */
public interface IDAO<K,V> {
    /**
     * 实现数据的增加操作
     * @param pojo  包含了要增加数据的pojo对象
     * @return 数据保存成功返回true,否则返回false
     * @throws SQLException SQL执行异常
     */
    public boolean doCreate(V pojo) throws SQLException;

    /**
     * 实现数据的修改操作，本次修改是根据id进行全部字段数据的修改
     * @param pojo 包含了要修改数据的信息，一定要提供有ID内容
     * @return  数据修改成功返回true,否则返回false
     * @throws SQLException SQL执行异常
     */
    public boolean doUpdate(V pojo) throws SQLException;

    /**
     *执行数据的批量删除操作，所以要删除的数据以Set集合的形式保存
     * @param ids 包含了所有要删除的 数据ID，不包含有重读内容
     * @return  删除成功返回true(删除的数据个数与要删除的数据个数相同)，否则返回false
     * @throws SQLException SQL执行异常
     */
    public boolean doRemoveBatch(Set<K> ids) throws SQLException;

    /**
     * 根据雇员编号查询指定的雇员信息
     * @param id 要查询的数据编号
     * @return 如果数据信息存在，则将数据以pojo类对象的形式返回，如果数据不存在，则返回null
     * @throws SQLException SQL执行异常
     */
    public V findById(K id) throws SQLException;

    /**
     * 查询指定数据表的全部内容，并且以集合的形式返回
     * @return 如果表中有数据，则所有的数据会封装为Pojo对象而后利用List集合返回
     * 如果没有数据，那么届的长度为0（size()==0,不是null）
     * @throws SQLException SQL执行异常
     */
    public List<V> findAll() throws SQLException;

    /**
     * 分页进行数据的模糊查询，查询结构以集合的形式返回
     * @param currentPage   当前躲在的页
     * @param lineSize 每页显示的数据行数
     * @param column    要进行数据查询的数据列
     * @param keyWord   模糊查询的关键字
     * @return  如果表中有数据，则所有的数据会封装为pojo对象而后利用List集合返回
     *  如果没有数据那么集合的长度为0(size()==0,不是null)
     * @throws SQLException SQL执行异常
     */
    public List<V> findAllSplit(Integer currentPage, Integer lineSize,String column,String keyWord) throws SQLException;

    /**
     * 进行模糊查询的统计，如果表中没有记录统计结果就是0
     * @param column 要进行模糊查询的数据列
     * @param keyWord 模糊查询的额关键字
     * @return 返回表中的数据量，如果没有数据返回0
     * @throws SQLException SQL执行异常
     */
    public Integer getAllCount(String column,String keyWord) throws SQLException;


}
