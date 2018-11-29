package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.base.BaseEntity;

import java.io.Serializable;

/**
 * 字典表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-27 15:39:28
 */
@TableName("cdp_dict")
public class CdpDictEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 
	 */
	private String attribute1;
	/**
	 * 
	 */
	private String attribute2;
	/**
	 * 
	 */
	private String attribute3;
	/**
	 * 
	 */
	private String attribute4;
	/**
	 * 
	 */
	private String attribute5;
	/**
	 * 
	 */
	private String attribute6;

	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：
	 */
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	/**
	 * 获取：
	 */
	public String getAttribute1() {
		return attribute1;
	}
	/**
	 * 设置：
	 */
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	/**
	 * 获取：
	 */
	public String getAttribute2() {
		return attribute2;
	}
	/**
	 * 设置：
	 */
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	/**
	 * 获取：
	 */
	public String getAttribute3() {
		return attribute3;
	}
	/**
	 * 设置：
	 */
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	/**
	 * 获取：
	 */
	public String getAttribute4() {
		return attribute4;
	}
	/**
	 * 设置：
	 */
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	/**
	 * 获取：
	 */
	public String getAttribute5() {
		return attribute5;
	}
	/**
	 * 设置：
	 */
	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}
	/**
	 * 获取：
	 */
	public String getAttribute6() {
		return attribute6;
	}
}
