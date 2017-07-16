package test.sample.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import test.common.dao.AbstractDAO;

@Repository("memberDAO")
public class MemberDAO extends AbstractDAO {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
    public int insertMember(Map<String, Object> commandMap){
		Object obj = insert("member.insertMember", commandMap);
        if(log.isDebugEnabled()){
            log.debug("\t insertMember return  \t:  " + obj);
        }
		
		return 0;
    }
	
	@SuppressWarnings("unchecked")
    public int updateMember(Map<String, Object> commandMap){
		Object obj = update("member.updateMember", commandMap);
        if(log.isDebugEnabled()){
            log.debug("\t insertMember return  \t:  " + obj);
        }
		
		return 0;
    }
	
	@SuppressWarnings("unchecked")
    public int deleteMember(String id){
		Object obj = delete("member.deleteMember", id);
        if(log.isDebugEnabled()){
            log.debug("\t insertMember return  \t:  " + obj);
        }
		
		return 0;
    }
	
	@SuppressWarnings("unchecked")
    public Map<String, Object> selectOne(String id){
		return (Map<String, Object>) selectOne("member.selectOne", (Object)id);
    	
    }
	
	@SuppressWarnings("unchecked")
    public List<Map<String, Object>> selectAll(){
		return (List<Map<String, Object>>)selectList("member.selectAll");
    }
}
