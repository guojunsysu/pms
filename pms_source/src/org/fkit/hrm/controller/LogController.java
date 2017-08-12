package org.fkit.hrm.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.fkit.hrm.domain.Log;
import org.fkit.hrm.domain.Project;
import org.fkit.hrm.service.HrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**   
 * @Description: 处理日志请求控制器
 * @author 郭俊 
 * @version V1.0   
 */

@Controller
public class LogController {

	/**
	 * 自动注入UserService
	 * */
	@Autowired
	@Qualifier("hrmService")
	private HrmService hrmService;
	
	
	/**
	 * 处理添加日志请求
	 * @param String flag 标记， 1表示跳转到上传页面，2表示执行上传操作
	 * @param Notice notice  要添加的日志
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/log/addLog")
	 public ModelAndView addLog(
			 String flag,
			 Integer project_id,
			 @ModelAttribute Log log,
			 ModelAndView mv,
			 HttpSession session)throws Exception{
		if(flag.equals("1")){
			mv.addObject("project_id", project_id);
			mv.setViewName("log/showAddLog");
		}else{
			System.out.println("addLog -->>");
			if(!log.getFile().isEmpty()){
				// 上传文件路径
				String path = session.getServletContext().getRealPath(
						"/upload/");
				System.out.println("上传文件存在");
				
				//随机重命名上传文件名
				UUID uuid = UUID.randomUUID();
				String realfilename = uuid.toString().replace("-", "");
				String filename = log.getFile().getOriginalFilename();
				
				 // 将上传文件保存到一个目标文件当中
				log.getFile().transferTo(new File(path+File.separator+ realfilename));
				
				// 插入数据库
				// 设置filename和realfilename
				log.setFilename(filename);
				log.setRealfilename(realfilename);
			}
			Project project = new Project();
			project.setId(project_id);
			log.setProject(project);
			System.out.println(log);
			// 插入数据库
			hrmService.addLog(log);
			// 返回
			mv.addObject("id", project_id);
			mv.setViewName("redirect:/project/selectProjectDetail");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理删除日志请求
	 * @param String ids 需要删除的id字符串
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/log/removeLog")
	 public ModelAndView removeLog(String ids,
			 Integer project_id,
			 //RedirectAttributes res
			 ModelAndView mv
			 ){
		// 分解id字符串
		String[] idArray = ids.split(",");
		for(String id : idArray){
			// 根据id删除文档
			hrmService.removeLogById(Integer.parseInt(id));
		}
		// 设置客户端跳转到查询请求
		// 返回ModelAndView
		mv.addObject("id", project_id);
		mv.setViewName("redirect:/project/selectProjectDetail");
		return mv;
	}
	
	/**
	 * 处理修改日志请求
	 * @param String flag 标记， 1表示跳转到修改页面，2表示执行修改操作
	 * @param Log log 要修改的日志对象
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/log/updateLog")
	 public ModelAndView pdateLog(
			 String flag,
			 Integer project_id,
			 @ModelAttribute Log log,
			 ModelAndView mv,
			 HttpSession session)throws Exception{
		if(flag.equals("1")){
			// 根据id查询文档
			Log target = hrmService.findLogById(log.getId());
			// 设置Model数据
			mv.addObject("log", target);
			mv.addObject("project_id", project_id);
			// 设置跳转到修改页面
			mv.setViewName("log/showUpdateLog");
		}else{
			if(!log.getFile().isEmpty()){
				// 上传文件路径
				String path = session.getServletContext().getRealPath(
						"/upload/");
				System.out.println("updateLog -->>");
				System.out.println("更新上传文件存在");
				
				//随机重命名上传文件名
				UUID uuid = UUID.randomUUID();
				String realfilename = uuid.toString().replace("-", "");
				String filename = log.getFile().getOriginalFilename();
				
				 // 将上传文件保存到一个目标文件当中
				log.getFile().transferTo(new File(path+File.separator+ realfilename));
				
				// 插入数据库
				// 设置filename和realfilename
				log.setFilename(filename);
				log.setRealfilename(realfilename);
			}
			Project project = new Project();
			project.setId(project_id);
			log.setProject(project);
			System.out.println(log);
			// 更新数据库
			hrmService.modifyLog(log);
			// 返回
			mv.addObject("id", project_id);
			mv.setViewName("redirect:/project/selectProjectDetail");
		}
		// 返回
		return mv;
	}
	
	/**
	 * 处理日志文件下载请求
	 * @param Integer id 要下载的日志文件的id
	 * @param ModelAndView mv
	 * */
	@RequestMapping(value="/log/downLoad")
	 public ResponseEntity<byte[]>  downLoad(
			 Integer id,
			 HttpSession session) throws Exception{
		// 根据id查询文档
		Log target = hrmService.findLogById(id);
		String realfilename = target.getRealfilename();
		String filename = target.getFilename();
		// 上传文件路径
		String path = session.getServletContext().getRealPath(
                "/upload/");
		// 获得要下载文件的File对象
		File file = new File(path+File.separator+ realfilename);
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
