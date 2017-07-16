package test.sample.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import test.common.dao.AbstractDAO;

@Repository("sampleDAO")
public class SampleDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Map<String, Object> commandMap) {
		// TODO Auto-generated method stub
		return (List<Map<String, Object>>)selectList("sample.selectBoardList", commandMap);
	}

}
