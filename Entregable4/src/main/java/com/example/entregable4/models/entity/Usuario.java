package com.example.entregable4.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.example.entregable4.models.component.UsuarioComp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String email;
	private String nombre;
	private String apellido;
	private Integer anioNacimiento;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Pelicula> peliculas;

	public Usuario() {
		super();
		this.peliculas = new ArrayList<Pelicula>();

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void aniadirPelicula(Pelicula p) {
		this.peliculas.add(p);
	}

	public Integer getAnioNacimiento() {
		return anioNacimiento;
	}

	public void setAnioNacimiento(Integer anioNacimiento) {
		this.anioNacimiento = anioNacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void copia(UsuarioComp u2) {
		this.anioNacimiento = u2.getAnioNacimiento();
		this.apellido = u2.getApellido();
		this.email = u2.getEmail();
		this.nombre = u2.getNombre();
		this.password = u2.getPassword();
		this.peliculas = u2.getPeliculas();
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + ", anioNacimiento="
				+ anioNacimiento + ", password=" + password + ", peliculas=" + peliculas + "]";
	}

}
