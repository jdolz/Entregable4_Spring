package com.example.entregable4.models.service;

import java.util.List;

import com.example.entregable4.models.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> findAll();

	public void save(Usuario usuario);

	public Usuario findOne(String email);

}
