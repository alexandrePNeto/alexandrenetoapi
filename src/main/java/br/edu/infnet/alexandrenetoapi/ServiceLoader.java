package br.edu.infnet.alexandrenetoapi;

import java.util.List;
import java.util.Random;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.edu.infnet.alexandrenetoapi.model.domain.Car;
import br.edu.infnet.alexandrenetoapi.model.domain.Client;
import br.edu.infnet.alexandrenetoapi.model.domain.Service;
import br.edu.infnet.alexandrenetoapi.model.domain.ServiceCar;
import br.edu.infnet.alexandrenetoapi.model.service.AttendantService;
import br.edu.infnet.alexandrenetoapi.model.service.ClientService;
import br.edu.infnet.alexandrenetoapi.model.service.ServiceCarService;
import br.edu.infnet.alexandrenetoapi.model.service.ServiceService;

@Order(4)
@Component
public class ServiceLoader implements ApplicationRunner {
    private ServiceService serviceService;
    private ClientService clientService;
    private AttendantService attendantService;
    private ServiceCarService serviceCarService;

    public ServiceLoader(
        ServiceService serviceService,
        ClientService clientService,
        AttendantService attendantService,
        ServiceCarService serviceCarService
        ) {
            this.serviceService = serviceService;
            this.clientService = clientService;
            this.attendantService = attendantService;
            this.serviceCarService = serviceCarService;
        }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Random random = new Random();
        for(Client client : clientService.readAll()) {
            Service service = new Service();

            int randomAttendantId = random.nextInt(attendantService.countTotalAttendants().intValue() - 1 + 1) + 1;

            service.setClient(client);
            service.setAttendant(attendantService.readById(randomAttendantId));
            service.setEmissionTime(LocalDateTime.now());
            service.setDueTime(LocalDateTime.now().plusDays(15));
            service.setService_value(new BigDecimal(client.getCars().size() * 15 + randomAttendantId));

            Service newService = serviceService.create(service);

            for(Car car : client.getCars()) {
                ServiceCar serviceCar = new ServiceCar();
                serviceCar.setCar(car);
                serviceCar.setService(newService);
                serviceCarService.create(serviceCar);
            }
        }

        System.out.println("SERVIÇO IMPORTADOS");
    }

}
