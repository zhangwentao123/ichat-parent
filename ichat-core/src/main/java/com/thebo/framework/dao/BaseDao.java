package com.thebo.framework.dao;

import java.util.List;

import com.thebo.framework.mybatis.Sort;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.thebo.framework.entity.BaseEntity;
import com.thebo.framework.mybatis.BaseProvider;
import com.thebo.framework.mybatis.complexQuery.CustomQueryParam;

/**
 * Dao接口 - Dao基接口
 */
public interface BaseDao<T extends BaseEntity> {
	
	@SelectProvider(type = BaseProvider.class, method = "getAll")
	@ResultMap("getMap")
	public List<T> getAll();

	@SelectProvider(type = BaseProvider.class, method = "getById")
	@ResultMap("getMap")
	public T getById(String id);

	@SelectProvider(type = BaseProvider.class, method = "count")
	public int count(T findParams);

	@SelectProvider(type = BaseProvider.class, method = "countQuery")
	public int countQuery(@Param("queryParams") List<CustomQueryParam> customQueryParams);
	
	@SelectProvider(type = BaseProvider.class, method = "get")
	@ResultMap("getMap")
	public T getOne(T findParams);
	
	@SelectProvider(type = BaseProvider.class, method = "query")
	@ResultMap("getMap")
	public List<T> query(@Param("queryParams") List<CustomQueryParam> customQueryParams, @Param("sortList") List<Sort> sortList);

	@SelectProvider(type = BaseProvider.class, method = "get")
	@ResultMap("getMap")
	public List<T> get(T findParams);

	@SelectProvider(type = BaseProvider.class, method = "find")
	@ResultMap("getMap")
	public List<T> find(T findParams);

	@InsertProvider(type = BaseProvider.class, method = "insert")
	@Options(keyProperty = "id")
	public int insert(T t);
	
	@DeleteProvider(type = BaseProvider.class, method = "delete")
	public int delete(String id);

	@UpdateProvider(type = BaseProvider.class, method = "update")
	public int update(T t);

    @DeleteProvider(type = BaseProvider.class,method = "deleteAll")
    public int deleteAll();

}