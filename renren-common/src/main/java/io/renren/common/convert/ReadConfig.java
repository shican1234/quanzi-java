package io.renren.common.convert;

import io.renren.common.service.SysParamsService;
import io.renren.common.smsconfig.SmsConfig;
import org.dromara.sms4j.aliyun.config.AlibabaConfig;
import org.dromara.sms4j.core.datainterface.SmsReadConfig;
import org.dromara.sms4j.provider.config.BaseConfig;
import org.dromara.sms4j.tencent.config.TencentConfig;
import org.dromara.sms4j.unisms.config.UniConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadConfig implements SmsReadConfig {

    @Autowired
    private SysParamsService sysParamsService;
    //这里的configId指的是框架在调用该接口方法时候会传递进来的参数，用户可以根据此参数作为标准来动态的进行查询
    @Override
    public BaseConfig getSupplierConfig(String configId) {
        SmsConfig config = sysParamsService.getValueObject(configId, SmsConfig.class);
        if(config.getType() == 1){
            AlibabaConfig alibabaConfig = new AlibabaConfig();
            alibabaConfig.setAccessKeyId(config.getAliyunAccessKeyId());
            alibabaConfig.setSignature(config.getAliyunSignature());
            alibabaConfig.setAccessKeySecret(config.getAliyunAccessKeySecret());
            alibabaConfig.setTemplateId(config.getAliyunTemplateId());
            alibabaConfig.setTemplateName(config.getAliyunTemplateName());
            return alibabaConfig;
        } else if (config.getType() == 2) {
            TencentConfig tencentConfig = new TencentConfig();
            tencentConfig.setAccessKeyId(config.getTencentAccessKeyId());
            tencentConfig.setSignature(config.getTencentSignature());
            tencentConfig.setAccessKeySecret(config.getTencentAccessKeySecret());
            tencentConfig.setTemplateId(config.getTencentTemplateId());
            tencentConfig.setSdkAppId(config.getTencentAppId());
            return tencentConfig;
        }else {
            return null;
        }

    }

    @Override
    public List<BaseConfig> getSupplierConfigList() {
        //此处仅为示例，实际环境中，数据可以来自任意位置，
        return null;
    }
}
