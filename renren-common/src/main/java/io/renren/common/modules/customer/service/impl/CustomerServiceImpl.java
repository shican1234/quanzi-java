package io.renren.common.modules.customer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.service.impl.CrudServiceImpl;
import io.renren.common.modules.customer.dao.CustomerDao;
import io.renren.common.modules.customer.dto.CustomerDTO;
import io.renren.common.modules.customer.entity.CustomerEntity;
import io.renren.common.modules.customer.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 客服联系方式表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2024-01-18
 */
@Service
public class CustomerServiceImpl extends CrudServiceImpl<CustomerDao, CustomerEntity, CustomerDTO> implements CustomerService {

    @Override
    public QueryWrapper<CustomerEntity> getWrapper(Map<String, Object> params){
        String id = (String)params.get("id");

        QueryWrapper<CustomerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id), "id", id);

        return wrapper;
    }


    @Override
    public void deleteCustomerAll() {
        baseDao.deleteCustomerAll();
    }

    @Override
    public CustomerEntity getCustomerAll() {
        return baseDao.getCustomerAll();
    }
}