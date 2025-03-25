package domain;


/**
 * 租户数据库路由表的实体类
 */
public class TenantDB {
    /**
     * 数据库实例编码
     */
    private Long tenantdbcode;
    /**
     * 租户编码
     */
    private Long tenantcode;
    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 连接串(密文)
     */
    private String connstr;
    /**
     * 名称名称
     */
    private String tenantdbname;
    /**
     * 数据库分类1.读写;2.读;3.报表扩展
     */
    private Integer tenantdbtype;
    /**
     * 行业产品编码
     */
    private Long productcode;
    /**
     * 状态： 1启用 2 停用
     */
    private int status;


    public Long getTenantdbcode() {
        return tenantdbcode;
    }

    public void setTenantdbcode(Long tenantdbcode) {
        this.tenantdbcode = tenantdbcode;
    }

    public Long getTenantcode() {
        return tenantcode;
    }

    public void setTenantcode(Long tenantcode) {
        this.tenantcode = tenantcode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getConnstr() {
        return connstr;
    }

    public void setConnstr(String connstr) {
        this.connstr = connstr;
    }

    public String getTenantdbname() {
        return tenantdbname;
    }

    public void setTenantdbname(String tenantdbname) {
        this.tenantdbname = tenantdbname;
    }

    public Integer getTenantdbtype() {
        return tenantdbtype;
    }

    public void setTenantdbtype(Integer tenantdbtype) {
        this.tenantdbtype = tenantdbtype;
    }

    public Long getProductcode() {
        return productcode;
    }

    public void setProductcode(Long productcode) {
        this.productcode = productcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

