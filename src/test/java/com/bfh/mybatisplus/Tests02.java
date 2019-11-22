package com.bfh.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bfh.mybatisplus.entity.User;
import com.bfh.mybatisplus.enumeration.GradeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Tests02 {

	/**
	 * AR 插入
	 */
	@Test
	void testARInsert() {
		User user = new User();
		user.setId(null);
		user.setName("a");
		user.setAge(20);
		user.setEmail("a@126.com");
		user.setGender(0);
		user.setGradeEnum(GradeEnum.PRIMARY);
		user.setFill(null);
		user.setVersion(1);
		user.setDeleted(0);
		
		user.insert();
	}

	/**
	 * AR 更新
	 */
	@Test
	void testARUpdate() {
		User user = new User();
		user.setId(1);
		user.setName("aa");
		user.setAge(30);
		user.setEmail("aa@126.com");
		user.setGender(1);
		user.setGradeEnum(GradeEnum.SECONDORY);
		user.setFill(null);
		user.setVersion(1);
		user.setDeleted(0);

		user.updateById();

		user.update(new UpdateWrapper<User>().lambda().eq(User::getId, 1));
	}

	/**
	 * AR 删除
	 *
	 * 注意: 删除不存在的数据 逻辑上也是属于成功的.
	 */
	@Test
	void testARDelete() {
		User user = new User();

		user.deleteById(1);

		user.setId(1);
		user.deleteById();

		user.delete(new QueryWrapper<User>().lambda().eq(User::getId, 1));
	}

	/**
	 * AR 查询
	 */
	@Test
	void testARSelect() {
		User user = new User();

		User user2 = user.selectById(1);
		System.out.println(user2);

		user.setId(1);
		User user3 = user.selectById();
		System.out.println(user3);

		List<User> userList = user.selectAll();
		System.out.println(userList);

		List<User> userList2 = user.selectList(new QueryWrapper<User>().lambda().eq(User::getId, 1));
		System.out.println(userList2);

		Integer count = user.selectCount(new QueryWrapper<User>().lambda().eq(User::getId, 1));
		System.out.println("count: " + count);
	}

	/**
	 * AR 分页查询
	 */
	@Test
	void testARPage() {
		User user = new User();

		IPage<User> page = user.selectPage(
				new Page<>(1, 1),
				new QueryWrapper<User>().lambda()
						.eq(User::getId, 1)
		);

		System.out.println(page.getRecords());
		System.out.println(page.getCurrent());
		System.out.println(page.getSize());
		System.out.println(page.getTotal());
		System.out.println(page.getPages());
	}

}
