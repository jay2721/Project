package libsys;

public class RentService {
	
	private BookDao bookDao;
	public RentService(BookDao bookDao) {
		this.bookDao=bookDao;
		
	}

	
	public Long rent(RentRequest rentreq){
		
		String title=rentreq.getTitle();
		String id=rentreq.getId();
		
		Book oldBook=bookDao.selectByTitle(title);
//		System.out.println(id);
//		System.out.println(title);
		Book newBook= new Book(oldBook.getTitle(),oldBook.getAuthor(),oldBook.getPublisher(),id);
		bookDao.rentBook(newBook);

		return newBook.getIdx();

	}
	
	public Long rtnbook(RentRequest rentreq){
		
		String title=rentreq.getTitle();
		String id=null;
		
		Book oldBook=bookDao.selectByTitle(title);
//		System.out.println(id);
//		System.out.println(title);
		Book newBook= new Book(oldBook.getTitle(),oldBook.getAuthor(),oldBook.getPublisher(),id);
		bookDao.rentBook(newBook);

		return newBook.getIdx();

	}

}
