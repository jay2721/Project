// 파일명: udp_talkserv.c
// 기능: 토크 클라이언트와 1대1 통신을 한다.
// 사용법: udp_talkserv 포트번호

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <time.h>

char *EXIT_STRING = "exit"; // 종료 문자

int recv_and_print(int sd, struct sockaddr_in *servaddr); // 상대로부터 메시지 수신 후 화면 출력
int input_and_send(int sd, struct sockaddr_in *servaddr); // 키보드 입력 받고 상대에게 메시지 전달

int check = 0; // 전역 변수로 체크한다.

#define MAXLINE 511

int main(int argc, char *argv[]) {
	struct sockaddr_in cliaddr, servaddr;
	int s;
	int nbyte, addrlen = sizeof(struct sockaddr);
	char buf[MAXLINE + 1];
	pid_t pid;

	// 사용법 출력
	if (argc != 2) {
		printf("사용법: %s port\n", argv[0]);
		exit(0);
	}

	// 소켓 생성
	if ((s = socket(PF_INET, SOCK_DGRAM, 0)) < 0) {
		perror("socket fail\n");
		exit(0);
	}

	// 서버의 소켓 주소 구조체 servadr을 '0'으로 초기화
	// 클라이언트도 같이 초기화
	bzero((char *)&servaddr, sizeof(servaddr));
	bzero((char *)&cliaddr, sizeof(cliaddr));

	// servaddr을 세팅
	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = htonl(INADDR_ANY);
	servaddr.sin_port = htons(atoi(argv[1]));


	if (bind(s, (struct sockaddr *)&servaddr, sizeof(servaddr)) < 0) {
		perror("bind fail\n");
		exit(0);
	}

	puts("서버가 클라이언트를 기다리고 있습니다.");

	if ((nbyte = recvfrom(s, buf, MAXLINE, 0, (struct sockaddr *)&cliaddr, &addrlen)) < 0) {
		perror("read fail");
		exit(0);
	}

	puts("클라이언트가 연결되었습니다.");

	buf[nbyte] = 0;
	printf("%s", buf);


	if ((pid = fork()) > 0) {
		input_and_send(s, &cliaddr); // 키보드 입력받고 상대에게 메시지 전달
	}
	else if (pid == 0) {
		recv_and_print(s, &cliaddr); // 상대로부터 메시지 수신 후 화면 출력
	}

	close(s);

	return 0;
}

// 키보드 입력받고 상대에게 메시지 전달
int input_and_send(int sd, struct sockaddr_in* servaddr) {
	char buf[MAXLINE + 1];
	int nbyte, addrlen = sizeof(struct sockaddr_in);

	while (fgets(buf, sizeof(buf), stdin) != NULL) {
		nbyte = strlen(buf);

		if (sendto(sd, buf, strlen(buf), 0, (struct sockaddr *)servaddr, addrlen) < 0)
			perror("sendto fail");

		// 종료 문자열 입력 처리
		if (strstr(buf, EXIT_STRING) != NULL) {
			puts("Good Bye!");
			close(sd);
			exit(0);
		}
	}

	return 0;
}

// 상대로부터 메시지 수신 후 화면 출력
int recv_and_print(int sd, struct sockaddr_in* servaddr) {
	char buf[MAXLINE + 1];
	int nbyte, addrlen = sizeof(struct sockaddr_in);

	while (1) {
		if ((nbyte = recvfrom(sd, buf, MAXLINE, 0, (struct sockaddr *)servaddr, &addrlen)) < 0) {
			perror("read fail");
			close(sd);
			exit(0);
		}

		buf[nbyte] = 0;

		// 종료 문자열 수신 시 종료
		if (strstr(buf, EXIT_STRING) != NULL) {
			break;
		}

		printf("%s", buf); // 화면 출력

	}
}

return 0;
}
