package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.config.bean.TrainPlanBefore;
import com.rensu.education.hctms.teach.bean.StuAttendanceInfo;
import com.rensu.education.hctms.teach.bean.TrainPlan;
import com.rensu.education.hctms.userauth.bean.UserAuthority;


/**
 * TrainPlan Mapper
 * @author Administrator
 *
 */
public interface TrainPlanMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	
	public List<T> selectBystu_auth_id(int stu_auth_id);


	public TrainPlan selectOneLun(TrainPlan trainPlan);

	public List<TrainPlan> selectOrgaNameList(int auth_id);

	public TrainPlan selectOneById(int id);

	public List<TrainPlan> selectToRotateList(TrainPlan trainPlan);

	public TrainPlan selectOneTrain(TrainPlan trainPlan);

	public int updateTrainStatus(TrainPlan trainPlan);

	public List<TrainPlan> selectAllStuByTJ(TrainPlan train);

	public List<TrainPlan> getPendingAudit(RowBounds rowBounds, TrainPlan trainPlan);

	public int getPendingAuditCount(TrainPlan trainPlan);

	public List<TrainPlan> selectLunByRole(TrainPlan trainPlan);
	
	public int insertList(List<T> t) ;
	public List<TrainPlan> getSecreDetails(TrainPlan trainPlan);
	
	public List<TrainPlan> selectByAuthOrga(T t);

	public List<TrainPlan> getGradeInit(TrainPlan trainPlan);

	public int absenceNum(StuAttendanceInfo stuAttendanceInfo);

	public Integer getRoundRobin(TrainPlan trainPlan);

	public List<TrainPlan> getStuManageInfo(TrainPlan trainPlan);

	public int selectNonAttendanceCount(StuAttendanceInfo stuAttendanceInfo);

	public List<UserAuthority> formTeacherSelect(UserAuthority userAuthority);
	
	public List<StuAttendanceInfo> selectAttendanceList(StuAttendanceInfo stuAttendanceInfo);
	
	public List<TrainPlan> getStudentInfo(TrainPlan trainPlan);
	
	//得到出科审核页面的个人的基本信息
	public TrainPlan getBasicInformation(TrainPlan trainPlan);
	
	
	//将时间上达到出科条件的学生 当前科室Train_status更新为58
	public int updateTrain_status58(TrainPlan trainPlan);
	//获取时间上达到出科条件的学生的Train_status
	public TrainPlan getTrain_status(TrainPlan trainPlan2);
	//将时间上达到出科条件的学生 下一科室Train_status更新为52
	public int updateTrain_status52(TrainPlan trainPlan);
	//查询时间上达到出科条件的学生 的唯一stu_auth_id
	public List<TrainPlan> getAuth_id(TrainPlan trainPlan);

	public List<TrainPlan> orgaPageInfo(TrainPlan trainPlan);

	public List<TrainPlan> selectListBySecretaryAuthId(TrainPlan trainplan);

	public int selectNonAttendanceCountHalf(StuAttendanceInfo stuAttendanceInfo);
	
	public List<TrainPlan> selectPlanList(TrainPlan trainplan);

	public List<TrainPlan> getAdvanceStuInfo(TrainPlan trainPlan);

	public TrainPlan getOrga_name(TrainPlan trainPlan);
	
	public List<TrainPlan> selectListByName(TrainPlan trainPlan);
	
	public List<TrainPlan> selectDiscussantInfo(TrainPlan trainPlan);

	public List<TrainPlan> selectAllStuByTea(TrainPlan plan);

	public List<TrainPlan> selectListByHomeworkAuthId(TrainPlan plan);

	public List<TrainPlan> getTrainStudent(RowBounds rowBounds,TrainPlan trainPlan);

	public int getTrainStudentCount(TrainPlan trainPlan);

	public List<TrainPlan> selectListTrain(TrainPlan plan);

	public int deleteList(TrainPlan plan);
			

}