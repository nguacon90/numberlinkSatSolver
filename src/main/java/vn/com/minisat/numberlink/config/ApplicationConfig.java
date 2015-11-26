package vn.com.minisat.numberlink.config;

import org.sat4j.minisat.SolverFactory;
import org.sat4j.tools.ExtendedDimacsArrayReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
	
	@Bean
	public ExtendedDimacsArrayReader extendedDimacsArrayReader() {
		ExtendedDimacsArrayReader extendedDimacsArrayReader = new ExtendedDimacsArrayReader(SolverFactory.newDefault());
		return extendedDimacsArrayReader;
	}
}
