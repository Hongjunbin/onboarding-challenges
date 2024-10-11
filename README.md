# ✏ 백엔드 개발 온보딩 과제 (Java)

### [🏫 Swagger Link 📝] ( http://localhost:8888/swagger-ui/index.html )
<br>

## 📝 요구사항
#### 필수 구현 사항
1. 회원가입 및 로그인 기능 구현
2. Spring Security & JWT & Filter 활용
3. Token 발행과 검증 단위 테스트
4. Git-Hub PR 사용
5. EC2 배포 및 Swagger UI로 접속
#### 개인적으로 추가 구현한 사항
1. 회원가입 및 로그인 예외처리 ( CustomException, Global, ErrorCode/Enum )
2. CI/CD 구축 ( Git-Hub Actions, Docker )
3. 회원 데이터 DB저장 ( MySQL, JPA )
4. 회원가입 유효성 검사 ( Vaildation )
5. Git-Hub Template 활용 및 이슈 활용 ( 이슈, PR )
6. Git 컨벤션 사용

#### 1) 회원가입 가능 - /signup
Request Message
```
{
  "username": "JIN HO",
  "password": "12341234",
  "nickname": "Mentos"
}
```

Response Message
```
{
   "username": "JIN HO",
   "nickname": "Mentos",
   "authorities": [
      {
        "authorityName": "ROLE_USER"
      }
   ]		
}
```
#### 2) 로그인 가능 - /sign
Request Message
```
{
  "username": "JIN HO",
  "password": "12341234"
}
```

Response Message
```
{
   "username": "JIN HO",
   "nickname": "Mentos",
   "authorities": [
      {
        "authorityName": "ROLE_USER"
      }
   ]		
}
```

<details>
<summary> 💻 서비스 아키텍처 </summary>
<div markdown="1">
  
![아키텍처](https://github.com/user-attachments/assets/ab819b6a-d161-4c90-8fb3-e537b55d30ed)

</div>
</details>

<details>
<summary>🌠 Commit Rule</summary>
<div markdown="1">
  
### 🌠 Commit Rules
| 작업 타입 | 작업내용 |
| --- | --- |
| ✨ feat | 새로운 기능을 추가 |
| 🩹 fix | 코드 수정 |
| 🍻 test | 테스트 코드를 작성 |
| 🔨 add | package.json 변경(npm 설치 등) |

</div>
</details>
