package vn.com.minisat.numberlink;

import java.io.IOException;
import java.util.Scanner;

import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.com.minisat.numberlink.service.SATSolverService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private SATSolverService satSolverService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		String input = Thread.currentThread().getContextClassLoader().getResource("cnf.in").getPath();
		try {
			IProblem problem = satSolverService.solve(input);
			if (problem.isSatisfiable()) {
				System.out.println("Satisfiable !");
				System.out.println(satSolverService.decode(problem));
				
				processExitApplication();
				
			} else {
				System.out.println("Unsatisfiable !");
			}
		} catch (ParseFormatException | IOException | ContradictionException
				| TimeoutException e) {
			e.printStackTrace();
		}
	}

	private void processExitApplication() {
		Scanner scanner = new Scanner(System.in);
		int nextInt = scanner.nextInt();
		if(nextInt == -1) {
			scanner.close();
			System.exit(0);
		}
	}
}
