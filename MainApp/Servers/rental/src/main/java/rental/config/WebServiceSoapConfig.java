package rental.config;

import com.netflix.client.ClientException;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.soap.server.endpoint.SoapFaultDefinition;
import org.springframework.ws.soap.server.endpoint.SoapFaultMappingExceptionResolver;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.xml.bind.JAXBException;
import java.util.Properties;

@Configuration
@EnableWs
public class WebServiceSoapConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/rental/ws/*");
    }

    @Bean(name = "rental")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema rentalSchema) {
        DefaultWsdl11Definition wsdl11Definition = new ReflectionWsdl11Definition();
        wsdl11Definition.setPortTypeName("Rental");
        wsdl11Definition.setLocationUri("/rental/ws");
        wsdl11Definition.setTargetNamespace("http://www.vehicle.com/rental");
        wsdl11Definition.setSchema(rentalSchema);
        wsdl11Definition.setRequestSuffix("Request");
        wsdl11Definition.setResponseSuffix("Response");
        wsdl11Definition.setFaultSuffix("commonFault");
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema rentalSchema() {
        return new SimpleXsdSchema(new ClassPathResource("rental.xsd"));
    }

    @Bean
    public SoapFaultMappingExceptionResolver exceptionResolver() throws JAXBException {
        SoapFaultMappingExceptionResolver exceptionResolver = new SoapFaultExceptionResolver();

        SoapFaultDefinition faultDefinition = new SoapFaultDefinition();
        faultDefinition.setFaultCode(SoapFaultDefinition.SERVER);
        exceptionResolver.setDefaultFault(faultDefinition);

        Properties errorMappings = new Properties();
        errorMappings.setProperty(Exception.class.getName(), SoapFaultDefinition.SERVER.toString());
        errorMappings.setProperty(ClientException.class.getName(), SoapFaultDefinition.CLIENT.toString());
        errorMappings.setProperty(DataIntegrityViolationException.class.getName(), SoapFaultDefinition.CLIENT.toString());
        exceptionResolver.setExceptionMappings(errorMappings);
        exceptionResolver.setOrder(1);
        return exceptionResolver;
    }
}
