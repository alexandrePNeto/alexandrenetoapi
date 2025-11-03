package br.edu.infnet.alexandrenetoapi.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.edu.infnet.alexandrenetoapi.clients.ApiCepFeignClient;
import br.edu.infnet.alexandrenetoapi.exceptions.AttendantException;
import br.edu.infnet.alexandrenetoapi.model.domain.Attendant;
import br.edu.infnet.alexandrenetoapi.model.repositories.AttendantRepository;

@Service
public class AttendantService implements CrudService<Attendant, Integer> {
        private final ApiCepFeignClient apiCepFeignClient;
    private final AttendantRepository attendantRepository;

    public AttendantService(AttendantRepository attendantRepository, ApiCepFeignClient apiCepFeignClient) {
        this.attendantRepository = attendantRepository;
        this.apiCepFeignClient = apiCepFeignClient;
    }

    private void checkAttendet(Attendant attendant) {
        if(attendant == null) {
            throw new IllegalArgumentException("Objeto inválido");
        }

		if(attendant.getName() == null || attendant.getName().trim().isEmpty()) {
			throw new AttendantException("Nome é obrigatório");
		}
    }

    @Override
    public Attendant create(Attendant attendant) {
        if(attendant == null) {
            throw new IllegalArgumentException("Objeto inválido");
        }

        checkAttendet(attendant);

        attendant.setAddress(apiCepFeignClient.findByCep(attendant.getCep()));

        return attendantRepository.save(attendant);
    }

    @Override
    public ArrayList<Attendant> readAll() {
        return new ArrayList<Attendant>(attendantRepository.findAll());
    }

    @Override
    public Attendant update(Integer id, Attendant attendant) {
        checkAttendet(attendant);

        attendant.setId(id);

        attendant.setAddress(apiCepFeignClient.findByCep(attendant.getCep()));

        return attendantRepository.save(attendant);
    }

    @Override
    public void delete(Integer id) {
        Attendant attendant = readById(id);
        if(attendant == null) {
            return;
        }

        attendantRepository.delete(attendant);
    }

    @Override
    public Attendant readById(Integer id) {
        if(id == null) {
            throw new IllegalArgumentException("Atendente inválido.");
        }

        return attendantRepository.findById(id).orElseThrow(() -> new AttendantException("Atendente não encontrado."));
    }

    public Attendant readByName(String name) {
        if(name == null) {
            throw new IllegalArgumentException("Campo nome inválido");
        }

        return attendantRepository.findByNameIgnoreCase(name)
            .orElseThrow(
                () -> new AttendantException(
                    String.format(
                        "Não foi possível encontrar atendente com nome '%s'",
                        name
                    )
                )
            );
    }
}
