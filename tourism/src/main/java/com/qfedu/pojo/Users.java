package com.qfedu.pojo;

// 
/**
 * 用户类
 */
public class Users {

	private String uuid;
	private String username;
	private String password;
	private String newPassword;
	private String nickname;
	private String gender;
	private String id;
	private String country;
	private String tel;
	private String email;
	private String birth;
	private String photo;
	private String token;
	private String tokenTime;

	public String getTokenTime() {
		return tokenTime;
	}

	public void setTokenTime(String tokenTime) {
		this.tokenTime = tokenTime;
	}

	public Users() {

	}

	public Users(String uuid, String token) {
		this.uuid = uuid;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Users [uuid=" + uuid + ", username=" + username + ", password=" + password + ", newPassword="
				+ newPassword + ", nickname=" + nickname + ", gender=" + gender + ", id=" + id + ", country=" + country
				+ ", tel=" + tel + ", email=" + email + ", birth=" + birth + ", photo=" + photo + ", token=" + token
				+ ", tokenTime=" + tokenTime + "]";
	}

}
