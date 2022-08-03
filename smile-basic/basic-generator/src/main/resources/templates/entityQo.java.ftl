package ${customizefo.qoPackage};

import com.smile.basic.core.qo.base.BaseQueryQo;
<#if swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
</#if>

/**
* <p>
    * ${table.comment!}
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if entityLombokModel>
@Data
</#if>
<#if swagger>
@ApiModel(value = "${entity}Qo对象", description = "${table.comment!}Qo对象")
</#if>
public class ${entity}Qo {

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.comment!?length gt 0>
        <#if swagger>
    @ApiModelProperty("${field.comment}")
        <#else>
    /**
    * ${field.comment}
    */
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#if swagger>
    @ApiModelProperty("基本查询对象")
</#if>
    private BaseQueryQo baseQueryQo;
<#------------  END 字段循环遍历  ---------->

}
