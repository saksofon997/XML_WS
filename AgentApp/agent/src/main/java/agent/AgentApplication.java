package agent;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
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

}
