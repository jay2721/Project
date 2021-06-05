package libsys;

public class UserUpdateService {
	   private MemberDao memberDao;

	    public UserUpdateService(MemberDao memberDao) {
	        this.memberDao = memberDao;
	    }


	    public Member userup(UserUpdateRequest req){

	        Member newMember = new Member( req.getName(), req.getId(), req.getPwd(), req.getEmail(), req. getAddr());
	        memberDao.update(newMember);
	        return newMember;
	    }
}
