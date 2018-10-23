package configuration.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.rensu.education.hctms.teach.bean.CathedraCondition;
import com.rensu.education.hctms.teach.bean.CathedraDetail;
import com.rensu.education.hctms.teach.bean.CathedraPlan;
import com.rensu.education.hctms.userauth.bean.StuClass;
import com.rensu.education.hctms.userauth.bean.StuType;


/**
 * CathedraPlan Mapper
 * @author Administrator
 *
 */
public interface CathedraPlanMapper<T> {
	
	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();
	//查询讲座安排
	public List<CathedraPlan> selectCathedraPlan(CathedraPlan cathedraPlan);
	//加载届次
	public List<StuClass> stuClassCheckbox();
	//加载学生类型
	public List<StuType> stuTypeCheckbox();
	//加载条数
	public int countCathedraPlan(String dateTime);
	//加载条数对应的date
	public int countCathedraPlanDate(String dateTime);
	//查询一个讲座安排
	public String selectcathedraOnePlan(Integer id);
	//查询左侧日期导航栏的日期
	public List<CathedraPlan> getCathDate();
	////查询讲座通知
	public List<CathedraPlan> selectCathedraNotice(CathedraPlan cathedraPlan);
	//根据权限id获取类型和届次
	public CathedraDetail getClassAndTypeByAuthId(Integer auth_id);
	//查询讲座通知的条件
	public CathedraCondition selectCathedraCondition(CathedraCondition cathedraCondition);
	//统计每月的条数
	public List<CathedraPlan> countCathedraNotice(CathedraPlan cathedraPlan);
	
}
