# shooting_game

1. 슈팅 게임 설명

3. 주요 코드


---

## 1. 슈팅 게임 설명 
* 목차 
1. 게임 시작
2. 게임 설명
3. 동작 과장
4. 게임 종료

### 1-1 게임 시작
![image](https://github.com/morningB/shooting_game/assets/114423035/91e54865-27cc-49ba-887f-26314e28eddf)

- 게임을 시작하면 나오는 처음 화면입니다.
- 사진에 보이다시피 enter키를 눌러 다음 화면으로 넘어갑니다

### 1-2 게임 설명
![image](https://github.com/morningB/shooting_game/assets/114423035/b57b5cd2-f7ff-4812-bb2c-cc43d8f186e4)

- enter를 누른 다음 화면입니다.
- 게임에 대한 전체적인 설명이 있습니다.

![image](https://github.com/morningB/shooting_game/assets/114423035/6d98d460-5200-4981-88d5-e577ae5c99c3)

- 게임이 시작하면 조종하게 되는 배입니다. W,A,S,D로 움직이며 몬스터들을 피하면 됩니다.
  
![image](https://github.com/morningB/shooting_game/assets/114423035/df358511-4c8a-498d-b9d6-ddd679a86d35)

- 배에서 나오는 미사일 입니다. space bar를 눌러 발사하면 됩니다. 맞출 경우 몬스터가 죽으며 점수가 올라갑니다.
  
![image](https://github.com/morningB/shooting_game/assets/114423035/505a4189-417a-4942-aca3-d92c8f36d7a0)

- 무작위로 생성되는 적입니다. 충돌하게 되면 life가 1씩 줄어들게 됩니다.

![image](https://github.com/morningB/shooting_game/assets/114423035/d9bcfe8d-0604-4538-b2c4-fd53f1459ca1)

- life가 줄어들 때 마다 나오는 보스 몹 입니다. 그러나 일반 몬스터와 다르게 미사일 10발을 맞춰야 죽지만, 점수는 10점을 줍니다. 속도가 매우 빠르므로 섬세한 조종이 필요합니다. 보스에게 닿으면 즉시 게임이 종료 됩니다.

![image](https://github.com/morningB/shooting_game/assets/114423035/e9fe60aa-c237-48ff-b06e-45e4d885d56d)

- 위 아래 방향키로 적 움직임의 빠르기를 정할 수 있습니다.

### 게임 화면 
![image](https://github.com/morningB/shooting_game/assets/114423035/d027aa52-87ab-4aca-ad46-d6e72d4d48bf)

- 게임이 본격적으로 시작되며 배를 조종하며 몬스터를 피하는 게임이며 처음 3개의 life를 가지고 시작하고 몬스터에게 닿을 때마다 life가 하나씩 줄어들게 됩니다.

![image](https://github.com/morningB/shooting_game/assets/114423035/da3cac79-dec9-479f-9bba-a4270d4b7bb7)

- 다음은 life가 0이 될 때 나오는 사진입니다. esc를 눌러 종료할 수 있습니다.


## 주요 코드


![image](https://github.com/morningB/shooting_game/assets/114423035/4f928539-da26-4f31-b48f-e0b71d9f3e7f)

1. 타임 리스너 사용
  
![image](https://github.com/morningB/shooting_game/assets/114423035/7a724d50-dc42-4cee-a65a-8cebc2465388)

2. 키 리스너 사용

![image](https://github.com/morningB/shooting_game/assets/114423035/7a50e5a1-cf1f-426d-97b0-406467306e06)

3. 충돌 알고리즘 사용

