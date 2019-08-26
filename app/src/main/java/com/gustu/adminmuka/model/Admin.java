package com.gustu.adminmuka.model;

import com.google.gson.annotations.SerializedName;

public class Admin{

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"Admin{" + 
			"password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}