package inrowbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inrowbackend.service.DemoService;

@RestController
@RequestMapping(value = "/api")
public class DemoController {
	
	@Autowired
	private DemoService demoService;
	
	@GetMapping
	public ResponseEntity<String> sendDemoData() {
		demoService.sendDemoData();
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	

}
