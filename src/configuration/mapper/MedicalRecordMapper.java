package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.MedicalRecord;


/**
 * MedicalRecord Mapper
 * @author Administrator
 *
 */
public interface MedicalRecordMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<MedicalRecord> selectMedicalDocumentTea(MedicalRecord medicalRecord);

	public int selectMedicalDocumentCountTea(MedicalRecord medicalRecord);

	public List<MedicalRecord> selectMedicalDocument(RowBounds rowBounds,
			MedicalRecord medicalRecord);

	public int selectMedicalDocumentCount(MedicalRecord medicalRecord);
	
	public MedicalRecord selectMedicalRecordById(int id);
}
