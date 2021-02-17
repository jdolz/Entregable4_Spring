package com.example.entregable4.models.dao;

import java.util.List;

import com.example.entregable4.models.entity.Pelicula;

public interface IPeliculaDao {

	public List<Pelicula> findAll();

	public void save(Pelicula pelicula);

	Pelicula findOne(String email);

}
