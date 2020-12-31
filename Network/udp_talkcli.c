// 파일명: udp_talkcli.c
// 기능: 토크 서버와 1대1 통신을 하는 클라이언트 프로그램
// 사용법: udp_talkcli ip주소 포트번호

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#define MAXLINE 511

char *EXIT_STRING = "exit"; // 종료 문자 정의

int recv_and_print(int sd, struct sockaddr_in* servaddr); // 상대로부터 메시지 수신 후 화면 출력
int input_and_send(int sd, struct sockaddr_in* servaddr); // 키보드 입력받고 상대에게 메시지 전달

int main(int argc, char *argv[]) {
	pid_t pid;
	static int s;
	static struct sockaddr_in servaddr;

	// 명령문 입력 인자 처리
	if(argc != 3) {
		printf("사용법: %s server_ip port\n", argv[0]);
		exit(0);
	}

	// 소켓 생성
	if((s = socket(PF_INET, SOCK_DGRAM, 0)) < 0) {
		printf("Client: Can't open dgram socket.\n");
		exit(0);
	}

	// servaddr을 '0'으로 초기화
	bzero((char *)&servaddr, sizeof(servaddr));

	// servaddr 세팅
	servaddr.sin_family = AF_INET;
	inet_pton(AF_INET, argv[1], &servaddr.sin_addr);
	servaddr.sin_port = htons(atoi(argv[2]));

	if((pid = fork()) > 0) { // 부모 프로세스
		input_and_send(s, &servaddr);
	} else if(pid == 0) { // 자식 프로세스
		recv_and_print(s, &servaddr);
	}

	close(s);

	return 0;
}

// 키보드 입력받고 상대에게 메시지 전달
int input_and_send(int sd, struct sockaddr_in *servaddr) {
	char buf[MAXLINE + 1];
	int nbyte, addrlen = sizeof(struct sockaddr_in);

	while(fgets(buf, sizeof(buf), stdin) != NULL) {
		nbyte = strlen(buf);
		if(sendto(sd, buf, strlen(buf), 0, (struct sockaddr *)servaddr, addrlen) < 0)
			perror("sendto fail\n");

		// 종료 문자열 입력 처리
		if(strstr(buf, EXIT_STRING) != NULL) {
			puts("Good Bye!");
			close(sd);
			exit(0);
		}
	}

	return 0;
}

// 상대로부터 메시지 수신 후 화면 출력
int recv_and_print(int sd, struct sockaddr_in *servaddr) {
	char buf[MAXLINE + 1];
	int nbyte, addrlen = sizeof(struct sockaddr_in);

	while(1) {
		if((nbyte = recvfrom(sd, buf, MAXLINE, 0, (struct sockaddr *)servaddr, &addrlen)) < 0) {
			perror("read fail");
			close(sd);
			exit(0);
		}

		buf[nbyte] = 0;

		// 종료 문자열 수신 시 종료
		if(strstr(buf, EXIT_STRING) != NULL) {
			break;
		}

		printf("%s", buf); // 화면 출력
	}

	return 0;
}
