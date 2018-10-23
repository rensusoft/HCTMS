package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;


/**
 * ScoEval Mapper
 * @author Administrator
 *
 */
public interface ScoEvalMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
}
