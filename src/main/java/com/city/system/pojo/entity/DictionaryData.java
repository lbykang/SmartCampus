package com.city.system.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("system_dictionary_data")
@ApiModel(value="DictionaryData对象", description="")
public class DictionaryData extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "字典数据名称")
    private String name;

    @ApiModelProperty(value = "键值")
    private Boolean value;

    @ApiModelProperty(value = "字典类型（FK）")
    private Long type;


}
