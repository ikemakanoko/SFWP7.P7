package com.example.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.Area;
import com.example.app.domain.Company;
import com.example.app.domain.FormItem;

import jakarta.validation.Valid;



@Controller
public class ContactController {
@GetMapping("/contact")
public String contact(Model model) {
	//フォームの初期値設定
	FormItem formItem = new FormItem();
	formItem.setAge(20);
	formItem.setPet(2);
	//ネストされたドメインの初期値
	Company company = new Company();
	company.setJobTitle("講師");
	formItem.setCompany(company);
	
	//P5 thymeleafの方でドメインクラスを
	//使えるようにする
	//セットするときの名前をドメインクラスの名前にする
	//ただし、スネークケースにする
	model.addAttribute("formItem", formItem);
	//住んだ地域のデータをセット
	model.addAttribute("areas", createAreaList());	
    return "contact";
}
@PostMapping("/contact")
public String contact(
//		@RequestParam("name")String name,
//		@RequestParam("age")Integer age
		//この受け取り方で再表示用のデータをセットしてくれる
		@Valid FormItem formItem,
		Errors error,//@Valid直後にErrors。間に何も記述しない
		Model model
		) {
	//エラーチェック
	if(error.hasErrors()) {
		// エラー内容の捕捉
		System.out.println("不備あり");
		//どんなエラーがあるかコンソール表示
		 List<ObjectError> objList = error.getAllErrors();
		 for(ObjectError obj : objList) {
			 System.out.println(obj.toString());
		 }
		}

	System.out.println(formItem);
	//住んだ地域のデータをセット
	model.addAttribute("areas", createAreaList());	
    return "contact";
}
//住んだ地域のデータ生成
private List<Area> createAreaList(){
	List<Area> areas = new ArrayList<>();
	areas.add(new Area(1, "関東"));
	areas.add(new Area(2, "関西"));
	areas.add(new Area(3, "海外"));
	return areas;
}
}

