package libsys;


public class RegisterService {
    private MemberDao memberDao;

    public RegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    
    //ID 중복체크를 위해 입력받은 ID값 DB에 
    public boolean chkId(RegisterRequest req){
        boolean result=false;
        Member member=memberDao.selectByID(req.getId());

        if(member==null){
            result=true;
        }

        return result;
    }

    //회원가입
    public Long regist(RegisterRequest req){

        Member newMember = new Member( req.getName(), req.getId(), req.getPwd(), req.getEmail(), req. getAddr());
        memberDao.insert(newMember);
        return newMember.getIdx();
    }
}
