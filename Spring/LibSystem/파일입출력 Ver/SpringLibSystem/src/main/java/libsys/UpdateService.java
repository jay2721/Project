package libsys;

import java.io.IOException;

public class UpdateService {
private MemberDao memberDao;
	
	public UpdateService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
public void update(RegisterMem regi, UpdateMem up) throws IOException {
		
		String name=regi.getName();
		String phone=regi.getPhone();
		String birth=regi.getBirth();
		String addr=regi.getAddr();
		
		String n_name=up.getName();
		String n_phone=up.getPhone();
		String n_birth=up.getBirth();
		String n_addr=up.getAddr();

		Member nowstate = new Member(name, phone, birth,addr);
		Member newstate = new Member(n_name, n_phone, n_birth,n_addr);

		memberDao.update(nowstate,newstate);	
	}
}
