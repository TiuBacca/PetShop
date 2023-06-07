package com.baccarin.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baccarin.petshop.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
