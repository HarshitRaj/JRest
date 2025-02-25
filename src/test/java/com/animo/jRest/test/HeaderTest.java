package com.animo.jRest.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.animo.jRest.util.APICall;
import com.animo.jRest.util.APIHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class HeaderTest {
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Ignore
	@Test
	public void testSingleStaticHeaderKey() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final APICall<Void, Map<String, Object>> testCall = testInterface.getCall();
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		assertTrue(((Map<String, String>) response.getResponseBody().get("headers")).containsKey("x-foo"));
		
	}
	
	@Ignore
	@Test
	public void testSingleStaticHeaderValue() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final APICall<Void, Map<String, Object>> testCall = testInterface.getCall();
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		assertEquals("Bar", ((Map<String, String>) response.getResponseBody().get("headers")).get("x-foo"));
	}
	
	@Ignore
	@Test
	public void testMultipleStaticHeaderKey() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final APICall<Void, Map<String, Object>> testCall = testInterface.getMultipleHeadersCall();
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		assertTrue(((Map<String, String>) response.getResponseBody().get("headers")).containsKey("x-foo") &&
				((Map<String, String>) response.getResponseBody().get("headers")).containsKey("x-ping"));
		
	}
	
	@Ignore
	@Test
	public void testMultipleStaticHeaderValue() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final APICall<Void, Map<String, Object>> testCall = testInterface.getMultipleHeadersCall();
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		assertEquals("Bar", ((Map<String, String>) response.getResponseBody().get("headers")).get("x-foo"));
		assertEquals("Pong", ((Map<String, String>) response.getResponseBody().get("headers")).get("x-ping"));
	}
	
	@Ignore
	@Test
	public void testFailureStaticHeader() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();		
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Header data invalid");
		
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final APICall<Void, Map<String, Object>> testCall = testInterface.getIncorrectHeader();
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		
	}
	@Ignore
	@Test
	public void testSingleDynamicHeaderKey() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final Map<String, String> testMap = new HashMap<>();
		testMap.put("x-Foo", "Bar");
		final APICall<Void, Map<String, Object>> testCall = testInterface.getSingleParamHeadersCall(testMap);
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		assertTrue(((Map<String, String>) response.getResponseBody().get("headers")).containsKey("x-foo"));
		
	}
	@Ignore
	@Test
	public void testSingleDynamicHeaderValue() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final Map<String, String> testMap = new HashMap<>();
		testMap.put("x-Foo", "Bar");
		final APICall<Void, Map<String, Object>> testCall = testInterface.getSingleParamHeadersCall(testMap);
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		
		assertEquals("Bar", ((Map<String, String>) response.getResponseBody().get("headers")).get("x-foo"));
		
	}
	@Ignore
	@Test
	public void testBothDynamicStaticHeadersKey() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final Map<String, String> testMap = new HashMap<>();
		testMap.put("x-Foo", "Bar");
		final APICall<Void, Map<String, Object>> testCall = testInterface.getBothSingleParamStaticHeadersCall(testMap);
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		assertTrue(((Map<String, String>) response.getResponseBody().get("headers")).containsKey("x-foo"));
		assertTrue(((Map<String, String>) response.getResponseBody().get("headers")).containsKey("x-ping"));
		assertTrue(((Map<String, String>) response.getResponseBody().get("headers")).containsKey("x-static"));
		
	}
	
	@Test
	public void testBothDynamicStaticHeadersValue() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final Map<String, String> testMap = new HashMap<>();
		testMap.put("x-Foo", "Bar");
		final APICall<Void, Map<String, Object>> testCall = testInterface.getBothSingleParamStaticHeadersCall(testMap);
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		
		assertEquals("Bar", ((Map<String, String>) response.getResponseBody().get("headers")).get("x-foo"));
		assertEquals("Pong", ((Map<String, String>) response.getResponseBody().get("headers")).get("x-ping"));
		assertEquals("True", ((Map<String, String>) response.getResponseBody().get("headers")).get("x-static"));
		
	}
	
	@Test
	public void testNoHeaders() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final Map<String, String> testMap = new HashMap<>();
		testMap.put("x-Foo", "Bar");
		final APICall<Void, Map<String, Object>> testCall = testInterface.noHeadersCall();
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		
		assertFalse(((Map<String, String>) response.getResponseBody().get("headers")).containsKey("x-foo"));
		
	}
	
	@Test
	public void testFailureDynamicHeader() throws Exception {
		
		final APIHelper testAPIHelper = APIHelper.APIBuilder
				.builder("https://postman-echo.com")
				.build();		
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Header Parameters should be passed in Map");
		
		final TestPostmanEchoAPIInterface testInterface = testAPIHelper.createApi(TestPostmanEchoAPIInterface.class);
		final APICall<Void, Map<String, Object>> testCall = testInterface.incorrectHeadersCall("X-Foo:Bar");
		final APICall<Void, Map<String, Object>> response = testCall.callMeNow();
		
	}

}
