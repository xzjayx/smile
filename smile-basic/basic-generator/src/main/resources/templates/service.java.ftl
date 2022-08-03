package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${customizefo.qoPackage}.${entity}Qo;
import com.smile.basic.core.base.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
    * 根据qoEntity查询唯一对象
    * */
    ${entity} findOne(${entity}Qo ${customizefo.capEntity}Qo);

    /**
    * 根据qoEntity查询对象分页
    * */
    Page<${entity}> listPage(${entity}Qo ${customizefo.capEntity}Qo);

    /**
    * 根据qoEntity删除符合对象
    * */
    Result<?> deleteByT(${entity}Qo ${customizefo.capEntity}Qo);
}
</#if>
