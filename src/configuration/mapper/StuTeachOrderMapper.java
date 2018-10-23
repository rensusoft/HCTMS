package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.StuTeachOrder;



/**
 * StuTeachOrder Mapper
 * @author Administrator
 *
 */
public interface StuTeachOrderMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public T selectReq_content(int id);

	public List<T> outlineExhibition(int tto_id);

	public int insertList(List<T> t) ;
	
	public int updateFinnish_num(StuTeachOrder stuTeachOrder);
}
