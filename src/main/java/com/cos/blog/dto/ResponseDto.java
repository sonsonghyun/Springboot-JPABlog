package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//응답용
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
	int status;
	T data;
}
