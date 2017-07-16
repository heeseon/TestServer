package test.sample.service;

import java.util.List;
import java.util.Map;

public interface MemberService {
	public String getData();
	public List< Map<String, Object> > selectAll() throws Exception;
	public void joinMember( Map<String, Object> params) throws Exception;
	public boolean login(String id, String pwd) throws Exception;
	public Map<String, Object> getMemberInfo(String id) throws Exception;
	public void memberUpdate(Map<String, Object> params) throws Exception;
	public void deleteMember(String id) throws Exception;
	
}
