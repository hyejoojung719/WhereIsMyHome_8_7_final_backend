# WhereIsMyHome_8_7_Spring

---

# 기본 기능

---

## WhereIsMyHome 웹 화면 구현

---

### CODE 1

> 메인페이지
> 

---

- home.jsp

```html
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

  <%@ include file="include/head.jsp" %>
</head>
<body>
<%@ include file="include/nav.jsp" %>
<main>
    <div class="container-fluid p-0">

      <!-- Carousel -->
      <div id="demo" class="carousel slide" data-bs-ride="carousel">

        <!-- Indicators/dots -->
        <div class="carousel-indicators">
          <button type="button" data-bs-target="#demo" data-bs-slide-to="0" class="active"></button>
          <button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
          <button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
        </div>

        <!-- The slideshow/carousel -->
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img src="${root}/assets/img/room2.png" alt="Los Angeles" class="d-block" style="width:100%">
            <div class="carousel-caption">
              <h1>Where Is My House</h1>
              <br>
              <p>우리에게 꼭 맞는 집을 찾아라!</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="${root}/assets/img/room.png" alt="Chicago" class="d-block" style="width:100%">
            <div class="carousel-caption">
              <h1>Where Is My House</h1>
              <br>
              <p>우리에게 꼭 맞는 집을 찾아라!</p>
            </div>
          </div>
          <div class="carousel-item">
            <img src="${root}/assets/img/room3.png" alt="New York" class="d-block" style="width:100%">
            <div class="carousel-caption">
              <h1>Where Is My House</h1>
              <br>
              <p>우리에게 꼭 맞는 집을 찾아라!</p>
            </div>
          </div>
        </div>

        <!-- Left and right controls/icons -->
        <button class="carousel-control-prev" type="button" data-bs-target="#demo" data-bs-slide="prev">
          <span class="carousel-control-prev-icon"></span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#demo" data-bs-slide="next">
          <span class="carousel-control-next-icon"></span>
        </button>
      </div>

      <!-- card divide -->
      <div class="car_wrapper row my-auto">
        <div class="card col-lg-6">
          <div class="card-body">
            <h3 class="card-title">내 집 마련! 이것만 확인하세요!</h3>
            <p class="card-text">1. 가용 자금 확인 및 대출 계획</p>
            <p class="card-text">2. 집 종류 및 지역 선택</p>
            <p class="card-text">3. 정보 수집 및 시세 파악</p>
            <p class="card-text">4. 부동산 방문 및 집 구경</p>
            <p class="card-text">5. 계약 및 잔금 치르기</p>
            <p class="card-text">6. 소유권 이전 동기</p>
          </div>
        </div>
        <div class="card col-lg-6">
          <div class="card-body">
            <h5 class="card-title">Where is My House는...</h5>
            <p class="card-text">내 집 마련을 위해서는 정보 수집이 중요합니다.</p>
            <p class="card-text">Where is My house는 사회 초년생부터, 신혼부부, 노부부까지</p>
            <p class="card-text">남녀노소 가장 적합한 집을 고를 수 있도록 전국의 집들을 보여주고 있습니다.</p>
            <p class="card-text">더 많은 정보가 궁금하다면??</p>
            <a href="sitemap.html" class="btn btn-primary" >사이트맵</a>
            <a href="kakaomap.html" class="btn btn-primary">실거래가 조회</a>
          </div>
        </div>
      </div>
      
    </div>
  </main>
<%@ include file="include/footer.jsp" %>
</body>
</html>
```

- src/main/java/com/ssafy/house/controller/homeController

```java
package com.ssafy.house.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		log.debug("Welcome home!");
		
		return "home";
	}
	
}
```

### 메인페이지

- 동작원리
    - HomeController를 통해 [localhost.com:8080/로](http://localhost.com:8080/로) url이 들어올 경우, home.jsp 페이지를 보여줍니다.

### Diagram

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled.png)

### CODE2

> 회원정보 등록, 수정, 삭제, 조회 페이지
> 

---

### 회원 정보 등록 페이지

- src/main/webapp/WEB-INF/views/signup.jsp

```jsx
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../include/head.jsp"%>
</head>
<body>
	<%@ include file="../include/nav.jsp"%>

	<main>
	<section class="vh-100" style="background-color: #eee;">
		<div class="container h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-lg-12 col-xl-11">
					<div class="card text-black" style="border-radius: 25px;">
						<div class="card-body p-md-5">
							<div class="row justify-content-center">
								<div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

									<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign
										up</p>

									<form id="form-join" class="mx-1 mx-md-4" method="POST" action="">
										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" id="userid" class="form-control" name="user_id"/> <label class="form-label" for="form3Example3c">Your
													Id</label>
											</div>
										</div>
										<div id="idcheck-result"></div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-user fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" id="username" name="user_name"
													class="form-control"/> <label class="form-label"
													for="form3Example1c">Your Name</label>
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-lock fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="password" id="userpwd" name="user_password"
													class="form-control"/> <label class="form-label"
													for="form3Example4c">Password</label>
											</div>
										</div>

										<div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
											<button id="btn-join" type="button"
												class="btn btn-primary btn-lg">Register</button>
										</div>

									</form>

								</div>
								<div
									class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

									<img
										src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/draw1.webp"
										class="img-fluid" alt="Sample image">

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	</main>

	<script>
      let isUseId = false;
      document.querySelector("#userid").addEventListener("keyup", function () {
    	 let user_id = this.value;
    	 console.log("user_id : " + user_id);
    	 let resultDiv = document.querySelector("#idcheck-result");
    	 if(user_id.length < 4 || user_id.length > 16) {
    		 resultDiv.setAttribute("class", "mb-3 text-dark");
    		 resultDiv.textContent = "아이디는 5자 이상 16자 이하 입니다.";
    		 isUseId = false;
    	 } else {
			fetch("${root}/user/idCheck?user_id=" + user_id)
				.then(response => response.text())
				.then(data => {
					console.log("data : " + data);
	    			 if(data == 0) {
	    			   resultDiv.setAttribute("class", " d-flex flex-row align-items-center mb-4 mb-3 text-primary");
	    		       resultDiv.textContent = user_id + "는 사용할 수 있습니다.";
	    		       isUseId = true;
	    			 } else {
	    			   resultDiv.setAttribute("class", " d-flex flex-row align-items-center mb-4 mb-3 text-danger");
	      		       resultDiv.textContent = user_id + "는 사용할 수 없습니다.";
	      		       isUseId = false;
	    			 }
				});
    	 }
      });
      
      document.querySelector("#btn-join").addEventListener("click", async function () {
    	  let user
    	  
        if (!document.querySelector("#userid").value) {
       	   alert("아이디 입력!!");
           return;
        } else if (!document.querySelector("#username").value) {
          alert("이름 입력!!");
          return;
        } else if (!document.querySelector("#userpwd").value) {
          alert("비밀번호 입력!!");
          return;
        } else if (!isUseId) {
          alert("아이디 확인!!");
          return;
        } else {
          let url = "${root}/user/signUp";
          
          let data = {
        	  "user_id" : userid.value,
        	  "user_name" : username.value,
        	  "user_password" : userpwd.value, 
          }
          
          let config = {
        	method : "POST",
  			headers : {
  				"Content-Type" : "application/json"
  			},
  			body : JSON.stringify(data),
          }
          
          let response = await fetch(url, config);
          if(response.ok) {
        	  location.href="${root}/";
          } else {
        	  alert("등록 실패");
        	  document.getElementById("form-join").reset();
          }
        }
      });
    </script>

	<%@ include file="../include/footer.jsp"%>
</body>
</html>
```

- src/main/java/com/ssafy/house/controller/PageController.java

```java
package com.ssafy.house.controller;
//..

@Controller
@RequestMapping(value = "/go")
@Slf4j
public class PageController {
   // 회원 가입페이지 이동
    @GetMapping(value = "/signUp")
    public String goSignUp(Model model) {
        log.debug("goSignUp() 메소드 요청");
        return "user/signup";
    }
}
```

- src/main/java/com/ssafy/house/controller/UserController.java

```java
//UserController.java
package com.ssafy.house.controller;

//..

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	// 회원 가입 => 중복된 회원인지 체크, db에 회원 정보 등록
	@ResponseBody
	@PostMapping(value = "/signUp")
	public ResponseEntity<?> signUp(Model model, @RequestBody User user) throws SQLException {
		log.debug("signUp() 메소드 요청");
		log.debug("user : {}", user.toString());
		int cnt = userService.insertUser(user);

		if (cnt == 1)
			return new ResponseEntity<Void>(HttpStatus.OK);
		else
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 아이디 중복 체크
	@RequestMapping(value = "/idCheck")
	@ResponseBody
	public ResponseEntity<?> idCheck(Model model, @RequestParam String user_id) throws SQLException {
		log.debug("idCheck() 메소드 요청");
		log.debug("user_id : {}", user_id);
		int cnt = userService.idCheck(user_id);

		return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
	}
}
```

- src/main/java/com/ssafy/house/model/service/UserServiceImpl.java

```java
//UserServiceImpl.java
package com.ssafy.house.model.service;
//..

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public int insertUser(User user) throws SQLException {
		return userMapper.insertUser(user);
	}

	@Override
	public int idCheck(String user_id) throws SQLException {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);
		
		int cnt = userMapper.idCheck(map);
		return cnt;
	}
}
```

- src/main/java/com/ssafy/house/model/mapper/UserMapper.java

```java
package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.house.dto.User;

public interface UserMapper {
	int insertUser(User user) throws SQLException;

	int idCheck(Map<String, Object> map) throws SQLException;
}

```

- src/main/resources/mapper/user.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ssafy.house.model.mapper.UserMapper">
	
	<!-- 회원가입 메소드 -->
	<insert id="insertUser" parameterType="user">
		insert into user
		values (#{user_id}, #{user_name}, #{user_password})
	</insert>
	
	<!-- 아이디 중복 체크 -->
	<select id="idCheck" resultType="int" parameterType="HashMap">
		select count(user_id)
		from user
		where user_id = #{user_id}
	</select>
</mapper>
```

### 회원정보 등록

- 동작원리
    1. `pageController`를 통해 navBar에 있는 signUp 버튼을 통해 `signUp.jsp` 페이지에 접근합니다. 
    2. `signUp.jsp` 에서 회원정보를 기입하고 중복된 아이디인지 확인해주는데, 이때 아래의 일들이 반복됩니다.  
        1. UserController에서 user_id를 가져와서 UserSerivceImpl.idCheck(user_id) 메소드를 호출합니다.
        2. UserServiceImpl에서 user_id값을 넘겨받아 Map형태로 만든 다음, UserMapper.idCheck(map)을 호출합니다. 
        3. UserMapper.java에서 UserServiceImpl에서 호출된 메서드를 user.xml로 전달합니다. 
        4. user.xml에서는 DB로부터 map에 있는 user_id값을 검색한 후 그 개수를 반환시켜줍니다. 
        5. 최종적으로 UserController에서 이 값을 받고 중복 여부를 signUp.jsp에 넘겨줍니다. 
    3. 회원정보를 다 기입하고 Register버튼을 클릭할 경우 아래의 일들이 발생합니다.
        1. UserController에서 현재 기입된 회원정보들을 User 객체 형태로 가져온 다음, UserServiceImpl.insertUser(user) 메소드를 호출합니다. 
        2. UserServiceImpl에서 User 값을 넘겨받아 UserMapper.insertUser(user)를 호출합니다. 
        3. UserMapper.java에서 UserServiceImpl에서 호출된 메서드를 user.xml로 전달합니다. 
        4. user.xml에서 DB로 하여금 user객체를 등록 하고 이에 대한 성공 여부를 int 형태로 반환합니다. 
        5. 최종적으로 UserController에서 성공여부를 판단하여 성공하였을 경우 200, 실패하였을 경우 500을 반환합니다. 
    4. singUp.jsp에서 server측에서 반환되는 값을 보고 성공했을 경우 메인페이지로 가며 실패했을 경우 현재 페이지에 그대로 머물게 됩니다.  

### 회원 정보 조회 페이지

- src/main/webapp/WEB-INF/views/user/mypage.jsp

```jsx
//mypage.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../include/head.jsp"%>

</head>
<body>
	<%@ include file="../include/nav.jsp"%>

	<c:set var="user" value="${user}"></c:set>
	<main>
	<section class="vh-100" style="background-color: #eee;">
		<div class="container h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-lg-12 col-xl-11">
					<div class="card text-black" style="border-radius: 25px;">
						<div class="card-body p-md-5">
							<div class="row justify-content-center">
								<div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

									<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Mypage</p>

									<form class="mx-1 mx-md-4" id="form-update" method="post" action="">
										<input type="hidden" name="action" value="updateUser" />
										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" id="id" class="form-control" name="user_id"
													value="" readonly/> <label class="form-label" for="form3Example3c">Your
													Id</label>
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-user fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" id="name" name="user_name"
													class="form-control" value=""  /> <label class="form-label"
													for="form3Example1c">Your Name</label>
											</div>
										</div>

										<div class="d-flex flex-row align-items-center mb-4">
											<i class="fas fa-lock fa-lg me-3 fa-fw"></i>
											<div class="form-outline flex-fill mb-0">
												<input type="text" id="pwd" name="user_password"
													class="form-control" value="" /> <label class="form-label"
													for="form3Example4c">Password</label>
											</div>
										</div>
										<div id="myPageBtn" class="hidden">
											<div class="d-flex flex-row justify-content-center mb-4">
												<button type="button" class="btn btn-primary btn-default" id="updateBtn">회원 정보 수정</button>
											</div>
											<div class="d-flex flex-row justify-content-center mb-4">
												<button type="button" class="btn btn-primary btn-default" id="deleteBtn">
												회원 정보 탈퇴</button>
											</div>
										</div>
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	</main>
	
	<script>
	window.onload = function(){
	
		// 회원 정보 조회
		async function getMyPage() {
			let user_id = document.getElementById("id");
			let user_name = document.getElementById("name");
			let user_password = document.getElementById("pwd");
			let myPageBtn = document.getElementById("myPageBtn");
			
			let url = "${root}/user/myPage";
			let response = await fetch(url);
			
			if(response.ok) {
				let user = await response.json();
				user_id.value = user.user_id;
				user_name.value = user.user_name;
				user_password.value = user.user_password;
				
				myPageBtn.classList.toggle("hidden");
			}
		}
		
		getMyPage();
		
		// 회원 정보 삭제
		document.querySelector("#deleteBtn").addEventListener("click", async function(){
				if(confirm("회원 탈퇴 하시겠습니까?")){
					let user_id = document.getElementById("id");
					let url = "${root}/user/deleteUser";
					let data = {
						"user_id" : user_id.value,
					}
					let config = {
				        method : "Delete",
				  		headers : {
				  			"Content-Type" : "application/json"
				  		},
				  		body : JSON.stringify(data),
					    }
					
					console.log("user_id : "  + user_id);
					
					let response = await fetch(url, config);
					if(response.ok) {
						location.href="${root}/";
					} else {
						location.href="${root}/go/myPage";
					}	 
				}
			
		});
		
		// 회원 정보 수정
		document.querySelector("#updateBtn").addEventListener("click", async function(){
				if(confirm("회원 수정 하시겠습니까?")){
					let form = document.querySelector("#form-update");
					
			        let url = "${root}/user/updateUser";
			        let user_id = document.getElementById("id");
					let user_name = document.getElementById("name");
					let user_password = document.getElementById("pwd");
					
					let data = {
						"user_id" : user_id.value,
						"user_name" : user_name.value,
						"user_password" : user_password.value,
					}
					
					let config = {
				        method : "Put",
				  		headers : {
				  			"Content-Type" : "application/json"
				  		},
				  		body : JSON.stringify(data),
				    }
					
					let response = await fetch(url, config);
					if(response.ok) {
						location.href ="${root}/go/myPage";
					}
					
				}else{
					location.href='${root}/go/myPage';
				}
			
		});
	}
	</script>

	<%@ include file="../include/footer.jsp"%>
</body>
</html>
```

- src/main/java/com/ssafy/house/controller/PageController.java

```java
package com.ssafy.house.controller;
//..

@Controller
@RequestMapping(value = "/go")
@Slf4j
public class PageController {
    // 마이페이지 이동 => 회원 정보 가져오기
    @GetMapping(value = "/myPage")
    public String goMyPage(Model model, HttpSession session) throws SQLException {
        log.debug("goMyPage() 메소드 요청");
        return "user/mypage";
    }
}
```

- src/main/java/com/ssafy/house/controller/UserController.java

```java
//UserController.java
package com.ssafy.house.controller;

//..

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	// 마이페이지 이동 => 회원 정보 가져오기
	@ResponseBody
	@RequestMapping(value = "/myPage")
	public ResponseEntity<?> myPage(Model model, HttpSession session) throws SQLException {
		log.debug("myPage() 메소드 요청");
		String id = (String) session.getAttribute("userId");

		User user = userService.selectUserInfo(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}

```

- src/main/java/com/ssafy/house/model/service/UserServiceImpl.java

```java
//UserServiceImpl.java
package com.ssafy.house.model.service;
//..

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public User selectUserInfo(String user_id) throws SQLException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		
		return userMapper.selectUserInfo(map);
	}
}
```

- src/main/java/com/ssafy/houser/model/mapper/UserMapper.java

```java
//UserMapper.java
package com.ssafy.house.model.mapper;
//..

public interface UserMapper {
	User selectUserInfo(Map<String, Object> map) throws SQLException;
}
```

- src/main/resources/mapper/user.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ssafy.house.model.mapper.UserMapper">
	<!-- 회원 정보 조회 -->
	<!-- 비밀번호 찾기 -->
	<select id="selectUserInfo" resultType="user" parameterType="HashMap">
		select * 
		from user
		where user_id = #{user_id}
	</select>
</mapper>
```

### 회원정보 조회

- 동작원리
    1. `pageController`를 통해 navBar에 있는 MyPage 버튼을 통해 `mypage.jsp` 페이지에 접근합니다. 
    2. `myPage.jsp`에서 fetch를 통해서 회원정보들을 DB로 하여금 가져옵니다. 이때 아래의 일들이 발생합니다. 
        1. UserController에서 session 값을 가져와서 session에 저장되어 있는 userId 값을 추출합니다. 이 값을 통해 UserServiceImpl.selectUserInfo(id)를 호출합니다. 
        2. UserServiceImpl에서 id 값을 넘겨받아 Map형태로 바꾼 다음,  UserMapper.selectUserInfo(map)를 호출합니다. 
        3. UserMapper.java에서 UserServiceImpl에서 호출된 메서드를 user.xml로 전달합니다. 
        4. user.xml에서 DB로부터 id 값을 통해 User 값을 검색한 후 User 객체를 반환합니다. 
        5. 최종적으로 UserController에서 User객체와 함께 200번 코드를 반환합니다. 
    3. DB로 하여금 가져온 User객체를 페이지에 rendering 한 뒤, 회원정보 수정, 삭제 버튼을 보이게 합니다. 
        1. hidden 클래스를 통해서 회원정보 수정, 삭제 버튼 display를 조정합니다. 

### 회원정보 수정, 삭제 페이지

- src/main/java/com/ssafy/house/controller/UserController.java

```java
//UserController.java
package com.ssafy.house.controller;

//..

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	// 회원 정보 업데이트
	@ResponseBody
	@PutMapping(value = "/updateUser")
	public ResponseEntity<?> updateUser(Model model, @RequestBody User user) throws SQLException {
		log.debug("updateUser() 메소드 요청");
		log.debug("user : {}", user.toString());
		int cnt = userService.updateUser(user);
		
		if(cnt == 1) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 회원 탈퇴 => db에서 지우고 세션 지우기
	@DeleteMapping(value = "/deleteUser")
	public ResponseEntity<?> deleteUser(HttpSession session, @RequestBody Map<String, String> map) throws SQLException {
		log.debug("deleteUser() 메소드 요청");
		log.debug("user_id : {}", map.get("user_id"));
		String user_id = map.get("user_id");
		int cnt = userService.deleteUser(user_id);
		
		if(cnt == 1) {
			session.removeAttribute("userId");
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
```

- src/main/java/com/ssafy/house/model/service/UserServiceImpl.java

```java
//UserServiceImpl.java
package com.ssafy.house.model.service;
//..

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public int updateUser(User user) throws SQLException {
		return userMapper.updateUser(user);
	}

	@Override
	public int deleteUser(String user_id) throws SQLException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		
		int cnt = userMapper.deleteUser(map);
		return cnt;
	}
}
```

- src/main/java/com/ssafy/house/model/mapper/UserMapper.java

```java
//UserMapper.java
package com.ssafy.house.model.mapper;

//..

public interface UserMapper {
	int updateUser(User user) throws SQLException;

	int deleteUser(Map<String, Object> map) throws SQLException;
}
```

- src/main/resources/mapper/user.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ssafy.house.model.mapper.UserMapper">
	<!-- 회원 정보 수정 -->
	<update id="updateUser" parameterType="user">
		update user
		set user_name = #{user_name}, user_password = #{user_password}
		where user_id = #{user_id}
	</update>
	
	<!-- 회원정보 삭제 -->
	<delete id="deleteUser" parameterType="HashMap">
		delete from user
		where user_id = #{user_id}
	</delete>
</mapper>
```

### 회원정보 수정

- 동작원리
    1. `mypage.jsp` 에서 회원정보 수정 버튼을 클릭합니다. 
    2. `myPage.jsp`에서 fetch를 통해서 회원정보 수정을 DB로 요청합니다. 이때 아래의 일들이 발생합니다. 
        1. UserController에서 현재 수정된 회원정보들을 User 객체 형태로 가져온 다음, UserServiceImpl.updateUser(user) 메소드를 호출합니다. 
        2. UserServiceImpl에서 넘겨받은 user 객체를 통해 UserMapper.updateUser(user)를 호출합니다. 
        3. UserMapper.java에서 UserServiceImpl에서 호출된 메서드를 user.xml로 전달합니다. 
        4. user.xml에서 DB로 하여금 user_id를 통해 user객체를 등록 하고 이에 대한 성공 여부를 int 형태로 반환합니다. 
        5. 최종적으로 UserController에서 성공 여부에 따라 값을 달리 보냅니다. 성공했을 경우 200번코드와 함께 user 객체를 보내고, 실패했을 경우 500번코드를 보냅니다. 
    3. 반환하는 값에 의해서 성공했을 경우 현재 페이지를 새로고침합니다. 

### 회원정보 삭제

- 동작원리
    1. `mypage.jsp` 에서 회원정보 삭제 버튼을 클릭합니다. 
    2. `myPage.jsp`에서 fetch를 통해서 회원정보 삭제를 DB로 요청합니다. 이때 아래의 일들이 발생합니다. 
        1. UserController에서 user_id값을 map형태로 가져온 다음, UserServiceImpl.deleteUser(user_id) 메소드를 호출합니다. 
        2. UserServiceImpl에서 넘겨받은 user_id값이 들어있는 map을 통해 UserMapper.deleteUser(map)를 호출합니다. 
        3. UserMapper.java에서 UserServiceImpl에서 호출된 메서드를 user.xml로 전달합니다. 
        4. user.xml에서 DB로 하여금 user_id를 통해 user객체를 삭제하고 이에 대한 성공 여부를 int 형태로 반환합니다. 
        5. 최종적으로 UserController에서 성공 여부에 따라 값을 달리 보냅니다. 성공했을 경우 200번코드를 보내고, 실패했을 경우 500번코드를 보냅니다. 
    3. 반환하는 값에 의해서 성공했을 경우 메인페이지로 이동하며, 실패했을 경우 현재 페이지에 머무릅니다. 

### Diagram

![user.jpg](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/user.jpg)

### CODE3

> 로그인 / 로그아웃 페이지
> 

---

- src/main/webapp/WEB-INF/views/include/nav.jsp

```jsx
//nav.jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<div class="container-fluid">
		<a class="navbar-brand" href="${root}"><i
			class="fa-solid fa-house"></i>Where is My Home</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarColor01" aria-controls="navbarColor01"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarColor01">
			<ul class="navbar-nav me-auto">
				<li class="nav-item"><a class="nav-link active" href="${root}">Home
						<span class="visually-hidden">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${root}/go/boardlist?key=&word=">Notice</a></li>

				<c:choose>
					<c:when test="${empty sessionScope.userId}">
						<li class="nav-item"><a class="nav-link" id="signupLink"
							href="${root}/go/signUp">SignUp</a></li>
						<li class="nav-item"><a class="nav-link" id="signinLink"
							data-bs-toggle="modal" data-bs-target="#loginModal">SignIn</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" id="signOutBtn"
							onclick="signOut()">SignOut</a></li>
						<li class="nav-item"><a class="nav-link" id="mypageLink"
							href="${root}/go/myPage">MyPage</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${root}/go/map">실거래가 조회</a>
								<a class="dropdown-item" href="${root}/go/interestapart">관심
									아파트</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="${root}/kakaomap.jsp">환경 관련
									정보</a>
							</div></li>
					</c:otherwise>
				</c:choose>

			</ul>
			<form class="d-flex">
				<input class="form-control me-sm-2" type="text" placeholder="Search">
				<button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</div>
</nav>

<!-- 로그인 Modal -->
<div class="modal fade" id="loginModal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">SignIn</h4>
				<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form class="row g-3" id="form-login">
					<div class="col-12">
						<label for="loginid" class="form-label">Your Id</label> <input
							type="text" class="form-control" id="loginid" name="user_id"
							placeholder="Please enter your id">
					</div>
					<div class="col-12">
						<label for="loginpwd" class="form-label">Your Password</label> <input
							type="password" class="form-control" id="loginpwd"
							name="user_password" placeholder="Please enter your password">
					</div>
					<div class="col-12">
						<button type="button" class="btn btn-primary"
							data-bs-dismiss="modal" id="signInBtn" onclick="signIn()">로그인</button>
						<input type="button" class="btn btn-info" id="findpwd"
							onclick="location.href='${root}/go/findPwd'"
							data-bs-dismiss="modal" value="비밀번호 찾기">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script>
	async function signIn() {
		let url = "${root}/user?user_id="+loginid.value+"&user_password="+loginpwd.value;
		console.log(url);
		
		//로그인 모달창 초기화 
		document.getElementById("form-login").reset();
		
		let response = await fetch(url);
		if(response.ok) {
			location.href ="${root}/";
		} else {
			alert("존재하지 않는 회원입니다.");
		}
	}
	
	async function signOut() {
		if (confirm("로그아웃 하시겠습니까?")) {
			alert("로그아웃 하였습니다.");
			let url = "${root}/user/signOut";
			let response = await fetch(url);
			if(response.ok) {
				location.href="${root}/";
			}
		}
	}
</script>

```

- src/main/java/com/ssafy/house/controller/PageController.java

```java
package com.ssafy.house.controller;
//..

@Controller
@RequestMapping(value = "/go")
@Slf4j
public class PageController {
    // 로그아웃 -> 로그아웃 -> 세션 삭제 및 메인페이지 이동
    @GetMapping(value = "/signOut")
    public String signOut(HttpSession session) {
        log.debug("signOut() 메소드 요청");
        session.removeAttribute("userId");
        return "redirect:/";
    }
}
```

- src/main/java/com/ssafy/house/UserController.java

```java
//UserController.java
package com.ssafy.house.controller;

//..

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	// 로그인 => 회원인지 체크 후 세션 처리
	@ResponseBody
	@GetMapping
	public ResponseEntity<?> signIn(Model model, HttpSession session, User user) throws SQLException {
		log.debug("signIn() 메소드 요청");
		log.debug("id : {} pwd : {}", user.getUser_id(), user.getUser_password());

		boolean check = userService.signIn(user);

		if (check) {
			session.setAttribute("userId", user.getUser_id());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {

			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 로그아웃 -> 로그아웃 -> 세션 삭제 및 메인페이지 이동
	@ResponseBody
	@GetMapping(value = "/signOut")
	public ResponseEntity<?> signOut(HttpSession session) {
		log.debug("signOut() 메소드 요청");
		session.removeAttribute("userId");
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
```

- src/main/java/com/ssafy/model/service/UserServiceImpl.java

```java
//UserServiceImpl.java
package com.ssafy.house.model.service;
//..

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public boolean signIn(User user) throws SQLException {
		int cnt = userMapper.signIn(user);
		if(cnt == 1) return true;
		else return false;
	}
}
```

- src/main/java/com/ssafy/model/mapper/UserMapper.java

```java
//UserMapper.java
package com.ssafy.house.model.mapper;

//..

public interface UserMapper {
	int signIn(User user) throws SQLException;
}
```

- src/main/resources/mapper/user.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ssafy.house.model.mapper.UserMapper">
	
	<!-- 로그인 체크 -->
	<select id="signIn" resultType="int" parameterType="user">
		select count(*)
		from user
		where user_id = #{user_id} and user_password = #{user_password}
	</select>
</mapper>
```

### 로그인

- 동작원리
    1. navBar에 있는 signIn 버튼을 통해 로그인 modal 창으로 이동합니다. 
    2. 로그인 modal 창에서 로그인 버튼을 클릭했을 때 아래의 일들이 발생합니다. 
        1. UserController에서 user객체를 넘겨받아 UserServiceImpl.signIn(user)를 호출합니다. 
        2. UserServiceImpl에서 넘겨받은 user객체를 가지고 UserMapper.signIn(user)를 호출합니다. 
        3. UserMapper.java에서 UserServiceImpl에서 호출된 메서드를 user.xml로 전달합니다.
        4. user.xml에서 DB로 하여금 user 객체를 통해 user 값을 검색하고 그 개수를 int 형태로 반환합니다. 
        5. UserServiceImpl에서 해당 반환값이 1일 경우 true, 1이 아닐 경우 false를 반환합니다. 
        6. UserController에서 해당 반환값에 따라 아래의 일을 발생시킵니다. 
            1. true일 경우 
                1. 세션을 생성한 후, session에 userId 키로 user_id를 저장합니다. 
                2. 클라이언트에게 200번 코드를 반환합니다.
            2. false일 경우
                1. 클라이언트에서 500번 코드를 반환합니다. 
    3. 서버로부터 반환하는 값에 따라 성공했을 경우 메인페이지로, 실패했을 경우 “존재하지 않는 회원입니다.” 알림창을 띄웁니다. 

### 로그아웃

- 동작원리
    1. navBar에 있는 signOut 버튼을 통해 로그아웃이 진행됩니다. 이때 서버에서 아래와 같은 일들이 발생합니다. 
        1. UserController에서 session안에 들어있는 userId 값을 삭제하고 200번코드를 반환합니다. 
    2. 서버에서로 하여금 200번 코드가 반환되었다면 메인페이지로 이동합니다. 
    

### Diagram

![login.jpg](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/login.jpg)

### CODE4

> 아파트 실거래가 전체 조회, 상세 조회 및 관심 아파트 조회 기능
> 

---

- DongController

```java
// 시도 정보 가져오기
	@GetMapping("/sido")
	private ResponseEntity<?> getSidoName() throws SQLException, IOException {

		log.debug("getSidoName() 메소드 실행 ");

		try {
			List<Dong> list = dongService.getSidoName();
			
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Dong>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 구군 정보 가져오기
	@GetMapping("/gugun")
	private ResponseEntity<?> getGugunName(@RequestParam("regcode") String regcode) throws SQLException, IOException {

		log.debug("getGugunName() 메소드 실행 ");

		String sidoCode = regcode.split("\\*")[0];
		
		try {
			List<Dong> list = dongService.getGugunName(sidoCode);
			
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Dong>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 동 정보 가져오기
	@GetMapping("/dong")
	private ResponseEntity<?> getDongName(@RequestParam("regcode") String regcode) throws SQLException, IOException {

		log.debug("getDongName() 메소드 실행 ");

		String dongCode = regcode.split("\\*")[0];
		
		try {
			List<Dong> list = dongService.getDongName(dongCode);
			
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Dong>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}
```

- ApartController

```java
// 아파트 정보 불러오기
	@GetMapping("/apartInfo")
	public ResponseEntity<?> getApartInfo(@RequestParam("dongcode") String dongcode,
			@RequestParam("year") String year,
			@RequestParam("month") String month,
			HttpServletRequest request) {

		log.debug("getApartInfo() 메소드 실행 ");

		// getApartInfo 매개변수 위한 map
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("dongcode", dongcode);
		map1.put("year", year);
		map1.put("month", month);

		// 찜목록 가져오기
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("userId");

		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("user_id", user_id);

		try {
			List<Apart>[] list = new List[2];
			for (int i = 0; i < list.length; i++) {
				list[i]  = new ArrayList<Apart>();
			}

			// 전체 아파트 정보 
			list[0] = apartService.getApartInfo(map1);

			// 찜 목록 가져오기 
			list[1] = apartService.getMyApartInfo(map2);

			if(list[0] != null && !list[0].isEmpty()) {
				return new ResponseEntity<List<Apart>[]>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 관심 아파트 등록하기
	@PostMapping("/ckApart")
	public void addCkApart(@RequestBody String[] ckList, HttpServletRequest request) throws Exception {

		log.debug("addCkApart() 메소드 실행 ");

		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("userId");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);

		// 현재 찜 목록
		List<Apart> list = apartService.getMyApartInfo(map);
		
		System.out.println("현재 저장되어있는 찜 목록");
		for (Apart apart : list) {
			System.out.print(apart.getAptCode() + " : ");
		}
		System.out.println();
		
		System.out.println("내가 찜한 것까지 포함한 찜 목록");
		for (String apart : ckList) {
			System.out.print(apart + " : ");
		}
		System.out.println();
		

		// 아예 저장된 게 없으면 그냥 추가
		if(list.size()==0) {
			for (String ac : ckList) {
				HashMap<String, Object> map2 = new HashMap<String, Object>();
				map2.put("user_id", user_id);
				map2.put("ac", ac);
				
				int cnt = apartService.insertMyApart(map2);
			}
		}else {
			
			// 관심 지역 추가
			for (String ac : ckList) {
				boolean flag = false;
				for (Apart apart : list) {
					if(ac.equals(apart.getAptCode())) {
						// 현재 저장되어 있는 찜 목록에 내가 찜한 것 까지 포함한 찜 목록이 있다면 
						flag = true;
					}
				}
				if(!flag) {
					// 현재 저장된 찜 목록에 없는 것이므로 추가하기
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					map2.put("user_id", user_id);
					map2.put("ac", ac);
					
					System.out.println(ac+" 추가");
					int cnt = apartService.insertMyApart(map2);
				}
			}
			
			// 관심 지역 삭제
			for (Apart apart : list) {
				boolean flag = false;
				for (String ac : ckList) {
					if(ac.equals(apart.getAptCode())) {
						// 현재 저장되어 있는 찜 목록중에 내가 찜한 것 까지 포함한 찜 목록에 있다면
						flag = true;
					}
				}
				if(!flag) {
					// 현재 저장된 찜목록에 있는게 내가 찜한 목록에 없으므로 삭제
					HashMap<String, Object> map2 = new HashMap<String, Object>();
					map2.put("user_id", user_id);
					map2.put("ac", apart.getAptCode());

					System.out.println(apart.getAptCode()+" 삭제");
					int cnt = apartService.delMyApart(map2);
				}
			}
			
		}

	}
	
	// 관심 아파트 목록 불러오기
	@GetMapping
	public ResponseEntity<?> listMyApart(HttpSession session) throws Exception{
		log.debug("listMyApart() 메소드 실행 ");

		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String userId = (String) session.getAttribute("userId");
			map.put("user_id", userId);
			List<Apart> list = apartService.getMyApartInfo(map);
			
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<Apart>>(list, HttpStatus.OK);
			}else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return exceptionHandling(e);
		}
	}

	// 관심 아파트 삭제하기
	@DeleteMapping("{aptCode}")
	public void deleteMyApart(@PathVariable("aptCode") String aptCode, HttpSession session) throws Exception {
		
			log.debug("deleteMyApart() 메소드 실행"); 
		
			HashMap<String, Object> map = new HashMap<String, Object>();
			String userId = (String) session.getAttribute("userId");
			map.put("user_id", userId);
			map.put("ac", aptCode);
			
			apartService.delMyApart(map);
	}
```

- PageController

```java
// 카카오맵 페이지 이동
@GetMapping("/map")
public String goMap() {
	log.debug("goMap() 메소드 실행 ");
	return "map/kakaomap";
}

// 관심 아파트 목록으로 가기
@GetMapping("/interestapart")
public String goInterestApart() throws Exception{
	log.debug("goInterestApart() 메소드 실행");
	return "map/interest";
}
```

- apart.xml

```xml
<select id="getApartInfo" parameterType="map" resultType="apart">
	select hi.aptCode, hi.buildYear, hi.apartmentName, hi.dong, hd.floor, hd.dealAmount, hd.dealYear,hd.dealMonth, hd.area, hi.jibun, hi.lng, hi.lat 
	from houseinfo hi 
	join housedeal hd 
	on hi.aptCode = hd.aptCode  
	where left(hi.dongCode,8)=#{dongcode} and dealYear=#{year} and dealMonth=#{month}
</select>

<select id="getMyApartInfo" parameterType="map" resultType="apart">
	select distinct hi.aptCode, hi.buildYear, hi.apartmentName, hi.dong, hi.jibun 
	from houseinfo hi 
	join housedeal hd 
	on hi.aptCode = hd.aptCode
	where hi.aptCode in (select aptCode from myhouse where user_id = #{user_id})
</select>

<insert id="insertMyApart" parameterType="map">
	insert into myhouse (user_id, aptCode) values(#{user_id}, #{ac})
</insert>

<delete id="delMyApart" parameterType="map">
	delete from myhouse where aptCode = #{ac} and user_id = #{user_id}
</delete>
```

- dong.xml

```xml
<resultMap type="dong" id="donginfo">
	<result column="dongCode" property="dongCode" />
	<result column="sidoName" property="sidoName" />
	<result column="gugunName" property="gugunName" />
	<result column="dongName" property="dongName" />
</resultMap>

<!-- 시도 정보 가져오기 -->
<select id="getSidoName" resultMap="donginfo">
	select * from dongcode where right(dongCode,8) = '00000000'
</select>

<!-- 구군 정보 가져오기 -->
<select id="getGugunName" parameterType="String" resultMap="donginfo">
	select * from dongcode where left(dongcode,2) = ${sidocode} and dongName is null and gugunName is not null
</select>

<!-- 동 정보 가져오기 -->
<select id="getDongName" parameterType="String" resultMap="donginfo">
	select * from dongcode where left(dongcode,5) = ${dongcode} and dongName is not null
</select>
```

- kakaomap.jsp

```html
<body>
	<%@ include file="../include/nav.jsp"%>
	<div class="dark-bg section">
		<div class="container-fluid">
			<div class="row col-md-12 justify-content-center mb-2">
				<div class="form-group col-md-2">
					<select class="form-select bg-secondary text-light" id="sido">
						<option value="">시도선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-secondary text-light" id="gugun">
						<option value="">구군선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-secondary text-light" id="dong">
						<option value="">동선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-dark text-light" id="year"></select>
				</div>
				<div class="form-group col-md-2">
					<select class="form-select bg-dark text-light" id="month">
						<option value="">매매월선택</option>
					</select>
				</div>
				<div class="form-group col-md-2">
					<button type="button" id="list-btn" class="btn btn-outline-info">
						아파트매매정보가져오기</button>
				</div>
			</div>
		</div>
		<section class="main-container">
			<div class="container-fluid">
				<div class="row">
					<div class="main col-lg-12 order-lg-2 ml-xl-auto">
						<div class="col-12 justify-content-center" id="map"
							style="width: 100%; height: 700px; position: relative; overflow: hidden"></div>
						<!-- services 라이브러리 불러오기-->
						<script type="text/javascript"
							src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9f2f61f8fb619932f1da3bd45427cbcd"></script>
					</div>
					<aside class="left col-lg-4 order-lg-1">
						<div calss="sidebar">
							<div class="block clearfix" id="houseInfo">
								<h3 id="apart-title" style="display: none">거래정보</h3>
								<table id="apart" class="table table-hover text-center"
									style="display: none">

									<tr>
										<th onclick="">아파트이름</th>
										<th>법정동</th>
										<th>지번</th>
										<th>관심지역설정</th>
									</tr>
									<tbody id="aptlist"></tbody>
								</table>
								<button type="button" id="ck-btn" class="btn btn-outline-info"
									style="display: none;">관심 지역 확정</button>
								<h3 id="detail-title" style="display: none">아파트 상세 정보</h3>
								<button type="button" id="back-btn" class="btn btn-outline-info"
									style="display: none;">뒤로가기</button>
								<table id="detail" class="table table-hover text-center"
									style="display: none">
									<tr>
										<th>아파트이름</th>
										<th>건축년도</th>
										<th>층</th>
										<th>면적</th>
										<th>거래금액</th>
									</tr>
									<tbody id="detail-list"></tbody>
								</table>
							</div>
						</div>
					</aside>
				</div>
			</div>
		</section>
		<%@ include file="../include/footer.jsp"%>
	</div>
	 <script src="${root}/assets/js/kakaomap.js?ver=1"></script>
```

- interest.jsp

```xml
<body>
	<%@ include file="../include/nav.jsp"%>
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">
				관심 아파트
			</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<table class="table table-hover">
				<thead>
					<tr class="text-center">
						<th scope="col">아파트 이름</th>
						<th scope="col">설립 년도</th>
						<th scope="col">동</th>
						<th scope="col">지번</th>
						<th scope="col">관심해제</th>
					</tr>
				</thead>
				<tbody id="myApartList"></tbody>
			</table>
		</div>
		<div class="row">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" href="#">이전</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item"><a class="page-link" href="#">다음</a></li>
			</ul>
		</div>
	</div>
	
	<script>
	
		let boardroot = "/house/apart";
		
   	 	fetch(boardroot)
		.then(response => response.json())
    	.then(data=>makeList(data))   
    	
    	// 관심 아파트 목록 불러오기
   	 	function makeList(el) {
   	 		
	            let tbody = ``;
	            el.forEach(function (e) { // 사용자 목록 띄우기
	              tbody += `
		            	   <tr class="text-center" data-id="${"${e.aptCode}"}">
	            	  			<td class="text-start"> ${"${e.apartmentName}"}</td>
	            	  			<td>${"${e.buildYear}"}</td>
	        					<td>${"${e.dong}"}</td>
	        					<td>${"${e.jibun}"}</td>
	        					<td><button type="button" class="btn btn-primary"
	        						onclick="deleteMyApart(this);">
	        						삭제</button></td>
							</tr>
	    					`;
	            });
            document.querySelector("#myApartList").innerHTML = tbody;
        } 
   	 	
   		// 관심 아파트 삭제 
        function deleteMyApart(el){
            let id = el.parentNode.parentNode.getAttribute("data-id");
            console.log("id :", id)

            let config = {
              method : "DELETE",
              headers : {"Content-Type" : "application/json"}, 
            };

            console.log(boardroot+"/"+id)
            fetch(boardroot+"/"+id, config)  
            .then((response) => response.json()) 
            .then(goInterestApart())  
        }
   		
        function goInterestApart(){
			 location.href = "${root}/go/interestapart";
	      }
	</script> 
	
	<%@ include file="../include/footer.jsp"%>
</body>
```

- kakaomap.js

```jsx
///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
            let date = new Date();
            let apartList;
            let ckList;
            let root = "http://localhost:8080/house"
            window.onload = function () {

                let yearEl = document.querySelector("#year");
                let yearOpt = `<option value="">매매년도선택</option>`;
                let year = date.getFullYear();
                for (let i = year; i > year - 20; i--) {
                    yearOpt += `<option value="${i}">${i}년</option>`;
                }
                yearEl.innerHTML = yearOpt;

                // 브라우저가 열리면 시도정보 얻기.
                sendRequest("sido", "*00000000");
            };
            
            document.querySelector("#year").addEventListener("change", function () {
                let month = date.getMonth() + 1;
                let monthEl = document.querySelector("#month");
                let monthOpt = `<option value="">매매월선택</option>`;
                let yearSel = document.querySelector("#year");
                let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
                for (let i = 1; i < m; i++) {
                    monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
                }
                monthEl.innerHTML = monthOpt;
            });

            // 시도가 바뀌면 구군정보 얻기.
            document.querySelector("#sido").addEventListener("change", function () {
            	
            	console.log("선택된 인덱스 : "+this.selectedIndex)
            	
                if (this[this.selectedIndex].value) {
                	let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
                    sendRequest("gugun", regcode);
                } else {
                    initOption("gugun");
                    initOption("dong");
                }
            });

            // 구군이 바뀌면 동정보 얻기.
            document.querySelector("#gugun").addEventListener("change", function () {
                if (this[this.selectedIndex].value) {
                    let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
                    sendRequest("dong", regcode);
                } else {
                    initOption("dong");
                }
            });

            function sendRequest(selid, regcode) {
            	
            	const url = `${root}/dong/${selid}`;
            	
                fetch(`${url}?regcode=${regcode}`)
                    .then((response) => response.json())
                    .then((data) => addOption(selid, data));
            	
            }

            function addOption(selid, data) {
                let opt = ``;
                initOption(selid);
                switch (selid) {
                    case "sido":
                        opt += `<option value="">시도선택</option>`;
                        data.forEach(function (el) {
                            opt += `
                  <option value="${el.dongCode}">${el.sidoName}</option>
                  `;
                        });
                        break;
                    case "gugun":
                        opt += `<option value="">구군선택</option>`;
                        data.forEach(function (el) {
                            opt += `
                  <option value="${el.dongCode}">${el.gugunName}</option>
                  `;
                        });
                        break;
                    case "dong":
                        opt += `<option value="">동선택</option>`;
                        data.forEach(function (el) {
                            opt += `
                  <option value="${el.dongCode}">${el.dongName}</option>
                  `;
                        });
                }
                document.querySelector(`#${selid}`).innerHTML = opt;
            }

            function initOption(selid) {
                let options = document.querySelector(`#${selid}`);
                options.length = 0;
            }
            //뒤로가기 버튼 이벤트 핸들링
            document.querySelector("#back-btn").addEventListener("click",function(){
            	document.querySelector("#apart").setAttribute("style", "display:;");
            	document.querySelector("#apart-title").setAttribute("style", "display:;");
            	document.querySelector("#ck-btn").setAttribute("style", "display:;");
            	document.querySelector("#detail").setAttribute("style", "display: none;");
            	document.querySelector("#detail-title").setAttribute("style", "display: none;");
            	document.querySelector("#back-btn").setAttribute("style", "display: none;");
            	setMarkers();
            })
            //관심 확정 등록 버튼 이벤트 핸들링
            document.querySelector("#ck-btn").addEventListener("click",function(){
            	const url = `${root}/apart/ckApart`;
            	fetch(url,{
            		method:"post",
            		headers:{
            			"Content-Type":"application/json"
            		},
            		body:JSON.stringify(ckList)
            	})
//            	console.log("보내버리기");
            })
            // /////////////////////// 아파트 매매 정보 /////////////////////////
            document.querySelector("#list-btn").addEventListener("click", function () {

                let dongSel = document.querySelector("#dong");
                let dongCode= dongSel[dongSel.selectedIndex].value.substr(0,8);

                let yearSel = document.querySelector("#year");
                let year = yearSel[yearSel.selectedIndex].value;
                let monthSel = document.querySelector("#month");
                let month = monthSel[monthSel.selectedIndex].value;
                let dealYM = year + month;
                
                let url = `${root}/apart/apartInfo`;
                let queryParams = `dongcode=${dongCode}&year=${year}&month=${month}`;
                fetch(`${url}?${queryParams}`)
                    .then((response) => response.json())
                    .then((data) => makeList(data));
            });
            // 지도 그리기
            var container = document.getElementById("map");
            var options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 3,
            };
            var map = new kakao.maps.Map(container, options);

            function setCenter(lat,lng) {            
                // 이동할 위도 경도 위치를 생성합니다
                var moveLatLon = new kakao.maps.LatLng(parseFloat(lat), parseFloat(lng));
                
                // 지도 중심을 이동 시킵니다
                map.setCenter(moveLatLon);
            }
            function makeList(data) {
                document.querySelector("table").setAttribute("style", "display: ;");
                apartList = []
                ckList = []
                
                for(let ckApart of data[1]){
                	ckList.push(ckApart.aptCode);
                }
                
                
                for(let home of data[0]){
                	let aptCode = home.aptCode;
                	let apartName = home.apartmentName; // 아파트 이름
                	let apartDong = home.dong; // 법적동
                	let apartJibun = home.jibun; // 지번
                	let apartSize = home.area; // 면적
                	let apartFloor = home.floor; // 층
                	let apartBuildYear = home.buildYear; // 건축년도
                	let apartValue = home.dealamount; // 가격
                	let apartLat = home.lat; // 위도
                	let apartLng = home.lng; // 경도
                	// 아파트 / 법적동 / 지번 / 면적/ 층 / 건축년도 / 가격 / 위도 / 경도
                	apartList.push(apartName+"/"+apartDong + "/" + apartJibun + "/" + apartSize + "/" + apartFloor + "/" + apartBuildYear + "/" +apartValue + "/"+apartLat+"/"+apartLng+"/"+aptCode)	
    	        }
                
                // 매물 데이터가 없으면 알람 처리
                if(data.size!=0){
                	setMarkers();
                }else{
                	alert("해당 기간의 매물 정보가 없습니다!")
                }
            }

            function initTable1() {
                let tbody = document.querySelector("#aptlist");
                let len = tbody.rows.length;
                for (let i = len - 1; i >= 0; i--) {
                    tbody.deleteRow(i);
                }
            }
            function initTable2() {
                let tbody = document.querySelector("#detail-list");
                let len = tbody.rows.length;
                for (let i = len - 1; i >= 0; i--) {
                    tbody.deleteRow(i);
                }
            }
            var markers = [];
            function detailMarkers(){
            	
            }
            let apartDict;
            function setMarkers() { // 주소를 찾아 마커와 가격 표시하는 함수
            	apartDict={};
                document.querySelector("table").setAttribute("style", "display: ;");
                document.querySelector("#apart-title").setAttribute("style", "display: ;");
                document.querySelector("#ck-btn").setAttribute("style", "display: ;");
                let aptTbody = document.querySelector("#aptlist");
                let detailTbody = document.querySelector("#detail-list");
                initTable1();
                removeMarker();
                let fragment1 = document.createDocumentFragment();
 				let fragment2 = document.createDocumentFragment();
                let bounds = new kakao.maps.LatLngBounds();
                var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

                console.log("아파트 리스트 길이 : " + apartList.length);
                
                for (let i = 0; i < apartList.length; i++) {
                	// 아파트 / 법적동 / 지번 / 면적/ 층 / 건축년도 / 가격 / 위도 / 경도 /아파트코드
                	
                    let name = apartList[i].split("/")[0]; // 아파트 이름
                    let dong = apartList[i].split("/")[1]; // 법적동
                    let jibun = apartList[i].split("/")[2]; // 지번
                    let size = apartList[i].split("/")[3]; // 면적
                    let floor = apartList[i].split("/")[4]; // 층
                    let buildYear = apartList[i].split("/")[5]; // 건축 년도
                    let value = apartList[i].split("/")[6]; // 가격
                    let lat = apartList[i].split("/")[7]; // 위도
                    let lng = apartList[i].split("/")[8]; // 경도
                    let aptCode = apartList[i].split("/")[9]; // 아파트 코드
                    if(name in apartDict){
                		apartDict[name].push({'dong':dong,'jibun':jibun,'size' : size,'floor':floor,'buildYear':buildYear,'value': value,'latlng':[lat,lng]})
                	}else{
                		apartDict[name] = [{'dong':dong,'jibun':jibun,'size' : size,'floor':floor,'buildYear':buildYear,'value': value,'latlng':[lat,lng]}]
                		let itemE1 = getListItem1(name, dong, jibun,aptCode);
                		console.log(itemE1.firstChild)
                        	var coords = new kakao.maps.LatLng(parseFloat(lat),parseFloat(lng));
                        	marker = new kakao.maps.Marker({
                        		map: map,
                        		position: coords
                        	});
                        	markers.push(marker);
                        	bounds.extend(coords);
                        	
                        	(function (marker){
                        		kakao.maps.event.addListener(marker, 'mouseover', function () {
                                    var content = '<div style="padding:5px;z-index:1;">' + value + '</div>';
                                    infowindow.setContent(content);
                                    infowindow.open(map, marker);
                                });

                                // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
                                kakao.maps.event.addListener(marker, 'mouseout', function () {
                                    infowindow.close();
                                });

                                itemE1.onmouseover = function () { // 테이블 열에
    																// 마우스 올렸을때
    																// 가격 출력
                                    var content = '<div style="padding:5px;z-index:1;">' + value + '</div>';
                                    infowindow.setContent(content);
                                    infowindow.open(map, marker);
                                };
                                // 테이블 열에 마우스 때면 가격 출력표 없애기
                                itemE1.onmouseout = function () {
                                    infowindow.close();
                                };
                                // 테이블열 클릭시 부드럽게 이동
                                itemE1.firstChild.onclick = function () {
                                	// 컬럼 클릭시 없애고 상세 정보 출력
                                	document.querySelector("#apart").setAttribute("style", "display:none;");
                                	document.querySelector("#apart-title").setAttribute("style", "display:none;");
                                	document.querySelector("#ck-btn").setAttribute("style", "display:none;");
                                	document.querySelector("#detail").setAttribute("style", "display:;");
                                	document.querySelector("#detail-title").setAttribute("style", "display: ;");
                                	document.querySelector("#back-btn").setAttribute("style", "display: ;");
                                	console.log(name);
                                	detailMarkers(name);
                                }
                                itemE1.lastChild.onclick = function(){
                                	if(itemE1.lastChild.innerHTML == '<img src="'+ root +'/assets/img/star-filled.svg">'){ // 관심 아파트 해제
                                		ckList = ckList.filter(function(item){
                                			return item !== aptCode;
                                		});
                                    	itemE1.lastChild.innerHTML = '<img src="'+ root +'/assets/img/star.svg"/>'                                		
                                	}else{ // 관심 아파트 등록
                                		ckList.push(aptCode);
                                		
                                    	itemE1.lastChild.innerHTML = '<img src="'+ root +'/assets/img/star-filled.svg"/>'                                		
                                	}
                                	console.log("관심 아파트 등록 : "  + ckList);
                                }
                        	})(marker);
                        	fragment1.appendChild(itemE1);
                        }
                        aptTbody.appendChild(fragment1);
                        map.setBounds(bounds);
                	}

                           
            }
            
            function detailMarkers(name){
                let detailTbody = document.querySelector("#detail-list");
                removeMarker();
                initTable2();
 				let fragment2 = document.createDocumentFragment();
                let bounds = new kakao.maps.LatLngBounds();
                var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
                for (let aptName in apartDict){
                	if( name !== aptName) continue;
                	for(let dealInfo of apartDict[aptName]){
                		let itemE2 = getListItem2(aptName,dealInfo['buildYear'],dealInfo['floor'],dealInfo['size'],dealInfo['value']);
                		let value = dealInfo['value'];
                		let lat = dealInfo['latlng'][0];
                		let lng = dealInfo['latlng'][1];
                		var coords = new kakao.maps.LatLng(parseFloat(lat),parseFloat(lng));
                    	marker = new kakao.maps.Marker({
                    		map: map,
                    		position: coords
                    	});
                    	markers.push(marker);
                    	bounds.extend(coords);
                    	
                    	(function (marker,value){
                    		kakao.maps.event.addListener(marker, 'mouseover', function () {
                                var content = '<div style="padding:5px;z-index:1;">' + value + '</div>';
                                infowindow.setContent(content);
                                infowindow.open(map, marker);
                            });

                            // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
                            kakao.maps.event.addListener(marker, 'mouseout', function () {
                                infowindow.close();
                            });

                            itemE2.onmouseover = function () { // 테이블 열에
																// 마우스 올렸을때
																// 가격 출력
                                var content = '<div style="padding:5px;z-index:1;">' + value + '</div>';
                                infowindow.setContent(content);
                                infowindow.open(map, marker);
                            };
                            // 테이블 열에 마우스 때면 가격 출력표 없애기
                            itemE2.onmouseout = function () {
                                infowindow.close();
                            };
                            // 테이블열 클릭시 부드럽게 이동
                            itemE2.onclick = function () {
                                map.panTo(coords);
                            }
                    	})(marker,value);
                    	fragment2.appendChild(itemE2);
                    }
                    detailTbody.appendChild(fragment2);
                    map.setBounds(bounds);
            	}                     	
            }
            function getListItem1(name, dong, jibun,aptCode) {
                var tr = document.createElement("tr");
                itemStr = '<td class="item1Name">' + name + "</td>" +
                    "<td>" + dong + "</td>" +
                    "<td>" + jibun + "</td>"
                console.log("aptCode : " + aptCode);
                console.log("ckList : " + ckList);
                if(ckList.includes(aptCode)){
                	console.log(aptCode+"를 포함한다.");
                	itemCk = '<td class ="text-end">'+ '<img src="'+ root +'/assets/img/star-filled.svg"/>'+"</td>"
                }else{
                	console.log(aptCode+"를 포함하지 않는다..");
                	itemCk = '<td class ="text-end">'+ '<img src="'+ root +'/assets/img/star.svg"/>'+"</td>"
                }
                tr.innerHTML = itemStr+itemCk;
                tr.className = 'item1';

                return tr;

            }
            function getListItem2(name, buildYear, floor, size, value) {
                var tr = document.createElement("tr");
                itemStr = "<td>" + name + "</td>" +
                	"<td>" + buildYear + "</td>" +
                    "<td>" + floor + "</td>" +
                    "<td>" + size + "</td>" +
                    '<td class ="text-end">' + value + "</td>"
                tr.innerHTML = itemStr;
                tr.className = 'item2';
                return tr;

            }
            // 지도 위에 표시되고 있는 마커를 모두 제거합니다
            function removeMarker() {
                for (var i = 0; i < markers.length; i++) {
                    markers[i].setMap(null);
                }
                markers = [];
            }
            // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
            function makeOverListener(map, marker, infowindow) {
                return function () {
                    infowindow.open(map, marker);
                };
            }

            // 인포윈도우를 닫는 클로저를 만드는 함수입니다
            function makeOutListener(infowindow) {
                return function () {
                    infowindow.close();
                };
            }
```

### 아파트 검색 및 카카오맵 마커 찍기

- 동작원리
    - nav바에서 실거래가 조회 메뉴 클릭 시 PageController로 `${root}/go/map` 요청을 보냅니다.
    
    ---
    
    **PageController**
    
    - 페이지 컨트롤러를 통해 `map/kakaomap.jsp` 페이지로 이동합니다.
    
    **DongController / ApartController / kakaomap.js / kakaomap.jsp**
    
    - kakaomap.jsp 페이지가 로드되면 sendRequest 메소드를 호출하여 매개변수로 selid, regcode를 매개변수로 보내줍니다.
    - sendRequest 메소드에서 fetch를 통해 DongController로  `${root}/dong/${selid}?regcode=${regcode}` 요청을 보냅니다.
    - DongController로  response 되면 addOption 메소드를 호출하여 매개변수로 selid와 data를 보냅니다.
    - 초기에는 selid가 sido, regcode가 *00000000로 설정되어 sendRequest 메소드를 호출하여 아래와 동일한 로직을 수행하여 시도 카테고리에 data가 불러와집니다.
    - addOption메소드에서 selid가 sido, gugun, dong일 경우 구분해서 각각의 카테고리에 data를 불러옵니다.
    - 아파트 매매정보 가져오기 버튼 클릭 시 makeList메소드를 호출하며, 매개변수로 data를 넘겨줍니다.
    - makeList메소드에서 data를 꺼내 apartList에 해당 아파트 정보(data)를 담습니다.
    - 만약 data가 없을 경우 alert를 이용해 해당 매물 정보가 없음을 알리고, 있을 경우 setMarkers메소드를 호출합니다.
    - setMarkers메소드에서는 지도를 띄우고 좌측에 해당 apartList에 담겨있는 apart 정보를 띄우고, 해당 아파트가 있는 위치에 마커를 표시합니다.

### 아파트 찜 하기

- 동작원리
    - 아파트 찜기능은 kakaomap.jsp에서 시작합니다.
    - 가져온 아파트 정보에서 별모양을 클릭하고 관심지역 확정 버튼을 누르면 찜 목록에 추가됩니다.
    
    ---
    
    **ApartController / kakaomap.js / kakaomap.jsp**
    
    - ckList는 현재 로그인 된 계정의 관심 지역 목록을 담고 있는 리스트입니다.
    - 만약 관심 지역 확정 버튼을 클릭하면 fetch를 통해 ApartController에`${root}/apart/ckApart` 요청을 보냅니다.

### Diagram

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%201.png)

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%202.png)

## 실행화면

---

### 초기화면

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%203.png)

- navBar에 있는 SignIn 버튼을 통해 로그인을 진행할 수 있습니다.
- navBar에 있는 SignUp 버튼을 통해 회원가입을 진행할 수 있습니다.
- navBar에 있는 Notice 버튼을 통해 공지사항 게시판으로 넘어갈 수 있습니다.
- 실거래가 조회 버튼을 통해 아파트 실거래가 조회 페이지로 넘어갑니다.
- 로그인이 진행되었을 경우 SignIn, SignUp 버튼이 사라지고 MyPage, SignOut, Dropdown 버튼이 생성됩니다.

### 아파트 실거래가 조회 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%204.png)

- 시도, 구군, 동, 매매년도, 매매월 선택을 통해 아파트 거래정보를 불러올 수 있습니다.
- 마커에 마우스를 올리면 아파트 매매 거래 금액을 마커 위에 띄웁니다.
- 관심 지역 설정에서 별을 클릭하고 관심 지역 확정을 누를 수 있게 하여 찜 기능을 구현했습니다.

### 아파트 실거래가 조회 상세 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%205.png)

- 특정 아파트 이름을 클릭하면 해당 아파트 마커로 확대 되며 상세 정보를 조회할 수 있습니다.

### 관심 아파트 조회 기능

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%206.png)

- 관심 아파트 목록을 조회할 수 있습니다.
- 삭제 버튼을 눌러 찜목록에서 해제할 수 있게 하였습니다.

### 회원가입 화면

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%207.png)

- 이름, 아이디, 비밀번호 입력을 통해 회원가입을 진행할 수 있습니다.

### 로그인 화면

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%208.png)

- 아이디, 비밀번호 입력을 통해 로그인을 할 수 있습니다.

### 로그인을 성공했을 때

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%209.png)

- 로그인을 성공할 경우 로그인, 회원 가입 버튼이 사라지고 로그아웃, 정보수정 버튼이 보입니다.

### 로그아웃 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2010.png)

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2011.png)

- 로그아웃을 클릭할 경우 로그아웃, 정보수정 버튼이 사라지고 로그인, 회원가입 버튼이 나타납니다. 또한 메인페이지로 다시 되돌아옵니다.

### 회원 정보 조회 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2012.png)

- 현재 저장되어 있는 회원정보를 볼 수 있습니다.

### 회원 정보 편집 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2013.png)

- 이름, 패스워드 수정이 가능하며 ID는 수정이 불가능합니다.
- 회원 정보 수정 버튼 클릭을 통해 회원정보 수정이 가능합니다.

### 회원 정보 삭제 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2014.png)

- 회원정보 탈퇴 버튼을 통해 회원 정보 삭제가 가능합니다.
- 다만, 공지사항 게시판에 글이 남겨있을 경우 삭제가 불가능합니다.

### 회원 정보 삭제 완료 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2015.png)

- 삭제가 완료되면 로그아웃이 처리되며, 메인페이지로 돌아옵니다.

# 추가 기능

---

## WhereIsMyHome 비밀번호 찾기

---

### CODE1

> 비밀번호 찾기
> 
- src/main/webapp/WEB-INF/views/user/findpwd.jsp

```jsx
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="../include/head.jsp"%>

</head>
<body>
	<%@ include file="../include/nav.jsp"%>

	<main>
		<section class="vh-100" style="background-color: #eee;">
			<div class="container h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-lg-12 col-xl-11">
						<div class="card text-black" style="border-radius: 25px;">
							<div class="card-body p-md-5">
								<div class="row justify-content-center">
									<div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

										<p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4"
											id="title">비밀번호 찾기</p>
										<div>
											<form class="mx-1 mx-md-4" id="form-update" method="post"
												action="${root}/user/findPwd">

												<div class="d-flex flex-row align-items-center mb-4">
													<i class="fas fa-user fa-lg me-3 fa-fw"></i>
													<div class="form-outline flex-fill mb-0">
														<input type="text" id="id" name="searchId"
															class="form-control" /> <label class="form-label"
															for="form3Example1c">Your ID</label>
													</div>
												</div>

												<div class="d-flex flex-row align-items-center mb-4">
													<i class="fas fa-user fa-lg me-3 fa-fw"></i>
													<div class="form-outline flex-fill mb-0">
														<input type="text" id="name" name="searchName"
															class="form-control" /> <label class="form-label"
															for="form3Example1c">Your Name</label>
													</div>
												</div>
												<div class="d-flex flex-row justify-content-center mb-4">
													<button type="button" class="btn btn-primary btn-default"
														id="findBtn" onclick="findpwd()">비밀번호 찾기</button>
												</div>
												<div>
													<div class="d-flex flex-row align-items-center mb-4">
														<div id="result-password" class="form-outline flex-fill mb-0 text-center"></div>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>

	<%@ include file="../include/footer.jsp"%>
	<script>
		async function findpwd() {
			let url = "${root}/user/findPwd";
			let data = {
				"user_id" : id.value,
				"user_name" : document.getElementById("name").value,
			};
			let config = {
				method : "POST",
				headers : {
					"Content-Type" : "application/json"
				},
				body : JSON.stringify(data),
			}
			const text = document.getElementById("result-password");
			let response = await fetch(url, config);

			if (response.ok) {
				let result = await response.text();
				text.innerText = "비밀번호는 "+result+" 입니다.";
			} else {
				text.innerText = "해당 회원 정보가 존재하지 않습니다.";
			}
		}
	</script>
</body>

</html>
```

- src/main/java/com/ssafy/house/controller/PageController.java

```java
package com.ssafy.house.controller;
//..

@Controller
@RequestMapping(value = "/go")
@Slf4j
public class PageController {
    // UserController
    // 비밀번호 찾기 페이지 이동
    @GetMapping(value = "/findPwd")
    public String goFindPwd(Model model) {
        log.debug("goFindPwd() 메소드 요청");
        return "user/findpwd";
    }
}
```

- src/main/java/com/ssafy/house/controller/UserController.java

```java
package com.ssafy.house.controller;

//..

@Controller
@RequestMapping(value = "/user")
@Slf4j
public class UserController {
	@Autowired
	UserService userService;

	// 비밀번호 찾기
	@ResponseBody
	@PostMapping(value = "/findPwd")
	public ResponseEntity<?> findpwd(@RequestBody User user) throws SQLException {
		log.debug("findpwd() 메소드 요청");

		User selectUser = userService.selectUserInfo(user.getUser_id());
		log.debug("selectUser : {}", selectUser.toString());
		if (selectUser != null) {
			log.debug("user : {}", user.toString());
			if (selectUser.getUser_name().equals(user.getUser_name())) {
				return new ResponseEntity<String>(selectUser.getUser_password(), HttpStatus.OK);
			}
		}

		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
```

- src/main/java/com/ssafy/house/model/service/UserServiceImpl.java

```java
package com.ssafy.house.model.service;

//..
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public User selectUserInfo(String user_id) throws SQLException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user_id);
		
		return userMapper.selectUserInfo(map);
	}
}
```

- src/main/java/com/ssafy/house/model/mapper/UserMapper.java

```java
package com.ssafy.house.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ssafy.house.dto.User;

public interface UserMapper {
	User selectUserInfo(Map<String, Object> map) throws SQLException;
}
```

- src/main/resources/mapper/user.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
    "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.ssafy.house.model.mapper.UserMapper">
	<!-- 회원 정보 조회 -->
	<!-- 비밀번호 찾기 -->
	<select id="selectUserInfo" resultType="user" parameterType="HashMap">
		select * 
		from user
		where user_id = #{user_id}
	</select>
</mapper>
```

### 비밀번호 찾기

- 동작원리
    1. `pageController`를 통해 로그인 modal 창에 있는 비밀번호 찾기 버튼을 통해 `findpwd.jsp` 페이지에 접근합니다. 
    2. `findpwd.jsp`에서 아이디, 이름 입력을 통해 비밀번호 찾기를 진행합니다. 
    3.  fetch를 통해서 아이디, 이름을 통해 DB로 비밀번호를 가져옵니다. 이때 아래의 일들이 발생합니다. 
        1. UserController에서 아이디, 이름을 Map으로 넘겨받아 id를 추출합니다. 이 값을 통해 UserServiceImpl.selectUserInfo(id)를 호출합니다. 
        2. UserServiceImpl에서 id 값을 넘겨받아 Map형태로 바꾼 다음,  UserMapper.selectUserInfo(map)를 호출합니다. 
        3. UserMapper.java에서 UserServiceImpl에서 호출된 메서드를 user.xml로 전달합니다. 
        4. user.xml에서 DB로부터 id 값을 통해 User 값을 검색한 후 User 객체를 반환합니다. 
        5. 최종적으로 UserController에서 User객체가 빈 객체가 아니라면 이름을 비교한 후 이름이 같다면 패스워드와 함께 200번코드를 반환합니다. 
        6. 만약 아니라면 500번코드를 반환합니다. 
    4. DB로 하여금 가져온 반환값을 통해 아래의 일들이 발생합니다. 
        1. 200번 코드를 반환할 경우 
            1. 비밀번호를 추출해 비밀번호를 화면에 rendering 해줍니다. 
        2. 500번 코드를 반환할 경우 
            1. “해당 회원 정보가 존재하지 않습니다.”를 화면에 rendering 해줍니다. 

### Diagram

![findpwd.jpg](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/findpwd.jpg)

## 실행화면

---

### 비밀번호 찾기

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2016.png)

- 로그인 모달 창에 있는 비밀번호 찾기를 통해 비밀번호 찾기 페이지에 이동할 수 있습니다.

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2017.png)

- 이름과 아이디를 입력한 후 비밀번호 찾기 버튼을 통해 비밀번호를 찾을 수 있습니다.

### 비밀번호 결과 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2018.png)

# 심화 기능

---

## WhereIsMyHome 공지사항 화면 구현

---

### CODE1

> 공지사항 등록
> 
- BoardController

```java
// 글 작성하기
	@PostMapping
	public void writeArticle(HttpSession session, @RequestBody Board board) throws Exception{

		log.debug("writeArticle() 메소드 실행 ");

		String userId = (String) session.getAttribute("userId");
		board.setUserId(userId);

		int cnt = boardService.writeArticle(board);
	}
```

- PageController

```java
// 글 쓰기 페이지 이동 
@GetMapping("/boardwrite")
public String goBoardWrite() {
    log.debug("goBoardWrite() 메소드 실행 ");
    return "board/write";
}
```

- board.xml

```xml
<!-- 글 쓰기 -->
<insert id="writeArticle" parameterType="board" >
		insert into
		board (user_id, subject, content, hit, register_time)
		values
		(#{userId}, #{subject}, #{content}, 0, now())
</insert>
```

- write.jsp

```html
<body>
	<%@ include file="../include/nav.jsp"%>
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">글쓰기</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<form id="form-register" method="POST" action="">
				<div class="mb-3">
					<label for="subject" class="form-label">제목 : </label> <input
						type="text" class="form-control" id="subject" name="subject"
						placeholder="제목..." />
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용 : </label>
					<textarea class="form-control" id="content" name="content" rows="7"></textarea>
				</div>
				<div class="col-auto text-center">
					<button type="button" id="btn-register"
						class="btn btn-outline-primary mb-3" onclick="writeArticle(this);">글작성</button>
					<button type="button" id="btn-list"
						class="btn btn-outline-danger mb-3">목록으로이동...</button>
				</div>
			</form>
		</div>
	</div>
	</div>

	<script>
	const boardroot = "/house/board"
	
	function writeArticle(el){
		if (!document.querySelector("#subject").value) {
	          alert("제목 입력!!");
	          return;
	        } else if (!document.querySelector("#content").value) {
	          alert("내용 입력!!");
	          return;
	        } else {
	        	
	        	let object = {
		          subject : el.parentNode.parentNode.childNodes[1].childNodes[3].value,
		          content : el.parentNode.parentNode.childNodes[3].childNodes[3].value,
		        }; 

        	 	let config = {
    	          method : "POST",
    	          headers : {"Content-Type" : "application/json"},
    	          body : JSON.stringify(object), 
    	        };

     	        fetch(boardroot+"/", config) 
     	          .then((response) => response.json()) 
     	          .then(goList()); 
        }
	}
      
      document.querySelector("#btn-list").addEventListener("click", function () {
    	if(confirm("취소를 하시면 작성한 글은 삭제됩니다.\n취소하시겠습니까?")) {
    		goList();
   	    }
      });
      
      function goList(){
			 location.href = "${root}/go/boardlist?key=&word=";
	   }
    </script>
	<%@ include file="../include/footer.jsp"%>
</body>
```

### 공지사항 등록

- 동작원리
    - 공지사항 등록은 `board/list.jsp` 화면에서 시작합니다.
    - 로그인이 되어있어야 합니다.
    - 글 작성 버튼을 누르면 `${root}/go/boardwrite`로 PageController에 요청이 갑니다.
    
    ---
    
    **PageController**
    
    - 페이지 컨트롤러를 통해 `board/write.jsp` 페이지로 이동합니다.
    
    **BoardController / write.jsp**
    
    - write.jsp에서 글 작성 버튼을 클릭 시 fetch를 통해 `/house/board/` 로 POST mapping 합니다.
    - BoardController로 부터 response 되면 write.jsp에서 goList() 메소드를 호출하여  `${root}/go/boardlist?key=&word=`로 이동합니다.

### CODE2

> 공지사항 수정
> 
- BoardController

```java
// 글 수정하기
	@PutMapping
	public void modifyArticle(@RequestBody Board board) throws Exception{

		log.debug("modifyArticle() 메소드 실행 ");

		boardService.modifyArticle(board);
	}
```

- PageController

```java
// 글 수정페이지로 가기
@GetMapping("/boardmodify")
public String goBoardModify(@RequestParam("articleNo") int articleNo, Model model) throws Exception {
    log.debug("goBoardModify() 메소드 실행 ");
    model.addAttribute("articleNo", articleNo);
    return "board/modify";
}
```

- board.xml

```xml
<!-- 글 수정 -->
<update id="modifyArticle" parameterType="board">
	update board set
	subject = #{subject}, content = #{content}
	where article_no =
	#{articleNo}
</update>
```

- modify.jsp

```html
<body>
	<%@ include file="../include/nav.jsp"%>
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">글수정</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<form id="form-modify" method="POST" action="">
				<input type="hidden" id="article_no" name="articleNo"
					value="">
				<div class="mb-3">
					<label for="subject" class="form-label">제목 : </label> <input
						type="text" class="form-control" id="subject" name="subject"
						value="" />
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용 : </label>
					<textarea class="form-control" id="content" name="content" rows="7"></textarea>
				</div>
				<div class="col-auto text-center">
					<button type="button" class="btn btn-outline-primary mb-3"
						id="article_modbtn" >글수정</button>
					<button type="button" id="btn-list"
						class="btn btn-outline-danger mb-3">목록으로이동...</button>
				</div>
			</form>
		</div>
	</div>
	</div>
	<script>
		const boardroot = "/house/board";
		let object;
		
		let uid = '<%=(String) session.getAttribute("userId")%>';
		
		 fetch(boardroot+"/"+${articleNo})
		.then((response) => response.json())
		.then((data)=>{
			document.querySelector("#subject").setAttribute("value", data.subject);
			document.querySelector("#content").innerText = data.content;
			
			makeObject(data)});
			
		function makeObject(el){
			object = {
		          articleNo: ${articleNo},
		          userId : uid,
		          subject : el.subject,
		          content : el.content,
		          hit : el.hit,
		          registerTime : el.registerTime, 
	        }; 
		}
		
	
	
		document.querySelector("#article_modbtn").addEventListener(
				"click",
				function() {
					 if (!document.querySelector("#subject").value) {
						alert("제목 입력!!");
						return;
					} else if (!document.querySelector("#content").value) {
						alert("내용 입력!!");
						return;
					} else {
					
						object.subject = document.querySelector("#subject").value;
						object.content = document.querySelector("#content").value;
						
						let config = {
				          method : "PUT",
				          headers : {"Content-Type" : "application/json"},
				          body : JSON.stringify(object), 
				        };

				        fetch(boardroot+"/", config) 
				          .then((response) => response.json()) 
				          .then(goDetail());
					} 
		}); 
		
		document.querySelector("#btn-list").addEventListener(
				"click",
				function() {
					if (confirm("취소를 하시면 작성중인 글은 삭제됩니다.\n취소하시겠습니까?")) {
						goList();
					}
				});

		function goDetail(){
			 location.href = "${root}/go/boarddetail?articleNo=${articleNo}";
	      }
		
		function goList(){
			 location.href = "${root}/go/boardlist?key=&word=";
	      }
	</script>
	<%@ include file="../include/footer.jsp"%>
</body>
```

### 공지사항 수정

- 동작원리
    - 공지사항 수정은 `board/detail.jsp` 화면에서 시작합니다.
    - 본인 글이어야 수정할 수 있습니다.
    - 글 수정 버튼을 누르면 `${root}/go/boardmodify`로 PageController에 요청이 갑니다.
    
    ---
    
    **PageController**
    
    - 페이지 컨트롤러를 통해 `board/modify.jsp` 페이지로 이동합니다.
    
    **BoardController / modify.jsp**
    
    - modify.jsp에서 글 수정 버튼을 클릭 시 fetch를 통해 `/house/board/` 로 PUT mapping 합니다.
    - BoardController로 부터 response 되면 detail.jsp에서 goDetail() 메소드를 호출하여  `${root}/go/boarddetail?articleNo=${articleNo}`로 이동합니다.

### CODE3

> 공지사항 삭제
> 
- BoardController

```java
// 글 삭제하기
@DeleteMapping("{articleNo}")
public void deleteArticle(@PathVariable("articleNo") String articleNo) throws Exception{

	log.debug("deleteArticle() 메소드 실행 ");

	HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("articleNo", articleNo);

	boardService.deleteArticle(map);

}
```

- board.xml

```xml
<!-- 글 삭제 -->
<delete id="deleteArticle" parameterType="map">
	delete from board
	where
	article_no = #{articleNo}
</delete>
```

- detail.jsp

```html
<body>
	<%@ include file="../include/nav.jsp"%>
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">글 상세 조회</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<div class="row my-2">
				<h2 class="text-secondary px-5" id="article_top"></h2>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="clearfix align-content-center">
						<img class="avatar me-2 float-md-start bg-light p-2"
							src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
						<p>
							<span class="fw-bold" id="article_id"></span> <br /> <span
								class="text-secondary fw-light" id="article_info"> </span>
						</p>
					</div>
				</div>
				<div class="col-md-4 align-self-center text-end">댓글 : 17</div>
				<div class="divider mb-3"></div>
				<div class="text-secondary" id="article_content"></div>
				<div class="divider mt-3 mb-3"></div>
				<div class="d-flex justify-content-end">
					<button type="button" id="btn-list"
						class="btn btn-outline-primary mb-3" onclick="goList();">글목록</button>
					<button type="button"
							class="btn btn-outline-success mb-3 ms-1" id="article_modbtn" style="display: none;"  >글수정</button>
					<button type="button"
							class="btn btn-outline-danger mb-3 ms-1" id="article_delbtn"  style="display: none;" >글삭제</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	<form id="form-param" method="get" action="${root}/board/list">
		<input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
		<input type="hidden" id="key" name="key" value="${param.key}">
		<input type="hidden" id="word" name="word" value="${param.word}">
	</form>
	<form id="form-no-param" method="get" action="${root}/board/list">
		<input type="hidden" id="npgno" name="pgno" value="${param.pgno}">
		<input type="hidden" id="nkey" name="key" value="${param.key}">
		<input type="hidden" id="nword" name="word" value="${param.word}">
		<input type="hidden" id="articleno" name="articleNo"
			value="${article.articleNo}">
	</form>
	<script>
		const boardroot = "/house/board";
		
		let uid = '<%=(String)session.getAttribute("userId")%>';
		
		 fetch(boardroot+"/"+${articleNo})
		.then((response) => response.json())
		.then((data)=>{
			document.querySelector("#article_top").innerText = data.articleNo + "." + data.subject; 
			document.querySelector("#article_id").innerText = data.userId; 
			document.querySelector("#article_info").innerText = data.registerTime + " 조회:" + data.hit; 
			document.querySelector("#article_content").innerText = data.content; 
			
			if(data.userId == uid){
				document.querySelector("#article_modbtn").setAttribute("style", "display:inline-block;");
		        document.querySelector("#article_delbtn").setAttribute("style", "display:inline-block;");
			} 
			
		});
	
		 // 글 수정 버튼 클릭 시 수정 페이지로 이동
		document.querySelector("#article_modbtn").addEventListener("click",
				function() {
					location.href = "${root}/go/boardmodify?articleNo=${articleNo}";
		});
		

		document.querySelector("#article_delbtn").addEventListener("click",
				function() {
					if (confirm("정말 삭제하시겠습니까?")) {
						
						let config = {
							method : "DELETE",
							headers : {"Content-Type" : "application/json"},
						};
						
						fetch(boardroot+"/"+${articleNo}, config)
						.then(goList());  
					}
				}); 
		
		 function goList(){
			 location.href = "${root}/go/boardlist?key=&word=";
	      }
	</script>
	<%@ include file="../include/footer.jsp"%>
</body>
```

### 공지사항 삭제

- 동작원리
    - 공지사항 삭제는 `board/detail.jsp` 화면에서 시작합니다.
    - 본인 글이어야 삭제할 수 있습니다.
    
    ---
    
    **BoardController / modify.jsp**
    
    - 글 삭제 버튼을 누르면 confirm창에서 응답 값이 true일 경우 fetch를 통해 `/house/board/{articleNo}` 로 DELETE mapping 합니다.
    - BoardController로 부터 response 되면 detail.jsp에서 goList() 메소드를 호출하여  `${root}/go/boardlist?key=&word=`로 이동합니다.

### CODE4

> 공지사항 조회
> 
- BoardController

```java
// 글 목록 불러오기
	@GetMapping
	public ResponseEntity<?> listArticle(
			@RequestParam("key") String key, @RequestParam("word") String word) throws Exception{
		log.debug("listArticle() 메소드 실행 ");

		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("key", key);
			map.put("word", word);
			List<Board> list = boardService.listArticle(map);
			
			log.debug("key는 {}", key);
			log.debug("word는 {}", word);
			log.debug("list는 {}", list);
			
			
			log.debug("목록 데이터 :{}",list.toString());
			if(list != null && !list.isEmpty()) {
				//	목록을 얻어왔거나, 얻어왔는데 비어있지 않다면
				return new ResponseEntity<List<Board>>(list, HttpStatus.OK);
				// list가 OK와 함께 넘어감
			}else {
				// 값이 없다.
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				// 값이 없으니 Void 타입(객체니까V)
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 서버쪽에서 에러가 났다
			return exceptionHandling(e);
		}
	}
```

- PageController

```java
// 글 목록으로 가기
@GetMapping("/boardlist")
public String goBoardList(@RequestParam("key") String key, @RequestParam("word") String word, Model model) {
    log.debug("goBoardList() 메소드 실행 ");
    model.addAttribute("key", key);
    model.addAttribute("word", word);
    
    log.debug("key 값 {}", key);
    log.debug("word 값  {}", word);
    
    return "board/list";
}
```

- board.xml

```xml
<resultMap type="board" id="article">
	<result column="article_no" property="articleNo" />
	<result column="user_id" property="userId" />
	<result column="user_name" property="userName" />
	<result column="subject" property="subject" />
	<result column="content" property="content" />
	<result column="hit" property="hit" />
	<result column="register_time" property="registerTime" />
</resultMap>

<!-- 목록 불러오기 -->
<select id="listArticle" resultMap="article" parameterType="map">
	select article_no, user_id,
	subject, content, hit, register_time
	from board
	<if test="word != null and word != '' and key != null and key != ''">
		<where>
			<choose>
				<when test="key == 'subject'">subject like concat('%',#{word},'%')</when>
				<otherwise>user_id = #{word}</otherwise>
			</choose>
		</where>
	</if>
</select>
```

- list.jsp

```html
<body>
	<%@ include file="../include/nav.jsp"%>
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h2 class="my-3 py-3 shadow-sm bg-light text-center">공지사항</h2>
		</div>
		<div class="col-lg-8 col-md-10 col-sm-12">
			<div class="row align-self-center mb-2">
				<div class="col-md-2 text-start">
					<button type="button" id="btn-mv-register"
						class="btn btn-outline-primary btn-sm">글쓰기</button>
				</div>
				<div class="col-md-7 offset-3">
					<form class="d-flex" id="form-search" action="${root}/board/list">
						<select class="form-select form-select-sm ms-5 me-1 w-50"
							name="key" aria-label="검색조건" id="select_search">
							<option value=""
								<c:if test ="${empty key or key == ''}">selected="selected"</c:if>>검색조건</option>
							<option value="subject"
								<c:if test ="${key == 'subject'}">selected="selected"</c:if>>제목</option>
							<option value="userid"
								<c:if test ="${key == 'userid'}">selected="selected"</c:if>>작성자</option>
						</select>
						<div class="input-group input-group-sm">
							<input id="keyword" type="text" class="form-control" name="word"
								placeholder="검색어..."
								<c:if test ="${!empty word}">value="${word}"</c:if> />
							<button id="btn-search" class="btn btn-dark" type="button">검색</button>
						</div>
					</form>
				</div>
			</div>
			<table class="table table-hover">
				<thead>
					<tr class="text-center">
						<th scope="col">글번호</th>
						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">조회수</th>
						<th scope="col">작성일</th>
					</tr>
				</thead>
				<tbody id="boardList"></tbody>
			</table>
		</div>
		<div class="row">
			<ul class="pagination justify-content-center">
				<li class="page-item"><a class="page-link" href="#">이전</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item"><a class="page-link" href="#">다음</a></li>
			</ul>
		</div>
	</div>
	<form id="form-no-param" method="get" action="${root}/board">
		<input type="hidden" id="pgno" name="pgno" value="${param.pgno}">
		<input type="hidden" id="key" name="key" value="${param.key}">
		<input type="hidden" id="word" name="word" value="${param.word}">
		<input type="hidden" id="articleno" name="articleNo" value="">
	</form>
	<script>
	
		let boardroot = "/house/board";
		
   	 	fetch(boardroot+"?key=${key}&word=${word}")
		.then(response => response.json())
    	.then(data=>makeList(data))  
    		
   	 	function makeList(el) {
   	 		
	            let tbody = ``;
	            el.forEach(function (e) { // 사용자 목록 띄우기
	            	console.log(e.articleNo);
	              tbody += `
		            	   <tr class="text-center">
								<th scope="row">${"${e.articleNo}"}</th>
								<td class="text-start">
								<a href="/house/go/boarddetail?articleNo=${'${e.articleNo}'}"
									class="article-title link-dark" data-no=${"${e.articleNo}"}
									style="text-decoration: none">
									${"${e.subject}"}</a></td>
								<td>${"${e.userId}"}</td>
								<td>${"${e.hit}"}</td>
								<td>${"${e.registerTime}"}</td>
							</tr>
	    					`;
	            });
            document.querySelector("#boardList").innerHTML = tbody;
        } 
		
	
		let titles = document.querySelectorAll(".article-title");
		titles.forEach(function(title) {
			title.addEventListener("click", function() {
				//document.querySelector("#articleno").value = this.getAttribute("data-no");
				document.querySelector("#form-no-param").submit();
			});
		});

		document.querySelector("#btn-mv-register").addEventListener("click",
				function() {
					location.href = "${root}/go/boardwrite";
				});

		// 검색 버튼 클릭시
		document.querySelector("#btn-search").addEventListener("click",
				function() {
					let sel = document.querySelector("#select_search");
					let key = sel.options[sel.selectedIndex].value;
					let word = document.querySelector("#keyword").value;
					
					console.log("key값 ; " + key)
					console.log("value값 ; " + word)
					
					location.href = `${root}/go/boardlist?key=${'${key}'}&word=${'${word}'}`;
				}); 
		
		
				
	</script> 
	<%@ include file="../include/footer.jsp"%>
</body>
```

### 공지사항 조회

- 동작원리
    - 공지사항 조회는 nav바에서 Notice 클릭 시 PageController를 거쳐 list.jsp로 이동하게 됩니다.
    - nav바에서 `${root}/go/boardlist?key=&word=` 요청을 보냅니다.
    
    ---
    
    **PageController**
    
    - 페이지 컨트롤러를 통해 `board/list.jsp` 페이지로 이동합니다.
    
    **BoardController / list.jsp**
    
    - list.jsp 페이지가 로드 되면서 fetch를 통해 `/house/board`로 GET mapping 요청을 보냅니다.
    - BoardController로 부터 response 되면 **makeList()** 메소드를 호출하며 data와 함께 tbody변수에 해당 data를 보여줄 html코드를 작성하여, 목록을 띄울 위치인 #boardList에 innerHtml 합니다.
    - 만약 검색을 할 경우 검색 카테고리를 정하고, 키워드를 입력후 검색 버튼을 클릭하면 fetch를 통해  `${root}/go/boardlist?key="카테고리"&word="검색어”`  로 GET mapping 요청을 보냅니다.

### CODE5

> 공지사항 상세 조회
> 
- BoardController

```java
// 글 상세 보기
	@GetMapping("{articleNo}")
	public ResponseEntity<?> getArticle(@PathVariable("articleNo") String articleNo) throws Exception{

		log.debug("getArticle() 메소드 실행 ");

		
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("articleNo", Integer.parseInt(articleNo));

			// 조회수
			boardService.updateHit(map);

			//DB에서 글 가져오기
			Board board = boardService.getArticle(map);
			
			if(board != null) {
				//	목록을 얻어왔거나, 얻어왔는데 비어있지 않다면
				return new ResponseEntity<Board>(board, HttpStatus.OK);
				// list가 OK와 함께 넘어감
			}else {
				// 값이 없다.
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				// 값이 없으니 Void 타입(객체니까V)
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 서버쪽에서 에러가 났다
			return exceptionHandling(e);
		}
	}
```

- PageController

```java
// 글 상세페이지로 가기
@GetMapping("/boarddetail")
public String goBoardDetail(@RequestParam("articleNo") int articleNo, Model model) {
    log.debug("goBoardDetail() 메소드 실행");
    model.addAttribute("articleNo", articleNo);
    return "board/detail";
}
```

- board.xml

```xml
<!-- 글 보기 -->
<select id="getArticle" parameterType="map" resultMap="article">
	select
	article_no, user_id, subject, content, hit, register_time
	from board
	where article_no = #{articleNo}
</select>

<!-- 조회수 -->
<update id="updateHit" parameterType="map">
	update board
	set hit = hit +
	1
	where article_no = #{articleNo}
</update>
```

- detail.jsp ⇒ CODE3 코드 참고

### 공지사항 상세 조회

- 동작원리
    - 공지사항 상세 조회는  `board/list.jsp` 화면에서 시작합니다.
    - 글 제목 클릭 시 `/house/go/boarddetail?articleNo=${e.articleNo}`로 PageController에 페이지 요청을 보냅니다.
    
    ---
    
    **PageController**
    
    - 페이지 컨트롤러를 통해 `board/detail.jsp` 페이지로 이동합니다.
    
    **BoardController / detailjsp**
    
    - detail.jsp 페이지가 로드 되면서 fetch를 통해 `/house/board/{articleNo}`로 GET mapping 요청을 보냅니다.
    - BoardController로 부터 response되면 상세 페이지의 요소에 innerText하여 data를 넣어줍니다.
    - 만약 data의 userId가 현재 session에 로그인된 계정과 같으면 수정버튼과 삭제버튼의 display속성을 inline-block 처리해줍니다.

### Diagram

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2019.png)

## 실행화면

---

### 공지사항 목록 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2020.png)

- 작성한 글들을 조회할 수 있습니다.
- 글 번호, 제목, 작성자, 조회수, 작성일을 볼 수 있습니다.

### 공지사항 검색 기능

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2021.png)

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2022.png)

- 제목, 작성자별 검색 기능을 구현했습니다.

### 공지사항 글 보기

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2023.png)

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2024.png)

- 본인이 쓴 글에는 글수정, 글삭제 버튼이 보이게 했습니다.

### 글 수정 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2025.png)

### 글 삭제 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2026.png)

- 글 삭제 버튼 클릭시 삭제 의사를 묻는 confirm창에서 선택할 수 있습니다.

### 글 작성 페이지

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2027.png)

- 회원일 경우 글 작성이 가능하게 했습니다.
- 만약 제목, 내용을 입력 하지 않을 경우 alert 창을 띄워 글 작성을 막았습니다.

---

# Diagram

## ER Diagram

![Untitled](WhereIsMyHome_8_7_Spring%20e1a6c6df274544a09ed6d4638a69feef/Untitled%2028.png)