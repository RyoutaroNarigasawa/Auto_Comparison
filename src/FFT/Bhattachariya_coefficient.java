package FFT;
import java.util.ArrayList;

public class Bhattachariya_coefficient {
	ArrayList<Double> XA = new ArrayList<Double>(1); //
	ArrayList<Double> XB = new ArrayList<Double>(1);
	ArrayList<Double> XC = new ArrayList<Double>(1);
	ArrayList<Double> XD = new ArrayList<Double>(1);
	ArrayList<Double> XE = new ArrayList<Double>(1);
	ArrayList<Double> XF = new ArrayList<Double>(1);
	
	ArrayList<Double> YA = new ArrayList<Double>(1);
	ArrayList<Double> YB = new ArrayList<Double>(1);
	ArrayList<Double> YC = new ArrayList<Double>(1);
	ArrayList<Double> YD = new ArrayList<Double>(1);
	ArrayList<Double> YE = new ArrayList<Double>(1);
	ArrayList<Double> YF = new ArrayList<Double>(1);
	
	ArrayList<Double> xA= new ArrayList<Double>(1);
	ArrayList<Double> xB= new ArrayList<Double>(1);
	ArrayList<Double> xC= new ArrayList<Double>(1);
	ArrayList<Double> xD= new ArrayList<Double>(1);
	ArrayList<Double> xE= new ArrayList<Double>(1);
	ArrayList<Double> xF= new ArrayList<Double>(1);
	
	ArrayList<Double> yA = new ArrayList<Double>(1);
	ArrayList<Double> yB = new ArrayList<Double>(1);
	ArrayList<Double> yC = new ArrayList<Double>(1);
	ArrayList<Double> yD = new ArrayList<Double>(1);
	ArrayList<Double> yE = new ArrayList<Double>(1);
	ArrayList<Double> yF = new ArrayList<Double>(1);
	
	double sumXA,sumXB,sumXC,sumXD,sumXE,sumXF = 0;
	double sumYA,sumYB,sumYC,sumYD,sumYE,sumYF = 0;
	double sumxA,sumxB,sumxC,sumxD,sumxE,sumxF = 0;
	double sumyA,sumyB,sumyC,sumyD,sumyE,sumyF = 0;
	
	double resultA = 0;
	double resultB = 0;
	double resultC = 0;
	double resultD = 0;
	double resultE = 0;
	double resultF = 0;

	/*public Bhattachariya_coefficient(ArrayList<Double> XA, ArrayList<Double> XB,
			ArrayList<Double> XC, ArrayList<Double> XD, ArrayList<Double> XE,
			ArrayList<Double> XF,ArrayList<Double> YA, ArrayList<Double> YB,
			ArrayList<Double> YC, ArrayList<Double> YD, ArrayList<Double> YE,
			ArrayList<Double> YF) {*/ //ArrayListを使う場合
	public Bhattachariya_coefficient(double[] AXA,double[] AYA,double[] AZA,double[] AXG,double[] AYG,double[] AZG,
									 double[] BXA,double[] BYA,double[] BZA,double[] BXG,double[] BYG,double[] BZG){
	
		for(int i = 0; i < 512; i++){
		this.XA.add(AXA[i]);
		this.XB.add(AYA[i]);
		this.XC.add(AZA[i]);
		this.XD.add(AXG[i]);
		this.XE.add(AYG[i]);
		this.XF.add(AZG[i]);
		
		this.YA.add(BXA[i]);
		this.YB.add(BYA[i]);
		this.YC.add(BZA[i]);
		this.YD.add(BXG[i]);
		this.YE.add(BYG[i]);
		this.YF.add(BZG[i]);
		}
	}

	public void caluculate() {
		int i;
		for (i = 0; i < 512; i++) {
			
			if ((XA.get(i) + 300) < 0) {
				XA.set(i, (double) -300.0);
			}
			if ((XB.get(i) + 300) < 0) {
				XB.set(i, (double) -300.0);
			}
			if ((XC.get(i) + 300) < 0) {
				XC.set(i, (double) -300.0);
			}
			if ((XD.get(i) + 300) < 0) {
				XD.set(i, (double) -300.0);
			}
			if ((XE.get(i) + 300) < 0) {
				XE.set(i, (double) -300.0);
			}
			if ((XF.get(i) + 300) < 0) {
				XF.set(i, (double) -300.0);
			}
			
			if ((YA.get(i) + 300) < 0) {
				YA.set(i, (double) -300.0);
			}
			if ((YB.get(i) + 300) < 0) {
				YB.set(i, (double) -300.0);
			}
			if ((YC.get(i) + 300) < 0) {
				YC.set(i, (double) -300.0);
			}
			if ((YD.get(i) + 300) < 0) {
				YD.set(i, (double) -300.0);
			}
			if ((YE.get(i) + 300) < 0) {
				YE.set(i, (double) -300.0);
			}
			if ((YF.get(i) + 300) < 0) {
				YF.set(i, (double) -300.0);
			}
			
			XA.set(i, XA.get(i) + 300);
			XB.set(i, XB.get(i) + 300);
			XC.set(i, XC.get(i) + 300);
			XD.set(i, XD.get(i) + 300);
			XE.set(i, XE.get(i) + 300);
			XF.set(i, XF.get(i) + 300);
			
			YA.set(i, YA.get(i) + 300);
			YB.set(i, YB.get(i) + 300);
			YC.set(i, YC.get(i) + 300);
			YD.set(i, YD.get(i) + 300);
			YE.set(i, YE.get(i) + 300);
			YF.set(i, YF.get(i) + 300);
			

			sumXA = sumXA + XA.get(i);
			sumXB = sumXB + XB.get(i);
			sumXC = sumXC + XC.get(i);
			sumXD = sumXD + XD.get(i);
			sumXE = sumXE + XE.get(i);
			sumXF = sumXF + XF.get(i);
			
			sumYA = sumYA + YA.get(i);
			sumYB = sumYB + YB.get(i);
			sumYC = sumYC + YC.get(i);
			sumYD = sumYD + YD.get(i);
			sumYE = sumYE + YE.get(i);
			sumYF = sumYF + YF.get(i);
			
		}

		for (i = 0; i < 512; i++) {
			xA.add(XA.get(i) / sumXA);
			xB.add(XB.get(i) / sumXB);
			xC.add(XC.get(i) / sumXC);
			xD.add(XD.get(i) / sumXD);
			xE.add(XE.get(i) / sumXE);
			xF.add(XF.get(i) / sumXF);
			
			
			yA.add(YA.get(i) / sumYA);
			yB.add(YB.get(i) / sumYB);
			yC.add(YC.get(i) / sumYC);
			yD.add(YD.get(i) / sumYD);
			yE.add(YE.get(i) / sumYE);
			yF.add(YF.get(i) / sumYF);
			

			sumxA += xA.get(i);
			sumxB += xB.get(i);
			sumxC += xC.get(i);
			sumxD += xD.get(i);
			sumxE += xE.get(i);
			sumxF += xF.get(i);
			
			sumyA += yA.get(i);
			sumyB += yB.get(i);
			sumyC += yC.get(i);
			sumyD += yD.get(i);
			sumyE += yE.get(i);
			sumyF += yF.get(i);
			
		}
		for (i = 0; i < 512; i++) {
			resultA += Math.sqrt(xA.get(i) * yA.get(i));
			resultB += Math.sqrt(xB.get(i) * yB.get(i));
			resultC += Math.sqrt(xC.get(i) * yC.get(i));
			resultD += Math.sqrt(xD.get(i) * yD.get(i));
			resultE += Math.sqrt(xE.get(i) * yE.get(i));
			resultF += Math.sqrt(xF.get(i) * yF.get(i));
		}
	}
	
	public double getresultA(){
		return 1-resultA;
	}
	public double getresultB(){
		return 1-resultB;
	}
	public double getresultC(){
		return 1-resultC;
	}
	public double getresultD(){
		return 1-resultD;
	}
	public double getresultE(){
		return 1-resultE;
	}
	public double getresultF(){
		return 1-resultF;
	}
	
}
