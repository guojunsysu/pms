package org.fkit.hrm.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.fkit.hrm.domain.Project;
import org.fkit.hrm.domain.Log;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.Statis;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.util.tag.PageModel;

/**   
 * @Description: 项目管理服务层接口
 * @author 郭俊
 * @version V1.0   
 */
public interface HrmService {


	/**
	 * 用户登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String loginname,String password);
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return 用户对象
	 * */
	User findUserById(Integer id);
	
	/**
	 * 获得所有用户
	 * @return User对象的List集合
	 * */
	List<User> findUser(User user,PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 * */
	void removeUserById(Integer id);
	
	/**
	 * 修改用户
	 * @param User 用户对象
	 * */
	void modifyUser(User user);
	
	/**
	 * 添加用户
	 * @param User 用户对象
	 * */
	void addUser(User user);
	
	/**
	 * 根据id查询日志
	 * @param id
	 * @return 日志对象
	 * */
	Log findLogById(Integer id);
	
	/**
	 * 根据项目id查询日志
	 * @param project_id
	 * @return 日志对象列表
	 * */
	List<Log> findLogByProjectId(Integer project_id);
	
	/**
	 * 根据id删除日志
	 * @param id
	 * */
	void removeLogById(Integer id);
	
	/**
	 * 修改日志
	 * @param Log 日志对象
	 * */
	void modifyLog(Log log);
	
	/**
	 * 添加日志
	 * @param Log 日志对象
	 * */
	void addLog(Log log);
	
	/**
	 * 获得所有项目，分页查询
	 * @return Project对象的List集合
	 * */
	List<Project> findProject(String department, String team, String username, User user, Project project, PageModel pageModel);
	
	/**
	 * 根据id删除项目
	 * @param id
	 * */
	public void removeProjectById(Integer id);
	
	/**
	 * 添加项目
	 * @param project 项目对象
	 * */
	void addProject(Project project);
	
	/**
	 * 根据id查询项目
	 * @param id
	 * @return 项目对象
	 * */
	Project findProjectById(Integer id);
	
	/**
	 * 修改项目
	 * @param project 项目对象
	 * */
	void modifyProject(Project project);
	
	/**
	 * 根据信息统计个数
	 * @param project 项目对象
	 * */
	Integer statisCount(String department, String team, User user, Project project);
	
	/**
	 * 根据信息统计总和
	 * @param project 项目对象
	 * */
	Float statisSum(String department, String team, User user, Project project);
	
	/**
	 * 生成excel表格
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * */
	void sourceExcel(List<Statis> statiss, String filename) throws FileNotFoundException, IOException;
	/**
	 * 生成excel表格
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * */
	void departmentExcel(List<Statis> statiss, String filename) throws FileNotFoundException, IOException;
}
