package libsys;

import java.io.*;

public class BookSearch implements SearchBean {

	String booktitle;
	String[][] book=new String[50][];
	String[] list=new String[50];

	public void setBooktitle(String title) {
		booktitle=title;
	}

	public void search()throws IOException {
		BufferedReader bReader=new BufferedReader(new FileReader(new File("booklist.txt"))); 

		try {
			String s="";

			int i=0;
			int count=0;
			System.out.println("\t*번호\t*제목\t*저자\t*출판사\t*대여가능여부");
			while((s = bReader.readLine()) != null) {
				this.book[i]=s.split("/");
				i++;
			}
			bReader.close();
			for(int j=0;j<i;j++) {
				for(int z=0;z<book[j].length;z++) {
					if(z==1) {
						if(book[j][z].contains(booktitle)) {
							list[count]="\t*"+book[j][z-1]+"\t*"+book[j][z]+"\t*"+book[j][z+1]+"\t*"+book[j][z+2]+"\t*"+book[j][z+3];
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
				System.out.println("\t\t-검색 결과가 없습니다.-");
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
