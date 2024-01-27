package io.renren.common.modules.customer.service;

import io.renren.common.service.CrudService;
import io.renren.common.modules.customer.dto.CustomerDTO;
import io.renren.common.modules.customer.entity.CustomerEntity;

/**
 * 客服联系方式表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2024-01-18
 */
public interface CustomerService extends CrudService<CustomerEntity, CustomerDTO> {

    /**
     * 删除所有的客服配置记录
     */
    void deleteCustomerAll();

    /**
     * 查询后台配置的客服联系方式
     */
    CustomerEntity getCustomerAll();

}