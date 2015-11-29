package vn.com.minisat.numberlink.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Service;

import vn.com.minisat.numberlink.model.NumberLink;

@Service
public class TransformerServiceImpl implements TransformerService{

	@Override
	public NumberLink readNumberLink(String filename) throws IOException, URISyntaxException {
		NumberLink numberLink = new NumberLink();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		int[][] inputs = null;
		try {
		    String line = br.readLine();
		    int i = 0;
		    while (line != null) {
		    	String[] coordinate = line.split(" +");
		    	if(numberLink != null && i > numberLink.getCol()) {
		    		throw new RuntimeException("Invalid format numberlink");
		    	}
		    		
		    	if(i == 0) {
		    		if(coordinate == null || coordinate.length != 2) {
		    			throw new RuntimeException("Invalid format numberlink");
		    		}
		    		numberLink.setRow(Integer.valueOf(coordinate[0]));
		    		numberLink.setCol(Integer.valueOf(coordinate[1]));
		    		inputs = new int[numberLink.getRow()+1][numberLink.getCol()+1];
		    	} else {
		    		
		    		for (int j = 1; j < coordinate.length; j++) {
		    			inputs[i][j] = Integer.valueOf(coordinate[j-1]);
					}
		    		
		    	}
		    	
		    	i++;
		        line = br.readLine();
		        
		    }
		} finally {
		    br.close();
		}
		
		numberLink.setInputs(inputs);
		return numberLink;
	}

}
