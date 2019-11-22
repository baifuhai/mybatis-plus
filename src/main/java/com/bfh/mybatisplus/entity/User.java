package com.bfh.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.bfh.mybatisplus.enumeration.GradeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * javaBean
 *
 * 定义JavaBean中成员变量时所使用的类型:
 * 	 因为每个基本类型都有一个默认值:
 * 	   int ==> 0
 * 	   boolean ==> false
 *
 * MybatisPlus会默认使用实体类的类名到数据中找对应的表.
 *
 */
//@KeySequence(value = "seq_user", clazz = Integer.class)
@TableName("tbl_user")
@Data
public class User extends Model<User> {

	/*
	 * @TableId:
	 * 	 value: 指定表中的主键列的列名，如果实体属性名与列名一致，可以省略不指定.
	 *    type: 指定主键策略.
	 */
	@TableId(value = "id", type = IdType.AUTO)
	//@TableId(value = "id", type = IdType.INPUT)
	private Integer id;

	@TableField("name")
	private String name;

	@TableField("age")
	private Integer age;

	@TableField("email")
	private String email;

	@TableField("gender")
	private Integer gender;

	@TableField("grade_enum")
	private GradeEnum gradeEnum;

	@TableField(value = "fill", fill = FieldFill.INSERT_UPDATE)
	private String fill;

	@Version
	@TableField("version")
	private Integer version;

	@TableLogic(value = "0", delval = "1")
	@TableField("deleted")
	private Integer deleted;

	@TableField(exist = false)
	private Boolean checked;

	/**
	 * 指定当前实体类的主键属性
	 */
	@Override
	protected Serializable pkVal() {
		return id;
	}

}