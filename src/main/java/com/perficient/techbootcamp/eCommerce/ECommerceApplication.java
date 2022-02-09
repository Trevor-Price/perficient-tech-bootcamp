package com.perficient.techbootcamp.eCommerce;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ECommerceApplication {

	private Logger log = LogManager.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner removeOldProcessDefs(final ProcessEngine engine, final RepositoryService service){
		
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				engine.getProcessEngineConfiguration().setAsyncExecutorActivate(true);
				
				List<ProcessDefinition> pds = service.createProcessDefinitionQuery().list();
				if(pds.isEmpty()) return;
				for(ProcessDefinition pd : pds) {
					log.info("Found process def: " + pd.toString() + " [version: " + pd.getVersion() + "]");
				}
			}
		};
		
	}
	
}
