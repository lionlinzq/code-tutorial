package pers.lionlinzq.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import pers.lionlinzq.utils.LocalDateTimeConverter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * 年龄
     */
    @ExcelProperty("年龄")
    private Integer age;

    /**
     * 邮箱
     */
    @ExcelProperty("邮箱")
    private String email;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}