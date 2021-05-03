package libsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;

public class BookRent implements RentBean {

	String bookno;
	String uname;
	String uphone;
	String[][] book=new String[50][];
	String[] list =new String[50];

	public void setBookno(String no) {
		bookno=no;

	}
	public void setUname(String name) {
		uname=name;

	}
	public void setUphone(String phone) {
		uphone=phone;

	}

	public void list() throws IOException {

		BufferedReader bReader=new BufferedReader(new FileReader(new File("booklist.txt"))); 

		try {
			String s="";

			int i=0;
			int count=0;
			int x=1;
			System.out.println("\t*번호\t*제목\t*저자\t*출판사\t*대여가능여부");
			while((s = bReader.readLine()) != null) {
				this.book[i]=s.split("/");
				i++;
			}
			bReader.close();
			for(int j=0;j<i;j++) {
				for(int z=0;z<book[j].length;z++) {
					if(z==4) {
						if(book[j][z].equals("가능")) {
							list[count]="\t*"+book[j][z-4]+"\t*"+book[j][z-3]+"\t*"+book[j][z-2]+"\t*"+book[j][z-1]+"\t*"+book[j][z];
							count++;
						}
					}
				}
			}
			if(count>0) {
				for(int j=0;j<count;j++) {
					System.out.println(list[j]);	
				}
			}
			else if(count==0)
				System.out.println("\t\t-대여 가능한 도서가 없습니다.-");
		} 
		catch(IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(bReader != null) bReader.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void rent() throws IOException {
		BufferedReader bReader=new BufferedReader(new FileReader(new File("booklist.txt"))); 
		File file = new File("booklist.txt");
		File file2 = new File("bookmanage.txt");
		String record=bookno+"/"+uname+"/"+uphone;
		try {
			String s="";

			int i=0;

			int count=0;

			while((s = bReader.readLine()) != null) {
				this.book[i]=s.split("/");
				i++;
			}

			bReader.close();
			
			for(int j=0;j<i;j++) {
				for(int z=0;z<book[j].length;z++) {
					if(z==4) {
						if(book[j][z].equals("가능")) {
							count++;
						}
					}
				}
			}
			
			if(count>0) {
				FileWriter writer = new FileWriter(file);
				for(int j=0;j<i;j++){
					if(book[j][0].equals(bookno)) {
						book[j][4]="불가능";
					}
				}

				for(int z=0;z<i;z++) {
					String line="";
					for(int j=0;j<4;j++) {
						line=String.join("/", book[z]); 
					} 
					writer.write(line+"\r\n");
					writer.flush(); 
				}

				writer.close();
				FileWriter writer2 = new FileWriter(file2,true);
				writer2.write(record+"\r\n");
				writer2.flush(); 
				writer2.close();
				System.out.println("대여에 성공하셨습니다.");

			}
			else if(count<=0)
				System.out.println("대여 가능한 도서가 아닙니다.");

		} 
		catch(IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(bReader != null) bReader.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}








}
