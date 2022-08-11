package inrowbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import inrowbackend.model.DemoModel;
import inrowbackend.repository.DemoRepository;

@Service
public class DemoService {
	
	@Autowired
	private DemoRepository demoRepository;

	public void sendDemoData() {
		demoRepository.save(new DemoModel("Test"));
	}

}
