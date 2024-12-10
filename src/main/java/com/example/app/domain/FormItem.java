package com.example.app.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormItem {
	//バリデーション
	//空白NG
	@NotBlank(message = "空はダメ")
	//文字数2～10
	@Size(min = 2, max = 10)
	private String name;
	
	//空白NG
	@NotNull
	//0～150
	@Min(0)
	@Max(150)
	private Integer age;
	private Integer pet;
	@Valid
	private Company company;
	//checkboxは複数のデータが渡ってくる可能性があるので
	//配列かListで対応
	private Integer[] areaId;
	//private List<Integer> areaId;
}
