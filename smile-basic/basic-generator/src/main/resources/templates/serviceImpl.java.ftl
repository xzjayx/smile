package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import ${customizefo.qoPackage}.${entity}Qo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smile.basic.core.base.Result;
/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

     @Override
     public ${entity} findOne(${entity}Qo ${customizefo.capEntity}Qo) {
     QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", ${customizefo.capEntity}Qo.getId());
        queryWrapper.allEq(map);
        return getOne(queryWrapper);
     }

     @Override
     public Page<${entity}> listPage(${entity}Qo ${customizefo.capEntity}Qo) {
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(${customizefo.capEntity}Qo.getId()), "id", ${customizefo.capEntity}Qo.getId());
        Page<${entity}> page = new Page<>(${customizefo.capEntity}Qo.getBaseQueryQo().getCurrent(),
        ${customizefo.capEntity}Qo.getBaseQueryQo().getSize());
        return page(page,queryWrapper);
     }

     @Override
     public Result<?> deleteByT(${entity}Qo ${customizefo.capEntity}Qo) {
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Objects.nonNull(${customizefo.capEntity}Qo.getId()),${entity}::getId,${customizefo.capEntity}Qo.getId());
        return remove(queryWrapper) ? Result.success() : Result.failed();
     }


}
</#if>
