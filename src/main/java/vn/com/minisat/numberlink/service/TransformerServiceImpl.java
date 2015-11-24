package vn.com.minisat.numberlink.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.com.minisat.numberlink.model.NumberLink;

@Service
public class TransformerServiceImpl implements TransformerService{

	@Override
	public NumberLink readNumberLink(String filename) throws IOException, URISyntaxException {
		NumberLink numberLink = new NumberLink();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		try {
		    String line = br.readLine();
		    int i = -1;
		    while (line != null) {
		    	String[] coordinate = line.split(" +");
		    	if(numberLink != null && i > numberLink.getCols() - 1) {
		    		throw new RuntimeException("Invalid format numberlink");
		    	}
		    		
		    	if(i == -1) {
		    		if(coordinate == null || coordinate.length != 2) {
		    			throw new RuntimeException("Invalid format numberlink");
		    		}
		    		numberLink.setRows(Integer.valueOf(coordinate[0]));
		    		numberLink.setCols(Integer.valueOf(coordinate[1]));
		    	} else {
		    		List<Integer> rows = new ArrayList<>();
		    		for (int j = 0; j < coordinate.length; j++) {
		    			rows.add(Integer.valueOf(coordinate[j]));
					}
		    		
		    		numberLink.getFields().add(rows);
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
