package libsys;


public class Book {
	private Long idx;
	private String title;
	private String author;
	private String publisher;
	private String rent;

	public Book(String title, String author, String publisher, String rent) 
	{
		this.title=title;
		this.author=author;
		this.publisher=publisher;
		this.rent = rent;
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getRent() {
		return rent;
	}
	public void setRent(String rent) {
		this.rent = rent;
	}


}
