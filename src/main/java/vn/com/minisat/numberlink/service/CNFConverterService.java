package vn.com.minisat.numberlink.service;

import java.io.IOException;
import java.net.URISyntaxException;

import vn.com.minisat.numberlink.model.NumberLink;


public interface CNFConverterService {
	NumberLink readNumberLink(String filename) throws IOException, URISyntaxException;
}
