package strategy;

import java.io.File;

public class Context {
	
	private Strategy strategy;

	   public Context(Strategy strategy){
	      this.strategy = strategy;
	   }

	   public int executeStrategy(Object o, File f){
		   
	     return strategy.doOperation(o, f);
	   }

}
