package org.fkit.hrm.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.fkit.hrm.dao.provider.LogDynaSqlProvider;
import org.fkit.hrm.domain.Log;

import static org.fkit.hrm.util.common.HrmConstants.LOGTABLE;

/**   
 * @Description: LogMapper接口 
 * @author 郭俊  
 * @version V1.0   
 */
public interface LogDao {

	// 通过id查询
	@Select("select * from "+LOGTABLE+" where ID = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="createtime",property="createtime",javaType=java.util.Date.class),
		@Result(column="project_id",property="project",
			one=@One(select="org.fkit.hrm.dao.ProjectDao.selectById",
		fetchType=FetchType.EAGER))
	})
	Log selectById(int id);
	
	// 通过项目id查询
	@Select("select * from "+LOGTABLE+" where project_id = #{project_id} order by createtime desc")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="createtime",property="createtime",javaType=java.util.Date.class),
		@Result(column="project_id",property="project",
			one=@One(select="org.fkit.hrm.dao.ProjectDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<Log> selectByProjectId(int project_id);

	
	// 根据id删除日志
	@Delete(" delete from "+LOGTABLE+" where id = #{id} ")
	void deleteById(Integer id);
	
	// 动态插入项目
	@SelectProvider(type=LogDynaSqlProvider.class,method="insertLog")
	void save(Log log);
	
	// 动态修改项目
	@SelectProvider(type=LogDynaSqlProvider.class,method="updateLog")
	void update(Log log);
}
