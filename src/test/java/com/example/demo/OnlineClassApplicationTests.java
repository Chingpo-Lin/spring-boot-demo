package com.example.demo;

import com.example.demo.model.entity.User;
import com.example.demo.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OnlineClassApplicationTests {

	@Test
	public void testGeneJwt() {
		User user = new User();
		user.setId(66);
		user.setName("bob");
		user.setHeadImg("png");

		String token = JWTUtils.geneJsonWebToken(user);

		System.out.println(token);
//		try {
//			Thread.sleep(4000L);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		Claims claims = JWTUtils.checkJWT(token);

		Assert.isTrue(claims.get("name").equals(user.getName()));

		System.out.println(user.getName());
	}
}
