package com.thebo.ichat.base;

import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 自定义Mapper，扩展Mysql方法
 * Created by hebo on 2016-1-15.
 */

@Component
public interface CustomMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
