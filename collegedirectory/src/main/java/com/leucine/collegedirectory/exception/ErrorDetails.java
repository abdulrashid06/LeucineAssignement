package com.leucine.collegedirectory.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {
	
	private String message;
	private String desc;
	private LocalDateTime timeStamp = LocalDateTime.now();

}
