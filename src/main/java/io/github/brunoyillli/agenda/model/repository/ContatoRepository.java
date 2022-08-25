package io.github.brunoyillli.agenda.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.brunoyillli.agenda.model.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato,Integer> {

}
