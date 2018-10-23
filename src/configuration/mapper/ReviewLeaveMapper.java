package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.teach.bean.ApprovalOpinion;
import com.rensu.education.hctms.teach.bean.ReviewLeaveInfo;



/**
 * ReviewLeaveMapper
 * @author Administrator
 *
 */
public interface ReviewLeaveMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	
	/**
	 * 查询流程配置中某角色是否参与请假申请的审核
	 * @return Integer
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public Integer selectProcessInfoState(ReviewLeaveInfo reviewLeaveInfo);
	
	/**
	 * 查询待审核的请假申请
	 * @param reviewLeaveInfo
	 * @param rowBounds
	 * @return List<ReviewLeaveInfo>
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public List<ReviewLeaveInfo> selectReviewLeave(RowBounds rowBounds, ReviewLeaveInfo reviewLeaveInfo);
	
	/**
	 * 查询待审核的请假申请的条数
	 * @param reviewLeaveInfo
	 * @return int
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public int selectReviewLeaveCount(ReviewLeaveInfo reviewLeaveInfo);
	
	/***
     * 根据ID查询待审批的申请详情
     * @param req
     * @return ReviewLeaveInfo
     * @author guocc
     * @date 2017年3月16日
     */
	public ReviewLeaveInfo selectReviewLeaveById(ReviewLeaveInfo reviewLeaveInfo);
	
	/***
     * 提交同意的请假申请审批
     * @param approvalOpinion
     * @return int
     * @author guocc
     * @date 2017年3月16日
     */
	public int submitAgree(ApprovalOpinion approvalOpinion);
	
	/***
     * 提交不同意的请假申请审批
     * @param approvalOpinion
     * @return int
     * @author guocc
     * @date 2017年3月16日
     */
	public int submitDisagree(ApprovalOpinion approvalOpinion);
	
	/***
     * 审批同意时，更改请假申请流程状态
     * @param approvalOpinion
     * @return int
     * @author guocc
     * @date 2017年3月16日
     */
	public int updateStuVacateState(ApprovalOpinion approvalOpinion);
	
	/***
     * 审批不同意时，更改请假申请流程状态
     * @param approvalOpinion
     * @return int
     * @author guocc
     * @date 2017年3月16日
     */
	public int updateStuVacateStateDis(ApprovalOpinion approvalOpinion);
	
	/***
     * 更改请假申请流程和审批人关系表的状态
     * @param approvalOpinion
     * @return int
     * @author guocc
     * @date 2017年3月16日
     */
	public int updateProcessUserRelaState(ApprovalOpinion approvalOpinion);
	
	/**
	 * 查询流程配置中教学秘书是否参与请假申请的审核
	 * @param create_roleid
	 * @return
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public String selectProcessInfoStateOfDirector(int create_roleid);
	
	/**
	 * 查询教学秘书待审核的请假申请
	 * @param reviewLeaveInfo
	 * @param rowBounds
	 * @return List<ReviewLeaveInfo>
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public List<ReviewLeaveInfo> selectReviewLeaveOfDirector(RowBounds rowBounds, ReviewLeaveInfo reviewLeaveInfo);
	
	/**
	 * 查询教学秘书待审核的请假申请的条数
	 * @param reviewLeaveInfo
	 * @return int
	 * @author guocc
	 * @date 2017年3月15日
	 */
	public int selectReviewLeaveCountOfDirector(ReviewLeaveInfo reviewLeaveInfo);
	
	/***
     * 根据ID查询教学秘书待审批的申请详情
     * @param reviewLeaveInfo
     * @return
     * @author guocc
     * @date 2017年3月16日
     */
	public ReviewLeaveInfo selectReviewLeaveByIdOfDirector(ReviewLeaveInfo reviewLeaveInfo);
	
	/***
     * 判断教学秘书审批流程前面是否配置有其他的审批流程
     * @param reviewLeaveInfo
     * @return Integer
     * @author guocc
     * @date 2017年3月16日
     */
	public Integer getFlagOfIsFrist(ReviewLeaveInfo reviewLeaveInfo);
	
	/***
	 * 查询请假流程详细信息
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月22日
	 */
	public List<ReviewLeaveInfo> selectLeaveProcessInfo(ReviewLeaveInfo reviewLeaveInfo);
	
	/***
	 * 查询请假申请的当前状态
	 * @param id
	 * @return int
	 * @author guocc
	 * @date 2017年3月22日
	 */
	public int selectVacateStatus(int id);
	
	public ProcessInfo getVacateProcessOfTeacher(int proc_num);
	
	public List<Integer> selectRoleId(int proc_num);
	
	public List<Integer> selectAuthId(Integer roleId);
	
}
