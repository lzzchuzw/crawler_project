package com.utils.random;

import java.util.Random;

public class RandomNumberGenerator {

	// Log log = LogFactory.getLog(this.getClass());

	/**
	 * 随机产生lenth长度的随机数,其中第一位非0
	 * 
	 * @param lenth
	 *            需要产生的随机数的长度
	 * @return
	 */
	public static String generateRandomString(int lenth) {
		StringBuffer sb = new StringBuffer(lenth);
		long times = System.currentTimeMillis();
		Random r = new Random(times);
		int index = 0;
		do {
			index = r.nextInt(10);
		} while (0 == index);
		sb.append((char) ('0' + index));
		for (index = 1; index < lenth; index++) {
			sb.append((char) ('0' + r.nextInt(10)));
		}
		return sb.toString();
	}
	/**
	 * 
	* @Title: generateRandomStringBeginWithZero
	* @Description: 随机生成以0开头的长度为lenth的随机数字串
	* @param lenth
	* @return String
	* @author leisure
	* @date 2018年8月24日上午9:15:33
	 */
	public static String generateRandomStringBeginWithZero(int lenth) {
		StringBuffer sb = new StringBuffer(lenth)
			                  .append((char) ('0' + 0));
		long times = System.currentTimeMillis();
		Random r = new Random(times);
		for(int index=1;index<lenth;index++) {
			sb.append((char) ('0' + r.nextInt(10)));
		}
		return new String(sb);
	}

	/**
	 * 产生指定总长度和整形部分长度的随机double数
	 * 
	 * @param lenth
	 *            产生的随机double总长度
	 * @param intLenth
	 *            产生的随便double数的整数部分长度
	 * @return 随机double数的String类型
	 */
	public static String generateRandomDoubleString(int lenth, int intLenth) {
		String s = null;
		// double数最大长度19位,一般15-19位之间
		if (0 >= lenth || 0 >= intLenth || lenth < intLenth || lenth > 18) {
			return s;
		}
		String sInt = null;

		double r = 0;

		do {
			r = Math.random();
			

			System.out.println("init---r = " + r + "----r.lenth = " + String.valueOf(r).length());
			double times = Math.pow(10, intLenth);
			r = r * times;
			System.out.println("final---r = " + r + "---r.lenth = " + String.valueOf(r).length()+"----times = "+times);
			s = String.valueOf(r).substring(0, lenth);
			sInt = s.substring(0, s.indexOf("."));
			System.out.println("s.lenth = " + s.length() + "----sInt.lenth = " + sInt.length());
		} while (s.length() != 15 || sInt.length() != 5);

		System.out.println("s = " + s + "---------------------s.lenth = " + s.length()
				+ "----------------------------s.intLenth = " + s.substring(0, s.indexOf(".")).length());

		return s;
	}
	/**
	 * 
	* @Title: generateRandomNumBetweenBounday
	* @Description: 随机产生介于min和max之间的整数
	* @param min
	* @param max
	* @return int
	* @author leisure
	* @date 2018年8月29日下午3:31:01
	 */
	public static int generateRandomNumBetweenBounday(int min,int max) {
		Random random = new Random();
		int ret = random.nextInt(max-min+1)+min;
		//System.out.println("min = "+min+"---max = "+max+"--ret = "+ret);
		return ret;
	}

}
