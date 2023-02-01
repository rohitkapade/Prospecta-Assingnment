package com.rohit.controler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rohit.exceptions.InvalidEntryException;
import com.rohit.models.Data;
import com.rohit.models.Entries;
import com.rohit.models.EntriesDTO;
import com.rohit.service.EntriesSerivce;

@RestController
public class Controllerr {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EntriesSerivce eService;

	
	@GetMapping("/entries")
	public ResponseEntity<List<EntriesDTO>> getentryHandler(){
		Data data = restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<Entries> apientries = data.getEntries();
		List<EntriesDTO> totalresult = new ArrayList<>();
		for(Entries e : apientries) {
			EntriesDTO EDTO = new EntriesDTO();
			EDTO.setTitle(e.getApi());
			EDTO.setDescription(e.getDescription());
				totalresult.add(EDTO);	
		}
		return new ResponseEntity<List<EntriesDTO>>(totalresult, HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntriesDTO>> getHandler(@PathVariable("category") String category) {

		Data data = restTemplate.getForObject("https://api.publicapis.org/entries", Data.class);
		List<Entries> entries = data.getEntries();

		List<EntriesDTO> ans = new ArrayList<>();

		for (Entries e : entries) {
			if(e.getCategory().equals(category)) {
				EntriesDTO EDTO = new EntriesDTO();
				EDTO.setTitle(e.getApi());
				EDTO.setDescription(e.getDescription());
				ans.add(EDTO);
			}
		}
		return new ResponseEntity<List<EntriesDTO>>(ans, HttpStatus.ACCEPTED);
	}
	
	
	
	@PostMapping("/entries")
	public ResponseEntity<String> saveEntryHandler(@RequestBody Entries entry)throws InvalidEntryException{	
		return new ResponseEntity<String>(eService.saveEntry(entry),HttpStatus.CREATED);	
	}


	

}
