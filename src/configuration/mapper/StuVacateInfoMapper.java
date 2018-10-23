package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.basicdata.bean.ProcessInfo;
import com.rensu.education.hctms.teach.bean.StuVacateInfo;


/**
 * StuVacateInfo Mapper
 * @author Administrator
 *
 */
public interface StuVacateInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<StuVacateInfo> selectVacateInfo(RowBounds rowBounds, T t);
	
	public T selectVacateById(int id) ;
	
	public List<StuVacateInfo> selectVacateInfo(T t);
	
	public ProcessInfo getVacateProcessOfTeacher(int proc_num);
	
	public List<Integer> selectRoleId(int proc_num);
	
	public List<Integer> selectAuthId(Integer roleId);

	public List<StuVacateInfo> selectVacateList(RowBounds rowBounds,
			StuVacateInfo stuVacateInfo);

	public int selectVacateListCount(StuVacateInfo stuVacateInfo);
	
	public List<StuVacateInfo> selectVacateListByStatus(StuVacateInfo stuVacateInfo);
}
