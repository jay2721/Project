package libsys;

import java.sql.*;
import java.util.Collection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
public class MemberDao {
	private JdbcTemplate jdbcTemplate;
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	//입력받은 ID값으로 DB에서 데이터 가져오기
	public Member selectByID(String id) {
		List<Member> results = jdbcTemplate.query("select * from BOOKUSER where USERID = ?",
				new RowMapper<Member>() {
			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member member = new Member( rs.getString("NAME"),
						rs.getString("USERID"),
						rs.getString("PWD"),
						rs.getString("EMAIL"),
						rs.getString("ADDR"));

				member.setIdx(rs.getLong("IDX"));
				return member;
			}
		}, id);
		return results.isEmpty() ? null : results.get(0);
	}

	//회원가입시 DB에 새로운 정보 저장
	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con) 
							throws SQLException
					{
						PreparedStatement pstmt = con.prepareStatement(
								"insert into BOOKUSER (NAME,USERID,PWD,EMAIL,ADDR) values (?, ?, ?, ?, ?)",
								new String[] {"IDX"} );
						pstmt.setString(1, member.getName());
						pstmt.setString(2, member.getId());
						pstmt.setString(3, member.getPwd());
						pstmt.setString(4, member.getEmail());
						pstmt.setString(5, member.getAddr());

						return pstmt;
					}
				}, 
				keyHolder );
		Number keyValue = keyHolder.getKey();
		member.setIdx(keyValue.longValue());
	}


	//회원정보 수정
	public void update(Member member) {
		jdbcTemplate.update("update BOOKUSER set NAME = ?, PWD = ?, EMAIL=?, ADDR=?  "
				+ " where USERID = ? ",
				member.getName(), member.getPwd(), member.getEmail(), member.getAddr(), member.getId() );
	}


}