package libsys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class JavaConfig {

	@Bean
	@Scope("prototype")
	public MemberDao memberDao() {
		return new MemberDao();
	}
	@Bean
	@Scope("prototype")
	public RegisterService RegSvc() {
		return new RegisterService(memberDao());
	}

	@Bean
	@Scope("prototype")
	public UpdateService UpSvc() {
		return new UpdateService(memberDao());
	}

	@Bean
	@Scope("prototype")
	public SearchBean searchBean() {
		return new BookSearch();
	}

	@Bean
	@Scope("prototype")
	public RentBean rentBean() {

		return new BookRent();
	}

	@Bean
	@Scope("prototype")
	public BookManage bookManage() {
		return new BookManage();
	}
	@Bean
	@Scope("prototype")
	public BookReturnService BrtSvc() {
		return new BookReturnService(bookManage());
	}

	@Bean
	@Scope("prototype")
	public BookManageService ShowSvc() {
		return new BookManageService(bookManage());
	}
}
