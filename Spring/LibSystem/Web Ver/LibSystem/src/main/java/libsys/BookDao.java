package libsys;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

public class BookDao {
	private JdbcTemplate jdbcTemplate;
	public BookDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Book> selectAll() {
		List<Book> results = jdbcTemplate.query("select * from BOOKLIST",
				(ResultSet rs, int rowNum) -> {
					Book book = new Book(rs.getString("TITLE"), rs.getString("AUTHOR"),
							rs.getString("PUBLISHER"), rs.getString("RENT"));
					book.setIdx(rs.getLong("IDX"));
					return book;
				});
		return results;
	}
	
	public Book selectByTitle(String title) {
		List<Book> results = jdbcTemplate.query("select * from BOOKLIST where TITLE = ?",
				new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book( rs.getString("TITLE"),
						rs.getString("AUTHOR"),
						rs.getString("PUBLISHER"),
						rs.getString("RENT"));

				book.setIdx(rs.getLong("IDX"));
				return book;
			}
		}, title);
		return results.isEmpty() ? null : results.get(0);
	}
	
	
	public List<Book> searchByTitle(String content) {
		
		List<Book> results = jdbcTemplate.query("select * from BOOKLIST where TITLE like ?",
				new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book( rs.getString("TITLE"),
						rs.getString("AUTHOR"),
						rs.getString("PUBLISHER"),
						rs.getString("RENT"));

				book.setIdx(rs.getLong("IDX"));
				return book;
			}
		}, "%"+content+"%");
		return results;
		
	}
	
	public List<Book> searchByAuthor(String content) {
		
		List<Book> results = jdbcTemplate.query("select * from BOOKLIST where AUTHOR like ?",
				new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book( rs.getString("TITLE"),
						rs.getString("AUTHOR"),
						rs.getString("PUBLISHER"),
						rs.getString("RENT"));

				book.setIdx(rs.getLong("IDX"));
				return book;
			}
		}, "%"+content+"%");
		return results;

		
	}
	
	
	
	public List<Book> selectByRent(String rentid) {
		List<Book> results = jdbcTemplate.query("select * from BOOKLIST where RENT = ?",
				new RowMapper<Book>() {
			@Override
			public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
				Book book = new Book( rs.getString("TITLE"),
						rs.getString("AUTHOR"),
						rs.getString("PUBLISHER"),
						rs.getString("RENT"));

				book.setIdx(rs.getLong("IDX"));
				return book;
			}
		}, rentid);
		return results;
	}
	
	public void rentBook(Book book) {
		jdbcTemplate.update("update booklist set rent=? where title=?",
				book.getRent(),book.getTitle());
	}
	
}
