package strategy;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

import crtanje.NaslovnaPokretanje;

public class ReadOperation implements Strategy{

	@Override
	public int doOperation(Object o, File f) {
		
		/*NaslovnaPokretanje np = (NaslovnaPokretanje) o;
		
		try {
			
			try(BufferedReader br = new BufferedReader(new FileReader(f))) {
			    for(String line; (line = br.readLine()) != null; ) {
			        
			    	
			    }
			    // line is not visible here.
			}
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return 0;*/
		
		return 0;
	}
	
	

}
