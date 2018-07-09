package com.ljw.levenshtein;


/**
 * 莱文斯坦算法： 计算2个字符串的最小距离，
 * 可以用于模糊搜索
 * 
 * https://baike.baidu.com/item/%E7%BC%96%E8%BE%91%E8%B7%9D%E7%A6%BB/8010193?fr=aladdin&fromid=792226&fromtitle=Levenshtein+Distance
 * 
 * @author PC
 *
 */
public class StringSimilar {

	/**
	 * 编辑距离求串相似度
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public double getStringSimilar(String s1, String s2) {

		double d[][];// matrix
		int n;// length of s
		int m;// length of t
		int i;// iterates through s
		int j;// iterates through t
		char s_i;// i th character of s

		char t_j;// j th character of t

		double cost;// cost
		// Step1
		n = s1.length();
		m = s2.length();

		if (n == 0) {
			return m;
		}

		if (m == 0) {
			return n;
		}

		d = new double[n + 1][m + 1];
		// Step2
		for (i = 0; i <= n; i++) {
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) {
			d[0][j] = j;
		}

		// Step3
		for (i = 1; i <= n; i++) {
			s_i = s1.charAt(i - 1);
			// Step4
			for (j = 1; j <= m; j++) {
				t_j = s2.charAt(j - 1);
				// Step5
				if (s_i == t_j) {
					cost = 0;
				} else {
					cost = 1;
				}
				// Step6
				d[i][j] = Minimum(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + cost);
			}
		}
		// Step7
		return d[n][m];
	}// 求最小值

	private double Minimum(double a, double b, double c) {

		double mi;
		mi = a;
		if (b < mi) {
			mi = b;
		}
		if (c < mi) {
			mi = c;
		}
		return mi;
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		StringSimilar similar = new StringSimilar();
		double stringSimilar = similar.getStringSimilar("中国近代史上", "中或近代史");
		System.out.println(stringSimilar);
	}
}
