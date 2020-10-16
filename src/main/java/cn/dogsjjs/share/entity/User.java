package cn.dogsjjs.share.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户实体类
 * </p>
 *
 * @author 断水流
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    private String username;

    @TableField(fill = FieldFill.INSERT)
    private String password;

    @TableField(fill = FieldFill.INSERT)
    private String avatar;

    private Integer type = 1;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private String salt;

    @TableLogic
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
