#roi영역 지정한 후 그 부분을 도장처럼 클릭한 위치에 찍기(붙여넣기)

import cv2
import numpy as np

img = cv2.imread('img/sunset.jpg')
cv2.imwrite('img/sunset_copy.jpg', img)
copy = cv2.imread('img/sunset_copy.jpg') #선택한 영역 겹쳐 보이는거 방지 하기 위해 복사본 만듬
x, y, w, h = cv2.selectROI('img', copy, False)


def onMouse(event, x, y, flags, param):
    if event == cv2.EVENT_LBUTTONDOWN:
        '''
        try:
            img[y:y+h,x:x+w]=roi
            cv2.imshow('img',img)
        except:
            print("범위 벗어남")
        '''

        if (y + h > 388 and x + w > 600):
            print("가로 세로 범위 벗어남")
            a = 600 - x #이미지 붙일 수 있는 가로길이
            b = 338 - y #이미지 붙일 수 있는 세로길이

            crop = cv2.imread('img/sunset_crop.jpg') #선택한 영역 저장한것 crop으로 불러옴
            crop_roi = crop[:b, :a] #붙일 수 있는 만큼 선택한 영역자른것이 crop_roi

            h1 = crop_roi.shape[0] #붙일 수 있는 만큼 자른 이미지의 세로(높이)
            w1 = crop_roi.shape[1] #붙일 수 있는 만큼 자른 이미지의 가로(너비)

            copy[y:y + h1, x:x + w1] = crop_roi
            cv2.imshow('img', copy)

        elif (y + h > 338 and x+w<=600):
            print("세로 범위 벗어남")
            b = 338-y

            crop = cv2.imread('img/sunset_crop.jpg')
            crop_roi = crop[:b, :]

            h1 = crop_roi.shape[0]

            copy[y:y + h1, x:x + w] = crop_roi
            cv2.imshow('img', copy)


        elif (x+w>600 and y+h<=338):
            print("가로 범위 벗어남 ")
            a=600-x

            crop=cv2.imread('img/sunset_crop.jpg')
            crop_roi=crop[:,:a]

            w1=crop_roi.shape[1]

            copy[y:y + h, x:x + w1] = crop_roi
            cv2.imshow('img', copy)


        elif(x+w<=600 and y+h<=338):
            copy[y:y + h, x:x + w] = roi
            cv2.imshow('img', copy)


if w and h:
    roi = img[y:y + h, x:x + w]
    cv2.imwrite('img/sunset_crop.jpg', roi) #선택한 영역만 잘라서 저장


cv2.setMouseCallback('img', onMouse)

cv2.imshow('img', img)
cv2.waitKey(0)
cv2.destroyAllWindows()

