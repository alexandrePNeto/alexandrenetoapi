package br.edu.infnet.alexandrenetoapi.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.infnet.alexandrenetoapi.model.domain.Address;

@FeignClient(name = "viacep", url = "${api.viacep.url}")
public interface ApiCepFeignClient {
	@GetMapping("/{cep}/json/")
	Address findByCep(@PathVariable String cep);
}
