package org.fkit.hrm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Project;
import org.fkit.hrm.domain.User;

import static org.fkit.hrm.util.common.HrmConstants.PROJECTTABLE;
import static org.fkit.hrm.util.common.HrmConstants.USERTABLE;

/**   
 * @Description: 项目动态SQL语句提供类
 * @author 郭俊  
 * @version V1.0   
 */
public class ProjectDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(final Map<String, Object> params){
		
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(PROJECTTABLE + " P");
//				if((params.get("username") != null && !params.get("username").equals(""))
//					|| (params.get("department") != null && !params.get("department").equals(""))
//					|| (params.get("team") != null && !params.get("team").equals("")))
				//{
					INNER_JOIN(USERTABLE + " U on U.id = P.user_id");
					// 个人查询权限
					User user = (User)params.get("user");
					if(user.getRole().equals("领导")  || user.getRole().equals("管理员")){
						//System.out.println("领导、管理员");
					}else if(user.getRole().equals("科室负责人") || user.getRole().equals("组长")){
						//System.out.println("科室负责人");
						WHERE("  U.department LIKE CONCAT ('%',#{user.department},'%') ");
					}else if(user.getRole().equals("组长")){
						//System.out.println("组长");
						WHERE("  U.team LIKE CONCAT ('%',#{user.team},'%') ");
					}else if(user.getRole().equals("员工")){
						//System.out.println("员工");
						WHERE("  U.id LIKE CONCAT ('%',#{user.id},'%') ");
					}
					// 模糊查询
					if(params.get("username") != null && !params.get("username").equals("")){
						WHERE("  U.username LIKE CONCAT ('%',#{username},'%') ");
					}
					if(params.get("department") != null && !params.get("department").equals("")){
						WHERE("  U.department LIKE CONCAT ('%',#{department},'%') ");
					}
					if(params.get("team") != null && !params.get("team").equals("")){
						WHERE("  U.team LIKE CONCAT ('%',#{team},'%') ");
					}
				//}
				if(params.get("project") != null){
					Project project = (Project) params.get("project");
					if(project.getImportant() != null && !project.getImportant().equals("")){
						WHERE("  important LIKE CONCAT ('%',#{project.important},'%') ");
					}
					if(project.getSource() != null && !project.getSource().equals("")){
						WHERE("  source LIKE CONCAT ('%',#{project.source},'%') ");
					}
					if(project.getManager() != null && !project.getManager().equals("")){
						WHERE("  manager LIKE CONCAT ('%',#{project.manager},'%') ");
					}
					if(project.getStatus() != null && !project.getStatus().equals("")){
						WHERE("  status LIKE CONCAT ('%',#{project.status},'%') ");
					}
					if(project.getProduct() != null && !project.getProduct().equals("")){
						WHERE("  product LIKE CONCAT ('%',#{project.product},'%') ");
					}
					if(project.getProjectname() != null && !project.getProjectname().equals("")){
						WHERE("  projectname LIKE CONCAT ('%',#{project.projectname},'%') ");
					}
					if(project.getOverview() != null && !project.getOverview().equals("")){
						WHERE("  overview LIKE CONCAT ('%',#{project.overview},'%') ");
					}
					if(project.getIncome_year() != null && !project.getIncome_year().equals("")){
						WHERE("  income_year LIKE CONCAT ('%',#{project.income_year},'%') ");
					}
					if(project.getIncome() != null && !project.getIncome().equals("")){
						WHERE("  income LIKE CONCAT ('%',#{project.income},'%') ");
					}
					if(project.getTime() != null && !project.getTime().equals("")){
						WHERE("  time LIKE CONCAT ('%',#{project.time},'%') ");
					}
					if(project.getIncrease_stock() != null && !project.getIncrease_stock().equals("")){
						WHERE("  increase_stock LIKE CONCAT ('%',#{project.increase_stock},'%') ");
					}
					if(project.getIncome_gz() != null && !project.getIncome_gz().equals("")){
						WHERE("  income_gz LIKE CONCAT ('%',#{project.income_gz},'%') ");
					}
					if(project.getCost() != null && !project.getCost().equals("")){
						WHERE("  cost LIKE CONCAT ('%',#{project.cost},'%') ");
					}
					if(project.getTime_success() != null && !project.getTime_success().equals("")){
						WHERE("  time_success LIKE CONCAT ('%',#{project.time_success},'%') ");
					}
					if(project.getTime_tagged() != null && !project.getTime_tagged().equals("")){
						WHERE("  time_tagged LIKE CONCAT ('%',#{project.time_tagged},'%') ");
					}
					if(project.getUser() != null && project.getUser().getId() != null && !project.getUser().getId().equals("")){
						WHERE("  user_id LIKE CONCAT ('%',#{project.user.id},'%') ");
					}
				}
				ORDER_BY("createtime desc");
			}
		}.toString();
		
		if(params.get("pageModel") != null){
			sql += " limit #{pageModel.firstLimitParam} , #{pageModel.pageSize}  ";
		}
		
		return sql;
	}	
	
	// 动态查询总数量
	public String count(final Map<String, Object> params){
		return new SQL(){
			{
				SELECT("count(*)");
				FROM(PROJECTTABLE + " P");
//				if((params.get("username") != null && !params.get("username").equals(""))
//					|| (params.get("department") != null && !params.get("department").equals(""))
//					|| (params.get("team") != null && !params.get("team").equals("")))
				//{
					INNER_JOIN(USERTABLE + " U on U.id = P.user_id");
					// 个人查询权限;
					User user = (User)params.get("user");
					if(user.getRole().equals("领导")  || user.getRole().equals("管理员")){
						//System.out.println("领导、管理员");
					}else if(user.getRole().equals("科室负责人") || user.getRole().equals("组长")){
						//System.out.println("科室负责人");
						WHERE("  U.department LIKE CONCAT ('%',#{user.department},'%') ");
					}else if(user.getRole().equals("组长")){
						//System.out.println("组长");
						WHERE("  U.team LIKE CONCAT ('%',#{user.team},'%') ");
					}else if(user.getRole().equals("员工")){
						//System.out.println("员工");
						WHERE("  U.id LIKE CONCAT ('%',#{user.id},'%') ");
					}
					// 模糊查询
					if(params.get("username") != null && !params.get("username").equals("")){
						WHERE("  U.username LIKE CONCAT ('%',#{username},'%') ");
					}
					if(params.get("department") != null && !params.get("department").equals("")){
						WHERE("  U.department LIKE CONCAT ('%',#{department},'%') ");
					}
					if(params.get("team") != null && !params.get("team").equals("")){
						WHERE("  U.team LIKE CONCAT ('%',#{team},'%') ");
					}
				//}
				if(params.get("project") != null){
					Project project = (Project) params.get("project");
					if(project.getImportant() != null && !project.getImportant().equals("")){
						WHERE("  important LIKE CONCAT ('%',#{project.important},'%') ");
					}
					if(project.getSource() != null && !project.getSource().equals("")){
						WHERE("  source LIKE CONCAT ('%',#{project.source},'%') ");
					}
					if(project.getManager() != null && !project.getManager().equals("")){
						WHERE("  manager LIKE CONCAT ('%',#{project.manager},'%') ");
					}
					if(project.getStatus() != null && !project.getStatus().equals("")){
						WHERE("  status LIKE CONCAT ('%',#{project.status},'%') ");
					}
					if(project.getProduct() != null && !project.getProduct().equals("")){
						WHERE("  product LIKE CONCAT ('%',#{project.product},'%') ");
					}
					if(project.getProjectname() != null && !project.getProjectname().equals("")){
						WHERE("  projectname LIKE CONCAT ('%',#{project.projectname},'%') ");
					}
					if(project.getOverview() != null && !project.getOverview().equals("")){
						WHERE("  overview LIKE CONCAT ('%',#{project.overview},'%') ");
					}
					if(project.getIncome_year() != null && !project.getIncome_year().equals("")){
						WHERE("  income_year LIKE CONCAT ('%',#{project.income_year},'%') ");
					}
					if(project.getIncome() != null && !project.getIncome().equals("")){
						WHERE("  income LIKE CONCAT ('%',#{project.income},'%') ");
					}
					if(project.getTime() != null && !project.getTime().equals("")){
						WHERE("  time LIKE CONCAT ('%',#{project.time},'%') ");
					}
					if(project.getIncrease_stock() != null && !project.getIncrease_stock().equals("")){
						WHERE("  increase_stock LIKE CONCAT ('%',#{project.increase_stock},'%') ");
					}
					if(project.getIncome_gz() != null && !project.getIncome_gz().equals("")){
						WHERE("  income_gz LIKE CONCAT ('%',#{project.income_gz},'%') ");
					}
					if(project.getCost() != null && !project.getCost().equals("")){
						WHERE("  cost LIKE CONCAT ('%',#{project.cost},'%') ");
					}
					if(project.getTime_success() != null && !project.getTime_success().equals("")){
						WHERE("  time_success LIKE CONCAT ('%',#{project.time_success},'%') ");
					}
					if(project.getTime_tagged() != null && !project.getTime_tagged().equals("")){
						WHERE("  time_tagged LIKE CONCAT ('%',#{project.time_tagged},'%') ");
					}
					if(project.getUser() != null && project.getUser().getId() != null && !project.getUser().getId().equals("")){
						WHERE("  user_id LIKE CONCAT ('%',#{project.user.id},'%') ");
					}
				}
			}
		}.toString();
	}	
	// 动态统计总和
		public String sum(final Map<String, Object> params){
			return new SQL(){
				{
					SELECT("IFNULL(sum(income_year),0.0)");
					FROM(PROJECTTABLE + " P");
//					if((params.get("username") != null && !params.get("username").equals(""))
//						|| (params.get("department") != null && !params.get("department").equals(""))
//						|| (params.get("team") != null && !params.get("team").equals("")))
					//{
						INNER_JOIN(USERTABLE + " U on U.id = P.user_id");
						// 个人查询权限
						User user = (User)params.get("user");
						if(user.getRole().equals("领导")  || user.getRole().equals("管理员")){
							//System.out.println("领导、管理员");
						}else if(user.getRole().equals("科室负责人")){
							//System.out.println("科室负责人");
							WHERE("  U.department LIKE CONCAT ('%',#{user.department},'%') ");
						}else if(user.getRole().equals("员工")){
							//System.out.println("员工");
							WHERE("  U.id LIKE CONCAT ('%',#{user.id},'%') ");
						}
						// 模糊查询
						if(params.get("username") != null && !params.get("username").equals("")){
							WHERE("  U.username LIKE CONCAT ('%',#{username},'%') ");
						}
						if(params.get("department") != null && !params.get("department").equals("")){
							WHERE("  U.department LIKE CONCAT ('%',#{department},'%') ");
						}
						if(params.get("team") != null && !params.get("team").equals("")){
							WHERE("  U.team LIKE CONCAT ('%',#{team},'%') ");
						}
					//}
					if(params.get("project") != null){
						Project project = (Project) params.get("project");
						if(project.getImportant() != null && !project.getImportant().equals("")){
							WHERE("  important LIKE CONCAT ('%',#{project.important},'%') ");
						}
						if(project.getSource() != null && !project.getSource().equals("")){
							WHERE("  source LIKE CONCAT ('%',#{project.source},'%') ");
						}
						if(project.getManager() != null && !project.getManager().equals("")){
							WHERE("  manager LIKE CONCAT ('%',#{project.manager},'%') ");
						}
						if(project.getStatus() != null && !project.getStatus().equals("")){
							WHERE("  status LIKE CONCAT ('%',#{project.status},'%') ");
						}
						if(project.getProduct() != null && !project.getProduct().equals("")){
							WHERE("  product LIKE CONCAT ('%',#{project.product},'%') ");
						}
						if(project.getProjectname() != null && !project.getProjectname().equals("")){
							WHERE("  projectname LIKE CONCAT ('%',#{project.projectname},'%') ");
						}
						if(project.getOverview() != null && !project.getOverview().equals("")){
							WHERE("  overview LIKE CONCAT ('%',#{project.overview},'%') ");
						}
						if(project.getIncome_year() != null && !project.getIncome_year().equals("")){
							WHERE("  income_year LIKE CONCAT ('%',#{project.income_year},'%') ");
						}
						if(project.getIncome() != null && !project.getIncome().equals("")){
							WHERE("  income LIKE CONCAT ('%',#{project.income},'%') ");
						}
						if(project.getTime() != null && !project.getTime().equals("")){
							WHERE("  time LIKE CONCAT ('%',#{project.time},'%') ");
						}
						if(project.getIncrease_stock() != null && !project.getIncrease_stock().equals("")){
							WHERE("  increase_stock LIKE CONCAT ('%',#{project.increase_stock},'%') ");
						}
						if(project.getIncome_gz() != null && !project.getIncome_gz().equals("")){
							WHERE("  income_gz LIKE CONCAT ('%',#{project.income_gz},'%') ");
						}
						if(project.getCost() != null && !project.getCost().equals("")){
							WHERE("  cost LIKE CONCAT ('%',#{project.cost},'%') ");
						}
						if(project.getTime_success() != null && !project.getTime_success().equals("")){
							WHERE("  time_success LIKE CONCAT ('%',#{project.time_success},'%') ");
						}
						if(project.getTime_tagged() != null && !project.getTime_tagged().equals("")){
							WHERE("  time_tagged LIKE CONCAT ('%',#{project.time_tagged},'%') ");
						}
						if(project.getUser() != null && project.getUser().getId() != null && !project.getUser().getId().equals("")){
							WHERE("  user_id LIKE CONCAT ('%',#{project.user.id},'%') ");
						}
					}
				}
			}.toString();
		}	
	// 动态插入
	public String insertProject(final Project project){
		
		return new SQL(){
			{
				INSERT_INTO(PROJECTTABLE);
				if(project.getImportant() != null && !project.getImportant().equals("")){
					VALUES("important", "#{important}");
				}
				if(project.getSource() != null && !project.getSource().equals("")){
					VALUES("source", "#{source}");
				}
				if(project.getManager() != null && !project.getManager().equals("")){
					VALUES("manager", "#{manager}");
				}
				if(project.getStatus() != null && !project.getStatus().equals("")){
					VALUES("status", "#{status}");
				}
				if(project.getProduct() != null && !project.getProduct().equals("")){
					VALUES("product", "#{product}");
				}
				if(project.getProjectname() != null && !project.getProjectname().equals("")){
					VALUES("projectname", "#{projectname}");
				}
				if(project.getOverview() != null && !project.getOverview().equals("")){
					VALUES("overview", "#{overview}");
				}
				if(project.getIncome_year() != null && !project.getIncome_year().equals("")){
					VALUES("income_year", "#{income_year}");
				}
				if(project.getIncome() != null && !project.getIncome().equals("")){
					VALUES("income", "#{income}");
				}
				if(project.getTime() != null && !project.getTime().equals("")){
					VALUES("time", "#{time}");
				}
				if(project.getIncrease_stock() != null && !project.getIncrease_stock().equals("")){
					VALUES("increase_stock", "#{increase_stock}");
				}
				if(project.getIncome_gz() != null && !project.getIncome_gz().equals("")){
					VALUES("income_gz", "#{income_gz}");
				}
				if(project.getCost() != null && !project.getCost().equals("")){
					VALUES("cost", "#{cost}");
				}
				if(project.getTime_success() != null && !project.getTime_success().equals("")){
					VALUES("time_success", "#{time_success}");
				}
				if(project.getTime_tagged() != null && !project.getTime_tagged().equals("")){
					VALUES("time_tagged", "#{time_tagged}");
				}
				if(project.getUser() != null && project.getUser().getId() != null && !project.getUser().getId().equals("")){
					VALUES("user_id", "#{user.id}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateProject(final Project project){
		
		return new SQL(){
			{
				UPDATE(PROJECTTABLE);
				if(project.getImportant() != null && !project.getImportant().equals("")){
					SET(" important = #{important} ");
				}
				if(project.getSource() != null && !project.getSource().equals("")){
					SET(" source = #{source} ");
				}
				if(project.getManager() != null && !project.getManager().equals("")){
					SET(" manager = #{manager} ");
				}
				if(project.getStatus() != null && !project.getStatus().equals("")){
					SET(" status = #{status} ");
				}
				if(project.getProduct() != null && !project.getProduct().equals("")){
					SET(" product = #{product} ");
				}
				if(project.getProjectname() != null && !project.getProjectname().equals("")){
					SET(" projectname = #{projectname} ");
				}
				if(project.getOverview() != null && !project.getOverview().equals("")){
					SET(" overview = #{overview} ");
				}
				if(project.getIncome_year() != null && !project.getIncome_year().equals("")){
					SET(" income_year = #{income_year} ");
				}
				if(project.getIncome() != null && !project.getIncome().equals("")){
					SET(" income = #{income} ");
				}
				if(project.getTime() != null && !project.getTime().equals("")){
					SET(" time = #{time} ");
				}
				if(project.getIncrease_stock() != null && !project.getIncrease_stock().equals("")){
					SET(" increase_stock = #{increase_stock} ");
				}
				if(project.getIncome_gz() != null && !project.getIncome_gz().equals("")){
					SET(" income_gz = #{income_gz} ");
				}
				if(project.getCost() != null && !project.getCost().equals("")){
					SET(" cost = #{cost} ");
				}
				if(project.getTime_success() != null && !project.getTime_success().equals("")){
					SET(" time_success = #{time_success} ");
				}
				if(project.getTime_tagged() != null && !project.getTime_tagged().equals("")){
					SET(" time_tagged = #{time_tagged} ");
				}
				if(project.getUser() != null && project.getUser().getId() != null && !project.getUser().getId().equals("")){
					SET(" user_id = #{user.id} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}


}
