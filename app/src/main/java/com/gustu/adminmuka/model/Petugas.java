package com.gustu.adminmuka.model;

import com.google.gson.annotations.SerializedName;
public class Petugas{

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("telepon")
	private String telepon;

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

	public void setTelepon(String telepon){
		this.telepon = telepon;
	}

	public String getTelepon(){
		return telepon;
	}

	public void setUsername(String id){
		this.username = id;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"Petugas{" + 
			"password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",telepon = '" + telepon + '\'' + 
			",username = '" + username + '\'' +
			"}";
		}
}