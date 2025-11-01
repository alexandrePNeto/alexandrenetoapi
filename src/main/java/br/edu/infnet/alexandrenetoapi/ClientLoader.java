package br.edu.infnet.alexandrenetoapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.edu.infnet.alexandrenetoapi.model.domain.Client;
import br.edu.infnet.alexandrenetoapi.model.service.ClientService;

@Component
@Order(1)
public class ClientLoader implements ApplicationRunner {
    private final ClientService clientService;

    public ClientLoader(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            FileReader fileRead = new FileReader("clientes.csv");
            BufferedReader fileBuffer = new BufferedReader(fileRead);

            while(true) {
                String line = fileBuffer.readLine();

                if(line == null) {
                    break;
                }

                String[] columns = line.split(",");

                clientService.create(new Client(columns[0], columns[1], columns[2]));
            }

            fileBuffer.close();

            ArrayList<Client> clients = clientService.readAll();

            for (Client client : clients) {
                System.out.println(client);
            }
        } catch(IOException e) {
            System.err.println(String.format(" -> ERROR: %s", e.toString()));
        }
    }

}
