package vn.com.minisat.numberlink.service;

import vn.com.minisat.numberlink.model.NumberLink;
import vn.com.minisat.numberlink.model.SatEncoding;

public interface CNFConverterService {

	SatEncoding generateSat(NumberLink numberLink);
	
}
