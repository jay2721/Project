package libsys;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


import java.io.IOException;
import java.util.*;

public class LibSystemApp {
	public static String update_name;
	public static String update_phone;
	public static String update_birth;
	public static String update_addr;
	public static String name;
	public static String phone;
	public static String birth;
	public static String addr;


	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);

		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(libsys.JavaConfig.class); // 자바설정 정보를 읽어 컨테이너를 초기화하고, singleton객체 생성
		
		RegisterService regSvc = (RegisterService) ctx.getBean("RegSvc");
		UpdateService UpSvc = (UpdateService) ctx.getBean("UpSvc");

		RegisterMem regMem=new RegisterMem();
		UpdateMem upMem=new UpdateMem();

		System.out.println("== 사용자 정보 입력 ==");
		System.out.print("이름: ");
		name=sc.nextLine();
		System.out.print("전화번호(숫자만 입력): ");
		phone=sc.nextLine();
		System.out.print("생년월일 입력: ");
		birth=sc.nextLine();
		System.out.print("주소: ");
		addr=sc.nextLine();

		regMem.setName(name); //사용자 이름 지정
		regMem.setPhone(phone); //사용자 핸드폰번호 지정
		regMem.setAddr(addr); //사용자 주소값 지정
		regMem.setBirth(birth); //시용자 생일값 지정

		regSvc.regist(regMem); 

		int act; //메뉴 선택값 저장 변수

		//0(종료) 누르기 전까지 반복
		do {
			System.out.println("== 메뉴를 선택 ==");
			System.out.print("1. 도서 검색 2. 대여 3. 반납 4. 회원정보 수정 0. 종료: ");

			act=sc.nextInt();
			sc.nextLine();

			String b_no;	//책번호 입력(대여/반납시 사용)
			String word;	//책검색 단어 입력(검색시 사용)

			SearchBean sbean = (SearchBean)ctx.getBean("searchBean");
			RentBean rbean = (RentBean)ctx.getBean("rentBean");
			BookManageService showSvc = (BookManageService) ctx.getBean("ShowSvc");
			BookReturnService brtSvc = (BookReturnService) ctx.getBean("BrtSvc");
			BookReturn bret= new BookReturn();

			//도서 검색
			if(act==1) {
				System.out.println("검색할 책 제목을 입력하세요.: ");
				word=sc.nextLine();
				sbean.setBooktitle(word);
				sbean.search();
			}
			//책 대출
			else if(act==2) {
				System.out.println("대여 가능한 책 목록");
				rbean.list();
				System.out.println("대여 할 책 번호를 입력하세요.");
				b_no=sc.next();
				rbean.setBookno(b_no);
				rbean.setUname(name);
				rbean.setUphone(phone);
				rbean.rent();
			}
			//책 반납
			else if(act==3) {
				System.out.println("반납할 책 목록");
				//본인이 대여할 책 목록 보여주기
				bret.setName(name);
				bret.setPhone(phone);
				showSvc.rshow(bret);
				System.out.println("반납 할 책 번호를 입력하세요.");
				b_no=sc.next();
				bret.setBookno(b_no);
				brtSvc.rbook(bret);
			}
			//사용자 정보 수정
			else if(act==4) {
				System.out.print("이름: ");
				update_name=sc.nextLine();
				System.out.print("전화번호(숫자만 입력): ");
				update_phone=sc.nextLine();
				System.out.print("생년월일 입력: ");
				update_birth=sc.nextLine();
				System.out.print("주소: ");
				update_addr=sc.nextLine();

				upMem.setName(update_name); //변경 이름 지정
				upMem.setPhone(update_phone); //변경 번호 지정
				upMem.setAddr(update_addr);	//변경 주소 지정
				upMem.setBirth(update_birth); //변경 생일 지정

				UpSvc.update(regMem,upMem); //사용자 정보 파일 수정을 위해 원래 정보와 변경하기 위해 입력받은 정보 전달
				showSvc.rupdate(regMem,upMem); //책 대여 목록파일의 대여자 정보 수정 위해 원래 정보와 변경하기 위해 입력받은 정보 전달

				name=update_name; //name을 새로 입력받은 값으로 저장
				phone=update_phone; //phone을 새로 입력받은 값로 저장
				addr=update_addr; //addr을 새로 입력받은 값로 저장
				birth=update_birth; //birth를 새로 입력받은 값로 저장
			}

		}while(act!=0);

		//키보드 0누르면 종료
		if(act==0) {
			System.out.println("종료");
			ctx.close();
			System.exit(0);
		}
	}

}
