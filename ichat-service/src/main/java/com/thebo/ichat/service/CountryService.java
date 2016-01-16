package com.thebo.ichat.service;

import com.thebo.ichat.base.BaseService;
import com.thebo.ichat.entity.Country;

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

}