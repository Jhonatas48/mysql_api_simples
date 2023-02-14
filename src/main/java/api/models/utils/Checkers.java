package api.models.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import api.exception.TransactionTypeIsNullException;
import api.models.enums.TransactionType;

public class Checkers {

	public static boolean isNull(String string) {
		
		if(string==null) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isEmpty(String string) {
	
		if(isNull(string)) {
			
			return true;
		
		}
	
		return string.isEmpty();
	}
	
	public static boolean isNotEmpty(String string) {
		
		return !isEmpty(string);
		
	}
	
	public  static boolean isListEmpty(List<?> list) {
		
		if(list==null) {
		
			return true;
		}
		
		return list.isEmpty();
	}
	
	public static void valideListNotEmpty(List<?>list,String listaName) {
		if(isListEmpty(list)) {
			throw new NullPointerException("A Lista "+listaName+" não pode ser vazia");
		}
	}
	public static void validateStringNotNull(String string,String nameString) {
		
		if(isEmpty(string)) {
			throw new NullPointerException("Campo "+nameString+" nao pode ser nulo");
		}
		
	}
	public static boolean isObjectNotNull(Object object) {
		
		if(object==null) {
			return false;
		}
		
		return true;
	}

	public static boolean validadeObjectNotNull(Object object,String nameString) {
		
		if(object==null) {
			throw new NullPointerException("O objeto"+nameString+" nao pode ser nulo");
		}
		
		return true;
	}
	
	public static void validateTransactionType(TransactionType type) {
		
		if(type == null) {
			throw new TransactionTypeIsNullException();
		}
	}
	public static boolean isValidIpV4(String ip)
	{
		if (isEmpty(ip)) {
			return false;
		}
		
		if(ip.equals("0.0.0.0")) {
			return false;
		}
		
		final String IPV4_REGEX =
				"^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
				"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
				"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
				"(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
		Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);
		Matcher matcher = IPv4_PATTERN.matcher(ip);

		return matcher.matches();
	}
}
