package agent;



import agent.exceptions.ConversionFailedError;
import agent.exceptions.EntityNotFound;
import agent.model.rental.Bundle;
import agent.model.rental.Rental;
import agent.model.rental.mappings.BundleMapping;
import agent.model.rental.mappings.RentalMapping;
import agent.model.user.User;
import agent.model.vehicle.mappings.*;
import agent.model.user.mappings.UserMapping;
import agent.repository.rental.BundleRepository;
import agent.repository.rental.RentalRepository;
import agent.repository.rental.mappingsRepo.BundleMappingRepo;
import agent.repository.rental.mappingsRepo.RentalMappingRepo;
import agent.repository.user.UserMappingRepo;
import agent.repository.user.UserRepository;
import agent.repository.vehicle.*;
import agent.repository.vehicle.mappingsRepo.*;
import agent.service.vehicle.VehicleService;
import agent.soap.RentalClient;
import agent.soap.UsersClient;
import agent.soap.VehicleClient;
import agent.soap.gen.*;
import org.dozer.DozerBeanMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableRabbit
public class AgentApplication {
	@Autowired
	VehicleClient vehicleClient;
	@Autowired
	RentalClient rentalClient;
	@Autowired
	UsersClient usersClient;
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
	UserRepository userRepository;
	@Autowired
	VehicleRepo vehicleRepo;

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
	@Autowired
	UserMappingRepo userMappingRepo;
	@Autowired
	VehicleMappingRepo vehicleMappingRepo;
	@Autowired
	VehicleService vehicleService;

	@Autowired
	BundleMappingRepo bundleMappingRepo;
	@Autowired
	RentalMappingRepo rentalMappingRepo;
	@Autowired
	RentalRepository rentalRepo;
	@Autowired
	BundleRepository bundleRepo;

	@Value("${queue.vehicleParts.name}")
	private String testQueue;

	@Value("${queue.rental.name}")
	private String rentalQueue;

	@Value("${company}")
	private String cid;

	public static void main(String[] args) {
		SpringApplication.run(AgentApplication.class, args);
	}
//
//	@Bean
//	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
//		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//		rabbitTemplate.setMessageConverter(consumerJackson2MessageConverter());
//		return rabbitTemplate;
//	}
//
//	@Bean
//	public Jackson2JsonMessageConverter consumerJackson2MessageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}

	@Bean
	public Queue queue(){
		return new Queue(testQueue,true);
	}
	@Bean
	public Queue queue2(){
		return new Queue(rentalQueue,true);
	}

	@Bean
	DozerBeanMapper mapper() {
		return new DozerBeanMapper();
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void  updateDatabase(){
//		agent.soap.gen.BundleDTO bundleDTO = new BundleDTO();
//		bundleDTO.setId(1L);
//		bundleDTO.setName("NOVI BUNDLE");
//
//		rentalClient.addBundle(bundleDTO);

		Company company = usersClient.getCompany(this.cid).getValue();
		System.out.println(company);
		if (company == null){
			System.out.println("COMPANY NOT REGISTERED");
			return;
		}

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

		List<User> agents = userRepository.findAllByCompanyNotNull();
		for (User user : agents) {
			agent.soap.gen.UserDTO saved;
			try {
				saved = usersClient.addAgent(mapper().map(user, agent.soap.gen.UserDTO.class)).getValue();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			if (saved != null) {
				UserMapping um = new UserMapping();
				um.setUserAgentId(user);
				um.setUserBackId(saved.getId());
				userMappingRepo.save(um);
				System.out.println("AGENT MAPPED: " + user.getId() + " --> " + saved.getId());
			}
		}

		List<agent.model.vehicle.Vehicle> vehicles = vehicleRepo.findAll();
		for (agent.model.vehicle.Vehicle vehicle: vehicles) {
			 Long saved = -1L;
			try {
				Vehicle vehicleToSend = vehicleService.updateVehicleSOAPParts(mapper().map(vehicle, Vehicle.class));
				saved = vehicleClient.createNewVehicle(vehicleToSend);
			} catch (EntityNotFound entityNotFound) {
				entityNotFound.printStackTrace();
				continue;
			}
			if (saved != -1) {
				VehicleMapping vm = new VehicleMapping();
				vm.setVehicleAgentId(vehicle);
				vm.setVehicleBackId(saved);
				vehicleMappingRepo.save(vm);
				System.out.println("VEHICLE MAPPED: " + vehicle.getId() + " --> " + saved);
			}
		}

		// Send rentals with bundles to MS
		List<Rental> rentals = rentalRepo.findAll();
		for(Rental rental : rentals) {
			// Rental can be in the bundle or not
			Long backBundleId = null;
			Bundle rentalBundle = rental.getBundle();
			// If rental is in the bundle
			if(rentalBundle != null) {
				// Check if bundle was already saved during previous bundle processing
				BundleMapping bundleMapping = bundleMappingRepo.findByBundleAgent(rentalBundle);
				if(bundleMapping == null){
					// If not, save bundle in MS and map the id
					BundleDTO savedBundleDTO = rentalClient.addBundle(mapper().map(rentalBundle, BundleDTO.class)).getValue();
					if(savedBundleDTO != null) {
						BundleMapping newBundleMapping = new BundleMapping();
						newBundleMapping.setBundleAgent(rentalBundle);
						newBundleMapping.setBundleBackId(savedBundleDTO.getId());
						bundleMappingRepo.save(newBundleMapping);
						backBundleId = savedBundleDTO.getId();
					}
				}else{
					backBundleId = bundleMapping.getBundleBackId();
				}
			}
			// Get the mappings for owner and vehicle
			agent.model.vehicle.Vehicle vehicle = vehicleRepo.findById(rental.getVehicleId()).get();
			VehicleMapping vehicleMapping = vehicleMappingRepo.findByVehicleAgentId(vehicle);
			User owner = userRepository.findById(rental.getOwnerId()).get();
			UserMapping ownerMapping = userMappingRepo.findByUserAgentId(owner);
			// Convert rental to DTO with mapped id's
			// Todo: mappings for rental report & (?) Customer (?)
			RentalDTO rentalDTOtoSend = convertRentalToSOAPDTO(rental, vehicleMapping.getVehicleBackId(), backBundleId, ownerMapping.getUserBackId());
			// Send rental to MS and map the ID.
			RentalDTO savedRentalDTO = rentalClient.addRental(rentalDTOtoSend).getValue();
			if(savedRentalDTO != null) {
				RentalMapping rentalMapping = new RentalMapping();
				rentalMapping.setRentalAgentId(rental);
				rentalMapping.setRentalBackId(savedRentalDTO.getId());
				rentalMappingRepo.save(rentalMapping);
			}
		}

	}
	// Function to convert rental to DTO with mapped id's
	private agent.soap.gen.RentalDTO convertRentalToSOAPDTO(Rental rental, Long vehicleId, Long bundleId, Long ownerId){
		agent.soap.gen.RentalDTO retVal = mapper().map(rental, agent.soap.gen.RentalDTO.class);
		if (retVal.getBundle() != null) {
			retVal.getBundle().setId(bundleId);
		}
		retVal.setVehicleId(vehicleId);
		retVal.setOwnerId(ownerId);
		return retVal;
	}
}
