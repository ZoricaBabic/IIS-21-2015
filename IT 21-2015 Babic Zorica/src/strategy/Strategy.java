package strategy;

import java.io.File;
import java.io.FileNotFoundException;

public interface Strategy {
	
	public int doOperation(Object o, File f);

}
