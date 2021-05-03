package libsys;

import java.util.*;
import java.io.*;

public class Member {
	String name;
	String phone;
	String birth;
	String addr;


	public Member(String name, String phone, String birth, String addr) {
		this.name = name; this.phone = phone;
		this.birth = birth; this.addr = addr;
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

}
