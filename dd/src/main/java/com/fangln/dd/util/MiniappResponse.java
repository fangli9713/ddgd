package com.fangln.dd.util;


import lombok.Data;

@Data
public class MiniappResponse {

	private int resp_code;
	private String resp_des;
	private Object data;

}
