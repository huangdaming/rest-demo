package com.hdm.restdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hdm.restdemo.pojo.User;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: RestTest
 * @description:
 * @author: huangdaming
 * @Date: 2020-04-06 17:32
 */
public class RestTest {

    CloseableHttpClient httpClient;

    @Before
    public void init() {
        httpClient = HttpClients.createDefault();
    }

    @Test
    public void testGet() throws IOException {
        HttpGet request = new HttpGet("http://www.baidu.com");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    @Test
    public void testPost() throws IOException {
        HttpPost request = new HttpPost("http://www.oschina.net/");
        request.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    @Test
    public void testGetPojo() throws IOException {
        HttpGet request = new HttpGet("http://localhost:8080/hello");
        String response = this.httpClient.execute(request, new BasicResponseHandler());
        System.out.println(response);
    }

    // json处理工具
    private ObjectMapper mapper = new ObjectMapper();
    @Test
    public void testJson() throws JsonProcessingException {
        User user = new User();
        user.setId(8L);
        user.setAge(21);
        user.setName("柳岩");
        user.setUserName("liuyan");
        // 序列化
        String json = mapper.writeValueAsString(user);
        System.out.println("json = " + json);

        // 反序列化，接收两个参数：json数据，反序列化的目标类字节码
        User result = mapper.readValue(json, User.class);
        System.out.println("result = " + result);

    }



    @Test
    public void testJson2() throws IOException {
        User user = new User();
        user.setId(8L);
        user.setAge(21);
        user.setName("柳岩");
        user.setUserName("liuyan");

        // 序列化,得到对象集合的json字符串
        String json = mapper.writeValueAsString(Arrays.asList(user, user));

        // 反序列化，接收两个参数：json数据，反序列化的目标类字节码
        List<User> users = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
        for (User u : users) {
            System.out.println("u = " + u);
        }
    }

    @Test
    public void testJson3() throws IOException {
        User user = new User();
        user.setId(8L);
        user.setAge(21);
        user.setName("柳岩");
        user.setUserName("liuyan");

        // 序列化,得到对象集合的json字符串
        String json = mapper.writeValueAsString(Arrays.asList(user, user));

        // 反序列化，接收两个参数：json数据，反序列化的目标类字节码
        List<User> users = mapper.readValue(json, new TypeReference<List<User>>(){});
        for (User u : users) {
            System.out.println("u = " + u);
        }
    }
}
