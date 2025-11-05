package br.edu.infnet.alexandrenetoapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.alexandrenetoapi.model.domain.Client;
import br.edu.infnet.alexandrenetoapi.model.service.ClientService;
import jakarta.validation.ConstraintViolationException;

@Component
@Order(2)
public class ClientLoader implements ApplicationRunner {
    private final ClientService clientService;

    public ClientLoader(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader fileRead = new FileReader("clientes.csv");
        BufferedReader fileBuffer = new BufferedReader(fileRead);

        while(true) {
            try {
                String line = fileBuffer.readLine();

                if(line == null) {
                    break;
                }

                String[] columns = line.split(",");

                clientService.create(new Client(columns[0], columns[1], columns[2]));
            } catch(IOException e) {
                System.err.println(e.toString());
            } catch(ConstraintViolationException e) {
                System.err.println(String.format(" -> ERROR: %s", e.toString()));
                continue;
            }
        }

        fileBuffer.close();

        for (Client client : clientService.readAll()) {
            System.out.println(client);
        }
    }
}
