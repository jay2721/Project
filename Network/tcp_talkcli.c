// 파일명: tcp_talkcli.c
// 기능: 토크 서버와 1대1 통신을 하는 클라이언트 프로그램
// 사용법: tcp_talkcli ip주소 포트번호 닉네임

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdlib.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#define MAXLINE   511
#define NAME_LEN    20

char *EXIT_STRING = "exit";
int recv_and_print(int sd);
int input_and_send(int sd);
char bufall[MAXLINE + NAME_LEN], *bufmsg;
int namelen;

int main(int argc, char *argv[]) {
	pid_t pid;
	static int s;
	static struct sockaddr_in servaddr;

	// 명령문 입력 인자 처리
	if (argc != 4) {
		printf("사용법: %s server_ip, port, name \n", argv[0]);
		exit(0);
	}

	sprintf(bufall, "[%s] :", argv[3]);

	// 소켓 생성
	if ((s = socket(PF_INET, SOCK_STREAM, 0)) < 0) {
		printf("Client: Can't open stream socket.\n");
		exit(0);
	}

	// servaddr을 '0'으로 초기화
	bzero((char *)&servaddr, sizeof(servaddr));

	// servaddr 세팅
	servaddr.sin_family = AF_INET;
	inet_pton(AF_INET, argv[1], &servaddr.sin_addr);
	servaddr.sin_port = htons(atoi(argv[2]));

	// 서버에 연결 요청
	if (connect(s, (struct sockaddr *)&servaddr, sizeof(servaddr)) <
		0) {
		printf("Client: can't connect to server.\n");
		exit(0);
	}
	// 부모 프로세스
	if ((pid = fork()) > 0)
		input_and_send(s);

	// 자식 프로세스
	else if (pid == 0)
		recv_and_print(s);

	close(s);

	return 0;
}

// 키보드 입력받고 상대에게 메시지 전달
int input_and_send(int sd) {
	namelen = strlen(bufall);
	bufmsg = bufall + namelen;
	int nbyte;
	while (fgets(bufmsg, MAXLINE + NAME_LEN, stdin) != NULL) {
		nbyte = strlen(bufmsg);
		write(sd, bufall, namelen + strlen(bufmsg));

		// 종료 문자열 입력 처리
		if (strstr(bufall, EXIT_STRING) != NULL) {
			puts("Good bye.");
			close(sd);
			exit(0);
		}
	}
	return 0;
}

// 상대로부터 메시지 수신 후 화면 출력
int recv_and_print(int sd) {
	namelen = strlen(bufall);
	bufmsg = bufall + namelen;
	int nbyte;
	while (1) {
		if ((nbyte = read(sd, bufall, MAXLINE + NAME_LEN))<0) {
			perror("read fail");
			close(sd);
			exit(0);
		}
		bufall[nbyte] = 0;

		// 종료 문자열 수신 시 종료
		if (strstr(bufall, EXIT_STRING) != NULL)
			break;

		printf("%s", bufall); // 화면 출력
	}
	return 0;
}