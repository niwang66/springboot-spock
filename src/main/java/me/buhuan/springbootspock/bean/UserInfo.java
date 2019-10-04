package me.buhuan.springbootspock.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author hbh
 * @version 1.0.0
 * @since 2017/12/10
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "t01_user")
public class UserInfo {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private Long userId;
	
	private String name;
	
	private Integer sex;
	
	private String address;
}
