package com.bfh.mybatisplus;

import com.bfh.mybatisplus.entity.User;
import com.bfh.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Tests05 {

	@Autowired
	UserMapper userMapper;

	/**
	 * 测试 Oracle 主键 Sequence
	 */
	@Test
	void testOracle() {
		User user = new User();
		user.setName("oracle");
		userMapper.insert(user);
	}

	/**
	 * 测试 公共字段填充
	 */
	@Test
	void testMetaObjectHandler() {
		User user = new User();
		user.setId(1);
		user.setFill(null);
		user.setDeleted(1);

		userMapper.updateById(user);
	}

	/**
	 * 测试 逻辑删除
	 */
	@Test
	void testLogicDelete() {
		userMapper.deleteById(1);

		User user = userMapper.selectById(1);
		System.out.println(user);
	}

	/**
	 * 测试 自定义全局操作
	 */
	@Test
	void testMySqlInjector() {
		userMapper.deleteAll();
	}

}
