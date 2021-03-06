package callable;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.Callable;

/*
 * Calcul une partie de l'intégrale de h*(1/1+x2) 
 */
public class CallableExemple implements Callable<BigDecimal> {

	

	public CallableExemple(BigDecimal from, BigDecimal to, BigDecimal h, MathContext mathContext) {
//		System.out.println("From :" + from + " / to : " + to + " / h : " + h);
		this.from = from;
		this.to = to;
		this.h = h;
		this.mathContext = mathContext;
		
		this.result = new BigDecimal(0);
		
	}

	@Override
	public BigDecimal call() throws Exception {
		BigDecimal one = new BigDecimal(1);
		for(BigDecimal i = from; i.compareTo(to) < 0 ; i = i.add(one)){
	        BigDecimal factor = h.multiply(i); //2.0*(i*h) - h / 2.0
	        result = result.add(h.multiply(  one.divide( one.add(factor.multiply(factor) ) , mathContext) ) );
	    }		
		
		// return the partial sum of this thread
		return result;
	}

	/*
	 * Attributs
	 */
	// Inputs
	private BigDecimal from;
	private BigDecimal to;
	private BigDecimal h;
	private MathContext mathContext;

	// Output
	private BigDecimal result;

}
