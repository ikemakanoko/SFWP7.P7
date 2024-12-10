package com.example.app.domain;

import com.example.app.validation.LoginGroup;
import com.example.app.validation.RegusterGroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
	
	@NotBlank(groups = {RegusterGroup.class})
	private String name;
	
	@NotBlank(groups = {RegusterGroup.class, LoginGroup.class})
	private String loginId;
	
	@NotBlank(groups = {LoginGroup.class})
	@Size(min=6, max=20, groups = {RegusterGroup.class})
	private String pass;
	
	private String passConf;
}
