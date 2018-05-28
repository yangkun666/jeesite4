/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.miaocup.modules.msg.entity;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.miaocup.modules.cm.entity.Coffee;
import com.miaocup.modules.user.entity.ClientUser;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 在线留言Entity
 * @author yangkun
 * @version 2018-04-17
 */
@Table(name="t_online_msg", alias="a", columns={
		@Column(name="id", attrName="id", label="编号", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户编号"),
		@Column(name="content", attrName="content", label="内容"),
		@Column(name="contact_way", attrName="contactWay", label="联系方式"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable={
		@JoinTable(type=JoinTable.Type.LEFT_JOIN, entity=ClientUser.class, alias="c",
				on="c.id = a.user_id",
				attrName="clientUser", columns={@Column(includeEntity=ClientUser.class)})
}, orderBy="a.update_date DESC"
)
public class OnlineMsg extends DataEntity<OnlineMsg> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户编号
	private String content;		// 内容
	private String contactWay;		// 联系方式
	private ClientUser clientUser;
	public OnlineMsg() {
		this(null);
	}

	public ClientUser getClientUser() {
		return clientUser;
	}

	public void setClientUser(ClientUser clientUser) {
		this.clientUser = clientUser;
	}

	public OnlineMsg(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="用户编号长度不能超过 255 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=255, message="内容长度不能超过 255 个字符")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=255, message="联系方式长度不能超过 255 个字符")
	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
}