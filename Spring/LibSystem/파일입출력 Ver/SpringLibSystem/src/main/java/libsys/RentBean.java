package libsys;

import java.io.IOException;

public interface RentBean {
	void rent()throws IOException;
	void list()throws IOException;
	void setBookno(String no);
	void setUname(String name);
	void setUphone(String phone);

}
