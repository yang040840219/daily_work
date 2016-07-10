package daily.machine;

public class StochasticGradientDescent {
	public void StochasticGradient() {
		double inputDataMatrix[][] = { { 1, 4,1}, { 2, 5,1}, { 5, 1,1}, { 4, 2,1} }; // X输入
		double expectResult[] = { 21, 28, 21, 22 }; // 期望输出值
		double w[] = { 2, 6 ,1}; // 权重参数 因为这里只涉及到两个变量 ，即X为两列输入
		double learningRate = 0.01;
		double loss = 100; // 损失值
		for (int i = 0; i < 1000 && loss > 0.001; i++) {
			double err_sum = 0;
			int j = i % 4; // 0 1 2 3  这样做每次只是选择一个样本进行计算
			double h = 0;
			for (int k = 0; k < 3; k++) {
				h = h + inputDataMatrix[j][k] * w[k];
			}

			err_sum = expectResult[j] - h;

			for (int k = 0; k < 3; k++) {
				w[k] = w[k] + learningRate * err_sum * inputDataMatrix[j][k]; // 权值每次改变的幅度，这个公式是通过梯度下降得到的
			}

			System.out.println("此时的w权值为：" + "w0:" + w[0] + "---" + "w1:" + w[1] + "---" + w[2]);
			double loss_sum = 0;
			for (int g = 0; g < 4; g++) {
				double sum = 0;
				for (int k = 0; k < 2; k++) {
					sum = sum + inputDataMatrix[g][k] * w[k];
				}

				loss_sum += (expectResult[g] - sum) * (expectResult[g] - sum);
			}
			System.out.println("loss:" + loss_sum);

		}
	}

	public static void main(String[] args) {
		StochasticGradientDescent sgd = new StochasticGradientDescent();
		sgd.StochasticGradient();
	}

}