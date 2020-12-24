import cv2
import numpy as np

img = cv2.imread('img/blank_500.jpg')
##Lines
cv2.putText(img,"Lines", (20,20),cv2.FONT_HERSHEY_PLAIN,1,(0,0,0))
cv2.line(img,(50,30),(160,30),(255,0,0)) #파란색 선
cv2.line(img,(195,30),(305,30),(0,255,0)) #초록색 선
cv2.line(img,(340,30),(450,30),(0,0,255)) #빨간색 선

##Rectangle
cv2.putText(img,"Rectangle", (20,70),cv2.FONT_HERSHEY_PLAIN,1,(255,0,255))
cv2.rectangle(img,(130,130),(80,80),(255,0,0)) #파란색 사각형
cv2.rectangle(img,(275,130),(225,80),(0,255,0),10) #초록색 사각형
cv2.rectangle(img,(420,130),(370,80),(0,0,255),-1) #빨간색 사각형

##Polyline
cv2.putText(img,"Polyline", (20,170),cv2.FONT_HERSHEY_COMPLEX_SMALL,1,(0,255,255))
pts1=np.array([[30,170],[90,230],[60,230],[120,290]], dtype=np.int32)
pts2=np.array([[195,190],[145,290],[245,290]], dtype=np.int32)
pts3=np.array([[325,190],[275,290],[375,290]], dtype=np.int32)
pts4=np.array([[425,190],[375,230],[400,290],[450,290],[475,230]], dtype=np.int32)

cv2.polylines(img,[pts1],False,(255,0,0)) #파란색 번개
cv2.polylines(img,[pts2],False,(0,0,0),10) #검정색 삼각형
cv2.polylines(img,[pts3],True,(0,0,255),10) #빨간색 삼각형
cv2.polylines(img,[pts4],True,(0,0,0)) #검정색 오각형

##Circle
cv2.putText(img,"Circle", (20,320),cv2.FONT_HERSHEY_SCRIPT_COMPLEX,1,(255,0,0))
cv2.circle(img,(50,370),50,(255,0,0)) #파란색 원
cv2.circle(img,(50,430),40,(0,255,0),5) #초록색 원
cv2.circle(img,(50,470),25,(0,0,255),-1) #빨간색 원

cv2.ellipse(img, (150,370),(50,50),0,0,180,(255,0,0)) #파란색 반원
cv2.ellipse(img, (200,370),(50,50),0,181,360,(0,0,255)) #빨간색 반원

cv2.ellipse(img, (325,370),(75,50),0,0,360,(0,255,0)) #초록색 타원
cv2.ellipse(img, (450,370),(50,75),0,0,360,(255,0,255)) #분홍색 타원
cv2.ellipse(img,(195,450),(35,60),45,0,360,(0,0,0)) #검정색 타원
cv2.ellipse(img,(300,450),(35,60),45,0,180,(0,0,255))  #빨간색 반타원
cv2.ellipse(img,(350,470),(35,60),45,181,360,(255,0,0)) #파란색 반타원

cv2.imshow('draw all',img)
cv2.waitKey(0)
cv2.destroyAllWindows()

