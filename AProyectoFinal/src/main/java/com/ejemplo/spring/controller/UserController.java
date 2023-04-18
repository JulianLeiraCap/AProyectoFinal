package com.ejemplo.spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.spring.model.User;
import com.ejemplo.spring.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "user", description = "The User API")
public class UserController {

	@Autowired
	UserService service;
	@Operation(
			summary = "Buscar todos los usuarios", 
			description = "Devuelve un listado de usuarios", 
			tags= {"user"})
	
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200", 
					description = "Usuarios encontrados", 
					content = {
							@Content(
									mediaType = "application/json", 
									schema = @Schema(
											implementation = User.class)
									) 
							}
					),
			@ApiResponse(
					responseCode = "204", 
					description = "La Lista de usuarios está vacia", 
					content = @Content)
	}
	)
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> users= service.findAll();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}			
		return ResponseEntity.ok(users);
		
	}
	
	// Vamos a indicar algunos elementos de OPenAPI
		@Operation(
				summary = "Buscar usuarios por ID", 
				description = "Dado un ID, devuelve un objeto User", 
				tags= {"user"})
		@ApiResponses(value = {
				@ApiResponse(
						responseCode = "200", 
						description = "Usuario localizado", 
						content = {
								@Content(
										mediaType = "application/json", 
										schema = @Schema(
												implementation = User.class)
										) 
								}
						),
				@ApiResponse(
						responseCode = "400", 
						description = "No válido (NO implementado) ", 
						content = @Content),
				@ApiResponse(
						responseCode = "404", 
						description = "Usuario no encontrado", 
						content = @Content) 
				}
		)
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable int id){
		
		Optional<User> user= service.findById(id);
		
		return user.map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
	}
		@Operation(
				summary = "Guardar Usuario", 
				description = "Dado un objeto User, se almacena en la base de datos", 
				tags= {"user"})
		@ApiResponses(value = {
				@ApiResponse(
						responseCode = "200", 
						description = "Usuario guardado", 
						content = {
								@Content(
										mediaType = "application/json", 
										schema = @Schema(
												implementation = User.class)
										) 
								}
						),
				@ApiResponse(
						responseCode = "204", 
						description = "Uno o varios campos del usuario están vacios", 
						content = @Content)
				})
		
		
	@PostMapping("/add")
	public ResponseEntity<User> save(@RequestBody User user) {
		
		User saveduser= service.save(user);
		return ResponseEntity.ok().body(saveduser);
	}
		@Operation(
				summary = "Eliminar usuario usando ID", 
				description = "Dado un ID, elimina el usuario y devuelve el usuario eliminado", 
				tags= {"user"})
		@ApiResponses(value = {
				@ApiResponse(
						responseCode = "200", 
						description = "Usuario eliminado", 
						content = {
								@Content(
										mediaType = "application/json", 
										schema = @Schema(
												implementation = User.class)
										) 
								}
						),
				@ApiResponse(
						responseCode = "404", 
						description = "Usuario no encontrado", 
						content = @Content) 
				}
		)
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteById(@PathVariable int id){
		
		Optional<User> user= service.deleteById(id);
		
		return user.map(c -> ResponseEntity.ok().body(c))
                .orElse(ResponseEntity.notFound().build());
	}
}
