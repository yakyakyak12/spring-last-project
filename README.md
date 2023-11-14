![밀리의 서재](https://github.com/muaga/flutter-last-project/assets/135561511/185d4cf1-4e8a-4b40-aed6-064c08574f57)

# 밀리의 서재 클론 코딩

## 프로젝트 2조
- 남은혜(팀장)
- 김대욱
- 김언약
- 진성훈

## 🎥 시연영상 [유튜브 링크] ()
## 🔧 기술스택
### Backend
### Frontend
### 협업도구
### 데이터베이스
### 의존성 관리 
```  dependencies { 
	implementation group: 'com.auth0', name: 'java-jwt', version: '4.4.0'
	implementation 'org.springframework.boot:spring-boot-starter-aop'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation "org.springframework.boot:spring-boot-starter-data-jpa"
	compileOnly 'org.projectlombok:lombok'
	annotationProcessor ('org.projectlombok:lombok')
	testImplementation 'org.springframework.boot:spring-boot-starter-test'

	// local
	runtimeOnly 'com.h2database:h2'
}
```

## 💻 기능 설명(기능 영상 포함)
밀리의 서재는 월정액으로 구독권을 구매한 후, 도서를 대여해 읽을 수 있는 전자책 서비스를 제공하고 책에 대한 포스트 작성과 댓글을 통해 소통이 가능한 서비스 앱으로써 해당 앱을 클론 코딩 했습니다.

### 유저 관련 api 
- 회원가입 
- 로그인 
- 로그아웃 
- 닉네임 변경 
- 비밀번호 변경 
- 이메일 변경 
- 회원탈퇴   
- 공지사항 및 FAQ 조회 
- 구독권 구매 

 ### 도서 관련 기능
- 서점 베스트 순 도서 목록 조회
- 한 달 이내 출간된 도서 목록 조회
- 밀리의 서재 이벤트 배너
- 카테고리별 도서 목록 조회
- 도서 검색
- 도서 추천 검색어로 검색
- 도서 상세 보기
- 도서 관련 댓글 작성 가능
- 좋아하는 도서 스크랩

### 독서 관련 기능
- 구독권 결제 후 결제 상태 변경
- 전자책 독서 클릭시 데이터 화면에 맞게 파싱해서 전송
- 좋아하는 페이지 북마크 가능
- 북마크한 페이지 목록 조회
- 전자책 진입 시 마지막으로 읽은 페이지로 진입 가능

### 포스트 관련 기능
- 모든 유저의 포스트 목록 조회
- 포스트 검색
- 포스트 관련 댓글 작성 가능
- 좋아하는 포스트 스크랩

### 내 서재 관련 기능
- 스크랩한 도서 목록 조회 및 삭제
- 읽고 있는 도서 목록 조회 및 삭제
- 내가 작성한 댓글 조회 및 삭제
- 내가 작성한 포스트 조회 및 수정, 삭제
- 댓글 작성 및 포스트 작성

## 🔗 엔티티 연관관계
![Screenshot_11](https://github.com/yakyakyak12/spring-last-project/assets/135561652/39ddc318-d891-44c6-b148-668946626c53)

## 📝 노션
https://chiseled-acoustic-3be.notion.site/2-82dc0df9935b4000afae6183f08a9b6d?pvs=4    

일정관리, 역할분담, 계획수립, 소통 등 노션을 통해 진행하였다.

## 🧩 블로그 테스트 정리

#### 공부내용 블로그 정리 
https://chiseled-acoustic-3be.notion.site/efedc2f9e4a74a04863509a841df5437?pvs=4   

#### Github에 푸시한 테스트 코드
https://github.com/muaga/flutter-test-code

https://github.com/muaga/spring-substring-test   

https://github.com/muaga/flutter-viewmodel   

https://github.com/saki8661/flutter_millie    


## 🔩 보완할 점
- 정기 결제기능 추가
- 환불기능 추가
- 관리자 페이지 추가
- 결제 테이블 수정

## 📋 느낀 점
- json 응답을 하면서 오타나 불필요한 데이터를 응답해서 반복된 작업을 많이 하였다. 조금 더 꼼꼼하게 체크할 필요성을 느꼈다.
- api 문서를 직접 notion에 작성하다보니 오타나 불필요한 데이터가 나올때마다 문서를 직접 수정하였다. 놓치는 부분도 많았고 일이 더 많아져서 힘들었다. restDOC라는 API문서 자동화를 공부해서 사용해야겠다.
- 여태까지 배웠던 것들임에도 flutter 수업을 들은 후 하니 너무 어색하고 기억이 안나는 부분들이 많았다. 앞으로도 여러가지 언어를 접하게 될텐대 다시 찾아 사용할 수 있도록 정리를 잘해야하겠구나 느꼈다.
