package com.cos.blog.test;

import java.util.UUID;

import org.junit.Test;

public class UUIDTest {

	@Test
	public void 유유아이디_테스트() {
		String fileName = "abc.jpg";
		// 다운을 받았다고 가정하고 
		String renameFileName = UUID.randomUUID() + "_" + fileName;
		System.out.println(renameFileName);
	}
}
