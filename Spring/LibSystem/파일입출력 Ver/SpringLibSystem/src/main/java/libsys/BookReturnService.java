package libsys;

import java.io.IOException;

public class BookReturnService {
	private BookManage manage;
	
	public BookReturnService(BookManage manage){
		this.manage=manage;
	}
	
	public void rbook(BookReturn bookret)throws IOException {
		
		String bookno=bookret.getBookno();
		String name=bookret.getName();
		String phone=bookret.getPhone();

		BookManageList newlist = new BookManageList(bookno,name,phone);
		manage.update(newlist);
	}
	

}