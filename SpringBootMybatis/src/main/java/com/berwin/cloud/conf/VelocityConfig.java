package com.berwin.cloud.conf;

import org.apache.velocity.context.Context;
import org.apache.velocity.tools.ToolboxFactory;
import org.apache.velocity.tools.config.ToolboxConfiguration;
import org.apache.velocity.tools.config.XmlFactoryConfiguration;
import org.apache.velocity.tools.view.ViewToolContext;
import org.springframework.boot.autoconfigure.velocity.VelocityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.view.velocity.VelocityToolboxView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;

/**
 * @author zengnianmei
 * @version 1.0, 2016/9/12.
 */
@Configuration
public class VelocityConfig {
    @Bean(name = "velocityViewResolver")
    public VelocityViewResolver  velocityViewResolver(VelocityProperties properties) {
        VelocityViewResolver viewResolver = new VelocityViewResolver();
        viewResolver.setViewClass(VelocityLayoutToolboxView.class);
        properties.applyToViewResolver(viewResolver);// 设置默认属性，比如前缀和后缀
        return viewResolver;
    }
}
class VelocityLayoutToolboxView extends VelocityToolboxView {
    private VelocityLayoutToolboxView() {
    }
    @Override
    protected Context createVelocityContext(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViewToolContext ctx = new ViewToolContext(this.getVelocityEngine(), request, response, this.getServletContext());
        if (this.getToolboxConfigLocation() != null) {
            XmlFactoryConfiguration factory = new XmlFactoryConfiguration();
            factory.read(ResourceUtils.getURL(getToolboxConfigLocation()).openStream());
            ToolboxFactory toolboxFactory = factory.createFactory();
            toolboxFactory.configure(factory);
            Collection<ToolboxConfiguration> toolboxes = factory.getToolboxes();
            for (ToolboxConfiguration tc : toolboxes) {
                ctx.addToolbox(toolboxFactory.createToolbox(tc.getScope()));// 这样操作后就可以用工具里面的东西了。
            }
        }
        if (model != null && !model.isEmpty()) {
            ctx.putAll(model);
        }
        return ctx;
    }
}
