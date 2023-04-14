# 2Week_KimWonHee.md

## Title: [2Week] 김원희

### 미션 요구사항 분석 & 체크리스트

---

- [ ] 호감상대 삭제
  - [x] 삭제처리 전 해당 항목에 대한 소유권 확인
  - [x] rq.redirectWithMsg 함수 사용하여 성공여부 알림
  - [x] 삭제 후 /likeablePerson/list로 리다이렉트
  - [ ] 예외 처리
    - [x] 다른 인스타회원에게 중복으로 호감표시를 할 수 없음
    - [x] 한명의 인스타회원이 11명 이상의 호감상대를 등록 할 수 없음
    - [ ] 이미 호감표시 한 사용자를 기존의 사유와 다른 사유로 호감을 표시하는 경우에는 성공으로 처리





### 1주차 미션 요약

---

**[접근 방법]**


- 호감상대 항목에 대한 소유권이 로그인한 본인에게 있는지 확인하기 위해 rq와 Principal 객체를 사용했다.
- rq.redirectWithMsg 함수를 사용하기 위해 likeablePersonService.delete의 return형을 RsData\<LikeablePerson>로 하여
  삭제와 동시에 반환하도록 했다.




**[특이사항]**

delete 메서드를 작성할 때 @Transactional 어노테이션을 하지 않아서 계속 db에서 삭제되지 않는 문제가 발생했었다. <br>
insert, update, delete 동작시에는 @Transactional을 통해 데이터 베이스에 commit을 해야한다는 것을 다시 상기하게 되었다.