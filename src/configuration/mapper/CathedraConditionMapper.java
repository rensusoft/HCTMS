package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.CathedraCondition;


/**
 * CathedraCondition Mapper
 * @author Administrator
 *
 */
public interface CathedraConditionMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	/**
	 * 查询讲座编排
	 * @param req
	 * @return
	 * @author guocc
	 * @date 2017年3月6日
	 */
	public List<CathedraCondition> selectCathedraCondition(CathedraCondition cathedraCondition);
}
