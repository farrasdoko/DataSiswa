package com.gmail.farasabiyyu12.datasiswa.ResponseServer;

//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class ResponseCreateData{

	@SerializedName("message")
	private String message;

	@SerializedName("success")
	private boolean success;

	public void setPesan(String pesan){
		this.message = pesan;
	}

	public String getPesan(){
		return message;
	}

	public void setSukses(boolean sukses){
		this.success = sukses;
	}

	public boolean isSukses(){
		return success;
	}

	@Override
 	public String toString(){
		return 
			"ResponseCreateData{" + 
			"pesan = '" + message + '\'' +
			",sukses = '" + success + '\'' +
			"}";
		}
}