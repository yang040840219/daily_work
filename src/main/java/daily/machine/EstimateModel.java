/**
 * 模型预测
 */
package daily.machine;

import java.util.Arrays;

import Jama.Matrix;

/**
 * @author lichx
 *
 */
public class EstimateModel {

	/**
	 * 模型
	 * 
	 * @param xvals
	 * @param yvals
	 * @return model
	 */
	public static Matrix model(double[][] xvals, double[][] yvals) {
		Matrix Atemp = new Matrix(xvals);
		System.out.println("Atemp ....") ;
		Atemp.print(xvals.length,xvals[0].length);
		Matrix A = Atemp.transpose().times(Atemp);
		System.out.println("A ....") ;
		A.print(A.getArray().length,A.getArray()[0].length);
		
		Matrix yy = new Matrix(yvals);
		Matrix b = Atemp.transpose().times(yy);

		Matrix x = A.solve(b);
		
		System.out.println("x ....") ;
		x.print(x.getArray().length,x.getArray()[0].length);

		return x;
	}

	/**
	 * 预测
	 * 
	 * @param x
	 * @param xvals
	 * @param yvals
	 * @return double
	 */
	public static double[][] estimate(double[][] xvals, double[][] yvals,
			double[][] x) {

		Matrix mx = model(xvals, yvals);

		Matrix estimtatemat = new Matrix(x).times(mx);

		return (estimtatemat.getArray());
	}

	/**
	 * 预测 estimate
	 * 
	 * @param x
	 * @param model
	 * @return double
	 */
	public static double[][] estimate(Matrix model, double[][] x) {

		Matrix estimtatemat = new Matrix(x).times(model);

		return (estimtatemat.getArray());
	}

	public static void main(String[] args) {

		double[][] xvals = {{1., 2., 3}, {1., 4., 6.}, {1., 8., 10.},
				{1., 12., 13.}};
		double[][] yvals = {{5.}, {6.}, {7.}, {8.}};

		Matrix x = model(xvals, yvals);
		System.exit(0);
		System.out.println("Matrix x" + Arrays.deepToString(x.getArray()));

		double[][] estimtaedata = {{1., 12., 13}};

		double[][] estimate = estimate(xvals, yvals, estimtaedata);
		System.out.println("Matrix estimtate" + Arrays.deepToString(estimate));
	}

}
