package com.city.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.city.system.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("system_dictionary_type")
@ApiModel(value="DictionaryType对象", description="")
public class DictionaryType extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典类型名称")
    private String name;

    @ApiModelProperty(value = "字典类型")
    private String type;


}
