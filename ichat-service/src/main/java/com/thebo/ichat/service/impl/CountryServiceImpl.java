package com.thebo.ichat.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thebo.ichat.base.impl.BaseServiceImpl;
import com.thebo.ichat.entity.Country;
import com.thebo.ichat.mapper.CountryMapper;
import com.thebo.ichat.model.CountryQueryModel;
import com.thebo.ichat.service.CountryService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @author liuzh_3nofxnp
 * @since 2015-09-19 17:17
 */
@Service
public class CountryServiceImpl extends BaseServiceImpl<Country> implements CountryService {

    public List<Country> selectByCountry(Country country, int page, int rows) {
        Example example = new Example(Country.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(country.getCountryname())) {
            criteria.andLike("countryname", "%" + country.getCountryname() + "%");
        }
        if (StringUtil.isNotEmpty(country.getCountrycode())) {
            criteria.andLike("countrycode", "%" + country.getCountrycode() + "%");
        }
        if (country.getId() != null) {
            criteria.andEqualTo("id", country.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return selectByExample(example);
    }

    public PageInfo<Country> selectByCountryQueryModel(CountryQueryModel queryModel) {
        return ((CountryMapper) getMapper()).selectByCountryQueryModel(queryModel);
    }

}
