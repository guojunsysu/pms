package org.fkit.hrm.dao.provider;

import org.apache.ibatis.jdbc.SQL;
import org.fkit.hrm.domain.Log;
import static org.fkit.hrm.util.common.HrmConstants.LOGTABLE;

/**   
 * @Description: 日志动态SQL语句提供类
 * @author 郭俊  
 * @version V1.0   
 */
public class LogDynaSqlProvider {
	
	// 动态插入
	public String insertLog(final Log log){
		
		return new SQL(){
			{
				INSERT_INTO(LOGTABLE);
				if(log.getProject() != null 
						&& log.getProject().getId() != null 
						&& !log.getProject().getId().equals("")){
					VALUES("project_id", "#{project.id}");
				}
				if(log.getThisweek() != null && !log.getThisweek().equals("")){
					VALUES("thisweek", "#{thisweek}");
				}
				if(log.getNextweek() != null && !log.getNextweek().equals("")){
					VALUES("nextweek", "#{nextweek}");
				}
				if(log.getProblem() != null && !log.getProblem().equals("")){
					VALUES("problem", "#{problem}");
				}
				if(log.getFilename() != null && !log.getFilename().equals("")){
					VALUES("filename", "#{filename}");
				}
				if(log.getRealfilename() != null && !log.getRealfilename().equals("")){
					VALUES("realfilename", "#{realfilename}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateLog(final Log log){
		
		return new SQL(){
			{
				UPDATE(LOGTABLE);
				if(log.getThisweek() != null && !log.getThisweek().equals("")){
					SET(" thisweek = #{thisweek} ");
				}
				if(log.getNextweek() != null && !log.getNextweek().equals("")){
					SET(" nextweek = #{nextweek} ");
				}
				if(log.getProblem() != null && !log.getProblem().equals("")){
					SET(" problem = #{problem} ");
				}
				if(log.getFilename() != null && !log.getFilename().equals("")){
					SET(" filename = #{filename} ");
				}
				if(log.getRealfilename() != null && !log.getRealfilename().equals("")){
					SET(" realfilename = #{realfilename} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}


}
