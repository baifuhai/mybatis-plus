package com.bfh.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Tests03 {

	/**
	 * 代码生成
	 */
	@Test
	public void testGenerator() {
		// 全局配置
		GlobalConfig config = new GlobalConfig();
		config.setActiveRecord(true); // 是否支持AR模式
		config.setAuthor("author"); // 作者
		config.setOutputDir(System.getProperty("user.dir") + "/src/main/java"); // 生成路径
		config.setFileOverride(true); // 文件覆盖
		config.setIdType(IdType.AUTO); // 主键策略
		config.setServiceName("%sService"); // 设置生成的 service 接口的名字的首字母是否为I，IEmployeeService
		config.setBaseResultMap(true);
		config.setBaseColumnList(true);
		config.setOpen(false);
		config.setSwagger2(true); // 实体属性 Swagger2 注解
		
		// 数据源配置
		DataSourceConfig dsConfig = new DataSourceConfig();
		dsConfig.setDbType(DbType.MYSQL);
		dsConfig.setDriverName("com.mysql.jdbc.Driver");
		dsConfig.setUrl("jdbc:mysql://127.0.0.1:3306/mybatis_plus?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
		dsConfig.setUsername("root");
		dsConfig.setPassword("123456");
		dsConfig.setSchemaName("public");
		 
		// 策略配置
		StrategyConfig stConfig = new StrategyConfig();
		stConfig.setCapitalMode(true); // 全局大写命名
//		stConfig.setDbColumnUnderline(true); // 指定表名、字段名是否使用下划线
		stConfig.setNaming(NamingStrategy.underline_to_camel); // 数据库表映射到实体的命名策略
		stConfig.setColumnNaming(NamingStrategy.underline_to_camel);
		stConfig.setTablePrefix("tbl_");
		stConfig.setInclude("tbl_user"); // 生成的表
//		stConfig.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//		stConfig.setSuperControllerClass("com.baomidou.ant.common.BaseController");
		stConfig.setEntityLombokModel(true);
		stConfig.setRestControllerStyle(true);
//		stConfig.setSuperEntityColumns("id");
		stConfig.setControllerMappingHyphenStyle(true);
		
		// 包配置
		PackageConfig pkConfig = new PackageConfig();
		pkConfig.setModuleName("module");
		pkConfig.setParent("com.bfh.generator");
		pkConfig.setMapper("mapper");
		pkConfig.setService("service");
		pkConfig.setController("controller");
		pkConfig.setEntity("entity");
		pkConfig.setXml("mapper");

		// 自定义模板配置（注意不要带上.ftl/.vm，会根据使用的模板引擎自动识别）
//		TemplateConfig templateConfig = new TemplateConfig();
//		templateConfig.setController("templates/controller2.java");
//		templateConfig.setService("templates/service2.java");
//		templateConfig.setServiceImpl("templates/serviceImpl2.java");
//		templateConfig.setMapper("templates/mapper2.java");
//		templateConfig.setXml("templates/mapper.xml2.java");
//		templateConfig.setEntity("templates/entity2.java");
//		templateConfig.setEntityKt("templates/entity.kt2.java");

		// 自定义模板属性注入
//		InjectionConfig cfg = new InjectionConfig() {
//			// 自定义属性注入abc
//			// 在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
//			@Override
//			public void initMap() {
//				Map<String, Object> map = new HashMap<>();
//				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//				this.setMap(map);
//			}
//		};

		// 代码生成器
		AutoGenerator ag = new AutoGenerator();
		ag.setGlobalConfig(config);
		ag.setDataSource(dsConfig);
		ag.setStrategy(stConfig);
		ag.setPackageInfo(pkConfig);
//		ag.setTemplate(templateConfig);
//		ag.setCfg(cfg);
//		ag.setTemplateEngine(new VelocityTemplateEngine());
//		ag.setTemplateEngine(new FreemarkerTemplateEngine());
//		ag.setTemplateEngine(new BeetlTemplateEngine());

		// 执行
		ag.execute();
	}

}
