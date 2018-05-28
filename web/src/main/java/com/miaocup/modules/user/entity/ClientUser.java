/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.user.entity;

import com.miaocup.modules.cm.entity.CmGrade;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 会员表Entity
 * @author hejun
 * @version 2018-03-13
 */
@Table(name="t_client_user", alias="a", columns={
		@Column(name="id", attrName="id", label="用户ID", isPK=true),
		@Column(name="user_name", attrName="userName", label="用户名", queryType=QueryType.LIKE),
		@Column(name="account", attrName="account", label="登录账户"),
		@Column(name="password", attrName="password", label="登录密码"),
		@Column(name="amount", attrName="amount", label="用户余额"),
		@Column(name="point", attrName="point", label="用户积分"),
		@Column(name="exp", attrName="exp", label="用户经验值"),
		@Column(name="invite_code", attrName="inviteCode", label="邀请码"),
		@Column(name="last_login_time", attrName="lastLoginTime", label="最后登录时间"),
		@Column(name="grade", attrName="grade", label="用户等级"),
		@Column(name="register_date", attrName="registerDate", label="注册时间"),
		@Column(name="phone", attrName="phone", label="手机号"),
		@Column(name="company", attrName="company", label="公司名称"),
		@Column(name="open_id", attrName="openId", label="微信Open"),
	}, joinTable={	@JoinTable(type=Type.LEFT_JOIN, entity=UserGrade.class, alias="g",
		on="g.id = a.grade",
		attrName="userGrade", columns={@Column(includeEntity=UserGrade.class)})
		}, orderBy="a.id DESC"
)
public class ClientUser extends DataEntity<ClientUser> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// 用户名
	private String account;		// 登录账户
	private String password;		// 登录密码
	private Double amount;		// 用户余额
	private String company;
	private Integer point;		// 用户积分
	private Integer exp;		// 用户经验值
	private String inviteCode;		// 邀请码
	private Date lastLoginTime;		// 最后登录时间
	private String grade;		// 用户等级
	private Date registerDate;		// 注册时间
	private String phone;     //手机号
	private String openId;     //微信OpenID
	public ClientUser() {
		this(null);
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public UserGrade getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(UserGrade userGrade) {
		this.userGrade = userGrade;
	}

	private UserGrade userGrade;		// 用户等级
	public ClientUser(String id){
		super(id);
	}
	
	@NotBlank(message="用户名不能为空")
	@Length(min=0, max=64, message="用户名长度不能超过 64 个字符")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@NotBlank(message="登录账户不能为空")
	@Length(min=0, max=64, message="登录账户长度不能超过 64 个字符")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@NotBlank(message="登录密码不能为空")
	@Length(min=0, max=64, message="登录密码长度不能超过 64 个字符")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotNull(message="用户余额不能为空")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@NotNull(message="用户积分不能为空")
	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	@NotNull(message="用户经验值不能为空")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@NotNull(message="用户经验值不能为空")
	public Integer getExp() {
		return exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}
	
	@Length(min=0, max=8, message="邀请码长度不能超过 8 个字符")
	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@NotNull(message="用户等级不能为空")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="注册时间不能为空")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	public Date getLastLoginTime_gte() {
		return sqlMap.getWhere().getValue("last_login_time", QueryType.GTE);
	}

	public void setLastLoginTime_gte(Date lastLoginTime) {
		sqlMap.getWhere().and("last_login_time", QueryType.GTE, lastLoginTime);
	}
	
	public Date getLastLoginTime_lte() {
		return sqlMap.getWhere().getValue("last_login_time", QueryType.LTE);
	}

	public void setLastLoginTime_lte(Date lastLoginTime) {
		sqlMap.getWhere().and("last_login_time", QueryType.LTE, lastLoginTime);
	}
	
	public Date getRegisterDate_gte() {
		return sqlMap.getWhere().getValue("register_date", QueryType.GTE);
	}

	public void setRegisterDate_gte(Date registerDate) {
		sqlMap.getWhere().and("register_date", QueryType.GTE, registerDate);
	}
	
	public Date getRegisterDate_lte() {
		return sqlMap.getWhere().getValue("register_date", QueryType.LTE);
	}

	public void setRegisterDate_lte(Date registerDate) {
		sqlMap.getWhere().and("register_date", QueryType.LTE, registerDate);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}