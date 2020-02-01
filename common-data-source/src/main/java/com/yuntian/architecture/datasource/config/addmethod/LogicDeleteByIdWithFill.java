package com.yuntian.architecture.datasource.config.addmethod;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据 id 逻辑删除数据,并带字段填充功能
 * <p>注意入参是 entity !!! ,如果字段没有自动填充,就只是单纯的逻辑删除</p>
 * <p>
 * 自己的通用 mapper 如下使用:
 * <pre>
 * int deleteByIdWithFill(T entity);
 * </pre>
 * </p>
 *
 * @author miemie
 * @since 2018-11-09
 */
public class LogicDeleteByIdWithFill extends AbstractMethod {
    public LogicDeleteByIdWithFill() {
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE_BY_ID;
        String sql;
        if (tableInfo.isLogicDelete()) {
            List<TableFieldInfo> fieldInfos = (List)tableInfo.getFieldList().stream().filter(TableFieldInfo::isWithUpdateFill).collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(fieldInfos)) {
                String sqlSet = "SET " + (String)fieldInfos.stream().map((i) -> {
                    return i.getSqlSet("");
                }).collect(Collectors.joining("")) + tableInfo.getLogicDeleteSql(false, false);
                sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlSet, tableInfo.getKeyColumn(), tableInfo.getKeyProperty(), tableInfo.getLogicDeleteSql(true, true));
            } else {
                sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), this.sqlLogicSet(tableInfo), tableInfo.getKeyColumn(), tableInfo.getKeyProperty(), tableInfo.getLogicDeleteSql(true, true));
            }
        } else {
            sqlMethod = SqlMethod.DELETE_BY_ID;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty());
        }

        SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, modelClass);
        return this.addUpdateMappedStatement(mapperClass, modelClass, this.getMethod(sqlMethod), sqlSource);
    }

    @Override
    public String getMethod(SqlMethod sqlMethod) {
        return "deleteByIdWithFill";
    }
}
