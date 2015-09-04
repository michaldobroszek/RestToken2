package com.michal;

import java.util.UUID;

public class User {
	private String login;
	private String password;
	private UUID token = UUID.randomUUID();

	public User() {
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;

	}

	public UUID getToken() {
		return token;
	}

	public void setToken(UUID Zeton) {
		this.token = Zeton;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// @Override
	// public String toString() {
	// return "ExampleModel{" + "string='" + login + '\'' + ", anInt=" + password
	// + + '}';
	// }

}