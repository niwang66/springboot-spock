package me.buhuan.springbootspock.dao;

import me.buhuan.springbootspock.bean.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/10
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	
	UserInfo findByUserId(Long userId);
	
}
