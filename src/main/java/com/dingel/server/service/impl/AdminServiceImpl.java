package com.dingel.server.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dingel.server.config.security.JwtTokenUtil;
import com.dingel.server.mapper.AdminMapper;
import com.dingel.server.pojo.dto.LoginUsers;
import com.dingel.server.pojo.dto.ResponseBean;
import com.dingel.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author pjc
 * @since 2021-06-13
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, LoginUsers> implements IAdminService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResponseBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha= (String) request.getSession().getAttribute("verifyCode");
        if (StringUtils.isEmpty(captcha)||!captcha.equalsIgnoreCase(code)){
            return ResponseBean.error("验证码输入错误");
        }
        //登录
        System.out.println(passwordEncoder.encode(password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return ResponseBean.error("用户名或密码不正确！");
        }
        if (!userDetails.isEnabled()) {
            return ResponseBean.error("账号被禁用，请联系管理员！");
        }

        Integer type = adminMapper.selectOne(new QueryWrapper<LoginUsers>().eq("r_id", username)).getTType();

        //更新security登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);  // 把token放在全局

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        tokenMap.put("type",type.toString());
        return ResponseBean.success("登陆成功！", tokenMap);
    }

    @Override
    public LoginUsers getAdminByRId(String username) {
        return adminMapper.selectOne(new QueryWrapper<LoginUsers>().eq("r_id", username));
    }

    @Override
    public ResponseBean changePasswordById(Integer id, String newPassword, HttpServletRequest request) {
        if(passwordEncoder.matches(newPassword,
                (adminMapper.selectOne(new QueryWrapper<LoginUsers>().eq("id", id))).getPassword())){
            return ResponseBean.error("新密码与原密码输入一致，请重新输入");
        }
        LoginUsers user =  new LoginUsers();
        user.setRPassword(passwordEncoder.encode(newPassword));
        user.setId(id);
        adminMapper.updateById(user);
        return ResponseBean.success("修改成功!");
    }

}
