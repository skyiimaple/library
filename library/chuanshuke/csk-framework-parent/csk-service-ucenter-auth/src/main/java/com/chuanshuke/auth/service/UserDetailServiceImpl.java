package com.chuanshuke.auth.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import com.chuanshuke.auth.client.UserClient;
import com.chuanshuke.framework.domain.ucenter.TbUser;
import com.chuanshuke.framework.domain.ucenter.response.UcenterResult;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	ClientDetailsService clientDetailsService;
	
	@Autowired
	UserClient userClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication == null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
//            System.out.println("userdetail loaduserbyusername" + clientDetails.toString());
            if(clientDetails != null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        
        //请求ucenter查询用户
//        System.out.println("根据账号获取用户信息"+username);
        UcenterResult result = userClient.getUser(username);
        TbUser user = result.getTbUser();
//        System.out.println(user);
        if(user == null) {
        	//返回NULL表示用户不存在，spring security 会抛出异常
        	return null;
        }
        //从数据库查询用户的正确密码，sepring security 会去比对输入密码的正确性
        String password = user.getPassword();
        UserJwt userDetails = new UserJwt(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
        userDetails.setId(user.getId());
        userDetails.setName(user.getName());
        
        

        //测试用
//        TbUser userext = new TbUser();
//        userext.setUsername("csk");
//        userext.setPassword(new BCryptPasswordEncoder().encode("123"));
//        userext.setPermissions(new ArrayList<XcMenu>());  //权限功能
//        if(userext == null){
//            return null;
//        }
        //取出正确密码
//        String password= userext.getPassword();
        //这里暂时使用静态密码
//       String password ="123";
        //用户权限，这里暂时使用静态数据，最终会从数据库读取
        //从数据库获取权限
//        List<XcMenu> permissions = userext.getPermissions();
//        List<String> user_permission = new ArrayList<>();
//        permissions.forEach(item-> user_permission.add(item.getCode()));
        //使用静态的权限表示用户所拥有的权限
//        user_permission.add("course_get_baseinfo");
//        user_permission.add("course_find_pic");
//        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",");
//        UserJwt userDetails = new UserJwt(username,
//                password,
//				AuthorityUtils.commaSeparatedStringToAuthorityList(""/* user_permission_string */));
//        userDetails.setId(userext.getId());
//        userDetails.setUtype(userext.getUtype());//用户类型
//        userDetails.setCompanyId(userext.getCompanyId());//所属企业
//        userDetails.setName(userext.getUsername());//用户名称
//        userDetails.setUserpic(userext.getUserpic());//用户头像
       /* UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));*/
//                AuthorityUtils.createAuthorityList("course_get_baseinfo","course_get_list"));
        
        
        return userDetails;
	}

}
