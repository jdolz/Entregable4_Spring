package com.example.entregable4.models.component;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.entregable4.models.entity.Pelicula;
import com.example.entregable4.models.entity.Usuario;



@Component
@SessionScope
public class UsuarioComp {

	private String email;
	private String nombre;
	private String apellido;
	private Integer anioNacimiento;
	private String password;
	private List<Pelicula> peliculas;

	
	
	public UsuarioComp() {
		super();
	}

	public UsuarioComp(String email, String nombre, String apellido, Integer anioNacimiento, String password,
			List<Pelicula> peliculas) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.anioNacimiento = anioNacimiento;
		this.password = password;
		this.peliculas = peliculas;
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

	public List<Pelicula> getPeliculas() {
		return peliculas;
	}

	public void setPeliculas(List<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}
	
	public void aniadirPelicula(Pelicula p) {
		this.peliculas.add(p);
	}
	
	public void copia(Usuario u2) {
		this.anioNacimiento = u2.getAnioNacimiento();
		this.apellido = u2.getApellido();
		this.email = u2.getEmail();
		this.nombre = u2.getNombre();
		this.password = u2.getPassword();
		this.peliculas = u2.getPeliculas();
	}

	@Override
	public String toString() {
		return "UsuarioComp [email=" + email + ", nombre=" + nombre + ", apellido=" + apellido + ", anioNacimiento="
				+ anioNacimiento + ", password=" + password + ", peliculas=" + peliculas + "]";
	}

	
	
}
