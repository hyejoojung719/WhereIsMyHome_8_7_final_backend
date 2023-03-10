package com.ssafy.house.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
		// ?????? ???????????? ??????
		String random = RandomStringUtils.randomAlphanumeric(10);

		user.setUser_password(random);

		try {
			// ?????? ???????????? ??????
			userMapper.updatePassword(user);

			// ?????? ??????
			Boolean result = sendEmail(user);

			if (result)
				return "???????????? ?????? ??????????????? ?????????????????????.";
			else
				return "?????? ???????????? ?????? ??? ????????? ??????????????????.";

		} catch (Exception e) {
			log.debug("?????? ???????????? ?????? ??? ?????? ??????");
			return "?????? ???????????? ?????? ??? ????????? ??????????????????.";
		}
	}

	@Override
	public Boolean sendEmail(User user) throws Exception {
		// Mail Server ??????
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com";
		String hostSMTPid = "myhousecock";
		String hostSMTPpwd = "myhouse76";

		// ????????? ?????? Email, ??????, ??????
		String fromEmail = "myhousecock@naver.com";
		String fromName = "??????";
		String subject = "?????? ?????? ?????????????????????.";
		String msg = "";

		msg += "<h3 style=\"margin: 1em 0px; padding: 0px; display: block; font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: -0.5px; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-align: -webkit-center; background-color: rgb(255, 255, 255);\">\r\n"
				+ "  <span style=\"font-style: normal; font-family: Meiryo, sans-serif; color: rgb(162,123,92); font-weight: bold; background-color: rgba(0, 0, 0, 0); font-size: 18px; text-decoration: underline;\">\r\n"
				+ "    <strong style=\"font-family: Meiryo, sans-serif; color: rgb(162, 123, 92);\">??????</strong>\r\n"
				+ "  </span>\r\n"
				+ "  <span style=\"font-style: normal;font-family: Meiryo, sans-serif;color: rgb(63, 78, 79);font-weight: bold;background-color: rgba(0, 0, 0, 0);\">\r\n"
				+ "    <strong style=\"font-style: normal;font-family: Meiryo, sans-serif;color: rgb(63,78,79);\">\r\n"
				+ "      <strong>??? ??????&nbsp;????????????&nbsp;?????????.&nbsp;???????????????&nbsp;????????????&nbsp;???????????????.</strong>\r\n"
				+ "    </strong>\r\n" + "  </span>\r\n"
				+ "  <strong style=\"font-style: normal; font-family: &quot;Malgun Gothic&quot;, &quot;?????? ??????&quot;, -apple-system, BlinkMacSystemFont, system-ui, &quot;Apple SD Gothic Neo&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, Dotum, ??????, sans-serif; color: rgb(0, 0, 0);\"></strong>\r\n"
				+ "</h3>\r\n"
				+ "<strong style=\"color: rgb(0, 0, 0);font-family: &quot;Malgun Gothic&quot;, &quot;?????? ??????&quot;, -apple-system, BlinkMacSystemFont, system-ui, &quot;Apple SD Gothic Neo&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, Dotum, ??????, sans-serif;font-size: 14px;font-style: normal;font-variant-ligatures: normal;font-variant-caps: normal;letter-spacing: -0.5px;orphans: 2;text-align: start;text-indent: 0px;text-transform: none;white-space: normal;widows: 2;word-spacing: 0px;-webkit-text-stroke-width: 0px;background-color: rgb(255, 255, 255);text-decoration-thickness: initial;text-decoration-style: initial;text-decoration-color: initial;\"></strong>\r\n"
				+ "<h3 style=\"margin: 1em 0px; padding: 0px; display: block; font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: -0.5px; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-align: -webkit-center; background-color: rgb(255, 255, 255);\"></h3>\r\n"
				+ "<div style=\"font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: -0.5px; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\">\r\n"
				+ "  <br>\r\n" + "</div>\r\n"
				+ "<div style=\"font-variant-ligatures: normal; font-variant-caps: normal; letter-spacing: -0.5px; orphans: 2; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-align: center;\">\r\n"
				+ "  <span style=\"color: rgb(0, 0, 0); background-color: rgba(0, 0, 0, 0); font-size: 14px; font-style: normal; font-weight: 400; font-family: &quot;Malgun Gothic&quot;, &quot;?????? ??????&quot;, -apple-system, BlinkMacSystemFont, system-ui, &quot;Apple SD Gothic Neo&quot;, &quot;Helvetica Neue&quot;, Helvetica, Arial, Dotum, ??????, sans-serif;\">??????&nbsp;????????????&nbsp;:&nbsp;"
				+ user.getUser_password() + "</span>\r\n" + "</div>";

		// ?????? ?????? E-mail ??????
		String toEmail = user.getUser_id();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(587); // ????????????

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(toEmail, user.getUser_name());
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
			return true;
		} catch (Exception e) {
			log.debug("?????? ?????? ?????? : " + e);
			return false;
		}
	}

	@Override
	public List<User> selectUserListAll() throws SQLException {
		return userMapper.selectUserListAll();
	}

	@Override
	public int deleteUserList(List<User> userList) throws SQLException {
		int cnt = 0;
		for(User user : userList) {
			cnt += deleteUser(user.getUser_id());
		}
		return cnt;
	}

	@Override
	public User getAuthority(String user_id) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		return userMapper.selectUserRoleById(map);
	}
	
	
}
