package shop;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validation {
	//[a-zA-Z0-9]
	//"^[0-9a-zA-Z]+$"
	public static boolean StringMatch(String str){
		Pattern p = Pattern.compile("^[^\\\\<>&\"'()@,//.;]*$");
		//Pattern p = Pattern.compile("^[^\\\\<>&\"'()@,;]*$");
		Pattern p1 = Pattern.compile("^[0-9a-zA-Z]+$");
		Matcher m = p.matcher(str);
		boolean b = m.matches();
		return b;
	}

	public static boolean MailMatch(String str){
		Pattern p = Pattern.compile("^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$");
		Matcher m = p.matcher(str);
		boolean b = m.matches();
		return b;
	}

	public static boolean IntMatch(int i){
		String str = String.valueOf(i);
		Pattern p = Pattern.compile("^[0-9]+$");
		Matcher m = p.matcher(str);
		boolean b = m.matches();
		return b;
	}

	public static void checkMailAddressByRegularExpression(String address) {//[20]
		boolean result;//[21]
		String aText = "[\\w!#%&'/=~`\\*\\+\\?\\{\\}\\^\\$\\-\\|]";//[22]
		String dotAtom = aText + "+" + "(\\." + aText + "+)*";//[23]
		String regularExpression = "^" + dotAtom + "@" + dotAtom + "$";//[24]
		result = checkMailAddress(address, regularExpression);//[25]
		if (result)	return;;//[26]
		System.out.println("[27] 不正なメールアドレス：" + address);
	}
	public static boolean checkMailAddress(String address, String regularExpression) {//[40]
		Pattern pattern = Pattern.compile(regularExpression);//[41]
		Matcher matcher = pattern.matcher(address);//[42]
		if (matcher.find()) {//[43]
			System.out.println("[44] " + "メールアドレス：" + matcher.group());
			return true;//[45]
		}
		return false;//[46]
	}

	}

