package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.log.bean.OutdeptRecord;


/**
 * OutdeptRecord Mapper
 * @author Administrator
 *
 */
public interface OutdeptRecordMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	public List<OutdeptRecord> selectFlowList(OutdeptRecord outdeptRecord);

    public OutdeptRecord selectExamResult(OutdeptRecord outdeptRecord);

	public List<OutdeptRecord> getGroups(OutdeptRecord outdeptRecord);

	public OutdeptRecord getLastPubNum(OutdeptRecord outdeptRecord);

	public OutdeptRecord getPubNum(OutdeptRecord outdeptRecord);
	
	public List<OutdeptRecord> selectOrList(OutdeptRecord outdeptRecord);
	
}
