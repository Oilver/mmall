package com.mmall.common;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

//不要null的key
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse2<T> implements Serializable {

	private int status;
	private String msg;
	private T data;
	
	private ServerResponse2(int status){
		this.status = status;
	}
	private ServerResponse2(int status,String msg){
		this.status = status;
		this.msg = msg;
	}
	private ServerResponse2(int status,String msg,T data){
		this.msg = msg;
		this.data = data;
		this.status = status;
	}
	private ServerResponse2(int status,T data){
		this.status = status;
		this.data = data;
	}
	
	//使之不会返回到json中
	@JsonIgnore
	public boolean isSuccess(){
		return this.status==ResponseCode2.SUCCESS.getCode();
	}
	//这三个字段都会显示在json里
	public int getStatus(){
		return status;
	}
	public T getDate(){
		return data;
	}
	public String getMsg(){
		return msg;
	}
	
	
	public static <T> ServerResponse2<T> createBySuccess(){
		return new ServerResponse2<T>(ResponseCode2.SUCCESS.getCode());
	}
	
	public static <T> ServerResponse2<T> createBySuccessMessage(String message){
		return new ServerResponse2<T>(ResponseCode2.SUCCESS.getCode(), message);
	}
	
	public static <T> ServerResponse2<T> createBySuccess(T data){
		return new ServerResponse2<T>(ResponseCode2.SUCCESS.getCode(), data);
	}
	
	public static <T> ServerResponse2<T> createBySuccess(String msg,T data){
		return new ServerResponse2<T>(ResponseCode2.SUCCESS.getCode(),msg, data);
	}
	
	public static <T> ServerResponse2<T> createByError(){
		return new ServerResponse2<T>(ResponseCode2.ERROR.getCode(),ResponseCode2.ERROR.getDesc());
	}
	
	public static <T> ServerResponse2<T> createByErrorMessage(String msg){
		return new ServerResponse2<T>(ResponseCode2.ERROR.getCode(),msg);
	}
	
	public static <T> ServerResponse2<T> createByErrorCode(int errorCode,String errormsg){
		return new ServerResponse2<T>(errorCode, errormsg);
	}
	
	
}
