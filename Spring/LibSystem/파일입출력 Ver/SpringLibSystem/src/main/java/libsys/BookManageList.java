package libsys;

public class BookManageList {
	String name;
	String phone;
	String bookno;

	public BookManageList(String bookno,String name, String phone) {
		this.name = name; this.phone = phone;
		this.bookno = bookno;
	}
	public String getBookno() {
		return bookno;
	}

	public void setBookno(String bookno) {
		this.bookno = bookno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}




}
