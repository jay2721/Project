package libsys;

public class LoginService {

	private MemberDao memberDao;

	public LoginService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	//입력받은 아이디 값이 DB에 있는지 확인
	public boolean chkId(LoginRequest log){
		boolean result=true;
		Member member=memberDao.selectByID(log.getId());

		if(member==null){
			result=false;
		}

		return result;
	}

	//로그인
	public Member login(LoginRequest log){

		String id=log.getId();
		//System.out.println(id);
		Member member=memberDao.selectByID(id);

		return member;
	}

}
