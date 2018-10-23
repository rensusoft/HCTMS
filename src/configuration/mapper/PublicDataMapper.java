package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.publicdata.bean.PublicData;


/**
 * PublicData Mapper
 * @author Administrator
 *
 */
public interface PublicDataMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<PublicData> selectPage( T t);
	
	public T selectOneById(int id) ;

	public List<PublicData> selectPageOfOthers(RowBounds rowBounds,
			PublicData publicData);

	public int selectCountOfOthers(PublicData publicData);

	public List<PublicData> selectPageOfSchool(RowBounds rowBounds,
			PublicData publicData);
}
