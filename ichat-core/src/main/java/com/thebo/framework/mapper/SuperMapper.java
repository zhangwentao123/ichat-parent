package com.thebo.framework.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义Mapper，扩展Mysql方法
 * Created by hebo on 2016-1-15.
 */
public interface SuperMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
