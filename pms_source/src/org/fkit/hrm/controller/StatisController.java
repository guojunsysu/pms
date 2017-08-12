package org.fkit.hrm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.fkit.hrm.domain.Log;
import org.fkit.hrm.domain.User;
import org.fkit.hrm.domain.Statis;
import org.fkit.hrm.domain.Project;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.fkit.hrm.util.common.HrmConstants.*;

/**   
 * @Description: 统计请求控制器
 * @author 郭俊   
 * @version V1.0   
 */

@Controller
public class StatisController {

	/**
	 * 自动注入ProjectService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理/statis/selectDepartment请求
	 * */
	@RequestMapping(value="/statis/selectDepartment")
	 public String selectProject(Model model,
			 HttpSession session){
		System.out.println("/statis/selectDepartment -->>");
		List<Statis> statiss = new ArrayList<Statis>();	
		Project project = new Project();
		User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
		String department;
		String team;
		Integer count;
		Float sum;
		
		for(int i=0;i<3;i++){
			Integer temp1 = 0;
			Float temp2 = (float) 0;
			for(int j=0; j<3;j++){
				department = HrmConstants.departmentmap.get(i/3);
				team = HrmConstants.teammap.get(i);
				project.setStatus(HrmConstants.statusmap.get(j));
				count = hrmService.statisCount(department, team, user, project);
				sum = hrmService.statisSum(department, team, user, project);
				temp1 += count;
				temp2 = temp2.floatValue() + sum.floatValue();
				Statis statis = new Statis();
				statis.setDepartment(department);
				statis.setTeam(team);
				statis.setCount(count);
				statis.setSum(sum);
				statiss.add(statis);
			}
			Statis statis = new Statis();
			statis.setDepartment("合计");
			statis.setTeam("合计");
			statis.setCount(temp1);
			statis.setSum(temp2);
			statiss.add(statis);
		}
		{
			Integer temp1 = 0;
			Float temp2 = (float) 0;
			department = HrmConstants.departmentmap.get(0);
			for(int j=0; j<3;j++){
				project.setStatus(HrmConstants.statusmap.get(j));
				count = hrmService.statisCount(department, null, user, project);
				sum = hrmService.statisSum(department, null, user, project);
				temp1 += count;
				temp2 += sum;
				Statis statis = new Statis();
				statis.setSource("小计");
				statis.setCount(count);
				statis.setSum(sum);
				statiss.add(statis);
			}
			Statis statis = new Statis();
			statis.setSource("合计");
			statis.setCount(temp1);
			statis.setSum(temp2);
			statiss.add(statis);
		}
		
		for(int i=3;i<6;i++){
			Integer temp1 = 0;
			Float temp2 = (float) 0;
			for(int j=0; j<3;j++){
				department = HrmConstants.departmentmap.get(i/3);
				team = HrmConstants.teammap.get(i);
				project.setStatus(HrmConstants.statusmap.get(j));
				count = hrmService.statisCount(department, team, user, project);
				sum = hrmService.statisSum(department, team, user, project);
				temp1 += count;
				temp2 = temp2.floatValue() + sum.floatValue();
				Statis statis = new Statis();
				statis.setDepartment(department);
				statis.setTeam(team);
				statis.setCount(count);
				statis.setSum(sum);
				statiss.add(statis);
			}
			Statis statis = new Statis();
			statis.setDepartment("合计");
			statis.setTeam("合计");
			statis.setCount(temp1);
			statis.setSum(temp2);
			statiss.add(statis);
		}
		
		{
			Integer temp1 = 0;
			Float temp2 = (float) 0;
			department = HrmConstants.departmentmap.get(1);
			for(int j=0; j<3;j++){
				project.setStatus(HrmConstants.statusmap.get(j));
				count = hrmService.statisCount(department, null, user, project);
				sum = hrmService.statisSum(department, null, user, project);
				temp1 += count;
				temp2 += sum;
				Statis statis = new Statis();
				statis.setSource("小计");
				statis.setCount(count);
				statis.setSum(sum);
				statiss.add(statis);
			}
			Statis statis = new Statis();
			statis.setSource("合计");
			statis.setCount(temp1);
			statis.setSum(temp2);
			statiss.add(statis);
		}
		
		{
			Integer temp1 = 0;
			Float temp2 = (float) 0;
			for(int j=0; j<3;j++){
				project.setStatus(HrmConstants.statusmap.get(j));
				count = hrmService.statisCount(null, null, user, project);
				sum = hrmService.statisSum(null, null, user, project);
				temp1 += count;
				temp2 += sum;
				Statis statis = new Statis();
				statis.setSource("合计");
				statis.setCount(count);
				statis.setSum(sum);
				statiss.add(statis);
			}
			Statis statis = new Statis();
			statis.setSource("合计");
			statis.setCount(temp1);
			statis.setSum(temp2);
			statiss.add(statis);
		}
		
		model.addAttribute("statiss", statiss);
		return "statis/project";
	}
	
	/**
	 * 处理statis/selectSource请求
	 * */
	@RequestMapping(value="statis/selectSource")
	 public String selectSource(Model model,
			 HttpSession session){
		System.out.println("/statis/selectSource -->>");
		User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
		Project project = new Project();
		List<Statis> statiss = new ArrayList<Statis>();
		Integer count;
		Float sum;
		for(int i=0;i<13;i++){
			Integer temp1 = 0;
			Float temp2 = (float) 0;
			for(int j=0; j<3;j++){
				project.setSource(HrmConstants.sourcemap.get(i));
				project.setStatus(HrmConstants.statusmap.get(j));
				count = hrmService.statisCount(null, null, user, project);
				sum = hrmService.statisSum(null, null, user, project);
				temp1 += count;
				temp2 = temp2.floatValue() + sum.floatValue();
				Statis statis = new Statis();
				statis.setSource(HrmConstants.sourcemap.get(i));
				statis.setCount(count);
				statis.setSum(sum);
				statiss.add(statis);
			}
			Statis statis = new Statis();
			statis.setSource(HrmConstants.sourcemap.get(i));
			statis.setCount(temp1);
			statis.setSum(temp2);
			statiss.add(statis);
		}
		
		Integer temp1 = 0;
		Float temp2 = (float) 0;
		for(int j=0; j<3;j++){
			project.setSource(null);
			project.setStatus(HrmConstants.statusmap.get(j));
			count = hrmService.statisCount(null, null, user, project);
			sum = hrmService.statisSum(null, null, user, project);
			temp1 += count;
			temp2 += sum;
			Statis statis = new Statis();
			statis.setSource("合计");
			statis.setCount(count);
			statis.setSum(sum);
			statiss.add(statis);
		}
		Statis statis = new Statis();
		statis.setSource("合计");
		statis.setCount(temp1);
		statis.setSum(temp2);
		statiss.add(statis);
		
		model.addAttribute("statiss", statiss);
		return "statis/source";
	}
	
	/**
	 * 处理/statis/downloadSource请求
	 * */
	@RequestMapping(value="/statis/downloadSource")
	 public ResponseEntity<byte[]>  downloadSource(
			 HttpSession session) throws Exception{
		 	// 先查询信息
		 	System.out.println("/statis/downloadSource -->>");
		 	User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
		 	Project project = new Project();
		 	List<Statis> statiss = new ArrayList<Statis>();
		 	Integer count;
		 	Float sum;
			for(int i=0;i<13;i++){
				Integer temp1 = 0;
				Float temp2 = (float) 0;
				for(int j=0; j<3;j++){
					project.setSource(HrmConstants.sourcemap.get(i));
					project.setStatus(HrmConstants.statusmap.get(j));
					count = hrmService.statisCount(null, null, user, project);
					sum = hrmService.statisSum(null, null, user, project);
					temp1 += count;
					temp2 = temp2.floatValue() + sum.floatValue();
					Statis statis = new Statis();
					statis.setSource(HrmConstants.sourcemap.get(i));
					statis.setCount(count);
					statis.setSum(sum);
					statiss.add(statis);
				}
				Statis statis = new Statis();
				statis.setSource(HrmConstants.sourcemap.get(i));
				statis.setCount(temp1);
				statis.setSum(temp2);
				statiss.add(statis);
			}
			
			Integer temp1 = 0;
			Float temp2 = (float) 0;
			for(int j=0; j<3;j++){
				project.setSource(null);
				project.setStatus(HrmConstants.statusmap.get(j));
				count = hrmService.statisCount(null, null, user, project);
				sum = hrmService.statisSum(null, null, user, project);
				temp1 += count;
				temp2 += sum;
				Statis statis = new Statis();
				statis.setSource("合计");
				statis.setCount(count);
				statis.setSum(sum);
				statiss.add(statis);
			}
			Statis statis = new Statis();
			statis.setSource("合计");
			statis.setCount(temp1);
			statis.setSum(temp2);
			statiss.add(statis);
		
			// 传入statiss和filename，绘制excel
			String path = session.getServletContext().getRealPath(
	                "/upload/");
			String filename = "statis_source.xls";
			String path_filename = path+File.separator+ filename;
			hrmService.sourceExcel(statiss, path_filename);
			
			// 下载文件
		// 上传文件路径
		// 获得要下载文件的File对象
		File file = new File(path+File.separator+ filename);
		// 创建springframework的HttpHeaders对象
		HttpHeaders headers = new HttpHeaders();  
       // 下载显示的文件名，解决中文名称乱码问题  
       String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
       // 通知浏览器以attachment（下载方式）打开图片
       headers.setContentDispositionFormData("attachment", downloadFielName); 
       // application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    // 201 HttpStatus.CREATED
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
               headers, HttpStatus.CREATED); 
	}
	
	 
	 @RequestMapping(value="/statis/downloadDepartment")
	 public ResponseEntity<byte[]>  downloadProject(
			 HttpSession session) throws Exception{
		 	// 先查询信息
		 	System.out.println("/statis/downloadDepartment -->>");
			List<Statis> statiss = new ArrayList<Statis>();	
			Project project = new Project();
			User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
			String department;
			String team;
			Integer count;
			Float sum;
			
			for(int i=0;i<3;i++){
				Integer temp1 = 0;
				Float temp2 = (float) 0;
				for(int j=0; j<3;j++){
					department = HrmConstants.departmentmap.get(i/3);
					team = HrmConstants.teammap.get(i);
					project.setStatus(HrmConstants.statusmap.get(j));
					count = hrmService.statisCount(department, team, user, project);
					sum = hrmService.statisSum(department, team, user, project);
					temp1 += count;
					temp2 = temp2.floatValue() + sum.floatValue();
					Statis statis = new Statis();
					statis.setDepartment(department);
					statis.setTeam(team);
					statis.setCount(count);
					statis.setSum(sum);
					statiss.add(statis);
				}
				Statis statis = new Statis();
				statis.setDepartment("合计");
				statis.setTeam("合计");
				statis.setCount(temp1);
				statis.setSum(temp2);
				statiss.add(statis);
			}
			{
				Integer temp1 = 0;
				Float temp2 = (float) 0;
				department = HrmConstants.departmentmap.get(0);
				for(int j=0; j<3;j++){
					project.setStatus(HrmConstants.statusmap.get(j));
					count = hrmService.statisCount(department, null, user, project);
					sum = hrmService.statisSum(department, null, user, project);
					temp1 += count;
					temp2 += sum;
					Statis statis = new Statis();
					statis.setSource("小计");
					statis.setCount(count);
					statis.setSum(sum);
					statiss.add(statis);
				}
				Statis statis = new Statis();
				statis.setSource("合计");
				statis.setCount(temp1);
				statis.setSum(temp2);
				statiss.add(statis);
			}
			
			for(int i=3;i<6;i++){
				Integer temp1 = 0;
				Float temp2 = (float) 0;
				for(int j=0; j<3;j++){
					department = HrmConstants.departmentmap.get(i/3);
					team = HrmConstants.teammap.get(i);
					project.setStatus(HrmConstants.statusmap.get(j));
					count = hrmService.statisCount(department, team, user, project);
					sum = hrmService.statisSum(department, team, user, project);
					temp1 += count;
					temp2 = temp2.floatValue() + sum.floatValue();
					Statis statis = new Statis();
					statis.setDepartment(department);
					statis.setTeam(team);
					statis.setCount(count);
					statis.setSum(sum);
					statiss.add(statis);
				}
				Statis statis = new Statis();
				statis.setDepartment("合计");
				statis.setTeam("合计");
				statis.setCount(temp1);
				statis.setSum(temp2);
				statiss.add(statis);
			}
			
			{
				Integer temp1 = 0;
				Float temp2 = (float) 0;
				department = HrmConstants.departmentmap.get(1);
				for(int j=0; j<3;j++){
					project.setStatus(HrmConstants.statusmap.get(j));
					count = hrmService.statisCount(department, null, user, project);
					sum = hrmService.statisSum(department, null, user, project);
					temp1 += count;
					temp2 += sum;
					Statis statis = new Statis();
					statis.setSource("小计");
					statis.setCount(count);
					statis.setSum(sum);
					statiss.add(statis);
				}
				Statis statis = new Statis();
				statis.setSource("合计");
				statis.setCount(temp1);
				statis.setSum(temp2);
				statiss.add(statis);
			}
			
			{
				Integer temp1 = 0;
				Float temp2 = (float) 0;
				for(int j=0; j<3;j++){
					project.setStatus(HrmConstants.statusmap.get(j));
					count = hrmService.statisCount(null, null, user, project);
					sum = hrmService.statisSum(null, null, user, project);
					temp1 += count;
					temp2 += sum;
					Statis statis = new Statis();
					statis.setSource("合计");
					statis.setCount(count);
					statis.setSum(sum);
					statiss.add(statis);
				}
				Statis statis = new Statis();
				statis.setSource("合计");
				statis.setCount(temp1);
				statis.setSum(temp2);
				statiss.add(statis);
			}
		
			// 传入statiss和filename，绘制excel
			String path = session.getServletContext().getRealPath(
	                "/upload/");
			String filename = "statis_department.xls";
			String path_filename = path+File.separator+ filename;
			hrmService.departmentExcel(statiss, path_filename);
			
			// 下载文件
		// 上传文件路径
		// 获得要下载文件的File对象
		File file = new File(path+File.separator+ filename);
		// 创建springframework的HttpHeaders对象
		HttpHeaders headers = new HttpHeaders();  
       // 下载显示的文件名，解决中文名称乱码问题  
       String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
       // 通知浏览器以attachment（下载方式）打开图片
       headers.setContentDispositionFormData("attachment", downloadFielName); 
       // application/octet-stream ： 二进制流数据（最常见的文件下载）。
       headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    // 201 HttpStatus.CREATED
       return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
               headers, HttpStatus.CREATED); 
	}
}


