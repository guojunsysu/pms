package org.fkit.hrm.util.common;

import java.util.HashMap;
import java.util.Map;

public class HrmConstants {
	
	// 数据库表常量
	public static final String USERTABLE = "user_inf";
	public static final String PROJECTTABLE = "project_inf";
	public static final String LOGTABLE = "log_inf";
	
	// 登录
	public static final String LOGIN = "loginForm";
	// 用户的session对象
	public static final String USER_SESSION = "user_session";
	
	// 默认每页8条数据
	public static int PAGE_DEFAULT_SIZE = 8;
	
	// 默认项目来源字典
	public static Map<Integer, String> sourcemap = new HashMap<Integer, String>();
	static{
		sourcemap.put(0, "花都");
		sourcemap.put(1, "增城");
		sourcemap.put(2, "白云");
		sourcemap.put(3, "从化");
		sourcemap.put(4, "天河");
		sourcemap.put(5, "荔湾");
		sourcemap.put(6, "黄埔");
		sourcemap.put(7, "海珠");
		sourcemap.put(8, "南沙");
		sourcemap.put(9, "东山");
		sourcemap.put(10, "番禺");
		sourcemap.put(11, "越秀");
		sourcemap.put(12, "校园");
	};
	// 默认项目状态字典
	public static Map<Integer, String> statusmap = new HashMap<Integer, String>();
	static{
		statusmap.put(0, "重点关注");
		statusmap.put(1, "正常推进");
		statusmap.put(2, "停滞");
	};
	// 默认科室字典
	public static Map<Integer, String> departmentmap = new HashMap<Integer, String>();
	static{
		departmentmap.put(0, "省市政企支撑室");
		departmentmap.put(1, "区域支撑室");
	};
	// 默认科室字典
	public static Map<Integer, String> teammap = new HashMap<Integer, String>();
	static{
		teammap.put(0, "大企业支撑团队");
		teammap.put(1, "金融及互联网行业支撑团队");
		teammap.put(2, "党政军行业支撑团队");
		teammap.put(3, "东片区域支撑组");
		teammap.put(4, "西片区域支撑组");
		teammap.put(5, "政府行业支撑组");
	};
	
	
}
