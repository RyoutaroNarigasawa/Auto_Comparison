package AC;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import FFT.Bhattachariya_coefficient;
import FFT.FFT4g;
import FFT.Linear_interpolation;

public class Auto_Comparison extends JFrame implements ActionListener {

	//ラベルの設定
	JLabel origin = new JLabel("　比較元：");
	JLabel comparison = new JLabel("比較対象：");

	//テキストフィールドの設定
	JTextField original_data = new JTextField(85);//比較元のファイル選択
	JTextField comparison_data = new JTextField(85);//比較対象のファイル選択

	//ボタンの設定
	JButton O_button = new JButton("O_file_select");//比較元のファイル選択
	JButton C_button = new JButton("C_file_select");//比較対象のファイル選択
	JButton S_button = new JButton("START");//比較開始用

	//パネルの設定
	JPanel p = new JPanel();

	//テキストエリアの設定
	JTextArea result = new JTextArea(3, 105);//比較結果を表示するエリア

	//データ保存用の配列群
	ArrayList<Double> before_valueA_A_x_axis = new ArrayList<Double>(1);//正規化前の実験データ保存用A
	ArrayList<Double> before_valueA_A_y_axis = new ArrayList<Double>(1);
	ArrayList<Double> before_valueA_A_z_axis = new ArrayList<Double>(1);
	ArrayList<Double> before_valueA_G_x_axis = new ArrayList<Double>(1);
	ArrayList<Double> before_valueA_G_y_axis = new ArrayList<Double>(1);
	ArrayList<Double> before_valueA_G_z_axis = new ArrayList<Double>(1);

	ArrayList<Double> before_valueB_A_x_axis = new ArrayList<Double>(1);//正規化前の実験データ保存用B
	ArrayList<Double> before_valueB_A_y_axis = new ArrayList<Double>(1);
	ArrayList<Double> before_valueB_A_z_axis = new ArrayList<Double>(1);
	ArrayList<Double> before_valueB_G_x_axis = new ArrayList<Double>(1);
	ArrayList<Double> before_valueB_G_y_axis = new ArrayList<Double>(1);
	ArrayList<Double> before_valueB_G_z_axis = new ArrayList<Double>(1);

	ArrayList<Double> after_valueA_A_x_axis = new ArrayList<Double>(1);//正規化後の実験データ保存用A
	ArrayList<Double> after_valueA_A_y_axis = new ArrayList<Double>(1);
	ArrayList<Double> after_valueA_A_z_axis = new ArrayList<Double>(1);
	ArrayList<Double> after_valueA_G_x_axis = new ArrayList<Double>(1);
	ArrayList<Double> after_valueA_G_y_axis = new ArrayList<Double>(1);
	ArrayList<Double> after_valueA_G_z_axis = new ArrayList<Double>(1);

	ArrayList<Double> after_valueB_A_x_axis = new ArrayList<Double>(1);//正規化後の実験データ保存用B
	ArrayList<Double> after_valueB_A_y_axis = new ArrayList<Double>(1);
	ArrayList<Double> after_valueB_A_z_axis = new ArrayList<Double>(1);
	ArrayList<Double> after_valueB_G_x_axis = new ArrayList<Double>(1);
	ArrayList<Double> after_valueB_G_y_axis = new ArrayList<Double>(1);
	ArrayList<Double> after_valueB_G_z_axis = new ArrayList<Double>(1);

	double[] fft_after_valueA_A_x_axis = new double[512];//フーリエ級数の保存用A
	double[] fft_after_valueA_A_y_axis = new double[512];
	double[] fft_after_valueA_A_z_axis = new double[512];
	double[] fft_after_valueA_G_x_axis = new double[512];
	double[] fft_after_valueA_G_y_axis = new double[512];
	double[] fft_after_valueA_G_z_axis = new double[512];

	double[] fft_after_valueB_A_x_axis = new double[512];//フーリエ級数保存用B
	double[] fft_after_valueB_A_y_axis = new double[512];
	double[] fft_after_valueB_A_z_axis = new double[512];
	double[] fft_after_valueB_G_x_axis = new double[512];
	double[] fft_after_valueB_G_y_axis = new double[512];
	double[] fft_after_valueB_G_z_axis = new double[512];

	int i = 0; //ループカウンタ

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ		
		Auto_Comparison frame = new Auto_Comparison("センサデータ比較プログラム(FFT対応済み)");//フレーム作成
		frame.setVisible(true);
	}

	Auto_Comparison(String title) {//フレーム内容
		setBounds(200, 200, 1200, 230);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(title);

		//保存用配列に先頭に0.0を代入
		start_number();

		//テキストフィールドの権限設定
		original_data.setEnabled(false);//ファイル選択のため、テキストフィールドの変更は無効
		comparison_data.setEnabled(false);//上記と同様

		//ボタンの動作設定
		O_button.addActionListener(this);//比較元のファイル操作をおこなう
		C_button.addActionListener(this);//比較対象のファイル操作をおこなう
		S_button.addActionListener(this);//比較の開始をおこなう

		//テキストエリアの権限設定
		result.setEditable(false);//結果出力のためテキスト内の操作を編集不可の状態かつ選択有効に設定

		//パネルレイアウトの設定
		p.setLayout(new FlowLayout());

		//各項目の追加
		p.add(origin);
		p.add(original_data);
		p.add(O_button);
		p.add(comparison);
		p.add(comparison_data);
		p.add(C_button);
		p.add(result);
		p.add(S_button);
		getContentPane().add(p);
	}

	public void actionPerformed(ActionEvent e) {//各ボタン操作時の動作設定
		String cmdName = e.getActionCommand();//アクションコマンドによるボタン比較用
		//ファイル取得
		
		
		if ("O_file_select".equals(cmdName)) {//比較元のファイル選択に関する操作
			JFileChooser filechooser = new JFileChooser();
			int selectedA = filechooser.showOpenDialog(this);
			if (selectedA == JFileChooser.APPROVE_OPTION) {
				File fileA = filechooser.getSelectedFile();
				original_data.setText(fileA.getName());
				try {
					FileInputStream FISA = new FileInputStream(fileA); // ファイル読み込み
					XSSFWorkbook wbA = new XSSFWorkbook(FISA); // ワークブックに取り込む
					XSSFSheet sheetA = wbA.getSheetAt(0); // シート０番を取りだす
					XSSFCell cellA_A_x_axis = null;
					XSSFCell cellA_A_y_axis = null;
					XSSFCell cellA_A_z_axis = null;
					XSSFCell cellA_G_x_axis = null;
					XSSFCell cellA_G_y_axis = null;
					XSSFCell cellA_G_z_axis = null;
					i = 0;
					while (true) {
						if (sheetA.getRow(i).getCell(0) == null) {
							break;
						}
						cellA_A_x_axis = sheetA.getRow(i).getCell(0); //　セル(0,0)＝A1を指定
						cellA_A_y_axis = sheetA.getRow(i).getCell(1);
						cellA_A_z_axis = sheetA.getRow(i).getCell(2);
						cellA_G_x_axis = sheetA.getRow(i).getCell(3);
						cellA_G_y_axis = sheetA.getRow(i).getCell(4);
						cellA_G_z_axis = sheetA.getRow(i).getCell(5);

						before_valueA_A_x_axis.add(cellA_A_x_axis.getNumericCellValue());
						before_valueA_A_y_axis.add(cellA_A_y_axis.getNumericCellValue());
						before_valueA_A_z_axis.add(cellA_A_z_axis.getNumericCellValue());
						before_valueA_G_x_axis.add(cellA_G_x_axis.getNumericCellValue());
						before_valueA_G_y_axis.add(cellA_G_y_axis.getNumericCellValue());
						before_valueA_G_z_axis.add(cellA_G_z_axis.getNumericCellValue());
						i++;
					}
					wbA.close();

				} catch (Exception E) {
					//e.printStackTrace();
					//System.out.println("Error Loading");
				}

			} else {
				System.out.println("ファイルが見つからないか開けません");
			}

		}
		if ("C_file_select".equals(cmdName)) {//比較対象のファイル選択に関する操作
			JFileChooser filechooser = new JFileChooser();
			int selectedB = filechooser.showOpenDialog(this);
			if (selectedB == JFileChooser.APPROVE_OPTION) {
				File fileB = filechooser.getSelectedFile();
				comparison_data.setText(fileB.getName());
				try {
					FileInputStream FISB = new FileInputStream(fileB); // ファイル読み込み
					XSSFWorkbook wbB = new XSSFWorkbook(FISB); // ワークブックに取り込む
					XSSFSheet sheetB = wbB.getSheetAt(0); // シート０番を取りだす
					XSSFCell cellB_A_x_axis = null;
					XSSFCell cellB_A_y_axis = null;
					XSSFCell cellB_A_z_axis = null;
					XSSFCell cellB_G_x_axis = null;
					XSSFCell cellB_G_y_axis = null;
					XSSFCell cellB_G_z_axis = null;
					i = 0;
					while (true) {
						if (sheetB.getRow(i).getCell(0) == null) {
							break;
						}
						cellB_A_x_axis = sheetB.getRow(i).getCell(0); //　セル(0,0)＝A1を指定
						cellB_A_y_axis = sheetB.getRow(i).getCell(1);
						cellB_A_z_axis = sheetB.getRow(i).getCell(2);
						cellB_G_x_axis = sheetB.getRow(i).getCell(3);
						cellB_G_y_axis = sheetB.getRow(i).getCell(4);
						cellB_G_z_axis = sheetB.getRow(i).getCell(5);

						before_valueB_A_x_axis.add(cellB_A_x_axis.getNumericCellValue());
						before_valueB_A_y_axis.add(cellB_A_y_axis.getNumericCellValue());
						before_valueB_A_z_axis.add(cellB_A_z_axis.getNumericCellValue());
						before_valueB_G_x_axis.add(cellB_G_x_axis.getNumericCellValue());
						before_valueB_G_y_axis.add(cellB_G_y_axis.getNumericCellValue());
						before_valueB_G_z_axis.add(cellB_G_z_axis.getNumericCellValue());
						i++;
					}
					wbB.close();

				} catch (Exception E) {
					//e.printStackTrace();
					//System.out.println("Error Loading");
				}

			} else {
				System.out.println("ファイルが見つからないか開けません");
			}
		}
		if ("START".equals(cmdName)) {//比較対象のファイル選択に関する操作
			Linear_interpolation linearA = new Linear_interpolation(before_valueA_A_x_axis, before_valueA_A_y_axis,
					before_valueA_A_z_axis, before_valueA_G_x_axis, before_valueA_G_y_axis, before_valueA_G_z_axis);
			Linear_interpolation linearB = new Linear_interpolation(before_valueB_A_x_axis, before_valueB_A_y_axis,
					before_valueB_A_z_axis, before_valueB_G_x_axis, before_valueB_G_y_axis, before_valueB_G_z_axis);
			linearA.linear_interpolation_calculate();
			linearB.linear_interpolation_calculate();
			after_valueA_A_x_axis = linearA.getValueA();
			after_valueA_A_y_axis = linearA.getValueB();
			after_valueA_A_z_axis = linearA.getValueC();
			after_valueA_G_x_axis = linearA.getValueD();
			after_valueA_G_y_axis = linearA.getValueE();
			after_valueA_G_z_axis = linearA.getValueF();

			after_valueB_A_x_axis = linearB.getValueA();
			after_valueB_A_y_axis = linearB.getValueB();
			after_valueB_A_z_axis = linearB.getValueC();
			after_valueB_G_x_axis = linearB.getValueD();
			after_valueB_G_y_axis = linearB.getValueE();
			after_valueB_G_z_axis = linearB.getValueF();

			FFT4g fft = new FFT4g(after_valueA_A_x_axis.size());

			for (i = 0; i < after_valueA_A_x_axis.size(); i++) {
				fft_after_valueA_A_x_axis[i] = after_valueA_A_x_axis.get(i);
				fft_after_valueA_A_y_axis[i] = after_valueA_A_y_axis.get(i);
				fft_after_valueA_A_z_axis[i] = after_valueA_A_z_axis.get(i);
				fft_after_valueA_G_x_axis[i] = after_valueA_G_x_axis.get(i);
				fft_after_valueA_G_y_axis[i] = after_valueA_G_y_axis.get(i);
				fft_after_valueA_G_z_axis[i] = after_valueA_G_z_axis.get(i);
			}
			fft.rdft(1, fft_after_valueA_A_x_axis);
			fft.rdft(1, fft_after_valueA_A_y_axis);
			fft.rdft(1, fft_after_valueA_A_z_axis);
			fft.rdft(1, fft_after_valueA_G_x_axis);
			fft.rdft(1, fft_after_valueA_G_y_axis);
			fft.rdft(1, fft_after_valueA_G_z_axis);

			for (i = 0; i < after_valueB_A_x_axis.size(); i++) {
				fft_after_valueB_A_x_axis[i] = after_valueB_A_x_axis.get(i);
				fft_after_valueB_A_y_axis[i] = after_valueB_A_y_axis.get(i);
				fft_after_valueB_A_z_axis[i] = after_valueB_A_z_axis.get(i);
				fft_after_valueB_G_x_axis[i] = after_valueB_G_x_axis.get(i);
				fft_after_valueB_G_y_axis[i] = after_valueB_G_y_axis.get(i);
				fft_after_valueB_G_z_axis[i] = after_valueB_G_z_axis.get(i);
			}
			fft.rdft(1, fft_after_valueB_A_x_axis);
			fft.rdft(1, fft_after_valueB_A_y_axis);
			fft.rdft(1, fft_after_valueB_A_z_axis);
			fft.rdft(1, fft_after_valueB_G_x_axis);
			fft.rdft(1, fft_after_valueB_G_y_axis);
			fft.rdft(1, fft_after_valueB_G_z_axis);

			Bhattachariya_coefficient Bhat = new Bhattachariya_coefficient(
					fft_after_valueA_A_x_axis,
					fft_after_valueA_A_y_axis,
					fft_after_valueA_A_z_axis,
					fft_after_valueA_G_x_axis,
					fft_after_valueA_G_y_axis,
					fft_after_valueA_G_z_axis,
					fft_after_valueB_A_x_axis,
					fft_after_valueB_A_y_axis,
					fft_after_valueB_A_z_axis,
					fft_after_valueB_G_x_axis,
					fft_after_valueB_G_y_axis,
					fft_after_valueB_G_z_axis);
			Bhat.caluculate();

			result.setText(String.valueOf(Bhat.getresultA()+","+Bhat.getresultB()+","+Bhat.getresultC()+","+Bhat.getresultD()+","+Bhat.getresultE()+","+Bhat.getresultF()));
		}
	}

	private static boolean checkBeforeReadfile(File file) {
		if (file.exists()) {
			if (file.isFile() && file.canRead()) {
				return true;
			}
		}

		return false;
	}

	public void start_number() {//データ取得用の配列の先頭データに0.0を代入するメソッド
		before_valueA_A_x_axis.add(0.0);//正規化のために0番目に0を代入する
		before_valueA_A_y_axis.add(0.0);
		before_valueA_A_z_axis.add(0.0);
		before_valueA_G_x_axis.add(0.0);
		before_valueA_G_y_axis.add(0.0);
		before_valueA_G_z_axis.add(0.0);

		before_valueB_A_x_axis.add(0.0);
		before_valueB_A_y_axis.add(0.0);
		before_valueB_A_z_axis.add(0.0);
		before_valueB_G_x_axis.add(0.0);
		before_valueB_G_y_axis.add(0.0);
		before_valueB_G_z_axis.add(0.0);
	}
}
