#하르캐스케이드로 얼굴 인식 후 트랙바를 이용한 블러링

import cv2
import numpy as np
import datetime

#datetime 모듈을 이용해 today()객체 생성
today = datetime.datetime.today()
# 현재 날짜를 년/월/일 포맷으로 가져와 now라는 변수에 저장
now = today.strftime("%Y/%m/%d")

#블러링 강도를 조절해줄 트랙바 이벤트 핸들러 함수
def onChange(list):
    #트랙바 값을 받아와 k에 저장
    k = cv2.getTrackbarPos('Blur', 'Face_Blur')

    # 원본 이미지에 얼굴 인식된 부분 표시
    for (x, y, w, h) in faces:

        roi = frame[y:y + h, x:x + w]  # 인식된 얼굴 부분을 roi 지정

        #k=0이라면 블러함수를 부르지 않고 그냥 roi지정만함 (k=0을 blur함수에 넣을경우 오류발생하기때문)
        if (k == 0):
            frame[y:y + h, x:x + w] = roi #좌표에 roi추가

        #k값이 0이 아니라면 blur함수의 커널사이즈 값을 (k,k)으로 설정
        else:
            roi = cv2.blur(roi, (k, k)) #blur함수의 커널사이즈 값을 (k,k)으로 설정
            frame[y:y + h, x:x + w] = roi #좌표에 roi추가

    cv2.imshow('Face_Blur', frame) #frame을 보여줌


# 노트북 카메라(내장카메라)을 사용하기때문에 장치번호는 0
cap = cv2.VideoCapture(0)

# 카메라가 열리지 않았다면 열지못했다는 메세지를 출력해줌
if cap.isOpened() == False:  # 카메라 생성 확인
    print('Can\'t open the CAM')
    exit()

# 동영상 녹화를 위해 필요한 변수들
record = False #녹화샹태 변수를 False로 초기화
fourcc = cv2.VideoWriter_fourcc(*'XVID') #인코딩 포멧 문자
fps = 10 #초당 프레임수를 fps에 저장
width = cap.get(cv2.CAP_PROP_FRAME_WIDTH) #프레임의 너비 width에 저장
height = cap.get(cv2.CAP_PROP_FRAME_HEIGHT) #프레임의 높이 height에 저장


#윈도우 이름 'Face_Blur'라고 지정
cv2.namedWindow('Face_Blur')

#캐스케이드 분류기 객체 생성
face_cascade = cv2.CascadeClassifier('data/haarcascade_frontalface_default.xml')

# 초기값이 30이고 최대값이 100인 Blur라는 이름의 트랙바를 Face_Blur(윈도우)에 생성
cv2.createTrackbar('Blur', 'Face_Blur', 30, 100, onChange)


#카메라가 열렸을때
while (True):

    ret, frame = cap.read() #카메라의 상태 및 프레임을 받아와 frame에 저장

    #인자 1를 설정해 카메라 좌우대칭을 시킴
    frame = cv2.flip(frame, 1)
    #그레이 스케일로 변환
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    #얼굴검출(이미지 확대 크기제한(scaleFactor) =1.3, 오탐지를 위한 요구되는 이웃 수(minNeighbors)=5 검출할 수 있는 최소 사이즈(minsize) =(80,80))
    faces = face_cascade.detectMultiScale(gray,scaleFactor=1.3, minNeighbors=5, minSize=(80,80))
    #날짜를 화면 (530,20) 위치에 흰색으로 표시
    cv2.putText(frame, now, (530, 20), cv2.FONT_HERSHEY_PLAIN, 1, (255, 255, 255))

    #트랙바로 블러강도 조절할수있는 함수 호출
    onChange(30)


    # 키보드 c또는 C를 눌러 이미지 캡쳐
    if ((cv2.waitKey(1) == ord('c')) or (cv2.waitKey(1) == ord('C'))):
        print("캡쳐")
        time = today.strftime("%Y%m%d%H%M%S") #키보드를 눌렀을때의 시간을 년월일시분초 형식으로 time에 저장
        file = "camera/cap" + time + ".jpg" #저장할 파일 경로 이름
        cv2.imwrite(file, frame)

    # 키보드 b또는 B를 눌러 흑백으로 이미지 캡쳐
    elif ((cv2.waitKey(1) == ord('b')) or (cv2.waitKey(1) == ord('B'))):
        print("흑백 캡쳐")
        time = today.strftime("%Y%m%d%H%M%S") #키보드를 눌렀을때의 시간을 년월일시분초 형식으로 time에 저장
        file = "camera/graycap" + time + ".jpg" #저장할 파일 경로 이름
        cv2.imwrite(file, cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)) #흑백으로 변환해서 저장

    # 키보드 v또는 V를 눌러 동영상 녹화 시작
    elif ((cv2.waitKey(1) == ord('v')) or (cv2.waitKey(1) == ord('V'))):
        print("녹화시작")
        time = today.strftime("%Y%m%d%H%M%S") #키보드를 눌렀을때의 시간을 년월일시분초 형식으로 time에 저장
        file = "camera/video" + time + ".avi" #저장할 파일 경로 이름
        record = True #녹화상태 표시 변수 record를 True로 설정
        #파일명(file)과 인코딩형식(fourcc), 프레임률(fps), 영상크기(width, height) 지정해 VideoWriter 객체 생성
        video = cv2.VideoWriter(file, fourcc, fps, (int(width), int(height)))

    # 키보드 s또는 S를 눌러 녹화 중지
    elif ((cv2.waitKey(1) == ord('s')) or (cv2.waitKey(1) == ord('S'))):
        print("녹화중지")
        record = False #녹화상태 표시 변수 record를 False로 설정
        video.release() #비디오 객체 해제

    # 녹화중일때 녹화중이라는 메세지를 띄우며 파일을 저장
    if record is True:
        print("녹화중")
        video.write(frame) #파일 저장

    # ESC키를 눌러 종료
    if cv2.waitKey(1) == 27:
        break


cap.release() #카메라 장치에서 받아온 메모리 해제
cv2.destroyAllWindows() #모든 윈도우창 닫음

