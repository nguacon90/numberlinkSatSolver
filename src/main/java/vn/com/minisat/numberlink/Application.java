package vn.com.minisat.numberlink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.com.minisat.numberlink.model.NumberLink;
import vn.com.minisat.numberlink.service.CNFConverterService;
import vn.com.minisat.numberlink.service.SATSolverService;
import vn.com.minisat.numberlink.service.TransformerService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private SATSolverService satSolverService;
	
	@Autowired 
	private CNFConverterService cnfConverterService;
	
	@Autowired
	private TransformerService transformerService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		String input = Thread.currentThread().getContextClassLoader().getResource("cnf.in").getPath();
		String numberlinkInput = Thread.currentThread().getContextClassLoader().getResource("numberlink1.in").getPath();
		try {
//			IProblem problem = satSolverService.solve(input);
//			if (problem.isSatisfiable()) {
//				System.out.println("Satisfiable !");
//				System.out.println(satSolverService.decode(problem));
//				
//			} else {
//				System.out.println("Unsatisfiable !");
//			}
//			
//			System.out.println("-----------------------------------------");
			
			NumberLink readNumberLink = transformerService.readNumberLink(numberlinkInput);
			
			System.out.println(readNumberLink);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
