package _enum;

/**
 * @author <a href="lizongxian@wxchina.com">ZongXian.Li </a>
 * @Description:EditType
 * @Date 2017-04-07
 * @Version 1.0
 **/
public enum EditType {

    /**
     * 新增
     */
    ADD(1),
    /**
     * 修改
     */
    MODIFY(2);

    private int index;

    private EditType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
