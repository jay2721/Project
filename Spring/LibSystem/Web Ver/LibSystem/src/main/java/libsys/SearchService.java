package libsys;

import java.util.List;

public class SearchService {
	private BookDao bookDao;

	public SearchService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	
	public List<Book> search(SearchRequest searchreq){

		String searchfilter=searchreq.getSearchfilter();
		String searchcontent=searchreq.getSearchcontent();
		
		//System.out.println(searchfilter);
		//System.out.println(searchcontent);
		List<Book> book=null;
		if(searchfilter.equals("title")) {
			book=bookDao.searchByTitle(searchcontent);
			//System.out.println(searchfilter);
		}
		else if(searchfilter.equals("author")) {
			book=bookDao.searchByAuthor(searchcontent);
			//System.out.println(searchcontent);
		}
		
		return book;
	}
}
