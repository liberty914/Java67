[실습 목표]
- 페이징 처리
- 검색 구현
- 정렬 구현
  
1. 페이징 처리
1) BoardDao.xml에서 selectList 질의문을 변경한다.
<select id="selectList" parameterType="map" resultMap="BoardVoMap">
    SELECT bno, title, cre_date, views 
    FROM board2
    ORDER BY bno DESC
    LIMIT #{startIndex}, #{pageSize}
</select>
- LIMIT 문을 추가한다.
- startIndex와 pageSize 값을 받을 parameterType을 선언한다.

2) BoardDao.java에서 selectList() 메서드 변경
- 파라미터 선언

3) BoardListServlet.java 변경

4) BoardList.jsp 변경
- [이전], [다음] 링크 추가

2. 검색 구현
1) BoardList.jsp 페이지에 검색 입력폼 추가
2) BoardListServlet.java 변경
3) BoardDao.java, BoardDao.xml 변경

3. 정렬 구현
1) BoardList.jsp의 테이블 헤더에 정렬을 위한 링크를 붙인다.
2) BoardListServlet.java, BoardDao.java, BoardDao.xml  변경.
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 