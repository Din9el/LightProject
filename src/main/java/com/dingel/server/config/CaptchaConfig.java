package com.dingel.server.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 验证码配置类
 */
@Configuration
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha(){
        //验证码生成器
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        //是否有边框
        Properties properties=new Properties();
        //是否有边框
        properties.put("kaptcha.textproducer.char.string", "0123456789");
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.length","4");
        properties.put("kaptcha.image.height","40");
        properties.put("kaptcha.image.width","150");
        properties.put("kaptcha.textproducer.font.size","30");
        properties.put("kaptcha.session.key", "verifyCode");
        properties.put("kaptcha.textproducer.char.space", "5");
        properties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        //验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width","100");
        //验证码图片高度 默认为40
        properties.setProperty("kaptcha.image.height","40");
        Config config=new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;

    }
}
