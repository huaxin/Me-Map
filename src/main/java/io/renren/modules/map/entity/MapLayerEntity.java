package io.renren.modules.map.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.renren.common.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-11-21 17:04:59
 */
@TableName("map_layer")
public class MapLayerEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 图层服务名
	 */
	private String layerName;
	/**
	 * 英文名
	 */
	private String layerEnglishName;
	/**
	 * 图层目录ID
	 */
	private Long dirId;
	/**
	 * 图层目录
	 */
	private String dirName;
	/**
	 * 别名
	 */
	private String alias;
	/**
	 * 访问链接
	 */
	private String layerUrl;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 数据类型
	 */
	private String layerType;
	/**
	 * WFS命名空间
	 */
	private String featureNameSpace;
	/**
	 * WFS要素类型
	 */
	private String featureType;
	/**
	 * WFS版本
	 */
	private String version;
	/**
	 * 分辨率WMS
	 */
	private String resolution;
	/**
	 * 坐标系WMS
	 */
	private String projection;
	/**
	 * 地图范围WMS
	 */
	private String bound;
	/**
	 * 
	 */
	private String extent;
	/**
	 * 图层名WMS
	 */
	private String layers;
	/**
	 * WMS瓦片尺寸(256,256)
	 */
	private String tileSize;
	/**
	 * WMS(原点)
	 */
	private String tileOrigin;
	/**
	 * 地图角度
	 */
	private String angle;
	/**
	 * 排序
	 */
	private Integer seq;
	/**
	 * 
	 */
	private String display;
	/**
	 * 状态(0:不正常，1正常)
	 */
	private String status;
	/**
	 * 是否删除
	 */
	private String deleted;
	/**
	 * 删除人ID
	 */
	private Long deletedBy;
	/**
	 * 删除人
	 */
	private String deletedName;
	/**
	 * 删除时间
	 */
	private Date deletedDate;
	/**
	 * 备注
	 */
	private String description;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：图层服务名
	 */
	public void setLayerName(String layerName) {
		this.layerName = layerName;
	}
	/**
	 * 获取：图层服务名
	 */
	public String getLayerName() {
		return layerName;
	}
	/**
	 * 设置：英文名
	 */
	public void setLayerEnglishName(String layerEnglishName) {
		this.layerEnglishName = layerEnglishName;
	}
	/**
	 * 获取：英文名
	 */
	public String getLayerEnglishName() {
		return layerEnglishName;
	}
	/**
	 * 设置：图层目录ID
	 */
	public void setDirId(Long dirId) {
		this.dirId = dirId;
	}
	/**
	 * 获取：图层目录ID
	 */
	public Long getDirId() {
		return dirId;
	}
	/**
	 * 设置：图层目录
	 */
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
	/**
	 * 获取：图层目录
	 */
	public String getDirName() {
		return dirName;
	}
	/**
	 * 设置：别名
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * 获取：别名
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * 设置：访问链接
	 */
	public void setLayerUrl(String layerUrl) {
		this.layerUrl = layerUrl;
	}
	/**
	 * 获取：访问链接
	 */
	public String getLayerUrl() {
		return layerUrl;
	}
	/**
	 * 设置：来源
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * 获取：来源
	 */
	public String getSource() {
		return source;
	}
	/**
	 * 设置：数据类型
	 */
	public void setLayerType(String layerType) {
		this.layerType = layerType;
	}
	/**
	 * 获取：数据类型
	 */
	public String getLayerType() {
		return layerType;
	}
	/**
	 * 设置：WFS命名空间
	 */
	public void setFeatureNameSpace(String featureNameSpace) {
		this.featureNameSpace = featureNameSpace;
	}
	/**
	 * 获取：WFS命名空间
	 */
	public String getFeatureNameSpace() {
		return featureNameSpace;
	}
	/**
	 * 设置：WFS要素类型
	 */
	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}
	/**
	 * 获取：WFS要素类型
	 */
	public String getFeatureType() {
		return featureType;
	}
	/**
	 * 设置：WFS版本
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：WFS版本
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：分辨率WMS
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	/**
	 * 获取：分辨率WMS
	 */
	public String getResolution() {
		return resolution;
	}
	/**
	 * 设置：坐标系WMS
	 */
	public void setProjection(String projection) {
		this.projection = projection;
	}
	/**
	 * 获取：坐标系WMS
	 */
	public String getProjection() {
		return projection;
	}
	/**
	 * 设置：地图范围WMS
	 */
	public void setBound(String bound) {
		this.bound = bound;
	}
	/**
	 * 获取：地图范围WMS
	 */
	public String getBound() {
		return bound;
	}
	/**
	 * 设置：
	 */
	public void setExtent(String extent) {
		this.extent = extent;
	}
	/**
	 * 获取：
	 */
	public String getExtent() {
		return extent;
	}
	/**
	 * 设置：图层名WMS
	 */
	public void setLayers(String layers) {
		this.layers = layers;
	}
	/**
	 * 获取：图层名WMS
	 */
	public String getLayers() {
		return layers;
	}
	/**
	 * 设置：WMS瓦片尺寸(256,256)
	 */
	public void setTileSize(String tileSize) {
		this.tileSize = tileSize;
	}
	/**
	 * 获取：WMS瓦片尺寸(256,256)
	 */
	public String getTileSize() {
		return tileSize;
	}
	/**
	 * 设置：WMS(原点)
	 */
	public void setTileOrigin(String tileOrigin) {
		this.tileOrigin = tileOrigin;
	}
	/**
	 * 获取：WMS(原点)
	 */
	public String getTileOrigin() {
		return tileOrigin;
	}
	/**
	 * 设置：地图角度
	 */
	public void setAngle(String angle) {
		this.angle = angle;
	}
	/**
	 * 获取：地图角度
	 */
	public String getAngle() {
		return angle;
	}
	/**
	 * 设置：排序
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSeq() {
		return seq;
	}
	/**
	 * 设置：
	 */
	public void setDisplay(String display) {
		this.display = display;
	}
	/**
	 * 获取：
	 */
	public String getDisplay() {
		return display;
	}
	/**
	 * 设置：状态(0:不正常，1正常)
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态(0:不正常，1正常)
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除
	 */
	public String getDeleted() {
		return deleted;
	}
	/**
	 * 设置：删除人ID
	 */
	public void setDeletedBy(Long deletedBy) {
		this.deletedBy = deletedBy;
	}
	/**
	 * 获取：删除人ID
	 */
	public Long getDeletedBy() {
		return deletedBy;
	}
	/**
	 * 设置：删除人
	 */
	public void setDeletedName(String deletedName) {
		this.deletedName = deletedName;
	}
	/**
	 * 获取：删除人
	 */
	public String getDeletedName() {
		return deletedName;
	}
	/**
	 * 设置：删除时间
	 */
	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}
	/**
	 * 获取：删除时间
	 */
	public Date getDeletedDate() {
		return deletedDate;
	}
	/**
	 * 设置：备注
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：备注
	 */
	public String getDescription() {
		return description;
	}
}
