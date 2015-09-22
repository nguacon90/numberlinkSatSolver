package vn.com.minisat.numberlink.config;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.specs.ISolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public DimacsReader dimacsReader() {
		ISolver solver = SolverFactory.newDefault();
		return new DimacsReader(solver);
	}
}
