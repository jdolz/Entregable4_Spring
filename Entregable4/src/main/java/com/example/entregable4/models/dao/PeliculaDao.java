package com.example.entregable4.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entregable4.models.entity.Pelicula;

@Repository
public class PeliculaDao implements IPeliculaDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	@Override
	public List<Pelicula> findAll() {

		return em.createQuery("select p from Pelicula p").getResultList();
	}

	@Override
	@Transactional
	public void save(Pelicula pelicula) {

		if (findOne(pelicula.getImdbId()) != null) {

			em.merge(pelicula);
		} else {
			em.persist(pelicula);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Pelicula findOne(String id) {
		return em.find(Pelicula.class, id);
	}

}
