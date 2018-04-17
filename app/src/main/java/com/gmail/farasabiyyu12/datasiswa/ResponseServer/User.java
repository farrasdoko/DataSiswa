package com.gmail.farasabiyyu12.datasiswa.ResponseServer;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class User{

	@SerializedName("password")
	private String password;

	@SerializedName("nama")
	private String nama;

	@SerializedName("id")
	private String id;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("jenkel")
	private String jenkel;

	@SerializedName("alamat")
	private String alamat;

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

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNoTelp(String noTelp){
		this.noTelp = noTelp;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public void setJenkel(String jenkel){
		this.jenkel = jenkel;
	}

	public String getJenkel(){
		return jenkel;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
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
			"User{" + 
			"password = '" + password + '\'' + 
			",nama = '" + nama + '\'' + 
			",id = '" + id + '\'' + 
			",no_telp = '" + noTelp + '\'' + 
			",jenkel = '" + jenkel + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}