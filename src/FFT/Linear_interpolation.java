package FFT;
import java.util.ArrayList;

public class Linear_interpolation {

	ArrayList<Double> Interpolated_valueA = new ArrayList<Double>(1);//補間値
	ArrayList<Double> Interpolated_valueB = new ArrayList<Double>(1);
	ArrayList<Double> Interpolated_valueC = new ArrayList<Double>(1);
	ArrayList<Double> Interpolated_valueD = new ArrayList<Double>(1);
	ArrayList<Double> Interpolated_valueE = new ArrayList<Double>(1);
	ArrayList<Double> Interpolated_valueF = new ArrayList<Double>(1);

	double Interpolation_interval; //補間する間隔

	ArrayList<Double> valueA = new ArrayList<Double>(1);//正規化前の実験データ
	ArrayList<Double> valueB = new ArrayList<Double>(1);
	ArrayList<Double> valueC = new ArrayList<Double>(1);
	ArrayList<Double> valueD = new ArrayList<Double>(1);
	ArrayList<Double> valueE = new ArrayList<Double>(1);
	ArrayList<Double> valueF = new ArrayList<Double>(1);

	ArrayList<Double> after_valueA = new ArrayList<Double>(1);//正規化後の実験データ
	ArrayList<Double> after_valueB = new ArrayList<Double>(1);
	ArrayList<Double> after_valueC = new ArrayList<Double>(1);
	ArrayList<Double> after_valueD = new ArrayList<Double>(1);
	ArrayList<Double> after_valueE = new ArrayList<Double>(1);
	ArrayList<Double> after_valueF = new ArrayList<Double>(1);

	ArrayList<Double> interval = new ArrayList<Double>(1); //元の要素数からの補間する間隔
	int i;
	double answer = 0.0;

	public Linear_interpolation(ArrayList<Double> before_valueA, ArrayList<Double> before_valueB,
			ArrayList<Double> before_valueC, ArrayList<Double> before_valueD, ArrayList<Double> before_valueE,
			ArrayList<Double> before_valueF) {
		valueA = before_valueA;
		valueB = before_valueB;
		valueC = before_valueC;
		valueD = before_valueD;
		valueE = before_valueE;
		valueF = before_valueF;
	}

	public void linear_interpolation_calculate() {
		for (i = 1; i <= 512; i++) {
			interval.add((double) ((valueA.size() - 1) / 512.00) * (i));//補間間隔の決定
		}
		for (i = 1; i <= 512; i++) {//決定した補間する間隔を用いて線形補間をおこなう
			//X軸回りに関する加速度
			if ((Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)) == 0)) {
				Interpolated_valueA.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueA.get((int) Math.ceil(interval.get(i - 1))) 
								- valueA.get((int) Math.floor(interval.get(i - 1)))))
								+ valueA.get((int) Math.floor(interval.get(i - 1))));
			} else {
				Interpolated_valueA.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueA.get((int) Math.ceil(interval.get(i - 1))) 
								- valueA.get((int) Math.floor(interval.get(i - 1)))))
								/ (Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)))
								+ valueA.get((int) Math.floor(interval.get(i - 1))));
			}
			//Y軸回りに関する加速度
			if ((Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)) == 0)) {
				Interpolated_valueB.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueB.get((int) Math.ceil(interval.get(i - 1))) 
								- valueB.get((int) Math.floor(interval.get(i - 1)))))
								+ valueB.get((int) Math.floor(interval.get(i - 1))));
			} else {
				Interpolated_valueB.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueB.get((int) Math.ceil(interval.get(i - 1))) 
								- valueB.get((int) Math.floor(interval.get(i - 1)))))
								/ (Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)))
								+ valueB.get((int) Math.floor(interval.get(i - 1))));
			}
			//Z軸回りに関する加速度
			if ((Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)) == 0)) {
				Interpolated_valueC.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueC.get((int) Math.ceil(interval.get(i - 1))) 
								- valueC.get((int) Math.floor(interval.get(i - 1)))))
								+ valueC.get((int) Math.floor(interval.get(i - 1))));
			} else {
				Interpolated_valueC.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueC.get((int) Math.ceil(interval.get(i - 1))) 
								- valueC.get((int) Math.floor(interval.get(i - 1)))))
								/ (Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)))
								+ valueC.get((int) Math.floor(interval.get(i - 1))));
			}
			//X軸回りに関する角速度
			if ((Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)) == 0)) {
				Interpolated_valueD.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueD.get((int) Math.ceil(interval.get(i - 1))) 
								- valueD.get((int) Math.floor(interval.get(i - 1)))))
								+ valueD.get((int) Math.floor(interval.get(i - 1))));
			} else {
				Interpolated_valueD.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueD.get((int) Math.ceil(interval.get(i - 1))) 
								- valueD.get((int) Math.floor(interval.get(i - 1)))))
								/ (Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)))
								+ valueD.get((int) Math.floor(interval.get(i - 1))));
			}
			//Y軸回りに関する角速度
			if ((Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)) == 0)) {
				Interpolated_valueE.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueE.get((int) Math.ceil(interval.get(i - 1))) 
								- valueE.get((int) Math.floor(interval.get(i - 1)))))
								+ valueE.get((int) Math.floor(interval.get(i - 1))));
			} else {
				Interpolated_valueE.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueE.get((int) Math.ceil(interval.get(i - 1))) 
								- valueE.get((int) Math.floor(interval.get(i - 1)))))
								/ (Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)))
								+ valueE.get((int) Math.floor(interval.get(i - 1))));
			}
			//Z軸回りに関する角速度
			if ((Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)) == 0)) {
				Interpolated_valueF.add(((interval.get(i - 1) - Math.floor(interval.get(i - 1))) 
								* (valueF.get((int) Math.ceil(interval.get(i - 1))) 
								- valueF.get((int) Math.floor(interval.get(i - 1)))))
								+ valueF.get((int) Math.floor(interval.get(i - 1))));
			} else {
				Interpolated_valueF.add(
						((interval.get(i - 1) - Math.floor(interval.get(i - 1)))
								* (valueF.get((int) Math.ceil(interval.get(i - 1)))
								- valueF.get((int) Math.floor(interval.get(i - 1)))))
								/ (Math.ceil(interval.get(i - 1)) - Math.floor(interval.get(i - 1)))
								+ valueF.get((int) Math.floor(interval.get(i - 1))));
			}
		}
	}

	public ArrayList<Double> getValueA() {

		return Interpolated_valueA;
	}

	public ArrayList<Double> getValueB() {

		return Interpolated_valueB;
	}

	public ArrayList<Double> getValueC() {

		return Interpolated_valueC;
	}

	public ArrayList<Double> getValueD() {

		return Interpolated_valueD;
	}

	public ArrayList<Double> getValueE() {

		return Interpolated_valueE;
	}

	public ArrayList<Double> getValueF() {

		return Interpolated_valueF;
	}
}
