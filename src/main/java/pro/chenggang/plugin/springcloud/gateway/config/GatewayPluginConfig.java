package pro.chenggang.plugin.springcloud.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.chenggang.plugin.springcloud.gateway.filter.GatewayContextFilter;
import pro.chenggang.plugin.springcloud.gateway.properties.GatewayPluginProperties;

/**
 * Gateway Plugin Config
 * @author chenggang
 * @date 2019/01/29
 */
@Slf4j
@Configuration
public class GatewayPluginConfig {

    @Bean
    @ConditionalOnMissingBean(GatewayPluginProperties.class)
    public GatewayPluginProperties gatewayPluginProperties(){
        return new GatewayPluginProperties();
    }

    @Bean
    @ConditionalOnMissingBean(GatewayContextFilter.class)
    @ConditionalOnProperty(prefix = GatewayPluginProperties.GATEWAY_PLUGIN_PROPERTIES_PREFIX,value = "log-request",havingValue = "true")
    public GatewayContextFilter gatewayContextFilter(GatewayPluginProperties gatewayPluginProperties){
        GatewayContextFilter gatewayContextFilter = new GatewayContextFilter(gatewayPluginProperties);
        log.debug("Load GatewayContextFilter Config Bean");
        return gatewayContextFilter;
    }

}
