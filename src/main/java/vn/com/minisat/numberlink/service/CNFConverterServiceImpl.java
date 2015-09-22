package vn.com.minisat.numberlink.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import vn.com.minisat.numberlink.model.NumberLink;

@Service
public class CNFConverterServiceImpl implements CNFConverterService {

	@Override
	public NumberLink readNumberLink(String filename) throws IOException, URISyntaxException {
		NumberLink numberLink = null;
		BufferedReader br = new BufferedReader(new FileReader(filename));
		try {
		    String line = br.readLine();
		    int i = -1;
		    while (line != null) {
		    	String[] coordinate = line.split(" +");
		    	if(numberLink != null && i > numberLink.getMatrix().length - 1) {
		    		throw new RuntimeException("Invalid format numberlink");
		    	}
		    		
		    	if(i == -1) {
		    		if(coordinate == null || coordinate.length != 2) {
		    			throw new RuntimeException("Invalid format numberlink");
		    		}
		    		
		    		numberLink = new NumberLink(Integer.valueOf(coordinate[0]), Integer.valueOf(coordinate[1]));
		    	} else {
		    		for (int j = 0; j < coordinate.length; j++) {
						numberLink.getMatrix()[i][j] = Integer.valueOf(coordinate[j]);
					}
		    	}
		    	
		    	i++;
		        line = br.readLine();
		        
		    }
		} finally {
		    br.close();
		}
		
		return numberLink;
	}

}
