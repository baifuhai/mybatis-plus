package com.bfh.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bfh.mybatisplus.entity.User;
import com.bfh.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Tests04 {

	@Autowired
	UserMapper userMapper;

	/**
	 * 测试 分页插件
	 */
	@Test
	void testPage() {
		Page<User> page = new Page<>(1,1);

		IPage<User> iPage = userMapper.selectPage(page, null);

		System.out.println(page == iPage);
		System.out.println(page.getRecords());
		System.out.println(page.getCurrent());
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.getPages());
		System.out.println(page.hasPrevious());
		System.out.println(page.hasNext());
	}

	/**
	 * 测试 SQL执行分析插件
	 */
	@Test
	void testSQLExplain() {
		userMapper.delete(null); // 全表删除
	}

	/**
	 * 测试 性能分析插件
	 */
	@Test
	void testPerformance() {
		userMapper.selectList(null);
	}

	/**
	 * 测试 乐观锁插件
	 */
	@Test
	void testOptimisticLocker() {
		User user = new User();
		user.setId(1);
		user.setName("aa");
		user.setAge(30);
		user.setEmail("aa@126.com");
		user.setGender(1);
		user.setVersion(1);
		
		userMapper.updateById(user);
	}

}
