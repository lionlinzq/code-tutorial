package domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.List;

/**
 * 登录用户的信息<br>
 * 用于存放当前会话所需的一些信息
 *
 * @author linjing
 * @since 2017/8/8
 */
public class SessionInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountInfoCode;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountCode;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tenantCode;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productCode;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productVersionCode;
    private Long clientTypeCode;//终端类型：1 Web管理端，2 iphone APP，3 安卓APP 7 鸿蒙OS
    private String tokenId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long orgCode;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userInfoId;
    private String userInfoName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long positionCode;
    private String positionName;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long memberCode;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long refPositionCode;
    /**
     * 当前职位类别
     */
    private String categoryCode;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long orgStructTypeId;

    private String userName;

    private String userName1;
    private String userName2;
    private String userName3;
    /**
     * 租户名称
     */
    private String tenantName;
    /**
     * 当前应用
     */
    private String appCode;
    /**
     * 可用应用
     */
    private List<String> appCodes;
    /**
     * 当前租户子产品列表
     */
    private List<String> subPdCodes;
    /**
     * 组织codepath
     */
    private String codepath;
    /**
     * 是否为末级组织节点:true,false
     */
    private String isleaforg;

    private Long metamodeltype = 1L;

    private Boolean isSmsLogin = false;//是否是短信登录的

    public SessionInfo() {
    }

    public SessionInfo(Long tenantCode, Long productCode, Long productVersionCode, String tokenId) {
        this.tenantCode = tenantCode;
        this.productCode = productCode;
        this.productVersionCode = productVersionCode;
        this.tokenId = tokenId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Long getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(Long accountCode) {
        this.accountCode = accountCode;
    }

    public Long getTenantCode() {
        return tenantCode;
    }

    public void setTenantCode(Long tenantCode) {
        this.tenantCode = tenantCode;
    }

    public Long getProductVersionCode() {
        return productVersionCode;
    }

    public void setProductVersionCode(Long productVersionCode) {
        this.productVersionCode = productVersionCode;
    }

    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public Long getClientTypeCode() {
        return clientTypeCode;
    }

    public void setClientTypeCode(Long clientTypeCode) {
        this.clientTypeCode = clientTypeCode;
    }

    public Long getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Long orgCode) {
        this.orgCode = orgCode;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Long getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(Long positionCode) {
        this.positionCode = positionCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(Long memberCode) {
        this.memberCode = memberCode;
    }

    public Long getRefPositionCode() {
        return refPositionCode;
    }

    public void setRefPositionCode(Long refPositionCode) {
        this.refPositionCode = refPositionCode;
    }

    public Long getOrgStructTypeId() {
        return orgStructTypeId;
    }

    public void setOrgStructTypeId(Long orgStructTypeId) {
        this.orgStructTypeId = orgStructTypeId;
    }

    public String getUserInfoName() {
        return userInfoName;
    }

    public void setUserInfoName(String userInfoName) {
        this.userInfoName = userInfoName;
    }

    public Long getMetamodeltype() {
        return metamodeltype;
    }

    public void setMetamodeltype(Long metamodeltype) {
        this.metamodeltype = metamodeltype;
    }

    public SessionInfo(Long productVersionCode, Long tenantCode) {
        this.tenantCode = tenantCode;
        this.productVersionCode = productVersionCode;
    }

    public String getUserName1() {
        return userName1;
    }

    public void setUserName1(String userName1) {
        this.userName1 = userName1;
    }

    public String getUserName2() {
        return userName2;
    }

    public void setUserName2(String userName2) {
        this.userName2 = userName2;
    }

    public String getUserName3() {
        return userName3;
    }

    public void setUserName3(String userName3) {
        this.userName3 = userName3;
    }

    public String getCodepath() {
        return codepath;
    }

    public void setCodepath(String codepath) {
        this.codepath = codepath;
    }

    public String getIsleaforg() {
        return isleaforg;
    }

    public void setIsleaforg(String isleaforg) {
        this.isleaforg = isleaforg;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public List<String> getAppCodes() {
        return appCodes;
    }

    public void setAppCodes(List<String> appCodes) {
        this.appCodes = appCodes;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public List<String> getSubPdCodes() {
        return subPdCodes;
    }

    public void setSubPdCodes(List<String> subPdCodes) {
        this.subPdCodes = subPdCodes;
    }

    public Long getAccountInfoCode() {
        return accountInfoCode;
    }

    public void setAccountInfoCode(Long accountInfoCode) {
        this.accountInfoCode = accountInfoCode;
    }

    public Boolean getIsSmsLogin() {
        return isSmsLogin;
    }

    public void setIsSmsLogin(Boolean isSmsLogin) {
        this.isSmsLogin = isSmsLogin;
    }
}
