/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 短信群发用户中间表Entity
 * @author yangkun
 * @version 2018-03-19
 */
@Table(name="t_msg_user", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户编号"),
		@Column(name="msg_id", attrName="msgId", label="短信编号"),
		@Column(includeEntity=DataEntity.class),
	}, orderBy="a.update_date DESC"
)
public class MsgUser extends DataEntity<MsgUser> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户编号
	private String msgId;		// 短信编号
	
	public MsgUser() {
		this(null);
	}

	public MsgUser(String id){
		super(id);
	}
	
	@NotBlank(message="用户编号不能为空")
	@Length(min=0, max=64, message="用户编号长度不能超过 64 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@NotBlank(message="短信编号不能为空")
	@Length(min=0, max=64, message="短信编号长度不能超过 64 个字符")
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}