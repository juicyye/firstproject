## 생각할만한 것들
- JpaRepository를 쓸 경우 Service 계층이 필요한가?
- 먼저 구조를 만들고 들어가자 -> api주소이름, html이름등
  - etc

### 1일차. 엔티티만들고 대충 컨트롤러로 웹페이지보이게 만들었다
=> 보완할 부분: html에서 bindingResult처리, service를 괜히만들었다. jpaRepository가 있으면 그걸 쓰고 다른게 필요하면 repository만들어서 impl할것, 웹페이지 꾸미기는 맨 마지막
