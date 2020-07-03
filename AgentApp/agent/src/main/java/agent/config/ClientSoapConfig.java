package agent.config;

import agent.soap.UsersClient;
import agent.soap.RentalClient;
import agent.soap.VehicleClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class ClientSoapConfig {


        @Bean
        public Jaxb2Marshaller marshaller() {
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setContextPath("agent.soap.gen");
            return marshaller;
        }
        @Bean
        public VehicleClient vehicleClient(Jaxb2Marshaller marshaller) {
            VehicleClient client = new VehicleClient();
            client.setDefaultUri("http://localhost:8087/vehicle/ws");
            client.setMarshaller(marshaller);
            client.setUnmarshaller(marshaller);
            return client;
        }
        @Bean
        public RentalClient rentalClient(Jaxb2Marshaller marshaller) {
            RentalClient client = new RentalClient();
            client.setDefaultUri("http://localhost:8087/rental/ws");
            client.setMarshaller(marshaller);
            client.setUnmarshaller(marshaller);
            return client;
        }
        @Bean
        public UsersClient usersClient(Jaxb2Marshaller marshaller) {
            UsersClient client = new UsersClient();
            client.setDefaultUri("http://localhost:8087/user/ws");
            client.setMarshaller(marshaller);
            client.setUnmarshaller(marshaller);
            return client;
        }

}
