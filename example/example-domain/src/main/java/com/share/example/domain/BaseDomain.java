package com.share.example.domain;

import java.io.Serializable;
import java.util.Date;

public class BaseDomain implements Serializable {

	private static final long serialVersionUID = 3762624559392099790L;
	
	protected Date createTime; // 创建时间
	protected Date updateTime; // 更新时间
	protected Integer yn; // 是否有效 0 无效 1 有效

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}
}
