package com.att.demo.component;

import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.demo.model.Account;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountResourceComponentTest {
	@LocalServerPort
	protected int randomServerPort;
	
	private String uri ="/accounts";
	
	@Before
	public void setUp() throws Exception {
		RestAssured.baseURI = "http://" + InetAddress.getLocalHost().getHostName() + ":" + randomServerPort + "/api";
	}
	
	private  RequestSpecification givenBaseSpec() {
		return 
				RestAssured.given()
					.accept(ContentType.JSON)
					.contentType(ContentType.JSON);
	}
	
	@Test
	public void testfindAllAccount_success() {
		
		givenBaseSpec()
				.when()
				.get(uri)
				.then()
					.statusCode(200);
	}
	
	@Test
	public void testCreateAccount_success() {
		
			
		Account account = new Account();
		account.setId(54321);
		account.setName("test-create_1");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	@Test
	public void testCreateAccount_NoId_success() {
		
			
		Account account = new Account();
		//account.setId();
		account.setName("test-create_NoID");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	
	@Test
	public void testCreateAccount_NoIdAndName_success() {
		
			
		Account account = new Account();
		//account.setId(54321);
		//account.setName("test-create");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	
	@Test
	public void testCreateUser_failure() {
		//TO-DO	
		Account account = new Account();
		account.setId(54321);
		account.setName("Account1");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(409);
	}
	
	
	@Test
	public void testGetAccount_success() {
		
		Account account = new Account();
		account.setId(12345);
		account.setName("test-get");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
				.statusCode(201);

		givenBaseSpec()
		.when()
			.get(uri + "/12345")
			.then()
				.statusCode(200);
	}
	
	
	@Test
	public void testGetAccount_NotFound() {
		
		Account account = new Account();
		account.setId(12345);
		account.setName("test-get");
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
				.statusCode(201);

		givenBaseSpec()
		.when()
			.get(uri + "/99999")
			.then()
				.statusCode(404);
	}

	
	
	
	
	/*@Test
	public void testCreateUser_success() {
		
			
		User user = new User();
		user.setId(10);
		user.setName(TestUser);
		user.setAge(50);
		user.setAccountId(Account1);
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}
	
	@Test
	/*public void testCreateUser_Failure400() {
		
			
		User user = new User();
		user.setId(10);
		user.setName(TestUser);
		user.setAge(50);
		user.setAccountId(Account1);
		
		givenBaseSpec()
			.body(account)
			.when()
				.post(uri)
				.then()
					.statusCode(201);
	}*/
	
}
