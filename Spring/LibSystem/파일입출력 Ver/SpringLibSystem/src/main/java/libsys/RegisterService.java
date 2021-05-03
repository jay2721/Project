package libsys;

import java.io.IOException;

public class RegisterService {
	private MemberDao memberDao;
	
	public RegisterService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	
	public void regist(RegisterMem regi) throws IOException {
	
		String name=regi.getName();
		String phone=regi.getPhone();
		String birth=regi.getBirth();
		String addr=regi.getAddr();

		Member newMember = new Member(name, phone, birth,addr);
		memberDao.insert(newMember);
	}
	
	
}
