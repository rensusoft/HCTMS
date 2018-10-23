package configuration.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;


import com.rensu.education.hctms.basicdata.bean.TfcStutypeformRela;


import com.rensu.education.hctms.basicdata.bean.TfcDeptformRela;
import com.rensu.education.hctms.basicdata.bean.TfcStutypeformRela;



/**
 * TfcStutypeformRela Mapper
 * @author Administrator
 *
 */
public interface TfcStutypeformRelaMapper<T> {
	
	 

	public int add(T t) ;
	
	public int update(T t) ;
	
	public int delete(T t) ;
	
	public T selectOne(int id) ;
	
	public List<T> selectList(T t) ;

	public List<T> selectPage(RowBounds rowBounds, T t) ;

	public int selectCount(T t) ;

	public int getSequence();

	//得到出科审核页面的表单配置列表
	public List<TfcStutypeformRela> getFormList(TfcStutypeformRela tfcStutypeformRela);

	
	public List<TfcDeptformRela> getTfcList();
	
	public List<TfcStutypeformRela> getTfcList(
			TfcStutypeformRela tfcStutypeformRela);
	
	public int deleteAllByCon(TfcStutypeformRela tfcStutypeformRela);

}
