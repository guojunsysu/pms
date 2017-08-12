package org.fkit.hrm.domain;

import java.io.Serializable;

/**   
 * @Description: User实体类
 * @author 郭俊	
 * @version V1.0   
 */
public class User implements Serializable {

	private Integer id;			// id
	private String loginname;	// 登录名
	private String password;	// 密码
	private String username;	// 用户名
	private String department;	// 部门
	private String team;		// 组别
	private String role;		// 角色
	// 无参数构造器
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", loginname="
				+ loginname + ", password=" + password + ", department=" + department
				+ ", team=" + team + ", role=" + role + "]";
	}
	
	
}
