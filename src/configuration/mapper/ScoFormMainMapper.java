package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.basicdata.bean.FormInfo;
import com.rensu.education.hctms.basicdata.bean.MarksheetDetail;
import com.rensu.education.hctms.basicdata.bean.MarksheetSub;
import com.rensu.education.hctms.log.bean.OutdeptRecord;
import com.rensu.education.hctms.score.bean.ScoFormMain;


/**
 * ScoFormMain Mapper
 * @author Administrator
 *
 */
public interface ScoFormMainMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	
	public int delStuOldData(T t);
	
	public ScoFormMain querySFMId(ScoFormMain scoFormMain);
	
	public List<MarksheetSub> findMarksheetSubM(FormInfo formInfo);
	
	public List<MarksheetDetail> findMarksheetDetail(Integer id);
	
	public List<MarksheetSub> findMarksheetSubS(Integer id);
	
	public ScoFormMain getSFMId(ScoFormMain scoFormMain);

	public List<ScoFormMain> getOrIdByPubNum(OutdeptRecord outdeptRecord);

	public ScoFormMain selectByOrId(ScoFormMain scoFormMain);
	
	public int updateState(ScoFormMain scoFormMain);
}
