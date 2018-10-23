package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.StuActiveData;


/**
 * StuActiveData Mapper
 * @author Administrator
 *
 */
public interface StuActiveDataMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<StuActiveData> selectPageEntering(RowBounds rowBounds,
			StuActiveData t);

	public List<StuActiveData> selectShenHeCountByOA(StuActiveData stuActiveData);

	public List<StuActiveData> getStuInput(StuActiveData stuActiveData);

	public StuActiveData getExamineTextById(StuActiveData stuActiveData);
	
	public int selectStuActiveDataCount(StuActiveData stuActiveData);
	
	public List<StuActiveData> selectStuActiveData(RowBounds rowBounds, StuActiveData stuActiveData);
}
