package vn.com.minisat.numberlink.service;

import java.io.IOException;

import org.sat4j.reader.ParseFormatException;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;


public interface SATSolverService {
	IProblem solve(String filename) throws ParseFormatException, IOException, ContradictionException;
	
	String decode(IProblem iProblem);
}
