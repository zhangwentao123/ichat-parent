package com.thebo.ichat.service;

import com.github.pagehelper.PageInfo;
import com.thebo.ichat.base.BaseService;
import com.thebo.ichat.entity.Country;
import com.thebo.ichat.model.CountryQueryModel;

import java.util.List;

public interface CountryService extends BaseService<Country> {

    /**
     * 根据条件分页查询
     *
     * @param country
     * @param page
     * @param rows
     * @return
     */
    List<Country> selectByCountry(Country country, int page, int rows);

    /**
     * 分页查询,分页插件4.0.3版本特性演示
     *
     * @param queryModel
     * @return
     */
    PageInfo<Country> selectByCountryQueryModel(CountryQueryModel queryModel);

}