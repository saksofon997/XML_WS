package vehicle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.dozer.DozerBeanMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@EnableRabbit
public class VehicleApplication {
	@Value("${queue.vehicleParts.name}")
	private String vehiclePartsQueue;

	public static void main(String[] args) {
		SpringApplication.run(VehicleApplication.class, args);
	}

	@Bean
	public Queue queue(){
		return new Queue(vehiclePartsQueue,true);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	DozerBeanMapper mapper()
	{
		return new DozerBeanMapper();
	}
	@Bean
	XStream xstream(){
		XStream xstream = new XStream();
		// clear out existing permissions and set own ones
		xstream.addPermission(NoTypePermission.NONE);
		// allow any type from the same package
		xstream.allowTypesByWildcard(new String[] {
				"saga.**",
				"vehicle.**",
				"org.axonframework.**",
				"java.**",
				"com.thoughtworks.xstream.**"
		});

		return xstream;
	}

	@Bean
	@Primary
	public Serializer serializer(XStream xStream) {
		return XStreamSerializer.builder().xStream(xStream).build();
	}

}
