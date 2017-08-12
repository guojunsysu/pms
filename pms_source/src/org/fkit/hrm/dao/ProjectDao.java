package org.fkit.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.ProjectDynaSqlProvider;
import org.fkit.hrm.domain.Project;

import static org.fkit.hrm.util.common.HrmConstants.PROJECTTABLE;

/**   
 * @Description: ProjectMapper接口 
 * @author 郭俊  
 * @version V1.0   
 */
public interface ProjectDao {

	// 动态查询
	@SelectProvider(type=ProjectDynaSqlProvider.class,method="selectWhitParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="createtime",property="createtime",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",
			one=@One(select="org.fkit.hrm.dao.UserDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<Project> selectByPage(Map<String, Object> params);
	
	// 动态查询数量
	@SelectProvider(type=ProjectDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
	
	// 动态统计数据和
	@SelectProvider(type=ProjectDynaSqlProvider.class,method="sum")
	Float sum(Map<String, Object> params);
	
	// 查询所有项目
	@Select("select * from "+PROJECTTABLE+" ")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="createtime",property="createtime",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",
			one=@One(select="org.fkit.hrm.dao.UserDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<Project> selectAllProject();
	
	// 通过id查询
	@Select("select * from "+PROJECTTABLE+" where ID = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="createtime",property="createtime",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",
			one=@One(select="org.fkit.hrm.dao.UserDao.selectById",
		fetchType=FetchType.EAGER))
	})
	Project selectById(int id);

	// 根据id删除项目
	@Delete(" delete from "+PROJECTTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入项目
	@SelectProvider(type=ProjectDynaSqlProvider.class,method="insertProject")
	void save(Project project);
	
	// 动态修改项目
	@SelectProvider(type=ProjectDynaSqlProvider.class,method="updateProject")
	void update(Project project);
}
