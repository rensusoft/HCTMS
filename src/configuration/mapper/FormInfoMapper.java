package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.score.bean.ScoFormMain;


/**
 * FormInfo Mapper
 * @author Administrator
 *
 */
public interface FormInfoMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<T> selectFormInfo(T t);

	public List<T> formRelation(T t);

	public List<FormInfo> getForm(RowBounds rowBounds, FormInfo formInfo);
	
	public FormInfo getTableContentFromSFM(ScoFormMain scoFormMain);

	public FormInfo selectOneFromSFM(ScoFormMain scoFormMain);
	
	public FormInfo getTableContent(FormInfo formInfo);
}
