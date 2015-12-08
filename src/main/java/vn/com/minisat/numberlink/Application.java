package vn.com.minisat.numberlink;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
//		String numberlinkInput = Thread.currentThread().getContextClassLoader().getResource("numberlink1.in").getPath();
//		String cnfOut = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cnf.in";
//
//		NumberLink numberLink = transformerService.readNumberLink(numberlinkInput);
//		System.out.println(numberLink);
//
//		SatEncoding satEncoding = cnfConverterService.generateSat(numberLink);
//		try {
//			File file = new File(cnfOut);
//			FileWriter fileWriter = new FileWriter(file);
//			String firstLine = "p cnf " + satEncoding.getVariables() + " " + satEncoding.getClauses();
//			fileWriter.write(firstLine + "\n");
//			List<String> rules = satEncoding.getRules();
//			for (int i = 0; i < rules.size(); i++) {
//				if (i == rules.size() - 1) {
//					fileWriter.write(rules.get(i));
//					continue;
//				}
//				fileWriter.write(rules.get(i) + "\n");
//			}
//			fileWriter.flush();
//			fileWriter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		IProblem problem = satSolverService.solve(cnfOut);
//		if (problem.isSatisfiable()) {
//			int[] model = problem.model();
//			int[][] board = numberLink.getInputs();
//			List<Cell> cells = new ArrayList<Cell>();
//			for (int row = 1; row < board.length; row++) {
//				for (int col = 1; col < board[row].length; col++) {
//					Cell cell = null;
//					for (int k = 0; k < model.length; k++) {
//						if (model[k] > 0) {
//							int value = cnfConverterService.getValueOf(row, col, model[k], numberLink);
//							if (value <= 4 && value >= 1) {
//								if(cell == null) {
//									cell = new Cell(row-1, col-1, board[row][col]);
//									cells.add(cell);
//									cell.getPattern().add(value);
//								} else {
//									cell.getPattern().add(value);
//								}
//							}
//						}
//
//					}
//				}
//			}
//			
//		} else {
//			System.err.println("UNSAT");
//		}

	}

}
