package org.fkit.hrm.domain;

import org.fkit.hrm.domain.User;
import java.io.Serializable;
import java.util.Date;

/**   
 * @Description: Project实体类
 * @author 郭俊	
 * @version V1.0   
 */
public class Project implements Serializable{

	private Integer id;			// id
	private String important;	// 是否重要
	private String source;		// 项目来源
	private String manager;		// 项目经理
	private String status;		// 项目阶段
	private String product;		// 项目产品
	private String projectname;	// 项目名称
	private String overview;	// 项目概况
	private Float income_year;	// 年收入预测
	private Float income;		// 合同收入预测
	private Integer time;		// 合同年限
	private String increase_stock;// 增/存量
	private Float income_gz;	// 广州年收入预测
	private Float cost;			// 成本预测
	private String time_success;// 预计成功时间
	private String time_tagged;	// 预计挂标时间
	private Date createtime;	// 建档时间
	private User user;			// 用户
	// 无参数构造器
	public Project() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public Float getIncome_year() {
		return income_year;
	}
	public void setIncome_year(Float income_year) {
		this.income_year = income_year;
	}
	public Float getIncome() {
		return income;
	}
	public void setIncome(Float income) {
		this.income = income;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public String getIncrease_stock() {
		return increase_stock;
	}
	public void setIncrease_stock(String increase_stock) {
		this.increase_stock = increase_stock;
	}
	public Float getIncome_gz() {
		return income_gz;
	}
	public void setIncome_gz(Float income_gz) {
		this.income_gz = income_gz;
	}
	public Float getCost() {
		return cost;
	}
	public void setCost(Float cost) {
		this.cost = cost;
	}
	public String getTime_success() {
		return time_success;
	}
	public void setTime_success(String time_success) {
		this.time_success = time_success;
	}
	public String getTime_tagged() {
		return time_tagged;
	}
	public void setTime_tagged(String time_tagged) {
		this.time_tagged = time_tagged;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getImportant() {
		return important;
	}
	public void setImportant(String important) {
		this.important = important;
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", important=" + important 
				+ ", souce=" + source + ", status=" + status
				+ ", user=" + user + "]";
	}
}