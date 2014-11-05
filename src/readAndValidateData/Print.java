package readAndValidateData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * Class Print - Impressao de dados
 * @author Goncalo Silva Pereira 2009111643
 */
public class Print {
	/**
	 * Imprime uma msg e um dado ArrayList
	 * @param msg para imprimir antes do array
	 * @param array a imprimir
	 */
	public static void printArrayList(String msg, ArrayList<?> array)
	{
		if(array.size() == 0)
		{
			System.out.println("Sem dados para Imprimir!!!");
			return;
		}
		System.out.println("\n" + msg);
		for (Object element : array)
			System.out.println(element);
		System.out.println();
	}	
	
	/**
	 * Imprime uma msg e um dado ArrayList, em forma de Menu
	 * @param msg para imprimir antes do array
	 * @param array a imprimir
	 */
	public static void printArrayListMenu(String msg, ArrayList<?> array)
	{
		System.out.println(msg);
		int i = 0;
		for (Object element : array)
		{
			System.out.println(i+"-> "+element);
			i++;
		}
	}
	
	/**
	 * Imprime uma msg e um dado Set
	 * @param msg para imprimir antes do array
	 * @param set a imprimir
	 */
	public static void printSet(String msg, Set<?> set)
	{
		if(set.size() == 0)
		{
			System.out.println("Sem dados para Imprimir!!!");
			return;
		}
		System.out.println("\n" + msg);
		for (Object element : set)
			System.out.println(element);
		System.out.println();
	}
	
	/**
	 * Imprime uma msg e um dado Set, em forma de Menu
	 * @param msg para imprimir antes do array
	 * @param set a imprimir
	 */
	public static void printSetMenu(String msg, Set<?> set)
	{
		System.out.println(msg);
		int i = 0;
		for (Object element : set)
		{
			System.out.println(i+"-> "+element);
			i++;
		}
	}
	
	/**
	 * Retorna uma data no formato dd-MM-yyyy
	 * @param date a formatar
	 * @return String
	 */
	public static String returnDate(GregorianCalendar date)
	{
		Date aux = new Date(date.getTimeInMillis());
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String formattedDate = formatter.format(aux);		
		return formattedDate;
	}
	
	/**
	 * Imprime um vector simples de inteiros
	 * @param msg para imprimir antes do array
	 * @param array a imprimir
	 */
	public static void printArrayInt(String msg, int [] array)
	{
		if(array.length == 0)
		{
			System.out.println("Sem dados para Imprimir!!!");
			return;
		}
		System.out.println("\n" + msg);
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
		System.out.println();
	}	
	
	/**
	 * Imprime um vector simples de doubles
	 * @param msg para imprimir antes do array
	 * @param array a imprimir
	 */
	public static void printArrayDouble(String msg, double [] array)
	{
		if(array.length == 0)
		{
			System.out.println("Sem dados para Imprimir!!!");
			return;
		}
		System.out.println("\n" + msg);
		for (int i = 0; i < array.length; i++)
			System.out.println(array[i]);
		System.out.println();
	}	

}
