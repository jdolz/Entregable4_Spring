package com.example.entregable4.models.dao;

import java.util.List;

import com.example.entregable4.models.entity.Usuario;

public interface IUsuarioDao {

	public List<Usuario> findAll();

	public void save(Usuario usuario);

	Usuario findOne(String email);

}
