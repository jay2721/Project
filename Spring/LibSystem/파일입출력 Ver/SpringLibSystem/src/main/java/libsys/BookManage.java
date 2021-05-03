package libsys;

import java.io.*;


public class BookManage {
	String[] list=new String[50];
	String[][] book=new String[50][];
	String[][] show=new String[50][]; 
	String[][] num=new String[50][];

	public void showlist(BookManageUpdateList slist) throws IOException{
		File file = new File("bookmanage.txt"); 
		BufferedReader bReader=new BufferedReader(new FileReader(file)); 

		try {
			String s="";
			int i=0;
			String now=slist.getName()+"/"+slist.getPhone();
	
			while((s = bReader.readLine())!= null) {
				if(s.contains(now)){
					show[i]=s.split("/");
					i++;
				}
			}
			bReader.close();

			if(i==0)
				System.out.println("\t\t-반납할 도서가 없습니다.-");
			else if(i>0) {
				BufferedReader bReader2=new BufferedReader(new FileReader(new File("booklist.txt"))); 

				String line="";
				int k=0;

				System.out.println("\t*번호\t*제목\t*저자\t*출판사");
				while((line = bReader2.readLine()) != null) {
					this.book[k]=line.split("/");
					k++;
				}

				for(int j=0;j<k;j++){
					for(int p=0;p<i;p++) {
						if(book[j][0].equals(show[p][0])) {
							System.out.println("\t*"+book[j][0]+"\t*"+book[j][1]+"\t*"+book[j][2]+"\t*"+book[j][3]);
						}
					}
				}
			}
		} catch(IOException e) {
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


	public void update(BookManageList mlist) throws IOException{
		File file = new File("bookmanage.txt"); 
		BufferedReader bReader=new BufferedReader(new FileReader(file)); 
		int count=0;

		try { 
			String s="";
			int i=0;
			int y=0;
			String now=mlist.getBookno()+"/"+mlist.getName()+"/"+mlist.getPhone();

			while((s = bReader.readLine())!= null) {
				if(!(s.equals(now))){
					list[i]=s;
					i++;
				}
				else {
					num[y]=s.split("/");
					for(int j=0;j<num[y].length;j++){
						if(num[y][0].equals(mlist.getBookno())) {
							count++;
						}
					}
					y++;
				}
			}

			bReader.close();

			if(count>0) {
				FileWriter writer = new FileWriter(file);
				for(int j=0;j<i;j++) {
					writer.write(list[j]+"\r\n");   
					writer.flush();
				}
				writer.close();

				File file2 = new File("booklist.txt"); 
				BufferedReader bReader2=new BufferedReader(new FileReader(file2)); 

				String b="";
				int idx=0;
				while((b = bReader2.readLine()) != null) {
					book[idx]=b.split("/");
					idx++;
				}

				bReader2.close();

				for(int j=0;j<idx;j++){
					if(book[j][0].equals(mlist.getBookno())) {
						book[j][4]="가능";
					}
				}

				FileWriter writer2 = new FileWriter(file2);
				for(int z=0;z<idx;z++) {
					String line="";
					for(int j=0;j<4;j++) {
						line=String.join("/", book[z]); 
					} 
					writer2.write(line+"\r\n");
					writer2.flush(); 
				}
				writer2.close();
				
				System.out.println("반납에 성공하셨습니다.");
				System.out.println("정보 수정");
			}
			else if(count<=0)
				System.out.println("반납가능한 도서가 아닙니다.");

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

	public void userup(BookManageUpdateList slist,BookManageUpdateList newlist)throws IOException{
		File file = new File("bookmanage.txt"); 
		BufferedReader bReader=new BufferedReader(new FileReader(file)); 

		try {
			String s="";

			int i=0;

			String mname=slist.getName();
			String mphone=slist.getPhone();
			String up="/"+newlist.getName()+"/"+newlist.getPhone();
			while((s = bReader.readLine())!= null) {
				show[i]=s.split("/");
				list[i]=s;
				i++;
			}
			
			bReader.close();

			FileWriter writer = new FileWriter(file);  
			for(int j=0;j<i;j++) {
				if(show[j][1].equals(mname)) {
					if(show[j][2].equals(mphone)) {
						list[j]=show[j][0]+up;        		
					}
				}
				writer.write(list[j]+"\r\n");  
			}

			writer.flush(); 
			writer.close();
			
			System.out.println("정보 수정");

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
