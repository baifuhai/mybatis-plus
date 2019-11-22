package com.bfh.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.SqlExplainInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.bfh.mybatisplus.mapper")
@Configuration
public class MybatisPlusConfig {

	/**
	 * 分页插件
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

		// 设置请求的页面大于最大页后操作，true 调回到首页，false 继续请求，默认 false
		paginationInterceptor.setOverflow(false);

		// 设置最大单页限制数量，默认 500 条，-1 不受限制
		paginationInterceptor.setLimit(-1);

		return paginationInterceptor;
	}

	/**
	 * 执行分析插件
	 */
	@Bean
	public SqlExplainInterceptor sqlExplainInterceptor() {
		return new SqlExplainInterceptor();//stopProceed=true
	}

	/**
	 * 性能分析插件
	 */
//	@Bean
//	public PerformanceInterceptor performanceInterceptor() {
//		PerformanceInterceptor x = new PerformanceInterceptor();
//		x.format = true;
//		x.maxTime = 5;
//	}

	/**
	 * 乐观锁插件
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}

	/**
	 * Oracle主键生成器
	 */
	@Bean
	public IKeyGenerator oracleKeyGenerator() {
		return new OracleKeyGenerator();
	}

	/**
	 * 公共字段填充处理器
	 */
	@Bean
	public MetaObjectHandler metaObjectHandler() {
		return new MyMetaObjectHandler();
	}

	/**
	 * sql注入器
	 * LogicSqlInjector 逻辑删除在3.2.0中没有了
	 */
//	@Bean
//	public ISqlInjector mySqlInjector() {
//		return new MySqlInjector();
//	}

}