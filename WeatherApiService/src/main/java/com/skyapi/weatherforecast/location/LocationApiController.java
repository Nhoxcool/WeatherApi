package com.skyapi.weatherforecast.location;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyapi.weatherforecast.common.Location;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/locations")
@Tag(name = "Location API") 
public class LocationApiController {
	private LocationService service;

	public LocationApiController(LocationService service) {
		super();
		this.service = service;
	}
	
	@PostMapping
    @Operation(summary = "Create new Location")
	@ApiResponses(value = {
			 @ApiResponse(responseCode = "201", description = "Location created"),
			 @ApiResponse(responseCode = "400", description = "Invalid input") , 
	})
	public ResponseEntity<Location> addLocation(@RequestBody @Valid Location location){
		Location addedLocation = service.add(location);
		URI uri = URI.create("/v1/locations/" + addedLocation.getCode());
		
		
		return ResponseEntity.created(uri).body(addedLocation);
	}
	
	@GetMapping
    @Operation(summary = "Get all locations")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Successful operation"),
		    @ApiResponse(responseCode = "204", description = "No content available"), 
	})
	public ResponseEntity<?> listLocations() {
		List<Location> locations = service.list();
		
		if (locations.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(locations);
	}
	
	
	
	@GetMapping("/{code}")
	@Operation(summary = "Get a location by ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Successful operation"), 
	    @ApiResponse(responseCode = "404", description = "Location not found"),   
	    @ApiResponse(responseCode = "405", description = "Method not allowed")    
	})
	public ResponseEntity<?> getLocation(@PathVariable("code") String code) {
		Location location = service.get(code);
		
		if(location == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(location);
	}
	
	
	@PutMapping
    @Operation(summary = "Update a location")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Location not found")
    })
	public ResponseEntity<?> updateLocation(@RequestBody @Valid Location location) {
		try {
			Location updateLocation = service.update(location);
			
			return ResponseEntity.ok(updateLocation);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{code}")
	public ResponseEntity<?> deleteLocation(@PathVariable("code") String code){
		try {
			service.delete(code);
			
			return ResponseEntity.noContent().build();
		} catch (LocationNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
