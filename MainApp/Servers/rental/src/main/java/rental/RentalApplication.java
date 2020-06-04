package rental;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;


@SpringBootApplication
public class RentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalApplication.class, args);
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
				"rental.**",
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
