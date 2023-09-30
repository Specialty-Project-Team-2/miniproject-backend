# 프로젝트 - Mine Sweeper

|  | Team React | Team Spring |
| --- | --- | --- |
| 🔗 Git hub | https://github.com/Specialty-Project-Team-2/miniproject-frontend | https://github.com/Specialty-Project-Team-2/miniproject-backend |
| 🌐 Web Site | https://miniproject-frontend-chi.vercel.app/ | https://miniproject.kro.kr/ |

---

# 📌 프로젝트 소개

![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled.png)

![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%201.png)

### **💬 Mine Sweeper**

‘**지뢰 찾기**’ 게임을 영어로 표현하면
’**Mine Sweeper**’

Mine Sweeper는 **기업들에 대한 정보를 얻고**
각 기업을 경험한 사람들에게
해당 기업 문화, 업무 등등을
**댓글로 공유**할 수 있는 웹 사이트.

---

# 📌 웹 사이트 시연

![[Mine Sweeper](https://miniproject-frontend-chi.vercel.app/)](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/시연%20영상%20썸네일.png)

[시연 영상 Youtube](https://youtu.be/zcD7XP_6VWw)

---

# 📌 웹 사이트 구조

![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%202.png)

- `Vercel`에 **`React`** 빌드 결과물 배포
- `EC2`에 **`Spring`** 빌드 결과물 배포
- `RDS`에 **`MySQL`** 서버 구동
- `JobKorea`에서 데이터 스크래핑
- `Https`로 사용자와 통신

---

# 📌 적용한 기술 혹은 기능

- `테스트 코드 작성`

  💯 **시나리오 별로 작동 여부 확인**

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%203.png)
    
  ---

  💯 Repository 테스트에서 사용되는 데이터를 격리 시키기 위해 `@SQL` Annotation 적용

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%204.png)

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%205.png)

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%206.png)
    
  ---

- `**Profile에 따른 설정 값 적용**`

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%207.png)

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%208.png)
    
  ---

  💯 **3개의 Profile 사용**

    - **Local** `[Default]`
        - 개발을 위한 환경 설정들
        - DB: `MySQL`
        - Kakao Social Login 중 Redirect URI : `http://localhost:3000`
    - **Test**
        - React 팀이 사용할 수 있도록 띄운 임시 서버의 환경 설정들
        - DB: `H2`
        - Kakao Social Login 중 Redirect URI : `http://localhost:3000`
    - **Prod**
        - 실제 서비스를 위한 배포 서버의 환경 설정들
        - DB: `MySQL`
        - Kakao Social Login 중 Redirect URI : `https://miniproject-frontend-chi.vercel.app/`

        ---


---

# 📌 트러블 슈팅

- **`Social Login 기능 구현 문제`**

  💦 `겪었던 문제`

  → API 서버는 Authorization Code 기반으로 DB에 회원 등록 후 JWT 발급

  → 하지만 인증 서버에 의한 Redirect 응답을 React에서 잡지 못함.

    ```bash
    # Redirect URI 주소 - API 서버
    http://localhost:8080/api/user/login/token
    ```

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%209.png)

  💡 `해결 방법`

    ```bash
    # Redirect URI 주소 - 웹 브라우저
    http://localhost:3000/api/user/login/token
    ```

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%2010.png)
    
  ---


---

# 📌 앞으로 적용하고 싶은 기능 및 기술

- **`기업 위치를 지도에 표시해주는 기능`**

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%2011.png)

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%2012.png)
    
  ---

- **`다크 모드`**

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%2013.png)
    
  ---

- **`좋아요 기능`**

  ![Untitled](%E1%84%91%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8C%E1%85%A6%E1%86%A8%E1%84%90%E1%85%B3%20-%20Mine%20Sweeper%205aaa881233db40bd9201acc6f33415a9/Untitled%2014.png)

  🙏🏻 복잡한 쿼리를 작성해야 하는 **좋아요 기능**
    
  ---

- **`이미지 업로드 기능`**

  ![https://miro.medium.com/v2/resize:fit:720/0*7cYKxWt-d8qs5Ngs.gif](https://miro.medium.com/v2/resize:fit:720/0*7cYKxWt-d8qs5Ngs.gif)
    
  ---


---