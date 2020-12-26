//폴링 형식
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <errno.h>
#include <strings.h>
#include <fcntl.h>
#include <sys/socket.h>
#include <sys/file.h>
#include <sys/time.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <unistd.h>

#define MAXLINE 511
#define PEOPLE 3

char *EXIT_STRING = "exit";
char *START_STRING = "Connected to chat server\n";
char *NOT_YOUR_TURN = "Stay...\n";
char *YOUR_TURN = "Input Number...\n";
char *WRONG_ANSWER = "Wrong Answer...\n";
char *RIGHT_ANSWER = "Right Answer...\n";
char *DIE = "You're Die. Bye..\n";

int turn = 0; //차례 계산을 위한 변수
int num_chat = 0; // 연결된 소켓 개수
int clisock_list[PEOPLE]; // 소켓 리스트
int listen_sock; // 연결을 대기하는 소켓
int life[PEOPLE] = { 0,0,0 }; //생명
int state; //게임상태

void addClient(int s, struct sockaddr_in *newCliaddr); //사용자 추가
void removeClient(int s); //사용자 제거
int tcp_listen(int host, int port, int backlog);
int is_nonblock(int sockfd); //소켓이 넌블록 모드인지 확인
void errquit(char *mesg) { perror(mesg); exit(1); } // 에러 뜨면 메시지 띄워주고 종료

int makeQuestion(); //구구단 문제 생성
void checkAnswer(int c_id, char *buf, int answer); //정답과 클라이언트가 입력한 답 비교
void checkLife(int c_id);	//답이 틀렸을때 생명체크


int main(int argc, char *argv[]) {
	struct sockaddr_in cliaddr;
	char buf[MAXLINE + 1];
	int answer; //makeQuestion()으로 부터 얻은 답

	int i, j, nbyte, accp_sock, addrlen = sizeof(struct sockaddr_in), count, clilen;
	fd_set read_fds;


	if (argc != 3) {
		printf("사용법: %s ip주소, port\n", argv[0]);
		exit(0);
	}

	listen_sock = tcp_listen(INADDR_ANY, atoi(argv[2]), 5);

	if (listen_sock == -1)
		errquit("tcp_listen fail");
	if (set_nonblock(listen_sock) == -1)
		errquit("set_nonblock fail");

	for (count = 0; ; count++) {
		//사용자 세명만 게임할 수 있고, 그 후에 연결된 클라이언트는 기다림
		if (num_chat < PEOPLE) {
			clilen = sizeof(cliaddr);
			accp_sock = accept(listen_sock, (struct sockaddr *)&cliaddr, &clilen);

			if (accp_sock == -1 && errno != EWOULDBLOCK)
				errquit("accept fail");
			else if (accp_sock > 0) {
				clisock_list[num_chat] = accp_sock;
				if (is_nonblock(accp_sock) != 0 && set_nonblock(accp_sock) < 0)
					errquit("set_nonblock fail");
				addClient(accp_sock, &cliaddr);
				send(accp_sock, START_STRING, strlen(START_STRING), 0);
				printf("%d번째 사용자 추가.\n", num_chat);
				if (num_chat == 1) {
					state = 1;
				}

			}
		}


		// 순서 메세지 방송
		for (i = 0; i < num_chat; i++) {

			errno = 0;
			//state=1 문제 생성
			if (state == 1) {
				answer = makeQuestion();
				state++;
			}
			//state=2 차례 메세지 보내기
			if (state == 2) {
				if (num_chat == 1) {
					send(clisock_list[(turn) % 1], YOUR_TURN, strlen(YOUR_TURN), 0);             // 현재 턴인 사람
					turn = 0;
				}
				else if (num_chat == 2) {
					send(clisock_list[(turn) % 2], YOUR_TURN, strlen(YOUR_TURN), 0);             // 현재 턴인 사람
					send(clisock_list[(turn + 1) % 2], NOT_YOUR_TURN, strlen(NOT_YOUR_TURN), 0);   // 현재 턴 아닌 사람 
				}
				else if (num_chat == 3) {
					send(clisock_list[(turn) % 3], YOUR_TURN, strlen(YOUR_TURN), 0);             // 현재 턴인 사람
					send(clisock_list[(turn + 1) % 3], NOT_YOUR_TURN, strlen(NOT_YOUR_TURN), 0);   // 현재 턴 아닌 사람 
					send(clisock_list[(turn + 2) % 3], NOT_YOUR_TURN, strlen(NOT_YOUR_TURN), 0);	 // 현재 턴 아닌 사람
				}
				state++;
			}

			//클라이언트가 입력한 답 받고 비교
			if (state == 3) {
				nbyte = recv(clisock_list[i], buf, MAXLINE, 0);

				if (nbyte == 0) {
					removeClient(i);
					continue;
				}
				else if (nbyte == -1 && errno == EWOULDBLOCK)
					continue;

				// 종료문자 처리
				if (strstr(buf, EXIT_STRING) != NULL) {
					removeClient(i);
					continue;
				}

				// 메시지 방송 및 답 비교
				buf[nbyte] = 0;

				//사용자가 1명일때
				if (num_chat == 1) {
					for (j = 0; j < num_chat; j++) {
						if (i != j)
							send(clisock_list[j], buf, nbyte, 0); 
					}
					printf("%s\n", buf);

					checkAnswer(i, buf, answer);	 //순서인 사용자가 입력한 답과 정답 비교

				}

				//사용자가 2명일때
				else if (num_chat == 2) {
					if (i == (turn % 2)) {
						for (j = 0; j < num_chat; j++) {
							if (i != j)
								send(clisock_list[j], buf, nbyte, 0);	//순서인 사람이 입력하면 방송
						}
						printf("%s\n", buf);

						checkAnswer(i, buf, answer);	//순서인 사용자가 입력한 답과 정답 비교
					}
					else {
						send(clisock_list[i], NOT_YOUR_TURN, strlen(NOT_YOUR_TURN), 0); //순서 아닌 사람이 입력하면 방송하지 않고, 차례가 아니라는 메세지 보내줌
					}
				}

				//사용자가 3명일때
				else if (num_chat == 3) {
					if (i == (turn % 3)) {
						for (j = 0; j < num_chat; j++) {
							if (i != j)
								send(clisock_list[j], buf, nbyte, 0); //순서인 사람이 입력하면 방송
						}
						printf("%s\n", buf);

						checkAnswer(i, buf, answer); //순서인 사용자가 입력한 답과 정답 비교
					}
					else {
						send(clisock_list[i], NOT_YOUR_TURN, strlen(NOT_YOUR_TURN), 0); //순서 아닌 사람이 입력하면 방송하지 않고, 차례가 아니라는 메세지 보내줌
					}
				}
			}

		}
	}
}

void addClient(int s, struct sockaddr_in *newcliaddr) {
	char buf[20];

	inet_ntop(AF_INET, &newcliaddr->sin_addr, buf, sizeof(buf));
	printf("New Client : %s\n", buf);

	clisock_list[num_chat] = s;
	num_chat++;

	if (num_chat > 1)
		state = 2;

}

void removeClient(int s) {
	close(clisock_list[s]);
	int i;
	if (s != num_chat - 1) {
		clisock_list[s] = clisock_list[num_chat - 1];
		life[s] = life[num_chat - 1];
		life[num_chat - 1] = 0;
	}
	num_chat--;
	printf("채팅 참가자 1명 탈퇴. 현재 참가자 수 : %d\n", num_chat);

	//모든 참가자가 나갔을때 life[]와 turn을 원상태로 초기화시켜줌
	if (num_chat == 0) {
		for (i = 0; i < PEOPLE; i++)
			life[i] = 0;
		turn = 0;
	}

	//게임도중 사용자가 나가거나 새로 들어왔을때 답 입력 순서에 대한 오류를 방지하기 위함
	if (num_chat == 1 && turn == 0)
		state = 1;
	else if (num_chat == 2 && turn % 2 == 1) {
		turn++;
		state = 2;
	}
	else
		state = 2;

}

int is_nonblock(int sockfd) {
	int val;
	// 기존의 플래그 값을 얻어온다.
	val = fcntl(sockfd, F_GETFL, 0);
	// 넌블록 모드인지 확인
	if (val & O_NONBLOCK)
		return 0;
	return -1;
}

int set_nonblock(int sockfd) {
	int val;
	val = fcntl(sockfd, F_GETFL, 0);
	if (fcntl(sockfd, F_SETFL, val | O_NONBLOCK) == -1)
		return -1;
	return 0;
}



int tcp_listen(int host, int port, int backlog) {
	int sd;
	struct sockaddr_in servaddr;

	sd = socket(AF_INET, SOCK_STREAM, 0);
	if (sd == -1) {
		perror("socket fail");
		exit(1);
	}

	bzero((char *)&servaddr, sizeof(servaddr));
	servaddr.sin_family = AF_INET;
	servaddr.sin_addr.s_addr = htonl(host);
	servaddr.sin_port = htons(port);

	if (bind(sd, (struct sockaddr *)&servaddr, sizeof(servaddr)) < 0) {
		perror("bind fail");
		exit(1);
	}

	listen(sd, backlog);
	return sd;
}

int makeQuestion() {

	int num1, num2, answer, j;
	char q_buf[MAXLINE];	//문제 메세지 저장 변수
	srand((unsigned)time(NULL));
	num1 = (rand() % 9 + 1);	//1~9까지 중 랜덤 첫번째 숫자
	num2 = (rand() % 9 + 1);	//1~9까지 중 랜덤 두번째 숫자
	answer = num1*num2;	//두 수의 곱(정답)
	sprintf(q_buf, "%d * %d = ?", num1, num2);	//클라이언트에게 보낼 문제 메세지 q_buf에 저장

	//모든 참가자에 문제 방송
	for (j = 0; j < num_chat; j++)
		send(clisock_list[j], q_buf, strlen(q_buf), 0);
	printf("%s\n", q_buf);

	return answer;
}

void checkAnswer(int c_id, char *buf, int answer) {

	int i;
	char an[100];

	sprintf(an, "%d", answer); //makeQuestion()의 정답을 문자열로 변환

	//정답과 사용자가 입력한 답이 일치 했을때
	if (strstr(buf, an) != NULL) {
		for (i = 0; i < num_chat; i++) {
			send(clisock_list[i], RIGHT_ANSWER, strlen(RIGHT_ANSWER), 0);  //맞췄다는 메세지 전송
		}

	}

	//정답과 사용자가 입력한 답이 일치하지 않았을때
	else if (strstr(buf, an) == NULL) {
		for (i = 0; i < num_chat; i++) {
			send(clisock_list[i], WRONG_ANSWER, strlen(WRONG_ANSWER), 0); //틀렸다는 메세지 전송
		}
		checkLife(c_id);
	}

	turn++;	
	state = 1;
}

void checkLife(int c_id) {
	//답이 틀렸을때 life[i]를 증가시키고 life[i]의 값이 2 이상이면 연결을 끊음
	life[c_id]++;
	if (life[c_id] > 2) {
		send(clisock_list[c_id], DIE, strlen(DIE), 0);	//죽었다는 메세지 전송
		removeClient(c_id);
	}
}

