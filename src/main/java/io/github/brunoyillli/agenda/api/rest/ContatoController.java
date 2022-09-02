package io.github.brunoyillli.agenda.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.brunoyillli.agenda.model.entity.Contato;
import io.github.brunoyillli.agenda.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {
	
	private final ContatoRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contato save( @RequestBody Contato contato ) {
		return repository.save(contato);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable Integer id) {
		repository.deleteById(id);
	}
	
	@GetMapping
	public List<Contato> list(){
		return repository.findAll();
	}
	
	@PutMapping("{id}/favorito")
	public void favorite( @PathVariable Integer id ) {
		Optional<Contato> contato = repository.findById(id);
		contato.ifPresent(c -> {
			boolean favorito = c.getFavorito() == Boolean.TRUE;
			c.setFavorito(!favorito);
			repository.save(c);
		});
	}
}
