package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.base.BaseEntity;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 字典条目表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-10-27 15:39:28
 */
@TableName("cdp_dict_item")
public class CdpDictItemEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 字典ID
	 */
	private Long dictId;
	/**
	 * 条目名称
	 */
	private String itemName;
	/**
	 * 条目值
	 */
	private String itemValue;
	/**
	 * 状态，1有效；0无效
	 */
	private String status;
	/**
	 * 顺序
	 */
	private BigDecimal orderNum;
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
	 * 设置：字典ID
	 */
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}
	/**
	 * 获取：字典ID
	 */
	public Long getDictId() {
		return dictId;
	}
	/**
	 * 设置：条目名称
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * 获取：条目名称
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * 设置：条目值
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	/**
	 * 获取：条目值
	 */
	public String getItemValue() {
		return itemValue;
	}
	/**
	 * 设置：状态，1有效；0无效
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态，1有效；0无效
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：顺序
	 */
	public void setOrderNum(BigDecimal orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * 获取：顺序
	 */
	public BigDecimal getOrderNum() {
		return orderNum;
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
}
