package org.fkit.hrm.controller;

import java.util.ArrayList;
import java.util.List;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.fkit.hrm.domain.User;
import org.fkit.hrm.domain.Project;
import org.fkit.hrm.domain.Log;
import org.fkit.hrm.service.HrmService;
import org.fkit.hrm.util.common.HrmConstants;
import org.fkit.hrm.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Description: 处理项目请求控制器
 * @author 郭俊   
 * @version V1.0   
 */

@Controller
public class ProjectController {

	/**
	 * 自动注入ProjectService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	/**
	 * 处理/project/selectProject请求
	 * */
	@RequestMapping(value="/project/selectProject")
	 public String selectProject(Model model,Integer pageIndex,
			 String department, 
			 String team,
			 String username,
			 @ModelAttribute Project project,
			 HttpSession session){
		System.out.println("selectProject -->>");
		//System.out.println("pageIndex = " + pageIndex);
		System.out.println("project = " + project);
		PageModel pageModel = new PageModel();
		//System.out.println("getPageIndex = " + pageModel.getPageIndex());
		//System.out.println("getPageSize = " + pageModel.getPageSize());
		//System.out.println("getRecordCount = " + pageModel.getRecordCount());
		if(pageIndex != null){
			pageModel.setPageIndex(pageIndex);
		}
		
		List<Project> projects = new ArrayList<Project>();
		
		User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
		projects = hrmService.findProject(department,team,username,user,project,pageModel);
		System.out.println("project.important = " + project.getImportant());
		model.addAttribute("project", project);
		model.addAttribute("projects", projects);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("department", department);
		model.addAttribute("team", team);
		model.addAttribute("username", username);
		return "project/project";
	}
	
	/**
	 * 处理/project/selectProjectDetail请求
	 * */
	@RequestMapping(value="/project/selectProjectDetail")
	 public String selectProjectDetail(Model model,
			 @ModelAttribute Project project){
		System.out.println("selectProjectDetail -->>");
		
		Integer id = project.getId();
		project = hrmService.findProjectById(id);
		System.out.println(project);
		model.addAttribute("project", project);
		
		List<Log> logs = hrmService.findLogByProjectId(id);
		model.addAttribute("logs", logs);
		
		return "project/showProjectDetail";
	}
	
	/**
	 * 处理删除项目请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/project/removeProject")
	 public ModelAndView removeProject(String ids,ModelAndView mv){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除项目
			hrmService.removeProjectById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		mv.setViewName("redirect:/project/selectProject");
		// 返回ModelAndView
		return mv;
	}
	
	/**
	 * 处理添加请求
	 * @param String flag 标记， 1表示跳转到添加页面，2表示执行添加操作
	 * @param Project  project  要添加的项目对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/project/addProject")
	 public ModelAndView addProject(
			 String flag,
			 @ModelAttribute Project project,
			 HttpSession httpSession,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 设置跳转到添加页面
			mv.setViewName("project/showAddProject");
		}else{
			// 执行添加操作
			User user = (User)httpSession.getAttribute(HrmConstants.USER_SESSION);
			project.setUser(user);
			hrmService.addProject(project);
			// 设置客户端跳转到查询请求
			mv.setViewName("redirect:/project/selectProject");
		}
		// 返回
		return mv;
	}
	
	
	/**
	 * 处理修改项目请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Project project 要修改项目的对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/project/updateProject")
	 public ModelAndView updateProject(
			 String flag,
			 @ModelAttribute Project project,
			 ModelAndView mv){
		if(flag.equals("1")){
			// 根据id查询项目
			Project target = hrmService.findProjectById(project.getId());
			// 设置Model数据
			mv.addObject("project", target);
			// 设置跳转到修改页面
			mv.setViewName("project/showUpdateProject");
		}else{
			// 执行修改操作
			hrmService.modifyProject(project);
			// 设置客户端跳转到查询请求
			mv.addObject("id", project.getId());
			mv.setViewName("redirect:/project/selectProjectDetail");
		}
		// 返回
		return mv;
	}
	
//	/**
//	 * 通过department和username查询User
//	 * @param department 部门
//	 * @param username 用户名
//	 * @return List<User> 用户列表
//	 * */
//	private List<User> getUserId(String department,
//			String username){
//		if((department != null && !department.equals("")) 
//				|| (username != null && !department.equals(""))){
//			User user = new User();
//			if(department != null && !department.equals("")){
//				user.setDepartment(department);
//			}
//			if(username != null && !department.equals("")){
//				user.setUsername(username);
//			}
//			return hrmService.findUser(user, null);
//			
//		}
//		return null;
//	}
	
}
