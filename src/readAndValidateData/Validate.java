package readAndValidateData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Class Validate - Validacoes de dados
 * @author Goncalo Silva Pereira 2009111643
 * @author Paulo Miguel Guimaraes da Silva 2009116753
 */
public class Validate {
	// Tamanho dos dias
	public static final int DAYLENGTH = 2;
	
	/**
	 * Recebe uma string e verifica se todos os caracteres dessa string sao digitos
	 * @param aux String para validar
	 * @return boolean
	 */
	public static boolean validInteger(String aux)
	{
		for (int i = 0; i < aux.length(); i++)
		{
			if (!Character.isDigit(aux.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}	
	
	/**
	 * Recebe uma string e verifica se todos os caracteres dessa string sao letras e/ou espacos
	 * @param aux String para validar
	 * @param withWhitespace booleano que indica se a String pode ou nao ter espacos
	 * @return boolean
	 */
	public static boolean validString(String aux, boolean withWhitespace)
	{
		for (int i = 0; i < aux.length(); i++)
		{
			if (!Character.isLetter(aux.charAt(i)) && aux.charAt(i) != ' ')
			{
				return false;
			}
			if (withWhitespace == false && withoutWhitespace(aux) == false)
			{
				return false;
			}
		}
		return true;
	}	
	
	/**
	 * Recebe uma string e retorna falso caso tenha algum espaco, caso contrario true
	 * @param aux String para validar
	 * @return boolean
	 */
	public static boolean withoutWhitespace(String aux)
	{
		if (aux.compareTo(aux.trim()) != 0)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Verifica se um dada mes existe ou nao
	 * @param date String para validar
	 * @return boolean
	 */
	public static boolean isValidMonth(String date)
	{
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
	    Date newDate = null;
	
	    try
	    {
	    	newDate = sdf.parse(date);
	    }
	    catch (java.text.ParseException e)
	    {
	    	return false;
	    }
//	    Verificar se a data � mesmo v�lida
	    if (!sdf.format(newDate).equals(date))
	    {
	    	return false;
	    }
	    return true;
	}
	
	/**
	 * Verifica se uma dada data existe ou nao
	 * @param date String para validar
	 * @param mes mes onde a data tem de se encontrar
	 * @return boolean
	 */
	public static boolean isValidDay(String date, GregorianCalendar mes)
	{
		if (date.length() < DAYLENGTH)
		{
			date = "0".concat(date);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
	    Date newDate = null;
	
	    try
	    {
	    	newDate = sdf.parse(date);
	    }
	    catch (java.text.ParseException e)
	    {
	    	return false;
	    }
//	    Verificar se a data e mesmo valida
	    if (!sdf.format(newDate).equals(date))
	    {
	    	return false;
	    }
	    return true;
	}
	
	/**
	 * Verifica se uma dada data existe ou nao
	 * @param date String para validar
	 * @return boolean
	 */
	public static boolean isValidDate(String date)
	{
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    Date newDate = null;
	
	    try
	    {
	    	newDate = sdf.parse(date);
	    }
	    catch (java.text.ParseException e)
	    {
	    	return false;
	    }
//	    Verificar se a data e mesmo valida
	    if (!sdf.format(newDate).equals(date))
	    {
	    	return false;
	    }
	    return true;
	}
}
