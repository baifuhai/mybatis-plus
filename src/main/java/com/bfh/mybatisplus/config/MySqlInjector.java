package com.bfh.mybatisplus.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * 自定义全局操作
 */
//public class MySqlInjector extends AutoSqlInjector {
public class MySqlInjector extends AbstractSqlInjector {

	@Override
	public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
		return Arrays.asList(new MyMethod());
	}

	/**
	 * 扩展 inject 方法，完成自定义全局操作
	 */
//	@Override
//	public void inject(Configuration configuration, MapperBuilderAssistant builderAssistant, Class<?> mapperClass,
//			Class<?> modelClass, TableInfo table) {
//		//将 EmployeeMapper 中定义的 deleteAll，处理成对应的 MappedStatement 对象，加入到 configuration 对象中。
//
//		//注入的SQL语句
//		String sql = "delete from " + table.getTableName();
//
//		//注入的方法名，一定要与EmployeeMapper接口中的方法名一致
//		String method = "deleteAll";
//
//		//构造 SqlSource 对象
//		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
//
//		//构造一个删除的 MappedStatement
//		this.addDeleteMappedStatement(mapperClass, method, sqlSource);
//	}

}

class MyMethod extends AbstractMethod {

	@Override
	public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
		String sql = "delete from " + tableInfo.getTableName();
		String method = "deleteAll";
		SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
		return this.addDeleteMappedStatement(mapperClass, method, sqlSource);
	}

}
