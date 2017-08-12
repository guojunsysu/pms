package org.fkit.hrm.dao.provider;

import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.User;
import static org.fkit.hrm.util.common.HrmConstants.USERTABLE;;

/**   
 * @Description: 用户动态SQL语句提供类
 * @author 郭俊
 * @version V1.0   
 */
public class UserDynaSqlProvider {
	// 分页动态查询
	public String selectWhitParam(final Map<String, Object> params){
		String sql =  new SQL(){
			{
				SELECT("*");
				FROM(USERTABLE);
				if(params.get("user") != null){
					User user = (User)params.get("user");
					if(user.getUsername() != null && !user.getUsername().equals("")){
						WHERE("  username LIKE CONCAT ('%',#{user.username},'%') ");
					}
					if(user.getDepartment() != null && !user.getDepartment().equals("")){
						WHERE("  department LIKE CONCAT ('%',#{user.department},'%') ");
					}
					if(user.getTeam() != null && !user.getTeam().equals("")){
						WHERE("  team LIKE CONCAT ('%',#{user.team},'%') ");
					}
					if(user.getRole() != null && !user.getRole().equals("")){
						WHERE("  role LIKE CONCAT ('%',#{user.role},'%') ");
					}
				}
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
				FROM(USERTABLE);
				if(params.get("user") != null){
					User user = (User)params.get("user");
					if(user.getUsername() != null && !user.getUsername().equals("")){
						WHERE("  username LIKE CONCAT ('%',#{user.username},'%') ");
					}
					if(user.getDepartment() != null && !user.getDepartment().equals("")){
						WHERE("  department LIKE CONCAT ('%',#{user.department},'%') ");
					}
					if(user.getTeam() != null && !user.getTeam().equals("")){
						WHERE("  team LIKE CONCAT ('%',#{user.team},'%') ");
					}
					if(user.getRole() != null && !user.getRole().equals("")){
						WHERE("  role LIKE CONCAT ('%',#{user.role},'%') ");
					}
				}
			}
		}.toString();
	}	
	
	// 动态插入
	public String insertUser(final User user){
		
		return new SQL(){
			{
				INSERT_INTO(USERTABLE);
				if(user.getUsername() != null && !user.getUsername().equals("")){
					VALUES("username", "#{username}");
				}
				if(user.getLoginname() != null && !user.getLoginname().equals("")){
					VALUES("loginname", "#{loginname}");
				}
				if(user.getPassword() != null && !user.getPassword().equals("")){
					VALUES("password", "#{password}");
				}
				if(user.getDepartment() != null && !user.getDepartment().equals("")){
					VALUES("department", "#{department}");
				}
				if(user.getTeam() != null && !user.getTeam().equals("")){
					VALUES("team", "#{team}");
				}
				if(user.getRole() != null && !user.getRole().equals("")){
					VALUES("role", "#{role}");
				}
			}
		}.toString();
	}
	// 动态更新
		public String updateUser(final User user){
			
			return new SQL(){
				{
					UPDATE(USERTABLE);
					if(user.getUsername() != null){
						SET(" username = #{username} ");
					}
					if(user.getLoginname() != null){
						SET(" loginname = #{loginname} ");
					}
					if(user.getPassword()!= null){
						SET(" password = #{password} ");
					}
					if(user.getDepartment() != null){
						SET(" department = #{department} ");
					}
					if(user.getTeam()!= null){
						SET(" team = #{team} ");
					}
					if(user.getRole()!= null){
						SET(" role = #{role} ");
					}
					WHERE(" id = #{id} ");
				}
			}.toString();
		}
}
