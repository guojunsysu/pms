package org.fkit.hrm.domain;

import org.fkit.hrm.domain.Project;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**   
 * @Description: Log实体类
 * @author 郭俊	
 * @version V1.0   
 */
public class Log implements Serializable{
	private Integer id;			//	id
	private Project project;	//	项目
	private String thisweek;	//	本周进展
	private String nextweek;	//	下周计划
	private String problem;		//	问题及风险点
	private String filename;	//	文件名
	private MultipartFile file;	//	文件
	private String realfilename;//	真实存储文件名
	private Date createtime;	//	建档时间
	// 无参数构造器
	public Log() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getThisweek() {
		return thisweek;
	}
	public void setThisweek(String thisweek) {
		this.thisweek = thisweek;
	}
	public String getNextweek() {
		return nextweek;
	}
	public void setNextweek(String nextweek) {
		this.nextweek = nextweek;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getRealfilename() {
		return realfilename;
	}
	public void setRealfilename(String realfilename) {
		this.realfilename = realfilename;
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", project=" + project + ", thisweek="
				+ thisweek + ", nextweek=" + nextweek + ", problem=" + problem
				+ ", filename=" + filename + ", file=" + file
				+ ", realfilename=" + realfilename + ", createtime="
				+ createtime + "]";
	}
}
