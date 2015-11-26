package vn.com.minisat.numberlink.service;

import java.util.ArrayList;
import java.util.List;

import org.sat4j.tools.ExtendedDimacsArrayReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.minisat.numberlink.model.Directions;
import vn.com.minisat.numberlink.model.NumberLink;

@Service
public class CNFConverterServiceImpl implements CNFConverterService {
	
	private ExtendedDimacsArrayReader extendedDimacsArrayReader;
	
	@Autowired
	public CNFConverterServiceImpl(ExtendedDimacsArrayReader extendedDimacsArrayReader) {
		this.extendedDimacsArrayReader = extendedDimacsArrayReader;
	}
	
	@Override
	public List<List<Integer>> generateSat(NumberLink numberLink) {
		List<List<Integer>> satEncoding = new ArrayList<>();
		defineSat(numberLink);
		return satEncoding;
	}

	private void defineSat(NumberLink numberLink) {
		for (List<Integer> row : numberLink.getFields()) {
			for (Integer value : row) {
				if(value != 0) {
					//Not blank cell -> directions: UP OR DOWN OR LEFT OR RIGHT
					int[] vars = new int[4]; 
					Directions[] directions = Directions.values();
					for (int i=0; i< directions.length; i++) {
						vars[i] = directions[i].value();
					}
					
					generateExactOneValue(vars);
				} else {
					
				}
			}
		}
	}

	//Generate logic: one of variable in vars is TRUE
	private void generateExactOneValue(int[] vars) {
		for (int var : vars) {
			
		}
	}


}
