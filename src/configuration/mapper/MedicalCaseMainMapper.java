package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.MedicalCaseMain;


/**
 * MedicalCaseMain Mapper
 * @author Administrator
 *
 */
public interface MedicalCaseMainMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;
	
	public int selectCount(T t) ;
	
	public int getSequence();


	public int selectCountDiscuss(MedicalCaseMain medicalCaseMain);

	public List<MedicalCaseMain> selectPageDiscuss(RowBounds rowBounds,MedicalCaseMain medicalCaseMain);

	
	public List<MedicalCaseMain> selectMedicalCaseDiscuss(RowBounds rowBounds, MedicalCaseMain medicalCaseMain);
	
	public int selectMCDCount(MedicalCaseMain medicalCaseMain);
	
	public List<MedicalCaseMain> getDiscussants(MedicalCaseMain medicalCaseMain);
	
	public List<MedicalCaseMain> getDiscussRecords(MedicalCaseMain medicalCaseMain);

}
