/*
 * Copyright (c) 2014 by XuanWu Wireless Technology Co.Ltd. 
 *             All rights reserved                         
 */
package domain;

import com.xuanwu.apaas.core.dao.factory.PlatformOperateFactory;
import com.xuanwu.apaas.core.utils.ParamMap;

import java.util.UUID;

/**
 * @author rongdi
 * @Date 2015年01月05日
 */
public class DataSourceAddress{

	/**
	 * 数据源地址编号
	 */
	private UUID id;
	/**
	 * 企业 E 号
	 */
	private int entNumber;
	/**
	 * 数据源地址名称
	 */
	private String name;
	/**
	 * 数据源地址信息
	 */
	private String address;
	/**
	 * 系统编号
	 */
	private String systemCode;
	/**
	 * 备注
	 */
	private String remark;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getEntNumber() {
		return entNumber;
	}

	public void setEntNumber(int entNumber) {
		this.entNumber = entNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "DataSourceAddress [id=" + id + ", entNumber=" + entNumber
				+ ", name=" + name + ", address=" + address + ", systemCode="
				+ systemCode + ", remark=" + remark + "]";
	}


	/**
	 * 获取数据源对象
	 * @param entNumber
	 * @param systemCode
	 * @return
	 */
	public DataSourceAddress findEntDataSourceAddress(int entNumber,
			String systemCode) {
		ParamMap params = ParamMap.getInstance();
		params.add("entNumber",entNumber);
		params.add("systemCode",systemCode);
		try {
			return (DataSourceAddress) PlatformOperateFactory.createReadableOperate()
					.singleResult("findEntDataSourceAddress", DataSourceAddress.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
