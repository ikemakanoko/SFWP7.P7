package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.User;
import com.example.app.validation.LoginGroup;
import com.example.app.validation.RegusterGroup;

@Controller
public class UserController {
	@GetMapping("/register")
	public String register(Model model) {
		// Thymeleafの方でドメインクラスを使えるようにする
		// セットする名前をドメインクラスのクラス名を
		// スネークケースで指定 FormItem -> formItem
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String register(
			@Validated(RegusterGroup.class) User user,
			Errors errors) {
		System.out.println(user);
		//パスワードの核にン
		if (!user.getPass().equals(user.getPassConf()))
			errors.rejectValue("passConf", "passconf.wrong");
		return "register";
	}

	@GetMapping("/login") //localhost:8080/login
	public String login(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login") //localhost:8080/login
	public String login(
			@Validated(LoginGroup.class) User user,
			Errors errors) {
		if (errors.hasErrors()) {
			System.out.println("不備あり");
		} else {
			//ログインID：taro パスワード：abcd
			if (user.getLoginId().equals("taro") &&
				user.getPass().equals("abcd")
					) {
				System.out.println("ログイン成功");
			} else {
				errors.reject("wrong.id.pass");
			}
		}

		return "login";
	}

}