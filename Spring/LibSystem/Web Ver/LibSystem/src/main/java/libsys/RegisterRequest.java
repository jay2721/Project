package libsys;

public class RegisterRequest {

	private String name;
    private String id;
    private String pwd;
    private String chkpwd;
    private String email;
    private String addr;


    public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getChkpwd() {
		return chkpwd;
	}


	public void setChkpwd(String chkpwd) {
		this.chkpwd = chkpwd;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


}
