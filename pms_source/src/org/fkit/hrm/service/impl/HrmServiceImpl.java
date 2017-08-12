package org.fkit.hrm.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.fkit.hrm.dao.ProjectDao;
import org.fkit.hrm.dao.LogDao;
import org.fkit.hrm.dao.DocumentDao;
import org.fkit.hrm.dao.EmployeeDao;
import org.fkit.hrm.dao.JobDao;
import org.fkit.hrm.dao.NoticeDao;
import org.fkit.hrm.dao.UserDao;
import org.fkit.hrm.domain.Project;
import org.fkit.hrm.domain.Document;
import org.fkit.hrm.domain.Employee;
import org.fkit.hrm.domain.Job;
import org.fkit.hrm.domain.Notice;
import org.fkit.hrm.domain.Statis;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.domain.Log;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;

import static org.fkit.hrm.util.common.HrmConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**   
 * @Description: 项目管理系统服务层接口实现类
 * @author 郭俊 
 * @version V1.0   
 */
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private LogDao logDao;
	
	@Autowired
	private JobDao jobDao;
	
	/*****************用户服务接口实现*************************************/
	/**
	 * HrmServiceImpl接口login方法实现
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public User login(String loginname, String password) {
//		System.out.println("HrmServiceImpl login -- >>");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}
	
	/**
	 * HrmServiceImpl接口findUser方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public List<User> findUser(User user,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("user", user);
		int recordCount = userDao.count(params);
		if(pageModel !=null){
			pageModel.setRecordCount(recordCount);
			if(recordCount > 0){
				/** 开始分页查询数据：查询第几页的数据 */
				params.put("pageModel", pageModel);
			}
		}
		List<User> users = userDao.selectByPage(params);
		return users;
	}
	
	/**
	 * HrmServiceImpl接口findUserById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public User findUserById(Integer id) {
		return userDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	public void removeUserById(Integer id) {
		userDao.deleteById(id);
		
	}
	
	/**
	 * HrmServiceImpl接口addUser方法实现
	 * @see { HrmService }
	 * */
	public void modifyUser(User user) {
		userDao.update(user);
		
	}
	
	/**
	 * HrmServiceImpl接口modifyUser方法实现
	 * @see { HrmService }
	 * */
	public void addUser(User user) {
		userDao.save(user);
		
	}
	
	/*****************项目接口实现*************************************/
	/**
	 * HrmService接口findProject方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	public List<Project> findProject(String department, String team, String username, User user, Project project, PageModel pageModel) {
		// TODO Auto-generated method stub
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("project", project);
		params.put("user", user);
		params.put("department", department);
		params.put("team", team);
		params.put("username", username);
		
		int recordCount = projectDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<Project> projects = projectDao.selectByPage(params);
	    return projects;
	}
	
	/**
	 * HrmService接口removeProjectById方法实现
	 * @see { HrmService }
	 * */
	public void removeProjectById(Integer id) {
		// TODO Auto-generated method stub
		projectDao.deleteById(id);
	}
	
	/**
	 * HrmService接口addProject方法实现
	 * @see { HrmService }
	 * */
	public void addProject(Project project) {
		projectDao.save(project);
	}
	
	/**
	 * HrmService接口findProjectById方法实现
	 * @see { HrmService }
	 * */
	public Project findProjectById(Integer id) {
		// TODO Auto-generated method stub
		return projectDao.selectById(id);
	}

	/**
	 * HrmService接口modifyProject方法实现
	 * @see { HrmService }
	 * */
	public void modifyProject(Project project) {
		// TODO Auto-generated method stub
		projectDao.update(project);
	}
	
	/*****************日志服务接口实现*************************************/
	/**
	 * HrmService接口findLogById方法实现
	 * @see { HrmService }
	 * */
	public Log findLogById(Integer id) {
		// TODO Auto-generated method stub
		return logDao.selectById(id);
	}
	
	/**
	 * HrmService接口findLogByProjectId方法实现
	 * @see { HrmService }
	 * */
	public List<Log> findLogByProjectId(Integer project_id) {
		// TODO Auto-generated method stub
		return logDao.selectByProjectId(project_id);
	}
	
	/**
	 * HrmService接口removeLogById方法实现
	 * @see { HrmService }
	 * */
	public void removeLogById(Integer id) {
		// TODO Auto-generated method stub
		logDao.deleteById(id);
	}
	
	/**
	 * HrmService接口modifyLog方法实现
	 * @see { HrmService }
	 * */
	public void modifyLog(Log log) {
		// TODO Auto-generated method stub
		logDao.update(log);
	}
	
	/**
	 * HrmService接口addLog方法实现
	 * @see { HrmService }
	 * */
	public void addLog(Log log) {
		// TODO Auto-generated method stub
		logDao.save(log);
	}
	
	/*****************数据统计服务接口实现*************************************/
	/**
	 * HrmService接口statisCount方法实现
	 * @see { HrmService }
	 * */
	public Integer statisCount(String department, String team, User user,
			Project project) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("project", project);
		params.put("user", user);
		params.put("department", department);
		params.put("team", team);
		
		return projectDao.count(params);
	}
	
	/**
	 * HrmService接口statisSum方法实现
	 * @see { HrmService }
	 * */
	public Float statisSum(String department, String team, User user,
			Project project) {
		// TODO Auto-generated method stub
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("project", project);
		params.put("user", user);
		params.put("department", department);
		params.put("team", team);
		
		return projectDao.sum(params);
	}
	
	/**
	 * HrmService接口sourceExcel方法实现
	 * @see { HrmService }
	 * */
	public void sourceExcel(List<Statis> statiss, String filename) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("projectExcel -->>");
		
		// 绘制excel表格
		@SuppressWarnings("resource")
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet");
		Cell[][] cell = new Cell[16][9];
		// 创建cell数组
		for(int i=0;i<16;i++){
			Row row = sheet.createRow((short)i);
			for(int j=0;j<9;j++){
				cell[i][j] = row.createCell(j);
			}
		}
		// 固定数据
		cell[0][0].setCellValue("项目渠道(具体到分公司)");
		cell[0][1].setCellValue("重点关注");
		cell[0][3].setCellValue("正常推进");
		cell[0][5].setCellValue("停滞");
		cell[0][7].setCellValue("合计");
		cell[1][1].setCellValue("项目量");
		cell[1][2].setCellValue("年收入规模（万）");
		cell[1][3].setCellValue("项目量");
		cell[1][4].setCellValue("年收入规模（万）");
		cell[1][5].setCellValue("项目量");
		cell[1][6].setCellValue("年收入规模（万）");
		cell[1][7].setCellValue("项目量");
		cell[1][8].setCellValue("年收入规模（万）");
		for(int i=0;i<13;i++){
			cell[i+2][0].setCellValue(HrmConstants.sourcemap.get(i));
		}
		cell[15][0].setCellValue("合计");
		
		// 动态数据
		for(int i=0;i<14;i++){
			for(int j=0;j<4;j++){
				Statis statis = statiss.get(i*4+j);
				cell[i+2][j*2+1].setCellValue(statis.getCount());
				cell[i+2][j*2+2].setCellValue(statis.getSum());
			}
		}
		
		// 单元格合并
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
		sheet.addMergedRegion(new CellRangeAddress(0,0,1,2));
		sheet.addMergedRegion(new CellRangeAddress(0,0,3,4));
		sheet.addMergedRegion(new CellRangeAddress(0,0,5,6));
		sheet.addMergedRegion(new CellRangeAddress(0,0,7,8));
		
		// 文件写出
	    FileOutputStream fileOut = new FileOutputStream(filename);
	    wb.write(fileOut);
	    fileOut.close();
	}
	
	/**
	 * HrmService接口departmentExcel方法实现
	 * @see { HrmService }
	 * */
	public void departmentExcel(List<Statis> statiss, String filename)
			throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet");
		Cell[][] cell = new Cell[11][10];
		// 创建cell数组
		for(int i=0;i<11;i++){
			Row row = sheet.createRow((short)i);
			for(int j=0;j<10;j++){
				cell[i][j] = row.createCell(j);
			}
		}
		// 固定数据
		cell[0][0].setCellValue("项目渠道(具体到科室)");
		cell[0][2].setCellValue("重点关注");
		cell[0][4].setCellValue("正常推进");
		cell[0][6].setCellValue("停滞");
		cell[0][8].setCellValue("合计");
		cell[1][2].setCellValue("项目量");
		cell[1][3].setCellValue("年收入规模（万）");
		cell[1][4].setCellValue("项目量");
		cell[1][5].setCellValue("年收入规模（万）");
		cell[1][6].setCellValue("项目量");
		cell[1][7].setCellValue("年收入规模（万）");
		cell[1][8].setCellValue("项目量");
		cell[1][9].setCellValue("年收入规模（万）");
		
		cell[2][0].setCellValue("省市政企支撑室");
		cell[5][0].setCellValue("小结");
		cell[6][0].setCellValue("区域支撑室");
		cell[9][0].setCellValue("小结");
		cell[10][0].setCellValue("合计");
		cell[2][1].setCellValue("大企业支撑团队");
		cell[3][1].setCellValue("金融及互联网行业支撑团队");
		cell[4][1].setCellValue("党政军行业支撑团队");
		cell[6][1].setCellValue("东片区域支撑组");
		cell[7][1].setCellValue("西片区域支撑组");
		cell[8][1].setCellValue("政府行业支撑组");
		
		// 动态数据
		for(int i=0;i<9;i++){
			for(int j=0;j<4;j++){
				Statis statis = statiss.get(i*4+j);
				cell[i+2][j*2+2].setCellValue(statis.getCount());
				cell[i+2][j*2+3].setCellValue(statis.getSum());
			}
		}
		
		// 单元格合并
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,1));
		sheet.addMergedRegion(new CellRangeAddress(0,0,2,3));
		sheet.addMergedRegion(new CellRangeAddress(0,0,4,5));
		sheet.addMergedRegion(new CellRangeAddress(0,0,6,7));
		sheet.addMergedRegion(new CellRangeAddress(0,0,8,9));
		sheet.addMergedRegion(new CellRangeAddress(2,4,0,0));
		sheet.addMergedRegion(new CellRangeAddress(6,8,0,0));
		sheet.addMergedRegion(new CellRangeAddress(5,5,0,1));
		sheet.addMergedRegion(new CellRangeAddress(9,9,0,1));
		sheet.addMergedRegion(new CellRangeAddress(10,10,0,1));
		// 文件写出
	    FileOutputStream fileOut = new FileOutputStream(filename);
	    wb.write(fileOut);
	    fileOut.close();
	}
	
}
