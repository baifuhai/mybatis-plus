package com.bfh.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 公共字段填充处理器
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 插入操作，自动填充
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		Object fieldValue = getFieldValByName("fill", metaObject);
		if (fieldValue == null) {
			System.out.println("插入操作，自动填充");
			setFieldValByName("fill", "admin", metaObject);
		}
	}

	/**
	 * 修改操作，自动填充
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		Object fieldValue = getFieldValByName("fill", metaObject);
		if (fieldValue == null) {
			System.out.println("修改操作，自动填充");
			setFieldValByName("fill", "admin2", metaObject);
		}
	}

}
