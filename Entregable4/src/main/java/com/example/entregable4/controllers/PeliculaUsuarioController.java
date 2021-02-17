package com.example.entregable4.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entregable4.models.component.PeliculaComp;
import com.example.entregable4.models.component.PeliculaOMDB;
import com.example.entregable4.models.component.UsuarioComp;
import com.example.entregable4.models.entity.Pelicula;
import com.example.entregable4.models.entity.Usuario;
import com.example.entregable4.models.service.IPeliculaService;
import com.example.entregable4.models.service.IUsuarioService;
import com.example.entregable4.models.service.PeticionGetExterna;
import com.google.gson.Gson;

@Controller
@RequestMapping("/omdb")
public class PeliculaUsuarioController {

	@Autowired
	private Gson gson;
	@Autowired
	private PeticionGetExterna peticion;

	@Autowired
	private IUsuarioService serviceUsuario;
	@Autowired
	private IPeliculaService servicePelicula;

	@Autowired
	private PeliculaComp peliculaComp;

	@Autowired
	private UsuarioComp usuarioComp;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("usuario") Usuario usuario, Model model) {
		model.addAttribute("titulo", "Login");
		return "index";
	}

	@RequestMapping(value = "/altaUsuario", method = RequestMethod.GET)
	public String formularioAltaUsuario(@ModelAttribute("usuario") Usuario usuario, Model model) {
		model.addAttribute("titulo", "Formulario Nuevo Usuario");
		return "formAltaUsuario";
	}

	@RequestMapping(value = "/comprobarUsuario", method = RequestMethod.POST)
	public String comprobarUsuario(Model model, Usuario usuario) {
		String siguientePantalla;

		Usuario usu = serviceUsuario.findOne(usuario.getEmail());

		if (usu == null || !usu.getPassword().equals(usuario.getPassword())) {
			model.addAttribute("logError", "logError");
			model.addAttribute("titulo", "Login");
			siguientePantalla = "index";
		} else {

			siguientePantalla = "redirect:buscarPelicula";
			usuarioComp.copia(usu);

		}
		return siguientePantalla;
	}

	@RequestMapping(value = "/buscarPelicula", method = RequestMethod.GET)
	public String crearFormulario(Model model) {
		System.out.println(usuarioComp);
		model.addAttribute("titulo", "Buscar Peliculas");
		Pelicula pelicula = new Pelicula();
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("usuario", usuarioComp);

		return "formBuscarPeliculas";

	}

	@RequestMapping(value = "/comprobarAltaUsuario", method = RequestMethod.POST)
	public String altaUsuario(Model model, Usuario usuario) {
		String siguientePantalla;
		Usuario usu = serviceUsuario.findOne(usuario.getEmail());
		if (usu == null && !usuario.getEmail().isBlank() && !usuario.getNombre().isBlank()
				&& !usuario.getApellido().isBlank() && !usuario.getPassword().isBlank()
				&& usuario.getAnioNacimiento() != null) {
			serviceUsuario.save(usuario);
			siguientePantalla = "redirect:login";

		} else {
			model.addAttribute("logError", "logError");
			model.addAttribute("titulo", "Formulario Nuevo Usuario");
			
			siguientePantalla = "formAltaUsuario";
		}
		return siguientePantalla;
	}

	@RequestMapping(value = "/verpelicula", method = RequestMethod.GET)
	public ModelAndView buscarOMDB(@RequestParam(value = "Title") String titulo) throws IOException {

		String busqueda = "http://www.omdbapi.com/?apikey=5fcfd7b0";
		if (titulo.contains(" ")) {
			titulo = titulo.replace(' ', '+');
		}
		busqueda += "&t=" + titulo;
		ModelAndView mav = new ModelAndView("formBuscarPeliculas");
		Usuario usuario = new Usuario();

		String texto = peticion.sendGET(busqueda);
		System.out.println(texto);

		Pelicula pelicula = gson.fromJson(texto, Pelicula.class);
		
		if (pelicula.getTitle() == null) {
			mav.addObject("emptyError", "emptyError");
		}

		peliculaComp.copia(pelicula);

		mav.addObject("titulo", "Buscar Peliculas");
		mav.addObject("usuario", usuario);
		mav.addObject("pelicula", peliculaComp);
		System.out.println(peliculaComp);
		System.out.println(usuarioComp);

		return mav;
	}

	@RequestMapping(value = "/guardarPeliUsuario", method = RequestMethod.GET)
	public String guardarUsuario(Model model, Usuario usu) {
		
		
		boolean encontrado = false;
		System.out.println(usuarioComp);
		System.out.println(peliculaComp);

		usu.copia(usuarioComp);

		Pelicula p = new Pelicula();
		p.copia(peliculaComp);
		servicePelicula.save(p);
		
		for (Pelicula p1 : usu.getPeliculas()) {
			if (p.getImdbId().equals(p1.getImdbId())) {
				encontrado = true;
			}
		}
		if (!encontrado) {
			usu.aniadirPelicula(p);
			serviceUsuario.save(usu);
			
		}else {
			model.addAttribute("logError", "logError");
			
			
		}
		Pelicula pelicula = new Pelicula();
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("titulo", "Buscar Peliculas");
		
		return "formBuscarPeliculas";
	}

	@RequestMapping(value = "/listarPeliculas", method = RequestMethod.GET)
	public String listar(Model model) {
		
		model.addAttribute("peliculas", usuarioComp.getPeliculas());
		//model.addAttribute("peliculas", servicePelicula.findAll());

		return "listarPeliculas";
	}

	@RequestMapping(value = "/listarDetalle/{id}", method = RequestMethod.GET)
	public String listarDetalle(Model model, @PathVariable("id") String id) throws IOException{
		//volver a buscar en omdb
		String busqueda = "http://www.omdbapi.com/?apikey=5fcfd7b0";
		
		busqueda += "&i=" + id;
		
		String texto = peticion.sendGET(busqueda);
		System.out.println(texto);

		PeliculaOMDB pelicula = gson.fromJson(texto, PeliculaOMDB.class);

		//PeliculaOMDB pelicula = servicePelicula.findOne(id); Request
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("titulo", "Detalles Película");
		return "listarDetalle";
	}

}
