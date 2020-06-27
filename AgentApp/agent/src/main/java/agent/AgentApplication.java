package agent;



import agent.model.vehicle.mappings.*;
import agent.repository.vehicle.*;
import agent.repository.vehicle.mappingsRepo.*;
import agent.soap.VehicleClient;
import agent.soap.gen.*;
import org.dozer.DozerBeanMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableRabbit
public class AgentApplication {
	@Autowired
	VehicleClient vehicleClient;
	@Autowired
	FuelRepo fuelRepo;
	@Autowired
	BrandRepo brandRepo;
	@Autowired
	ModelRepo modelRepo;
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	TransmissionRepo transmissionRepo;

	@Autowired
	FuelMappingRepo fuelMappingRepo;
	@Autowired
	ModelMappingRepo modelMappingRepo;
	@Autowired
	BrandMappingRepo brandMappingRepo;
	@Autowired
	CategoryMappingRepo categoryMappingRepo;
	@Autowired
	TransmissionMappingRepo transmissionMappingRepo;

	@Value("${queue.vehicleParts.name}")
	private String testQueue;

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
	}

	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(consumerJackson2MessageConverter());
		return rabbitTemplate;
	}

	@Bean
	public Jackson2JsonMessageConverter consumerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Queue queue(){
		return new Queue(testQueue,true);
	}

	@Bean
	DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}

	@EventListener(ApplicationReadyEvent.class)
	public void  updateDatabase(){
		List<Fuel> fuels = vehicleClient.getFuels().getValue().getItem();
		for (Fuel fuel : fuels) {
			agent.model.vehicle.Fuel f = fuelRepo.findByName(fuel.getName());
			if (f == null) {
				f = mapper().map(fuel, agent.model.vehicle.Fuel.class);
				f = fuelRepo.save(f);
			}
			FuelMapping fm = new FuelMapping();
			fm.setFuelAgent(f);
			fm.setFuelBackId(fuel.getId());
			fuelMappingRepo.save(fm);
		}

		List<Category> categories = vehicleClient.getCategories().getValue().getItem();
		for (Category category : categories) {
			agent.model.vehicle.Category c = categoryRepo.findByName(category.getName());
			if (c == null) {
				c = mapper().map(category, agent.model.vehicle.Category.class);
				c = categoryRepo.save(c);
			}
			CategoryMapping cm = new CategoryMapping();
			cm.setCategoryAgent(c);
			cm.setCategoryBackId(category.getId());
			categoryMappingRepo.save(cm);
		}

		List<Transmission> transmissions = vehicleClient.getTransmissions().getValue().getItem();
		for (Transmission transmission : transmissions) {
			agent.model.vehicle.Transmission t = transmissionRepo.findByName(transmission.getName());
			if (t == null) {
				t = mapper().map(transmission, agent.model.vehicle.Transmission.class);
				t = transmissionRepo.save(t);
			}
			TransmissionMapping tm = new TransmissionMapping();
			tm.setTransmissionAgent(t);
			tm.setTransmissionBackId(transmission.getId());
			transmissionMappingRepo.save(tm);
		}

		List<BrandDTO> brandDTOS = vehicleClient.getBrands().getValue().getItem();
		for (BrandDTO brandDTO : brandDTOS) {
			System.out.println("Models for brand: " + brandDTO.getName());
			for (ModelDTO model: brandDTO.getModels()) {
				System.out.println(model.getName());
			}
			agent.model.vehicle.Brand b = brandRepo.findByName(brandDTO.getName());
			if (b == null) {
				b = new agent.model.vehicle.Brand();
				b.setName(brandDTO.getName());
				b.setDeleted(false);
				b.setModels(new ArrayList<agent.model.vehicle.Model>());
				b = brandRepo.save(b);

			}
			for (ModelDTO modelDTO: brandDTO.getModels()) {
				System.out.println("Model " + modelDTO.getName() +  " ID: "+ modelDTO.getId());
				agent.model.vehicle.Model model = modelRepo.findByName(modelDTO.getName());
				if(model == null) {
					model = new agent.model.vehicle.Model();
					model.setBrand(b);
					model.setDeleted(false);
					model.setName(modelDTO.getName());
					model = modelRepo.save(model);
					//Todo: check circular dependencies.
					b.getModels().add(model);
				}

				ModelMapping mm = new ModelMapping();
				mm.setModelAgent(model);
				mm.setModelBackId(modelDTO.getId());
				modelMappingRepo.save(mm);
			}
			brandRepo.save(b);
			BrandMapping bm = new BrandMapping();
			bm.setBrandAgent(b);
			bm.setBrandBackId(brandDTO.getId());
			brandMappingRepo.save(bm);
		}
	}

}
