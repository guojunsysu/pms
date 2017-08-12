package org.fkit.hrm.domain;

import org.fkit.hrm.domain.User;
import java.io.Serializable;
import java.util.Date;

/**   
 * @Description: Statis实体类
 * @author 郭俊	
 * @version V1.0   
 */
public class Statis implements Serializable{

	private String department;
	private String team;
	private String source;
	private Integer count;		// 数量
	private Float sum;			// 总和
	// 无参数构造器
	public Statis() {
		super();
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Float getSum() {
		return sum;
	}
	public void setSum(Float sum) {
		this.sum = sum;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
}