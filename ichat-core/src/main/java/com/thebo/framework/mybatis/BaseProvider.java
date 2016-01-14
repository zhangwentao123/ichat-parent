package com.thebo.framework.mybatis;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Table;

import com.thebo.framework.entity.BaseUser;
import com.thebo.framework.mybatis.complexQuery.NoValueQueryParam;
import com.thebo.framework.mybatis.complexQuery.WithValueQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import com.thebo.framework.entity.BaseEntity;
import com.thebo.framework.mybatis.complexQuery.CustomQueryParam;
import com.thebo.framework.util.SessionUtils;

public class BaseProvider<T extends BaseEntity> {

	private String tableName;
	private Class<?> modelClass;
	private static ThreadLocal<Class<?>> threadModelClass = new ThreadLocal<Class<?>>();
	private static final String OPERATOR_EQUAL = " = ";
	private static final String OPERATOR_LIKE = " like ";

	private void initFromThreadLocal() {
		modelClass = BaseProvider.threadModelClass.get();
		tableName = modelClass.getAnnotation(Table.class).name();
		BaseProvider.threadModelClass.remove();
	}

	public static void setModelClass(Class<?> modelClass) {
		BaseProvider.threadModelClass.set(modelClass);
	}

	public String getAll() {
		initFromThreadLocal();
		SQL sql = SELECT_FROM();
		sql = ORDER(null, sql);
		return sql.toString();
	}

	public String getById() {
		initFromThreadLocal();
		SQL sql = SELECT_FROM().WHERE("ID = #{id}");
		return sql.toString();
	}

	public String count(T findParams) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
		initFromThreadLocal();
		SQL sql = new SQL() {
			{
				SELECT("COUNT(ID)");
				FROM(tableName);
			}
		};
		sql = WHERE(findParams, sql, OPERATOR_LIKE);
		return sql.toString();
	}

	public String countQuery(Map<String, Object> customQueryParams) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
		initFromThreadLocal();
		SQL sql = new SQL() {
			{
				SELECT("COUNT(ID)");
				FROM(tableName);
			}
		};

        sql = WHERE_CUSTOM(customQueryParams, sql);
		return sql.toString();
	}

    public String query(Map<String, Object> dataMap) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        List<Sort> sortList = (List<Sort>) dataMap.get("sortList");

		initFromThreadLocal();
		SQL sql = SELECT_FROM();
        sql = WHERE_CUSTOM(dataMap, sql);
		sql = ORDER(sortList, sql);
		return sql.toString();
	}

	public String find(T findParams) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
		initFromThreadLocal();
		SQL sql = SELECT_FROM();
		sql = WHERE(findParams, sql, OPERATOR_LIKE);
		sql = ORDER(null, sql);
        return sql.toString();
	}

	public String get(T findParams) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
		initFromThreadLocal();
		SQL sql = SELECT_FROM();
		sql = WHERE(findParams, sql, OPERATOR_EQUAL);
		sql = ORDER(null, sql);
		return sql.toString();
	}

	public String insert(final T t) throws IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		initFromThreadLocal();
		// 设置默认值
		Date now = Calendar.getInstance().getTime();
		BaseUser user = SessionUtils.getUser();
		if (StringUtils.isBlank(t.getId())) {
			t.setId(UUID.randomUUID().toString());
		}
		t.setCreateTime(now);
		t.setUpdateTime(now);
		if (user != null) {
			t.setCreateUser(user.getUsername());
			t.setUpdateUser(user.getUsername());
//            try {
//                Method orgCodeGetter = t.getClass().getDeclaredMethod("getOrgCode");
//                Method orgCodeSetter = t.getClass().getDeclaredMethod("setOrgCode", String.class);
//                Object value = orgCodeGetter.invoke(t);
//                if (value == null || StringUtils.isEmpty(value.toString())) {
//                    orgCodeSetter.invoke(t, user.getOrgCode());
//                }
//            } catch (NoSuchMethodException e) {
//            }
        }

		return new SQL() {
			{
				INSERT_INTO(tableName);

				Map<String, Property> properties = ModelUtils.getProperties(t, ColumnTarget.INSERT);
				for (Property property : properties.values()) {
					// 过滤不允许更新的字段
					if (property.isId() || property.isNullValue(t)) {
						continue;
					}

					VALUES(property.getColumnName(), "#{" + property.getName() + "}");

				}
			}
		}.toString();
	}

	public String delete(String id) {
		initFromThreadLocal();
		return new SQL() {
			{
				DELETE_FROM(tableName);
				WHERE("ID = #{id}");
			}
		}.toString();
	}

	public String update(final T t) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException {
		initFromThreadLocal();
		// 设置默认值
		t.setCreateTime(Calendar.getInstance().getTime());
		BaseUser user = SessionUtils.getUser();
		if (user != null) {
			t.setCreateUser(user.getUsername());
		}
		// 过滤不允许更新的字段
		t.setCreateTime(null);
		t.setCreateUser(null);

		return new SQL() {
			{
				UPDATE(tableName);

				String className = StringUtils.split(modelClass.getName(), "$")[0];
				try {
					Map<String, Property> properties = ModelUtils.getProperties(Class.forName(className), ColumnTarget.UPDATE);

					for (Property property : properties.values()) {
						// 过滤不允许更新的字段
						if (property.isId() || property.isNullValue(t)) {
							continue;
						}

						SET(property.getColumnName() + " = #{" + property.getName() + "}");
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				WHERE("ID = #{id}");
			}
		}.toString();
	}

	private SQL SELECT_FROM() {
		final Map<String, Property> columns = ModelUtils.getProperties(modelClass, ColumnTarget.SELECT);
		return new SQL() {
			{
				for (Property property : columns.values()) {
					SELECT(property.getColumnName());
				}
				FROM(tableName);
			}
		};
	}

	private SQL WHERE(T findParams, SQL sql, String operator) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<String, Property> properties = ModelUtils.getProperties(findParams, ColumnTarget.WHERE);


		for (Property property : properties.values()) {
            if (operator.equalsIgnoreCase("LIKE")) {
            }
			sql.WHERE(property.getColumnName() + operator + "#{" + property.getName() + "}");
		}
		return sql;
	}

    private SQL WHERE_CUSTOM(Map<String, Object> dataMap, SQL sql) {
        Map<String, Property> properties = ModelUtils.getProperties(modelClass, null);
        List<CustomQueryParam> customQueryParams = (List<CustomQueryParam>) dataMap.get("queryParams");
        if (customQueryParams == null) {
            return sql;
        }
        dataMap.clear();
        int i = 0;
        for (CustomQueryParam customQueryParam : customQueryParams) {
            String key = customQueryParam.getProperty();
            Property property = properties.get(key);
            if (customQueryParam instanceof WithValueQueryParam) {
                WithValueQueryParam withValueQueryParam = (WithValueQueryParam) customQueryParam;
                dataMap.put(i + "", withValueQueryParam.getValue());
                sql.WHERE(property.getColumnName() + " " + withValueQueryParam.getOperator() + " #{" + i + "}");
                i++;
            } else if (customQueryParam instanceof NoValueQueryParam) {
                NoValueQueryParam noValueQueryParam = (NoValueQueryParam) customQueryParam;
                sql.WHERE(property.getColumnName() + " " + noValueQueryParam.getCondition());
            }
        }
        return sql;
    }

	private SQL ORDER(List<Sort> sortList, SQL sql) {
		Map<String, Property> properties = ModelUtils.getProperties(modelClass, ColumnTarget.ORDER);
		for (Property property : properties.values()) {
			sql.ORDER_BY(property.getOrder());
		}
        if (sortList != null) {
            for (Sort sort : sortList) {
                sql.ORDER_BY(sort.getProperty() + " " + sort.getDirection());
            }
        }
		return sql;
	}

    public String deleteAll() {
        initFromThreadLocal();
        return new SQL() {
            {
                DELETE_FROM(tableName);
            }
        }.toString();
    }
}
