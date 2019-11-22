package com.bfh.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bfh.mybatisplus.entity.User;
import com.bfh.mybatisplus.enumeration.GradeEnum;
import com.bfh.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class Tests01 {

	@Autowired
	UserMapper userMapper;

	/**
	 * 插入
	 */
	@Test
	void testInsert() {
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

		//INSERT INTO user ( deleted, gender, grade_enum, name, fill, version, email, age ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )
		userMapper.insert(user);

		System.out.println("id=" + user.getId());
	}

	/**
	 * 更新
	 */
	@Test
	void testUpdate() {
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

		//UPDATE user SET gender=?, grade_enum=?, name=?, fill=?, version=?, email=?, age=? WHERE id=? AND deleted=0
		//UPDATE user SET name=?, fill=?, version=? WHERE id=? AND deleted=0
		userMapper.updateById(user);

		//UPDATE user SET gender=?, grade_enum=?, name=?, fill=?, version=?, email=?, age=? WHERE deleted=0 AND (id=?)
		//UPDATE user SET name=?, fill=?, version=? WHERE deleted=0 AND (id=?)
		userMapper.update(user, new UpdateWrapper<User>().lambda().eq(User::getId, 1));
	}

	/**
	 * 删除
	 */
	@Test
	void testDelete() {
		//UPDATE user SET deleted=1 WHERE id=? AND deleted=0
		userMapper.deleteById(1);

		//UPDATE user SET deleted=1 WHERE deleted=0 AND (id=?)
		userMapper.delete(new UpdateWrapper<User>().lambda().eq(User::getId, 1));

		//UPDATE user SET deleted=1 WHERE id IN (?, ?, ?) AND deleted=0
		userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));

		//UPDATE user SET deleted=1 WHERE id=? AND deleted=0
		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("id", 1);
		userMapper.deleteByMap(columnMap);
	}

	/**
	 * 查询
	 */
	@Test
	void testQuery01() {
		//SELECT id,deleted,gender,grade_enum,name,fill,version,email,age FROM tbl_user WHERE id=? AND deleted=0
		User user = userMapper.selectById(1);
		System.out.println(user);

		List<User> userList = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
		userList.forEach(System.out::println);

		User user2 = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 1));
		System.out.println(user2);

		List<User> userList2 = userMapper.selectList(new QueryWrapper<User>().lambda().ge(User::getId, 1));
		userList2.forEach(System.out::println);

		Map<String, Object> columnMap = new HashMap<>();
		columnMap.put("id", 1);
		List<User> userList3 = userMapper.selectByMap(columnMap);
		userList3.forEach(System.out::println);
	}

	@Test
	void testQuery02() {
		LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
		queryWrapper.select(User::getId, User::getName);
		queryWrapper.and((x) -> {
			x.like(User::getName, "a");
			x.notLike(User::getName, "a");
			x.likeLeft(User::getName, "a");
			x.likeRight(User::getName, "a");
		});
		queryWrapper.eq(User::getAge, 20);// ==
		queryWrapper.ne(User::getAge, 20);// ==
		queryWrapper.gt(User::getAge, 20);// >
		queryWrapper.ge(User::getAge, 20);// >=
		queryWrapper.lt(User::getAge, 20);// <
		queryWrapper.le(User::getAge, 20);// <=
		queryWrapper.isNull(User::getAge);
		queryWrapper.isNotNull(User::getAge);
		queryWrapper.or();
		queryWrapper.in(User::getAge, 1, 2, 3);
		queryWrapper.notIn(User::getAge, 1, 2, 3);
		queryWrapper.inSql(User::getAge, "1,2,3,4,5,6");
		queryWrapper.notInSql(User::getAge, "1,2,3,4,5,6");
//		queryWrapper.exists(true, "");
//		queryWrapper.notExists(true, "");
//		queryWrapper.allEq(new HashMap<String, Object>(), true);
		queryWrapper.apply("age = ${0}", 20);
		queryWrapper.between(User::getAge, 20, 30);
		queryWrapper.notBetween(User::getAge, 20, 30);
		queryWrapper.groupBy(User::getAge);
		queryWrapper.having("count(0) > ${0}", 2);
		queryWrapper.orderByAsc(User::getAge);
		queryWrapper.orderByDesc(User::getAge);
		queryWrapper.last("limit 0, 2");
		System.out.println(queryWrapper.getSqlSelect());
		List<User> userList = userMapper.selectList(queryWrapper);
		userList.forEach(System.out::println);

//		.or()		// SQL: (gender = ? AND last_name LIKE ? OR email LIKE ?)
//		.orNew()	// SQL: (gender = ? AND last_name LIKE ?) OR (email LIKE ?)
	}

	@Test
	void testQuery03() {
		//deleted=0 AND (id = ? AND (age = ? OR name = ?))
		List<User> userList = userMapper.selectList(new QueryWrapper<User>().lambda()
				.eq(User::getId, 1)
				.and(wrapper -> wrapper.eq(User::getAge, 1)
						.or(wrapper2 -> wrapper2.eq(User::getName, "a"))
				)
		);
		userList.forEach(System.out::println);
	}

	/**
	 * 分页查询
	 */
	@Test
	void testQueryByPage() {
		Page<User> page = new Page<>(1, 2);

		//SELECT id,deleted,grade_enum,name,email,age FROM user WHERE deleted=0 AND (age >= ?)
		IPage<User> iPage = userMapper.selectPage(page, new QueryWrapper<User>().lambda().ge(User::getAge, 1));
		System.out.println(page == iPage);//true
		System.out.println(iPage.getRecords());
		System.out.println(iPage.getCurrent());
		System.out.println(iPage.getSize());
		System.out.println(iPage.getTotal());
		System.out.println(iPage.getPages());

		//SELECT * FROM user WHERE age >= ?
		IPage<User> iPage2 = userMapper.selectByPage(page, 1);
		System.out.println(page == iPage2);//true
		System.out.println(iPage2.getRecords());
		System.out.println(iPage2.getCurrent());
		System.out.println(iPage2.getSize());
		System.out.println(iPage2.getTotal());
		System.out.println(iPage2.getPages());
	}

}
