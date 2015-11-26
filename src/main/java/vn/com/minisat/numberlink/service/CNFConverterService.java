package vn.com.minisat.numberlink.service;

import java.util.List;

import vn.com.minisat.numberlink.model.NumberLink;

public interface CNFConverterService {

	List<List<Integer>> generateSat(NumberLink numberLink);
	
}
