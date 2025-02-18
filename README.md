# todo 웹 앱

1. 설명
- 평소에 할일(ex버킷리스트), 오늘의 할일로 구분됨
- 오늘의 할일은 일별로 선택해서 작성가능(왼쪽 달력을 통해서 선택)

- 버전: 리액트 18.3.1, 스프링부트 3.4.2, 자바 17
- db는 JPA,Mysql 사용

2. 실행
- 백엔드 스프링부트 application.properties에서 db스키마 todo 설정후 계정암호 비밀번호 변경 후 실행
- 프론트는 npm install 진행 후 실행
- apidoc 주소는 http://localhost:8080/swagger-ui/index.html

3. 주력 컴포넌트 : TodoProvider 이유 : 상태관리 편함
