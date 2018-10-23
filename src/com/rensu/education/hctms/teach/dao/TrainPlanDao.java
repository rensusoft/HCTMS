package com.rensu.education.hctms.teach.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.core.dao.BaseDao;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.userauth.bean.UserAuthority;

import configuration.mapper.TrainPlanMapper;

@Repository("trainPlanDao")
public class TrainPlanDao extends BaseDao<TrainPlan> {
	
	Logger log = Logger.getLogger(TrainPlanDao.class);
	
	@Autowired
	private TrainPlanMapper<TrainPlan> trainPlanMapper;
	
	@Override
	public int add(TrainPlan trainPlan) {
		return trainPlanMapper.add(trainPlan);
	};
	
	@Override
	public int update(TrainPlan trainPlan) {
		return trainPlanMapper.update(trainPlan);
	};
	
	@Override
	public int delete(TrainPlan trainPlan) {
		return trainPlanMapper.delete(trainPlan);
	};
	
	@Override
	public TrainPlan selectOne(int id) {
		return trainPlanMapper.selectOne(id);
	};
	
	@Override
	public List<TrainPlan> selectList(TrainPlan trainPlan) {
		return trainPlanMapper.selectList(trainPlan);
	};
	
	@Override
	public List<TrainPlan> selectPage(RowBounds rowBounds, TrainPlan trainPlan) {
		return trainPlanMapper.selectPage(rowBounds, trainPlan);
	};
	
	@Override
	public int selectCount(TrainPlan trainPlan) {
		return trainPlanMapper.selectCount(trainPlan);
	};
	
	@Override
	public int getSequence() {
		return trainPlanMapper.getSequence();
	}


	public List<TrainPlan>  selectBystu_auth_id(Integer stu_auth_id) {
		return trainPlanMapper.selectBystu_auth_id(stu_auth_id);
	}


	public TrainPlan selectOneLun(TrainPlan trainPlan) {
		return trainPlanMapper.selectOneLun(trainPlan);
	}
	/**
	 * 查询轮转计算中的科室和科室名
	 * @param auth_id
	 * @return
	 *@author huq
	 *@date 2017年1月19日
	 */
	public List<TrainPlan> selectOrgaNameList(int auth_id) {
		return trainPlanMapper.selectOrgaNameList(auth_id);
	}

	public TrainPlan selectOneById(int id) {
		return trainPlanMapper.selectOneById(id);
	}

	public List<TrainPlan> selectToRotateList(TrainPlan trainPlan) {
		return trainPlanMapper.selectToRotateList(trainPlan);
	}

	public TrainPlan selectOneTrain(TrainPlan trainPlan) {
		return trainPlanMapper.selectOneTrain(trainPlan);
	}

	public int updateTrainStatus(TrainPlan trainPlan) {
		return trainPlanMapper.updateTrainStatus(trainPlan);
	}

	public List<TrainPlan> selectAllStuByTJ(TrainPlan train) {
		return trainPlanMapper.selectAllStuByTJ(train);
	}

	public List<TrainPlan> getPendingAudit(RowBounds rowBounds, TrainPlan trainPlan) {
		return trainPlanMapper.getPendingAudit(rowBounds,trainPlan);
	}

	public int getPendingAuditCount(TrainPlan trainPlan) {
		return trainPlanMapper.getPendingAuditCount(trainPlan);
	}

	public List<TrainPlan> selectLunByRole(TrainPlan trainPlan) {
		return trainPlanMapper.selectLunByRole(trainPlan);
	}

	public List<TrainPlan> getSecreDetails(TrainPlan trainPlan) {
		return trainPlanMapper.getSecreDetails(trainPlan);
	}

	public int insertList(List<TrainPlan> trainPlanList) {
		return trainPlanMapper.insertList(trainPlanList);
	}

	public List<TrainPlan> getGradeInit(TrainPlan trainPlan) {
		return trainPlanMapper.getGradeInit(trainPlan);
	}

	public List<TrainPlan>  selectByAuthOrga(TrainPlan trainPlan) {
		return trainPlanMapper.selectByAuthOrga(trainPlan);
	}
	
	public int absenceNum(StuAttendanceInfo stuAttendanceInfo) {
		return trainPlanMapper.absenceNum(stuAttendanceInfo);
	}
	/**
	 * 得到科教科角色首页的全院轮转情况板块数据
	 * @param trainPlan
	 * @return Integer
	 * @author guocc
	 * @date 2017年4月18日
	 */
	public Integer getRoundRobin(TrainPlan trainPlan) {
		return trainPlanMapper.getRoundRobin(trainPlan);
	}
	
	/**
	 * 获取学生管理模块主页面数据
	 * @param trainPlan
	 * @return List<TrainPlan>
	 * @author guocc
	 * @date 2017年4月19日
	 */
	public List<TrainPlan> getStuManageInfo(TrainPlan trainPlan) {
		return trainPlanMapper.getStuManageInfo(trainPlan);
	}  
	
	public int selectNonAttendanceCount(StuAttendanceInfo stuAttendanceInfo) {
		return trainPlanMapper.selectNonAttendanceCount(stuAttendanceInfo);
	}
	
	public List<UserAuthority> formTeacherSelect(UserAuthority userAuthority) {
		return trainPlanMapper.formTeacherSelect(userAuthority);
	}
	
	
	public List<StuAttendanceInfo> selectAttendanceList(StuAttendanceInfo stuAttendanceInfo) {
		return trainPlanMapper.selectAttendanceList(stuAttendanceInfo);
	}
	
	public List<TrainPlan> getStudentInfo(TrainPlan trainPlan) {
		return trainPlanMapper.getStudentInfo(trainPlan);
	}
	
	/*
	 * 得到出科审核页面的个人的基本信息
	 * @param trainPlan
	 * @return TrainPlan
	 * @author guocc
	 * @date 2017年4月24日
	 */
	public TrainPlan getBasicInformation(TrainPlan trainPlan) {
		return trainPlanMapper.getBasicInformation(trainPlan);
	}
	

	/**
	 * 将时间上达到出科条件的学生 强制出科
	 * @author xudk 
	 * @date 2017年5月18日
	 */
	public int updateTrain_status58(TrainPlan trainPlan) {
	
		return trainPlanMapper.updateTrain_status58(trainPlan);
	}
	
	public int updateTrain_status52(TrainPlan trainPlan) {
		
		return trainPlanMapper.updateTrain_status52(trainPlan);
	}
	
	public List<TrainPlan> getAuth_id(TrainPlan trainPlan) {
		
		return trainPlanMapper.getAuth_id(trainPlan);
	}

	public TrainPlan getTrain_status(TrainPlan trainPlan2) {
		
		return trainPlanMapper.getTrain_status(trainPlan2);
	}

	public List<TrainPlan> orgaPageInfo(TrainPlan trainPlan) {
		return trainPlanMapper.orgaPageInfo(trainPlan);
	}

	/**
	 * 根据教学秘书id查询学生信息
	 * @param trainplan
	 * @return List<TrainPlan>
	 * @author guocc
	 * @date 2017年5月26日
	 */
	public List<TrainPlan> selectListBySecretaryAuthId(TrainPlan trainplan) {
		return trainPlanMapper.selectListBySecretaryAuthId(trainplan);
	}
	
	/**
	 * 获取个人未考勤天数
	 * @param stuAttendanceInfo
	 * @return int
	 * @author guocc
	 * @date 2017年4月21日
	 */
	public int selectNonAttendanceCountHalf(StuAttendanceInfo stuAttendanceInfo) {
		return trainPlanMapper.selectNonAttendanceCountHalf(stuAttendanceInfo);
	}

	public List<TrainPlan> selectPlanList(TrainPlan trainplan) {
		return trainPlanMapper.selectPlanList(trainplan);
	}

	public List<TrainPlan> getAdvanceStuInfo(TrainPlan trainPlan) {
		return trainPlanMapper.getAdvanceStuInfo(trainPlan);
	}

	public TrainPlan getOrga_name(TrainPlan trainPlan) {
		return trainPlanMapper.getOrga_name(trainPlan);
	}

	public List<TrainPlan> selectAllStuByTea(TrainPlan plan) {
		return trainPlanMapper.selectAllStuByTea(plan);
	}
	
	public List<TrainPlan> selectListByName(TrainPlan trainPlan) {
		return trainPlanMapper.selectListByName(trainPlan);
	}
	
	public List<TrainPlan> selectDiscussantInfo(TrainPlan trainPlan) {
		return trainPlanMapper.selectDiscussantInfo(trainPlan);
	}

	public List<TrainPlan> selectListByHomeworkAuthId(TrainPlan plan) {
		// TODO Auto-generated method stub
		return trainPlanMapper.selectListByHomeworkAuthId(plan);
	}

	public List<TrainPlan> getTrainStudent(RowBounds rowBounds,TrainPlan trainPlan) {
		return trainPlanMapper.getTrainStudent(rowBounds,trainPlan);
	}

	public int getTrainStudentCount(TrainPlan trainPlan) {
		return trainPlanMapper.getTrainStudentCount(trainPlan);
	}

	public List<TrainPlan> selectListTrain(TrainPlan plan) {
		return trainPlanMapper.selectListTrain(plan);
	}

	public int deleteList(TrainPlan plan) {
		return trainPlanMapper.deleteList(plan);
	}
}
