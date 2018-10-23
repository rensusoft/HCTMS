package com.rensu.education.hctms.config.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.core.service.BaseService;
import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.config.bean.TrainPlanConfig;
import com.rensu.education.hctms.config.bean.TrainTeachOrder;
import com.rensu.education.hctms.config.dao.TrainPlanBeforeDao;
import com.rensu.education.hctms.config.dao.TrainPlanConfigDao;
import com.rensu.education.hctms.config.dao.TrainTeachOrderDao;
import com.rensu.education.hctms.teach.bean.StuTeachOrder;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.teach.dao.StuTeachOrderDao;
import com.rensu.education.hctms.teach.dao.TrainPlanDao;
import com.rensu.education.hctms.userauth.bean.OrgaInfo;
import com.rensu.education.hctms.userauth.bean.StudentInfo;
import com.rensu.education.hctms.userauth.dao.OrgaInfoDao;
import com.rensu.education.hctms.userauth.dao.StudentInfoDao;
import com.rensu.education.hctms.userauth.service.OrgaInfoService;
import com.rensu.education.hctms.util.Consts;
import com.rensu.education.hctms.util.DateUtil;


@Service("trainPlanBeforeService")
public class TrainPlanBeforeService extends BaseService<TrainPlanBefore> {
	
	Logger log = Logger.getLogger(TrainPlanBeforeService.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	@Autowired
	protected TrainPlanBeforeDao trainPlanBeforeDao;
	@Autowired
	protected TrainPlanConfigDao trainPlanConfigDao;
	@Autowired
	protected StudentInfoDao studentDao;
	@Autowired
	protected TrainPlanDao trainPlanDao;
	@Autowired
	protected TrainTeachOrderDao trainTeachOrderDao;
	@Autowired
	protected StuTeachOrderDao stuTeachOrderDao;
    @Autowired
    protected OrgaInfoDao orgaInfoDao;
	

	public boolean doTrainPlanBefore(HttpServletRequest req){
		boolean flag = false;
		String scId = req.getParameter("stuClass");
		if(scId!=null&&!scId.equals("")){
				
			StudentInfo student = new StudentInfo();
			student.setStu_class(Integer.parseInt(scId));
			student.setState("N");
			List<StudentInfo> stuList = studentDao.selectStuList(student);
//			trainPlanBeforeDao.deleteList(new TrainPlanBefore());
			if(stuList!=null&&stuList.size()>0){
				for(int i=0;i<stuList.size();i++){
					StudentInfo stu = stuList.get(i);
					int startday = Integer.parseInt(sdf.format(new Date()));
					String time = req.getParameter("time");
					if(time!=null&&!time.equals("")){
						startday = Integer.parseInt(time);
					}
					TrainPlanConfig trainPlan = new TrainPlanConfig();
					trainPlan.setTsc_id(stuList.get(i).getTsc_id());
					List<TrainPlanConfig> trainList = trainPlanConfigDao.selectList(trainPlan);
					int trainSize = trainList.size();
					for(int m=trainSize;m>0;m--){
						int days =0,maxNum = 0,deptId = 0,num = 0;
						for(int j=0;j<trainList.size();j++){
							if(trainList.get(j)!=null){
								days = getTotalDays(startday+"",trainList.get(j).getDuration_unit(),
										trainList.get(j).getDuration());
							}
							int deptMinNum = getDeptMinNum(startday,trainList.get(j).getDept_id(),days);
							if(deptMinNum>maxNum){
								deptId = trainList.get(j).getDept_id();
								maxNum = deptMinNum;
								num = j;
							}
						}
						int endday = getDate(startday,getTotalDays(startday+"",trainList.get(num).getDuration_unit(),
								trainList.get(num).getDuration())-1);
						if(maxNum>0){
							TrainPlanBefore before = new TrainPlanBefore();
							before.setConfig_id(trainList.get(num).getId());
							before.setStu_auth_id(stu.getStu_auth_id());
							before.setTrain_dept_id(deptId);
							before.setTrain_start_date(startday);
							before.setTrain_end_date(endday);
							before.setTrain_start_time(getDateTime(startday+"000000"));
							before.setTrain_end_time(getDateTime(endday+"235959"));
							before.setState("Y");
							trainPlanBeforeDao.add(before);
							trainList.remove(num);
						}
						startday = getDate(endday,1);
					}
				}
				//将预编排过的学生的状态设为M，待生成轮转计划状态
				StudentInfo studentInfo = new StudentInfo();
                studentInfo.setStu_class(Integer.parseInt(scId));
                studentInfo.setState("M");
                studentDao.updateStateList(studentInfo);
			}
			flag = true;
		}
		return flag;
	}
	
	private int getTotalDays(String date,String unit,int day){
		int days=0;
		if(unit!=null&&!unit.equals("")&&day>0){
			if(unit.equals("D")){
				days = day;
			}
			if(unit.equals("W")){
				days = day*7;
			}
			if(unit.equals("M")){
				String time = DateUtil.getDateByMonth(date, day);
				try {
					days = DateUtil.daysBetween(sdf.parse(date),sdf.parse(time));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return days;
	}
	
	private int getDeptMinNum(int day,int deptId,int days){
		int deptMinNum = 0;
		for(int m=0;m<days;m++){
	        int date = getDate(day,m);
	        TrainPlanBefore count = new TrainPlanBefore();
	        count.setTrain_start_date(date);
	        count.setTrain_dept_id(deptId);
	        int num = trainPlanBeforeDao.selectTrainCount(count);
	        if(num>0&&(deptMinNum<=0||num<deptMinNum)){
	        	deptMinNum = num;
	        }
		}
		return deptMinNum;
	}
	
	private int getDate(int day,int num){
        Calendar c = Calendar.getInstance();
        try {
			c.setTime(sdf.parse(day+""));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        c.add(Calendar.DAY_OF_MONTH, num);
        Date nextday = c.getTime();
        return Integer.parseInt(sdf.format(nextday).toString());
	}
	
	private Timestamp getDateTime(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date datetime = new Date();
		try {
			datetime = new SimpleDateFormat("yyyyMMddHHmmss").parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = dateFormat.format(datetime);//时间存储为字符串
		return Timestamp.valueOf(str);//转换时间字符串为Timestamp
	}
	
	public boolean changeTrainPlanBefore(HttpServletRequest req){
		boolean flag = false;
		String data = req.getParameter("data");
		String stu_auth_id = req.getParameter("stu_auth_id");
		StudentInfo studentInfo=studentDao.selectStuTscId(Integer.parseInt(stu_auth_id));
		if(data!=null&&!data.equals("")&&stu_auth_id!=null&&!stu_auth_id.equals("")){
			JSONArray jsonArray = JSONArray.fromObject(data);
			List<TrainPlanBefore> list = JSONArray.toList(jsonArray, TrainPlanBefore.class);
			if(list!=null&&list.size()>0){
				TrainPlanBefore plan = new TrainPlanBefore();
				plan.setStu_auth_id(Integer.parseInt(stu_auth_id));
				plan.setState("Y");
				plan.setOrderCondition("train_start_date asc");
				int startday = Integer.parseInt(sdf.format(new Date()));
				String time = req.getParameter("time");
				if(time!=null&&!time.equals("")){
					startday = Integer.parseInt(time);
				}
				int num = trainPlanBeforeDao.deleteList(plan);
				TrainPlanConfig config = new TrainPlanConfig();
				config.setTsc_id(studentInfo.getTsc_id());
				List<TrainPlanConfig> trainList = trainPlanConfigDao.selectList(config);
				if(trainList!=null&&trainList.size()>0){
					for(int i=0;i<list.size()&&num>0;i++){
						int j=0;
						for(;j<trainList.size();j++){
							if(trainList.get(j).getId().intValue()==list.get(i).getConfig_id().intValue()){
								break;
							}
						}
						if(j<trainList.size()){
							config = trainList.get(j);
							int endday = getDate(startday,
									getTotalDays(startday+"",config.getDuration_unit(),config.getDuration())-1);
							TrainPlanBefore before = new TrainPlanBefore();
							before.setConfig_id(list.get(i).getConfig_id());
							before.setStu_auth_id(Integer.parseInt(stu_auth_id));
							before.setTrain_dept_id(list.get(i).getTrain_dept_id());
							before.setTrain_start_date(startday);
							before.setTrain_end_date(endday);
							before.setTrain_start_time(getDateTime(startday+"000000"));
							before.setTrain_end_time(getDateTime(endday+"235959"));
							before.setState("Y");
							trainPlanBeforeDao.add(before);
							startday = getDate(endday,1);
							trainList.remove(j);
						}
					}
				}
			}
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 按顺序码微调轮转计划
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年9月22日
	 */
	public boolean resetTrainPlanBefore(HttpServletRequest req){
        boolean flag = false;
        String data = req.getParameter("data");
        String stu_auth_id = req.getParameter("stu_auth_id");
        StudentInfo studentInfo=studentDao.selectStuTscId(Integer.parseInt(stu_auth_id));
        if(data!=null&&!data.equals("")&&stu_auth_id!=null&&!stu_auth_id.equals("")){
            JSONArray jsonArray = JSONArray.fromObject(data);
            List<TrainPlanBefore> list = JSONArray.toList(jsonArray, TrainPlanBefore.class);
            if(list!=null&&list.size()>0){
                TrainPlanBefore plan = new TrainPlanBefore();
                plan.setStu_auth_id(Integer.parseInt(stu_auth_id));
                plan.setState("Y");
                plan.setOrderCondition("train_start_date asc");
//                String time = req.getParameter("time");
//                int startday = Integer.parseInt(sdf.format(new Date()));
//                if(time!=null&&!time.equals("")){
//                    startday = Integer.parseInt(time);
//                }
                int num = trainPlanBeforeDao.deleteList(plan);
                TrainPlanConfig config = new TrainPlanConfig();
                config.setTsc_id(studentInfo.getTsc_id());
                List<TrainPlanConfig> trainList = trainPlanConfigDao.selectList(config);
                OrgaInfo orgaInfo=new OrgaInfo();
        		orgaInfo.setState("Y");
        		orgaInfo.setOrga_type(1);
        		String orderCondition="orga_id asc";
        		orgaInfo.setOrderCondition(orderCondition);
        		List<OrgaInfo> orgaInfos=orgaInfoDao.selectList(orgaInfo);
                for(int i=0;i<list.size()&&num>0;i++){
                            TrainPlanBefore before = new TrainPlanBefore();
//                            before.setConfig_id(list.get(i).getConfig_id());
                            before.setStu_auth_id(Integer.parseInt(stu_auth_id));
                            for(int j=0;j<orgaInfos.size();j++){
                            	if(list.get(i).getOrga_name().equals(orgaInfos.get(j).getOrga_name())){
                            		before.setTrain_dept_id(orgaInfos.get(j).getOrga_id());
                            	}
                            }
                            for(int k=0;k<trainList.size();k++){
                            	if(trainList.get(k).getDept_id().equals(before.getTrain_dept_id())){
                            		before.setConfig_id(trainList.get(k).getId());
                            	}
                            }
                            before.setTrain_start_date(list.get(i).getTrain_start_date());
                            before.setTrain_end_date(list.get(i).getTrain_end_date());
                            before.setTrain_start_time(getDateTime(list.get(i).getTrain_start_date()+"000000"));
                            before.setTrain_end_time(getDateTime(list.get(i).getTrain_end_date()+"235959"));
                            before.setState("Y");
                            trainPlanBeforeDao.add(before);
//                            startday = getDate(endday,1);
//                            trainList.remove(j);
                        }
                }
            flag = true;
        }
        return flag;
    }
	
	public boolean resetTrainPlan(HttpServletRequest req){
        boolean flag = false;
        String data = req.getParameter("data");
        String stu_auth_id = req.getParameter("stu_auth_id");
        StudentInfo studentInfo=studentDao.selectStuStateYTscId(Integer.parseInt(stu_auth_id));
        if(data!=null&&!data.equals("")&&stu_auth_id!=null&&!stu_auth_id.equals("")){
            JSONArray jsonArray = JSONArray.fromObject(data);
            List<TrainPlan> list = JSONArray.toList(jsonArray, TrainPlan.class);
            if(list!=null&&list.size()>0){
                TrainPlan plan = new TrainPlan();
                plan.setStu_auth_id(Integer.parseInt(stu_auth_id));
                plan.setState("Y");
                plan.setTrain_status(51);
                plan.setOrderCondition("train_start_date asc");
//                String time = req.getParameter("time");
//                int startday = Integer.parseInt(sdf.format(new Date()));
//                if(time!=null&&!time.equals("")){
//                    startday = Integer.parseInt(time);
//                }
                int num = trainPlanDao.deleteList(plan);
                TrainPlanConfig config = new TrainPlanConfig();
                config.setTsc_id(studentInfo.getTsc_id());
                List<TrainPlanConfig> trainList = trainPlanConfigDao.selectList(config);
                OrgaInfo orgaInfo=new OrgaInfo();
        		orgaInfo.setState("Y");
        		orgaInfo.setOrga_type(1);
        		String orderCondition="orga_id asc";
        		orgaInfo.setOrderCondition(orderCondition);
        		List<OrgaInfo> orgaInfos=orgaInfoDao.selectList(orgaInfo);
                for(int i=0;i<list.size()&&num>0;i++){
                            TrainPlan trainPlan= new TrainPlan();
//                            before.setConfig_id(list.get(i).getConfig_id());
                            trainPlan.setStu_auth_id(Integer.parseInt(stu_auth_id));
                            for(int j=0;j<orgaInfos.size();j++){
                            	if(list.get(i).getOrga_name().equals(orgaInfos.get(j).getOrga_name())){
                            		trainPlan.setTrain_dept_id(orgaInfos.get(j).getOrga_id().toString());
                            	}
                            }
                            for(int k=0;k<trainList.size();k++){
                            	if(trainList.get(k).getDept_id().equals(trainPlan.getTrain_dept_id())){
                            		trainPlan.setTpc_id(trainList.get(k).getId());
                            	}
                            }
                            trainPlan.setTrain_start_date(list.get(i).getTrain_start_date());
                            trainPlan.setTrain_end_date(list.get(i).getTrain_end_date());
                            trainPlan.setTrain_start_time(getDateTime(list.get(i).getTrain_start_date()+"000000"));
                            trainPlan.setTrain_end_time(getDateTime(list.get(i).getTrain_end_date()+"235959"));
                            trainPlan.setState("Y");
                            trainPlan.setTrain_status(51);
                            trainPlan.setId(trainPlanDao.getSequence());
                            trainPlanDao.add(trainPlan);
//                            startday = getDate(endday,1);
//                            trainList.remove(j);
                        }
                }
            flag = true;
        }
        return flag;
    }
	
	public boolean createTrainPlan(HttpServletRequest req){
		boolean flag = false;
		String stuClass = req.getParameter("stuClass");
		if(stuClass!=null&&!stuClass.equals("")){
			TrainPlanBefore trainPlanBefore = new TrainPlanBefore();
			trainPlanBefore.setState("Y");
			trainPlanBefore.setStu_class(Integer.parseInt(stuClass));
			trainPlanBefore.setOrderCondition("stu_auth_id,train_start_date");
			List<TrainPlanBefore> planList = trainPlanBeforeDao.selectTPBList(trainPlanBefore);
			List<TrainPlan> list = new ArrayList<TrainPlan>();
			if(planList!=null&&planList.size()>0){
				int startStatus = 52;
				int status = 51;
				List<ProcessInfo> proList = (List<ProcessInfo>) req.getAttribute(Consts.SESSION_PROCESS_INFO);
				if(proList!=null&&proList.size()>0){
					for(int i=0;i<proList.size();i++){
						ProcessInfo process = proList.get(i);
						if(process.getProc_num()==0){
							startStatus = process.getId();
						}
						if(process.getProc_num()==-1){
							status = process.getId();
						}
					}
				}
				int stu_auth_id = 0;
//				List<StuTeachOrder> stuOderList = new ArrayList<StuTeachOrder>();
				for(int i=0;i<planList.size();i++){
					TrainPlanBefore before = planList.get(i);
					TrainPlan plan = new TrainPlan();
					plan.setStu_auth_id(before.getStu_auth_id());
					plan.setTrain_dept_id(before.getTrain_dept_id().toString());
					plan.setTrain_start_date(before.getTrain_start_date());
					plan.setTrain_end_date(before.getTrain_end_date());
					plan.setTrain_start_time(before.getTrain_start_time());
					plan.setTrain_end_time(before.getTrain_end_time());
					plan.setState("Y");
					plan.setTpc_id(before.getConfig_id());
					if(before.getStu_auth_id()==stu_auth_id){
						plan.setTrain_status(status);
					}else{
						plan.setTrain_status(startStatus);
					}
					list.add(plan);
					stu_auth_id = before.getStu_auth_id();
//					TrainTeachOrder trainOrder = new TrainTeachOrder();
//					trainOrder.setTpc_id(before.getConfig_id());
//					trainOrder.setState("Y");
//					List<TrainTeachOrder> trainOrderList = trainTeachOrderDao.selectList(trainOrder);
//					if(trainOrderList!=null&&trainOrderList.size()>0){
//						for(int j=0;j<trainOrderList.size();j++){
//							trainOrder = trainOrderList.get(j);
//							StuTeachOrder stuOrder = new StuTeachOrder();
//							stuOrder.setTto_id(trainOrder.getId());
//							stuOrder.setDept_id(before.getTrain_dept_id());
//							stuOrder.setStu_auth_id(before.getStu_auth_id());
//							stuOrder.setOrder_name(trainOrder.getOrder_name());
//							stuOrder.setType_id(trainOrder.getType_id());
//							stuOrder.setSup_id(trainOrder.getSup_id());
//							stuOrder.setSort_num(trainOrder.getSort_num());
//							stuOrder.setOrder_num(trainOrder.getOrder_num());
//							stuOrder.setOrder_num_unit(trainOrder.getOrder_num_unit());
//							stuOrder.setState(trainOrder.getState());
//							stuOrder.setData_type_id(trainOrder.getData_type_id());
//							stuOderList.add(stuOrder);
//						}
//					}
				}
				if(list.size()>0){
					trainPlanDao.insertList(list);
				}
				//
				TrainPlanBefore TPBefore = new TrainPlanBefore();
                TPBefore.setStu_class(Integer.parseInt(stuClass));
                trainPlanBeforeDao.deleteTPBList(TPBefore);
                //
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setStu_class(Integer.parseInt(stuClass));
				studentInfo.setState("Y");
				studentDao.updateStuStateList(studentInfo);
//				if(stuOderList.size()>0){
//					stuTeachOrderDao.insertList(stuOderList);
//				}
			}
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 根据train_plan_before的记录倒推查轮转方案id
	 * @param studentInfo
	 * @return
	 * @author guocc
	 * @date 2017年9月25日
	 */
	public TrainPlanBefore getTscIdFromTPBefore(StudentInfo studentInfo) {
	    return trainPlanBeforeDao.getTscIdFromTPBefore(studentInfo);
	}
}
