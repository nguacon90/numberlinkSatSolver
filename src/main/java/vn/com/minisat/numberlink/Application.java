package vn.com.minisat.numberlink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.sat4j.specs.IProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.com.minisat.numberlink.model.NumberLink;
import vn.com.minisat.numberlink.model.SatEncoding;
import vn.com.minisat.numberlink.service.CNFConverterService;
import vn.com.minisat.numberlink.service.CNFConverterServiceImpl;
import vn.com.minisat.numberlink.service.SATSolverService;
import vn.com.minisat.numberlink.service.TransformerService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	@Autowired
	private TransformerService transformerService;
	
	@Autowired
	private CNFConverterService cnfConverterService;
	
	@Autowired
	private SATSolverService satSolverService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		String numberlinkInput = Thread.currentThread().getContextClassLoader()
									   .getResource("numberlink1.in").getPath();
		String cnfOut = Thread.currentThread().getContextClassLoader()
				   .getResource("").getPath() + "cnf.in";
		
		
		NumberLink numberLink = transformerService.readNumberLink(numberlinkInput);
		System.out.println(numberLink);

		SatEncoding satEncoding = cnfConverterService.generateSat(numberLink);
		try {
			File file = new File(cnfOut);
			FileWriter fileWriter = new FileWriter(file);
			String firstLine = "p cnf " + satEncoding.getVariables() + " " + satEncoding.getClauses();
			fileWriter.write(firstLine + "\n");
			List<String> rules = satEncoding.getRules();
			for (int i=0; i<rules.size(); i++) {
				if(i == rules.size() - 1) {
					fileWriter.write(rules.get(i));
					continue;
				} 
				fileWriter.write(rules.get(i) + "\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IProblem problem = satSolverService.solve(cnfOut);
		if(problem.isSatisfiable()) {
			System.out.println("Sat");
			int[] model = problem.model();
			int singleItemCount = CNFConverterServiceImpl.NUM_OF_DIRECTION + numberLink.getMaxNum();
			boolean isLeft, isRight, isUp,isDown;
			int listSize = model.length;
			int indexRow;
		    int indexCol;
		    int indexValue;
		    int[][] inputs = numberLink.getInputs();
		    int count = 0;
		    for (int is : model) {
				if(is > 0) {
					count++;
				}
			}
		    System.out.println(count);
//			for(int i = 1; i <= listSize - singleItemCount + 1; i += singleItemCount){
//
//		        isLeft = isRight = isUp = isDown = false;
//		        indexValue = ( i % singleItemCount == 0) ? singleItemCount : (i % singleItemCount);
//
//		        int tmp = (i - indexValue) / singleItemCount;
//		        indexCol = (tmp % numberLink.getCol() == 0) ? 1 : tmp % numberLink.getCol() + 1;
//		        tmp = (indexCol - 1) * singleItemCount + indexValue;
//		        indexRow = (i - tmp)/(singleItemCount * numberLink.getCol()) + 1;
//
//		        System.out.println(indexRow + ", " + indexCol + " : " + indexValue);
//			}
			
		}
		
	}

}
