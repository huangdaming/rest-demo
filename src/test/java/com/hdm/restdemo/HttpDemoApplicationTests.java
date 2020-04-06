package com.hdm.restdemo;

import com.hdm.restdemo.pojo.User;
import org.apache.commons.codec.binary.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestDemoApplication.class)
public class HttpDemoApplicationTests {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void httpGet() {
        String user = "111";
        String password = "66666";
        String userMsg = user + ":" + password;
        String basicBase64UserMsg = null;
        try {
            basicBase64UserMsg = "Basic " + Base64.encodeBase64String(userMsg.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(basicBase64UserMsg);
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
        headers.add("Authorization ", basicBase64UserMsg);

        String postBody = "data";

        HttpEntity<String> entity = new HttpEntity<String>(postBody, headers);

        ResponseEntity<String> response = restTemplate.getForEntity("https://localhost:8080/hello", String.class);

        System.out.println(response.getBody());
	}

    @Test
    public void test() throws Exception {
        String url = "https://localhost:8080/hello";
        String user = "111";
        String password = "66666";
        String userMsg = user + ":" + password;
        String basicBase64UserMsg = null;
        try {
            basicBase64UserMsg = "Basic " + Base64.encodeBase64String(userMsg.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko");
        requestHeaders.add("Authorization ", basicBase64UserMsg);
        //body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        //requestBody.add("id", "1");
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);

        ResponseEntity<User> responseEntity1 = restTemplate.exchange(url,
                HttpMethod.GET, requestEntity, User.class);
        System.out.println(responseEntity1.getBody());
    }

    @Test
    public void getHasHeader() {
        long userId = 32L;
        String url = "https://localhost:8080/hello?id=1";
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", "123");
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("id", "1");
        //HttpEntity
        ResponseEntity<User> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<MultiValueMap>(headers,requestBody),
                User.class);
        System.out.println(response);


    }

    @Test
    public void getTest() {

        String url = "https://localhost:8080/hello?id=1";
        String user = "yejiawei";
        String password = "234";
        String userMsg = user + ":" + password;
        String base64UserMsg = null;
        try {
            base64UserMsg = Base64.encodeBase64String(userMsg.getBytes("UTF-8"));
            //base64UserMsg = "Basic " + userMsg;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64UserMsg);
	    HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<User> response = restTemplate.exchange(url,HttpMethod.GET,request,User.class);
        User user2 = response.getBody();
        System.out.println(user2);

    }


}