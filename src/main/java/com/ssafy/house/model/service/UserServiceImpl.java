package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.house.dto.User;
import com.ssafy.house.model.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public int insertUser(User user) throws SQLException {
		int cnt_user = userMapper.insertUser(user);
		int cnt_role = userMapper.insertRole(user);

		if (cnt_user == 1 && cnt_role == 1)
			return 1;
		else
			return 0;
	}

	@Override
	public boolean signIn(User user) throws SQLException {
		int cnt = userMapper.signIn(user);
		if (cnt == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<Map<String, String>> selectUserId(User user) throws SQLException {
		return userMapper.selectUserId(user);
	}

	@Override
	public User selectUserInfo(String user_id) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);

		return userMapper.selectUserInfo(map);
	}

	@Override
	public User selectUserInfoNoPassword(String user_id) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);

		return userMapper.selectUserInfoNoPassword(map);
	}

	@Override
	public int updateUser(User user) throws SQLException {
		return userMapper.updateUser(user);
	}

	@Override
	public int updatePassword(User user) throws SQLException {
		return userMapper.updatePassword(user);
	}

	@Override
	public int deleteUser(String user_id) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);

		int cnt = userMapper.deleteUser(map);
		return cnt;
	}

	@Override
	public int idCheck(String user_id) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", user_id);

		int cnt = userMapper.idCheck(map);
		return cnt;
	}

	@Override
	public String findPassword(User user) throws SQLException {
		// 임시 비밀번호 생성
		String random = RandomStringUtils.randomAlphanumeric(10);

		user.setUser_password(random);

		try {
			// 임시 비밀번호 설정
			userMapper.updatePassword(user);

			// 메일 발송
			Boolean result = sendEmail(user);

			if (result)
				return "이메일로 임시 비밀번호를 발송하였습니다.";
			else
				return "임시 비밀번호 생성 및 발송에 실패했습니다.";

		} catch (Exception e) {
			log.debug("임시 비밀번호 생성 및 발송 실패");
			return "임시 비밀번호 생성 및 발송에 실패했습니다.";
		}
	}

	@Override
	public Boolean sendEmail(User user) throws Exception {
		// Mail Server 설정
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "myhousecock";
		String hostSMTPpwd = "myhouse76";

		// 보내는 사람 Email, 제목, 내용
		String fromEmail = "myhousecock@naver.com";
		String fromName = "집콕";
		String subject = "집콕 임시 비밀번호입니다.";
		String msg = "";
		
		msg += "<h3 style=\"margin: 1em 0px; padding: 0px; display: block; font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: -0.5px; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-align: -webkit-center; background-color: rgb(255, 255, 255);\">\r\n" + 
				"  <span style=\"font-style: normal; font-family: Meiryo, sans-serif; color: rgb(162,123,92); font-weight: bold; background-color: rgba(0, 0, 0, 0); font-size: 18px; text-decoration: underline;\">\r\n" + 
				"    <strong style=\"font-family: Meiryo, sans-serif; color: rgb(162, 123, 92);\">집콕</strong>\r\n" + 
				"  </span>\r\n" + 
				"  <span style=\"font-style: normal;font-family: Meiryo, sans-serif;color: rgb(63, 78, 79);font-weight: bold;background-color: rgba(0, 0, 0, 0);\">\r\n" + 
				"    <strong style=\"font-style: normal;font-family: Meiryo, sans-serif;color: rgb(63,78,79);\">\r\n" + 
				"      <strong>의 임시&nbsp;비밀번호&nbsp;입니다.&nbsp;비밀번호를&nbsp;변경하여&nbsp;사용하세요.</strong>\r\n" + 
				"    </strong>\r\n" + 
				"  </span>\r\n" + 
				"  <strong style=\"font-style: normal; font-family: &quot;Malgun Gothic&quot;, &quot;맑은 고딕&quot;, -apple-system, BlinkMacSystemFont, system-ui, &quot;Apple SD Gothic Neo&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, Dotum, 돋움, sans-serif; color: rgb(0, 0, 0);\"></strong>\r\n" + 
				"</h3>\r\n" + 
				"<strong style=\"color: rgb(0, 0, 0);font-family: &quot;Malgun Gothic&quot;, &quot;맑은 고딕&quot;, -apple-system, BlinkMacSystemFont, system-ui, &quot;Apple SD Gothic Neo&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, Dotum, 돋움, sans-serif;font-size: 14px;font-style: normal;font-variant-ligatures: normal;font-variant-caps: normal;letter-spacing: -0.5px;orphans: 2;text-align: start;text-indent: 0px;text-transform: none;white-space: normal;widows: 2;word-spacing: 0px;-webkit-text-stroke-width: 0px;background-color: rgb(255, 255, 255);text-decoration-thickness: initial;text-decoration-style: initial;text-decoration-color: initial;\"></strong>\r\n" + 
				"<h3 style=\"margin: 1em 0px; padding: 0px; display: block; font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: -0.5px; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-align: -webkit-center; background-color: rgb(255, 255, 255);\"></h3>\r\n" + 
				"<div style=\"font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: -0.5px; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\">\r\n" + 
				"  <br>\r\n" + 
				"</div>\r\n" + 
				"<div style=\"font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: -0.5px; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-align: center;\">\r\n" + 
				"  <span style=\"color: rgb(0, 0, 0); background-color: rgba(0, 0, 0, 0); font-size: 14px; font-style: normal; font-weight: 400; font-family: &quot;Malgun Gothic&quot;, &quot;맑은 고딕&quot;, -apple-system, BlinkMacSystemFont, system-ui, &quot;Apple SD Gothic Neo&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, Dotum, 돋움, sans-serif;\">임시&nbsp;비밀번호&nbsp;:&nbsp;"+user.getUser_password()+"</span>\r\n" + 
				"</div>";

		// 받는 사람 E-mail 주소
		String toEmail = user.getUser_id();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); // 포트번호

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(toEmail, user.getUser_name());
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
			return true;
		} catch (Exception e) {
			log.debug("메일 발송 실패 : " + e);
			return false;
		}
	}
}
