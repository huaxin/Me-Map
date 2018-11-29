package io.renren.common.base;

import java.util.Date;

public class BaseEntity {
    /**
     * 创建ID
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
     * 更新人
     */
    private String lastUpdatedName;
    /**
     * 更新时间
     */
    private Date lastUpdateDate;

    /**
     * 获取：创建ID
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置：创建ID
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
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
     * 设置：更新人
     */
    public void setLastUpdatedName(String lastUpdatedName) {
        this.lastUpdatedName = lastUpdatedName;
    }
    /**
     * 获取：更新人
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
}
