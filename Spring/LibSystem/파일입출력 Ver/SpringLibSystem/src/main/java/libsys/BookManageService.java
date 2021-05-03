package libsys;

import java.io.IOException;

public class BookManageService {
	private BookManage manage;

	public BookManageService(BookManage manage){
		this.manage=manage;
	}

	public void rshow(BookReturn bookret)throws IOException {

		String name=bookret.getName();
		String phone=bookret.getPhone();

		BookManageUpdateList slist = new BookManageUpdateList(name,phone);
		manage.showlist(slist);
	}

	public void rupdate(RegisterMem regi, UpdateMem up) throws IOException{
		String name=regi.getName();
		String phone=regi.getPhone();
		String n_name=up.getName();
		String n_phone=up.getPhone();

		BookManageUpdateList slist = new BookManageUpdateList(name,phone);
		BookManageUpdateList newlist = new BookManageUpdateList(n_name,n_phone);
		manage.userup(slist,newlist);
	}
}
