package io.renren.common.modules.customer.dao;

import io.renren.common.dao.BaseDao;
import io.renren.common.modules.customer.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 客服联系方式表
 *
 * @author shican 1208296327@qq.com
 * @since 1.0.0 2024-01-18
 */
@Mapper
public interface CustomerDao extends BaseDao<CustomerEntity> {

    /**
     * 删除所有的客服配置记录
     */
	void deleteCustomerAll();

    /**
     * 查询后台配置的客服联系方式
     */
    CustomerEntity getCustomerAll();
}