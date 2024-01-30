# 개 요
이름 : blackBoard <br>
형태 : 게시판 for Backend <br>
개요 : Basic crud of RestAPI <br>
# 스택
언어 : java 11 <br>
프레임워크 : Spring Boot 2.5.4 <br>
데이터베이스 : MySQL 8.3.0 <br>
ORM : JPA <br>
API 아키텍쳐 : REST <br>
API 문서화 : Swagger ui 3.0.0 <br>
http://localhost:8080/swagger-ui/
# 설계
## DB 구조
schema : blackboard <br>
table : post  <br>
column :

| DESCRIPTION. | NAME | TYPE | KEY | OPTION |
|--------------| --- | --- | --- | --- |
| 인덱스          | idx | int | PK | AUTO_INCREMENT |
| 제목           | title | VARCHAR(100) |  |  |
| 내용           | contents | TEXT |  |  |
| 삭제유무         | use_yn | int |  | 0-false /1-true |
| 생성일시         | created_at | TIMESTAMP |  | DEFAULT CURRENT_TIMESTAMP |
| 생성자          | created_by | VARCHAR(50) |  |  |
| 수정일시         | updated_at | DATETIME |  | DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP |
| 수정자          | updated_by | VARCHAR(50) |  |  |

## 기능
1. 게시글 작성 기능 Create
2. 게시글 수정 기능 Update
3. 게시글 삭제 기능 Delete
4. 게시글 검색 기능 Search
5. 게시글 목록 조회 기능 Read - All
6. 게시글 상세 조회 기능 Read - Each