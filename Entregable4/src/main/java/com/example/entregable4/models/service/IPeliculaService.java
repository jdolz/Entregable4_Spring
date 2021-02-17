package com.example.entregable4.models.service;

import java.util.List;

import com.example.entregable4.models.entity.Pelicula;

public interface IPeliculaService {

	public List<Pelicula> findAll();

	public void save(Pelicula pelicula);

	public Pelicula findOne(String id);

}
