package io.renren.modules.map.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-21 17:04:59
 */
@TableName("map_dir")
public class MapDirEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 图层目录ID
	 */
	@TableId
	private Long id;
	/**
	 * 图层目录
	 */
	private String name;
	/**
	 * 顺序
	 */
	private Integer seq;
	/**
	 * 状态，1有效；0无效
	 */
	private String status;
	/**
	 * 父节点
	 */
	private Long parentId;
	/**
	 * 全路径
	 */
	private String fullPath;
	/**
	 * 全路径名称
	 */
	private String fullPathName;
	/**
	 * 创建人
	 */
	private Long createdBy;
	/**
	 * 创建人
	 */
	private String createdName;
	/**
	 * 创建时间
	 */
	private Date creationDate;
	/**
	 * 更新ID
	 */
	private Long lastUpdatedBy;
	/**
	 * 更新名
	 */
	private String lastUpdatedName;
	/**
	 * 更新时间
	 */
	private Date lastUpdateDate;
	/**
	 * 描述
	 */
	private String description;

	/**
	 * 父菜单名称
	 */
	@TableField(exist=false)
	private String parentName;
	/**
	 * 类型(图层、图层目录)
	 */
	@TableField(exist=false)
	private String type;
	/**
	 * ztree属性
	 */
	@TableField(exist=false)
	private Boolean open;
	@TableField(exist=false)
	private List<?> list;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	/**
	 * 设置：图层目录ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：图层目录ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：图层目录
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：图层目录
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：顺序
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：顺序
	 */
	public Integer getSeq() {
		return seq;
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
	 * 设置：父节点
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父节点
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：全路径
	 */
	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	/**
	 * 获取：全路径
	 */
	public String getFullPath() {
		return fullPath;
	}
	/**
	 * 设置：全路径名称
	 */
	public void setFullPathName(String fullPathName) {
		this.fullPathName = fullPathName;
	}
	/**
	 * 获取：全路径名称
	 */
	public String getFullPathName() {
		return fullPathName;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreatedBy() {
		return createdBy;
	}
	/**
	 * 设置：创建人
	 */
	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}
	/**
	 * 获取：创建人
	 */
	public String getCreatedName() {
		return createdName;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreationDate() {
		return creationDate;
	}
	/**
	 * 设置：更新ID
	 */
	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	/**
	 * 获取：更新ID
	 */
	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	/**
	 * 设置：更新名
	 */
	public void setLastUpdatedName(String lastUpdatedName) {
		this.lastUpdatedName = lastUpdatedName;
	}
	/**
	 * 获取：更新名
	 */
	public String getLastUpdatedName() {
		return lastUpdatedName;
	}
	/**
	 * 设置：更新时间
	 */
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getLastUpdateDate() {
		return lastUpdateDate;
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
}
