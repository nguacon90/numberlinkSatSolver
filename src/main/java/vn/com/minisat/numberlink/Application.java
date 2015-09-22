package vn.com.minisat.numberlink;

import java.io.IOException;

import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.com.minisat.numberlink.model.NumberLink;
import vn.com.minisat.numberlink.service.CNFConverterService;
import vn.com.minisat.numberlink.service.SATSolverService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private SATSolverService satSolverService;
	
	@Autowired 
	private CNFConverterService cnfConverterService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		String input = Thread.currentThread().getContextClassLoader().getResource("cnf.in").getPath();
		String numberlinkInput = Thread.currentThread().getContextClassLoader().getResource("numberlink1.in").getPath();
		try {
			IProblem problem = satSolverService.solve(input);
			if (problem.isSatisfiable()) {
				System.out.println("Satisfiable !");
				System.out.println(satSolverService.decode(problem));
				
			} else {
				System.out.println("Unsatisfiable !");
			}
			
			System.out.println("-----------------------------------------");
			
			NumberLink readNumberLink = cnfConverterService.readNumberLink(numberlinkInput);
			
			System.out.println(readNumberLink);
			
		} catch (ParseFormatException | IOException | ContradictionException
				| TimeoutException e) {
			e.printStackTrace();
		}
	}

}
