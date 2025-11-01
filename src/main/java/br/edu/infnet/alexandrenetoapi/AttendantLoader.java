package br.edu.infnet.alexandrenetoapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.edu.infnet.alexandrenetoapi.clients.ApiCepFeignClient;
import br.edu.infnet.alexandrenetoapi.model.domain.Attendant;
import br.edu.infnet.alexandrenetoapi.model.service.AttendantService;

@Component
public class AttendantLoader implements ApplicationRunner {
    private final AttendantService attendantService;

    public AttendantLoader(AttendantService attendantService, ApiCepFeignClient apiCepFeignClient) {
        this.attendantService = attendantService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        FileReader fileRead = new FileReader("atendentes.csv");
        BufferedReader fileBuffer = new BufferedReader(fileRead);

        while(true) {
            String line = fileBuffer.readLine();

            if(line == null) {
                break;
            }

            Attendant attendant = new Attendant();

            String[] columns = line.split(",");

            attendant.setRegistration(columns[0]);
            attendant.setName(columns[1]);
            attendant.setCpf(columns[4]);
            attendant.setCep(columns[3]);

            attendantService.create(attendant);
        }

        fileBuffer.close();

        ArrayList<Attendant> attendantList = attendantService.readAll();

        for (Attendant attendant : attendantList) {
            System.out.println(attendant);
        }
    }

}
