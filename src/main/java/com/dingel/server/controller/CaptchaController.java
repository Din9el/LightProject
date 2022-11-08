package com.dingel.server.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 验证码
 */
@RestController
@Api(tags = "权限校验")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer;//定义DefaultKaptcha对象

    @ApiOperation("验证码")
    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires",0);
        response.setHeader("Cache-Control","no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control","post-check=0,pre-check=0");
        response.setHeader("Pragma","no-cache");
        response.setContentType("image/jpeg");
        //------------------生成验证码 begin------------------
        // 创建字节数组用于存放图片信息
        byte[] captchaOutputStream = null;
        // 创建字节数组用于存放图片信息
        ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中

            // 通过DefaultKaptcha获得随机验证码
            String verifyCode = captchaProducer.createText();
            System.out.println("验证码:"+verifyCode);
            // 将生成的验证码存放在session中
            request.getSession().setAttribute("verifyCode", verifyCode);
            // 使用生成的验证码字符串，完成图片的生成
            BufferedImage challenge = captchaProducer.createImage(verifyCode);
            // 将图片写入到流中
            ImageIO.write(challenge, "jpg", imgOutputStream);
        } catch (IllegalArgumentException | IOException e) {
            //将图片写入到输入流出现错误
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 使用HttpServletResponse将图片写入到浏览器中
        captchaOutputStream = imgOutputStream.toByteArray();
        /// 通过response设定响应请求类型
        //
        // no-store用于防止重要的信息被无意的发布。在请求消息中发送将使得请求和响应消息都不使用缓存。
        response.setHeader("Cache-Control", "no-store");
        // no-cache指示请求或响应消息不能缓存
        response.setHeader("Pragma", "no-cache");
        /* expires是response的一个属性,它可以设置页面在浏览器的缓存里保存的时间 ,超过设定的时间后就过期 。
        过期后再次浏览该页面就需要重新请求服务器发送页面数据，
        如果在规定的时间内再次访问次页面 就不需从服务器传送 直接从缓存中读取。
         * */
        response.setDateHeader("Expires", 0);
        // servlet接受request请求后接受图片形式的响应
        response.setContentType("image/jpeg");
        //通过response获得输出流
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
        //------------------生成验证码 end------------------
    }

