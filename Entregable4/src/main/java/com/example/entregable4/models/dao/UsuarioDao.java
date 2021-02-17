package com.example.entregable4.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entregable4.models.entity.Pelicula;
import com.example.entregable4.models.entity.Usuario;
import com.example.entregable4.models.service.IPeliculaService;

@Repository
public class UsuarioDao implements IUsuarioDao {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	IPeliculaService peliculaService;

	@Transactional(readOnly = true)
	@Override
	public List<Usuario> findAll() {

		return em.createQuery("select u from Usuario u").getResultList();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {

		if (findOne(usuario.getEmail()) != null) {
			if (usuario.getPeliculas() != null && usuario.getPeliculas().size() > 0) {
				for (Pelicula p : usuario.getPeliculas()) {
					peliculaService.save(p);
				}
			}

			em.merge(usuario);
		} else {
			em.persist(usuario);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findOne(String email) {
		return em.find(Usuario.class, email);
	}

}
