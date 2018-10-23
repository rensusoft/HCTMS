package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.userauth.bean.StudentInfo;


/**
 * StudentInfo Mapper
 * @author Administrator
 *
 */
public interface StudentInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public StudentInfo selectOneByUserCode(String user_code);

	public int insertWithList(List<StudentInfo> list);
	
	public List<T> selectStuList(T t) ;
	
	public int updateStateList(T t) ;

	public List<T> countStatusList(T t);
	
	public int updateStuInfomation(StudentInfo studentInfo);

	public List<T> selectPageInfo(T t);

	public List<T> absenceStuInfo(T t);

	public StudentInfo selectStuNumByAuthId(Integer stu_auth_id);

	public List<StudentInfo> selectListStu(StudentInfo studentInfo);

	public List<StudentInfo> selectStudent();

	public StudentInfo selectStuAuthId(StudentInfo studentInfo);

	public StudentInfo selectStuAuthIdByStuName(String stuName);

	public StudentInfo selectStuNameByAuthId(Integer auth_id);
	
	public int updateStuStateList(StudentInfo studentInfo);

	public List<StudentInfo> selectPageWithStuType(RowBounds rowBounds, StudentInfo studentInfo);
	
	public List<StudentInfo> selectStudentList(StudentInfo studentInfo);

	public StudentInfo selectUserAndAuth(Integer id);

	public StudentInfo selectStuTscId(Integer stu_auth_id);

	public Object updateStuentState(StudentInfo studentInfo);

	public Object updateStuInfo(StudentInfo studentInfo);

	public Object updateUserInfo(StudentInfo studentInfo);

	public Object updateUserAuth(StudentInfo studentInfo);

	public StudentInfo selectStuStateYTscId(int stuAuthId);
}
