package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.MedicalCaseMember;


/**
 * MedicalCaseMember Mapper
 * @author Administrator
 *
 */
public interface MedicalCaseMemberMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<MedicalCaseMember> selectNum(int mcm_id);
}
