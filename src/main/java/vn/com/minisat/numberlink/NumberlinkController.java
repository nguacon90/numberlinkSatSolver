package vn.com.minisat.numberlink;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.sat4j.specs.IProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.com.minisat.numberlink.model.Cell;
import vn.com.minisat.numberlink.model.NumberLink;
import vn.com.minisat.numberlink.model.NumberLinkResponse;
import vn.com.minisat.numberlink.model.SatEncoding;
import vn.com.minisat.numberlink.service.CNFConverterService;
import vn.com.minisat.numberlink.service.SATSolverService;
import vn.com.minisat.numberlink.service.TransformerService;

@RestController
public class NumberlinkController {
	@Autowired
	private TransformerService transformerService;

	@Autowired
	private CNFConverterService cnfConverterService;

	@Autowired
	private SATSolverService satSolverService;
	
	@RequestMapping(value = "/numberlink", method = RequestMethod.GET)
	public ResponseEntity<NumberLinkResponse> load(@RequestParam("input") String input) {
		NumberLinkResponse response = new NumberLinkResponse();
		String numberlinkInput = Thread.currentThread().getContextClassLoader().getResource(input + ".in").getPath();
		NumberLink numberLink = null;
		try {
			numberLink = transformerService.readNumberLink(numberlinkInput);
			int[][] board = numberLink.getInputs();
			for (int row = 1; row < board.length; row++) {
				List<Cell> cells = new ArrayList<Cell>();
				for (int col = 1; col < board[row].length; col++) {
					Cell cell = new Cell(row, col, board[row][col]);
					cells.add(cell);
				}
				response.getCells().add(cells);
			}
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<NumberLinkResponse>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/numberlink/resolve", method = RequestMethod.GET)
	public ResponseEntity<NumberLinkResponse> resolve(@RequestParam("input") String input) {
		long startTimes = System.currentTimeMillis();
		NumberLinkResponse response = new NumberLinkResponse();
		String numberlinkInput = Thread.currentThread().getContextClassLoader().getResource(input + ".in").getPath();
		String cnfOut = Thread.currentThread().getContextClassLoader().getResource("").getPath() + "cnf.in";

		NumberLink numberLink = null;
		try {
			numberLink = transformerService.readNumberLink(numberlinkInput);

			SatEncoding satEncoding = cnfConverterService.generateSat(numberLink);
			File file = new File(cnfOut);
			FileWriter fileWriter = new FileWriter(file);
			String firstLine = "p cnf " + satEncoding.getVariables() + " " + satEncoding.getClauses();
			fileWriter.write(firstLine + "\n");
			List<String> rules = satEncoding.getRules();
			for (int i = 0; i < rules.size(); i++) {
				if (i == rules.size() - 1) {
					fileWriter.write(rules.get(i));
					continue;
				}
				fileWriter.write(rules.get(i) + "\n");
			}
			fileWriter.flush();
			fileWriter.close();

			IProblem problem = satSolverService.solve(cnfOut);
			if (problem.isSatisfiable()) {
				int[] model = problem.model();
				int[][] board = numberLink.getInputs();

				for (int row = 1; row < board.length; row++) {
					List<Cell> cells = new ArrayList<Cell>();
					for (int col = 1; col < board[row].length; col++) {
						Cell cell = null;
						for (int k = 0; k < model.length; k++) {
							if (model[k] > 0) {
								int value = cnfConverterService.getValueOf(row, col, model[k], numberLink);
								if (value <= 4 && value >= 1) {
									if (cell == null) {
										cell = new Cell(row-1, col-1, board[row][col]);
										cells.add(cell);
										cell.getPattern().add(value);
									} else {
										cell.getPattern().add(value);
									}
								}
							}

						}
					}
					response.getCells().add(cells);
				}
			}
			long endTimes = System.currentTimeMillis();
			response.setTimes(endTimes - startTimes);
			response.setSatisfiable(problem.isSatisfiable());
			response.setColNum(numberLink.getCol());
			response.setRowNum(numberLink.getRow());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return new ResponseEntity<NumberLinkResponse>(response, HttpStatus.OK);
	}
}
