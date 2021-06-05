package libsys;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class JavaConfig {
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8&serverTimezone=UTC");
		ds.setUsername("spring5");
		ds.setPassword("spring5");
		ds.setInitialSize(2);
		ds.setMaxActive(10);
		ds.setTestWhileIdle(true);
		ds.setMinEvictableIdleTimeMillis(60000 * 3);
		ds.setTimeBetweenEvictionRunsMillis(10 * 1000);
		return ds;
	}
	@Bean
	public BookDao bookDao() {
		return new BookDao(dataSource());
	}
	
	@Bean
    public MemberDao memberDao() {
          return new MemberDao(dataSource());
    }
    @Bean
    public RegisterService registerService() {
          return new RegisterService(memberDao());
    }
    
    @Bean
    public SearchService searchService() {
          return new SearchService(bookDao());
    }

    @Bean
    public UserUpdateService updateService() {
          return new UserUpdateService(memberDao());
    }
    
    @Bean
    public LoginService loginService() {
          return new LoginService(memberDao());
    }
    
    @Bean
    public RentService rentService() {
          return new RentService(bookDao());
    }
} 