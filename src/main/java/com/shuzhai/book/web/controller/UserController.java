package com.shuzhai.book.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.shuzhai.book.common.bean.User;
import com.shuzhai.book.common.utils.ServerResponse;
import com.shuzhai.book.service.IUserService;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${role}")
    private Integer role;
    @Value("${sex}")
    private Integer sex;
    @Value("${face}")
    private String face;
    @Value("${appId}")
    private String appId;
    @Value("${apiUrl}")
    private String apiUrl;
    @Value("${appSecret}")
    private String appSecret;

    @Autowired
    private IUserService userService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 验证用户名是否可用
     *
     * @param username
     * @return
     */
    @GetMapping(value = "/verifyName")
    @CrossOrigin
    public ServerResponse<Integer> verifyName(String username) {
        return userService.userVerify(username);
    }

    /**
     * 发送用户短信验证码
     *
     * @return
     */
    @GetMapping(value = "/sendSms")
    @CrossOrigin
    public ServerResponse<String> sendVerifyCode(@RequestParam(value = "number", required = true) String number) {
        System.out.println(number);
        JSONObject json = null;
        try {
            // 验证手机是否已经注册过了
            if (userService.phoneVerify(number).getStatus() == 1) {
                // 手机号已经注册了，提示用户直接进行注册
                return ServerResponse.createByErrorCodeMessage(8, "手机号已经注册");
            }
            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            //发送短信
            ZhenziSmsClient client = new ZhenziSmsClient("http://sms_developer.zhenzikj.com",
                    "100445", "ZWQ5Y2E5YTQtODUxNy00Zjg5LTk3ODYtY2IzZDRiNzRmY2E3");
            //向指定手机号发送短信
            String result = client.send(number, "您正在注册书斋网，手机验证码为:" + verifyCode + "，该码有效期为15分钟。");
            json = JSONObject.parseObject(result);
            if (json.getIntValue("code") != 0) {
                System.out.println(json.getIntValue("code"));
                // 短信发送失败
                return ServerResponse.createByErrorMessage("短信发送失败，请稍后重试");
            }
            // 验证码短信发送成功进行对验证码的redis存储
            Map<String, String> map = new HashMap<>();
            map.put("verifyCode", verifyCode);
            map.put("createTime", System.currentTimeMillis() + "");
            // 将短信验证码存储redis中
            redisTemplate.opsForHash().putAll(number, map);
            // 短信发送成功
            return ServerResponse.createBySuccessMessage("短信发送成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("系统错误，请稍后重试");
        }
    }

    @PostMapping(value = "/register")
    @CrossOrigin
    public ServerResponse<String> register(String name, String password, String number, String verify) {
        /*// 验证用户名可用，未重复, 考虑使用ajax，这里验证手机验证码的匹配
        ServerResponse<String> response = null;
        // 查询用户，用户名不存在返回1
        if (userService.userVerify(name).getStatus() == 1) {
            // 用户名可用
            response = userService.userRegister(new User(name, password, null, number, role, sex, face, new Date(), new Date()));
        } else {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        return response;*/
        ServerResponse<String> response = null;
        // 验证验证码的匹配问题  获取验证码
        String verifyCode = (String) redisTemplate.opsForHash().get(number, "verifyCode");
        String createTime = (String) redisTemplate.opsForHash().get(number, "createTime");
        if (verify.equals(verifyCode)) {
            // 验证验证码是否过期
            if (new Date().getTime() - Long.parseLong(createTime) < 15 * 60 * 1000) {
                response = userService.userRegister(new User(name, password, null, number, role, sex, face, new Date(), new Date()));
                // 成功注册删除相对应的手机验证码  预防注册多个账号
                if (response.getStatus() == 0) {
                    redisTemplate.opsForHash().delete("verify", number);
                }
                return response;
            } else {
                return ServerResponse.createByErrorMessage("验证码过期，请重新获取");
            }
        } else {
            return ServerResponse.createByErrorMessage("验证码错误");
        }
    }

    @PostMapping(value = "/login")
    @CrossOrigin
    public ServerResponse<User> userLogin(@RequestBody User user, HttpSession session) {
        ServerResponse<User> response = userService.userLogin(user.getUsername(), user.getPassword());
        if (response.isSuccess()) {
            // 登录成功，将用户信息存储session中
            session.setAttribute("successLoginUser", response.getData());
        }
        return response;
    }

    @GetMapping("/info")
    @CrossOrigin
    public ServerResponse<User> userInfo(String name) {
        ServerResponse<User> response = userService.userInfoByName(name);
        if (response == null) {
            return ServerResponse.createByErrorMessage("系统错误，请稍后再试");
        }
        return response;
    }
}
