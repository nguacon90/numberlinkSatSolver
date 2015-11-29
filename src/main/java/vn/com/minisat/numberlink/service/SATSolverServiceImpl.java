package vn.com.minisat.numberlink.service;

import java.io.IOException;

import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SATSolverServiceImpl implements SATSolverService {
	
	private DimacsReader dimacsReader;

	@Autowired
	public SATSolverServiceImpl(DimacsReader dimacsReader) {
		this.dimacsReader = dimacsReader;
	}
	
	@Override
	public IProblem solve(String filename) throws ParseFormatException, IOException, ContradictionException {
		return dimacsReader.parseInstance(filename);
	}

	@Override
	public String decode(IProblem iProblem) {
		return dimacsReader.decode(iProblem.model());
	}
}
