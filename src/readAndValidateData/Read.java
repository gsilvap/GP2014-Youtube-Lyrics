package readAndValidateData;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class Read - Leitura de dados do utlizador
 * @author Goncalo Silva Pereira 2009111643
 * @author Paulo Miguel Guimaraes da Silva 2009116753
 */
public class Read {
	// Inteiro Neutro
	public static final int INTNEUTER = -1;
	
	/**
	 * Le um inteiro se o min e o max forem iguais a INTNEUTRO, caso contrario entre o min e o max, inclusive
	 * @param msg a imprimir ao pedir um numero inteiro ao utilizador
	 * @param min minimo valor valido que pode ser recebido do utilizador
	 * @param max maximo valor valido que pode ser recebido do utilizador
	 * @return int numero lido do utilizador
	 */
	public static int readInt(String msg, int min, int max) throws NoSuchElementException
	{
		boolean flag = false;
		String aux;
		int number = 0;
		do{
			aux = readString(msg);
			while(!Validate.validInteger(aux))
			{
				System.out.println("ERRO!!! Nao e um numero inteiro positivo!!!");
				aux = readString(msg);
			}
			flag = true;
			try
			{
				number = Integer.parseInt(aux);
			}
			catch(java.lang.NumberFormatException e)
			{
				System.out.println("ERRO!!! Nunca pode ser maior que "+ Integer.MAX_VALUE);
				flag = false;
			}
			if (flag && (min != INTNEUTER && number < min || number > max && max != INTNEUTER))
			{
				System.out.println("ERRO!!! Nao esta no intervalo ["+min+","+max+"]!!!");
				flag = false;
			}
		}while(!flag);
		return number;
	}
	
	/**
	 * Le uma string introduzida pelo utilizador
	 * @param msg a imprimir ao pedir uma String ao utilizador
	 * @return String aux lida do utilizador
	 */
	public static String readString (String msg) throws NoSuchElementException
	{
		System.out.print(msg);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String aux = sc.nextLine();
		while(aux.length() == 0 || aux.trim().length() == 0)
		{
			System.out.println("ERRO!!! Dados invalidos!!!");
			System.out.print(msg);
			aux = sc.nextLine();
		}
//		sc.close();
		return aux;
	}
	
	/**
	 * Le uma string so de letras introduzida pelo utilizador
	 * @param msg a imprimir ao pedir uma String ao utilizador
	 * @return String aux lida do utilizador
	 */
	public static String readStringOnlyLetters(String msg)
	{
		String aux = readString(msg);
		while(Validate.validString(aux, false) == false)
		{
			System.out.println("ERRO!!! Dados invalidos!!!");
			aux = readString(msg);
		}
		return aux;
	}
	
	/**
	 * Le um mes introduzido pelo utilizador
	 * @param msg a imprimir ao pedir um mes ao utilizador
	 * @return GregorianCalendar aux lida do utilizador
	 */
	public static GregorianCalendar readMonth(String msg)
	{
		String date = readString(msg);
		while (!Validate.isValidMonth(date))
		{
			System.out.println("ERRO!!! Mes invalido!!! Formato MM/yyyy.");
			date = readString(msg);
		}
		GregorianCalendar aux = new GregorianCalendar(Integer.parseInt(date.substring(3)), (Integer.parseInt(date.substring(0,2))),0);
		return aux;
	}
	
	/**
	 * Le um dia introduzido pelo utilizador
	 * @param msg a imprimir ao pedir um dia ao utilizador
	 * @param mes de onde tem de ser o dia
	 * @return GregorianCalendar aux lida do utilizador
	 */
	public static GregorianCalendar readDay(String msg, GregorianCalendar mes)
	{
		String day = readString(msg);
		while (!Validate.isValidDay(day, mes))
		{
			System.out.println("ERRO!!! Dia invalido!!! Formato dd.");
			day = readString(msg);
		}
		GregorianCalendar aux = new GregorianCalendar(mes.get(Calendar.YEAR), (mes.get(Calendar.MONTH))+1, Integer.parseInt(day));
		return aux;
	}
	
	/**
	 * Le uma data introduzida pelo utilizador
	 * @param msg a imprimir ao pedir uma data ao utilizador
	 * @return GregorianCalendar aux data lida do utilizador
	 */
	public static GregorianCalendar readDate(String msg)
	{
		String date = readString(msg);
		while (!Validate.isValidDate(date))
		{
			System.out.println("ERRO!!! Data invalida!!! Formato dd/MM/yyyy.");
			date = readString(msg);
		}
		GregorianCalendar aux = new GregorianCalendar(Integer.parseInt(date.substring(6)), Integer.parseInt(date.substring(3, 5))-1, Integer.parseInt(date.substring(0,2)));
		
		return aux;
	}
}
