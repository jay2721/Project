package libsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class MemberDao {
	String[] memlist=new String[50];

	public void insert(Member member)throws IOException{
		File file = new File("member.txt"); //생성할 파일
		BufferedReader bReader=new BufferedReader(new FileReader(file)); 
		FileWriter writer = null;

		try {
			String s="";
			int i=0;
			int count=0;
			String mem=member.getName()+"/"+member.getPhone()+"/"+member.getBirth()+"/"+member.getAddr();

			while((s = bReader.readLine())!= null) {
				memlist[i]=s;
				i++;
			}
			bReader.close();

			writer = new FileWriter(file, true);

			for(int j=0;j<i;j++) {
				if((memlist[j].equals(mem))) {
					count++;
				}
			}
			if(count<1) {
				writer.write(mem+"\r\n"); 
				writer.flush(); 
				System.out.println("사용자 정보 저장 완료");
			}
			writer.close();

		} 
		catch(IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if(writer != null) writer.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(Member member, Member newmem)throws IOException{
		File file = new File("member.txt"); //생성할 파일
		BufferedReader bReader=new BufferedReader(new FileReader(file)); 

		try {
			String s="";

			int i=0;

			String mem=member.getName()+"/"+member.getPhone()+"/"+member.getBirth()+"/"+member.getAddr();
			String up=newmem.getName()+"/"+newmem.getPhone()+"/"+newmem.getBirth()+"/"+newmem.getAddr();

			while((s = bReader.readLine())!= null) {
				memlist[i]=s;
				i++;
			}
			bReader.close();

			FileWriter writer = new FileWriter(file);        
			for(int j=0;j<i;j++) {
				if(memlist[j].equals(mem)) {
					memlist[j]=up;
				}
				writer.write(memlist[j]+"\r\n");   
			}

			writer.flush(); 
			writer.close();

			System.out.println("사용자 정보 수정 완료");

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
