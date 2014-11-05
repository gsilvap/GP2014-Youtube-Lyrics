package readAndValidateData;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class Utilities - Funcoes uteis
 * @author Goncalo Silva Pereira 2009111643
 */
public class Utilities
{
	public static final int UNIT = 1;
	
	
	/**
	 * Funcao para comparar e devolver a diferenca em dias entre duas datas
	 * @param inputA Data inicial
	 * @param inputB Data final
	 * @return int difference
	 */
	public static int compareDate(GregorianCalendar inputA, GregorianCalendar inputB)
	{
		int difference = 0;
		GregorianCalendar aux = (GregorianCalendar)inputA.clone();
		
//		diferen�as de anos
		while((inputB.get(Calendar.YEAR) - aux.get(Calendar.YEAR))*12 + (inputB.get(Calendar.MONTH) - aux.get(Calendar.MONTH)) > 12)
		{
			if (aux.get(Calendar.MONTH) == Calendar.JANUARY && aux.get(Calendar.DAY_OF_MONTH) == aux.getActualMinimum(Calendar.DAY_OF_MONTH))
			{
				difference += aux.getActualMaximum(Calendar.DAY_OF_YEAR);
				aux.add(Calendar.YEAR, UNIT);
			}
			else
			{
				int diff = aux.getActualMaximum(Calendar.DAY_OF_YEAR) - aux.get(Calendar.DAY_OF_YEAR);
				aux.add(Calendar.DAY_OF_YEAR, diff);
				difference += diff;
			}
		}
		
//		diferen�as de meses
		while ((inputB.get(Calendar.MONTH) - aux.get(Calendar.MONTH) % 12) > UNIT) {
			difference += aux.getActualMaximum(Calendar.DAY_OF_MONTH);
			aux.add(Calendar.MONTH, UNIT);
		}
		
//		diferen�as de dias
		while(aux.before(inputB))
		{
			aux.add(Calendar.DAY_OF_MONTH, UNIT);
			difference++;
		}
		return difference;
	}
	
	/**
	 * Funcao para verificar se uma data e depois da outra
	 * @param inputA Data inicial
	 * @param inputB Data final
	 * @return boolean
	 */
	public static boolean beforeDate(GregorianCalendar inputA, GregorianCalendar inputB)
	{
		if (inputA.getTimeInMillis() <= inputB.getTimeInMillis())
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Funcao para verificar se dois intervalos de dias tem algum dia coincidente
	 * @param inputA Data inicial
	 * @param inputB Data final
	 * @param dateOfEntry Data de entrada do internamento
	 * @param dateOfDeparture Data de saida do internamento
	 * @return boolean
	 */
	public static boolean withCoincidentDay(GregorianCalendar inputA, GregorianCalendar inputB, GregorianCalendar dateOfEntry, GregorianCalendar dateOfDeparture)
	{
		if (inputA.getTimeInMillis() == dateOfEntry.getTimeInMillis() || inputA.getTimeInMillis() == dateOfDeparture.getTimeInMillis()) {
			return true;
		}
		else if (inputB.getTimeInMillis() == dateOfEntry.getTimeInMillis() || inputB.getTimeInMillis() == dateOfDeparture.getTimeInMillis()) {
			return true;
		}
		else if (inputA.getTimeInMillis() > dateOfEntry.getTimeInMillis() && inputB.getTimeInMillis() < dateOfDeparture.getTimeInMillis()) {
			return true;
		}
		else if (inputA.getTimeInMillis() < dateOfEntry.getTimeInMillis() && inputB.getTimeInMillis() > dateOfEntry.getTimeInMillis()) {
			return true;
		}
		else if (inputA.getTimeInMillis() < dateOfDeparture.getTimeInMillis() && inputB.getTimeInMillis() > dateOfDeparture.getTimeInMillis()) {
			return true;
		}
		return false;
	}
	
	/**
	* Funcao para comparar e devolver a diferenca em meses entre duas datas
	* @param inputA Data inicial
	* @param inputB Data final
	* @return int difference
	*/
	public static int compareMonth(GregorianCalendar inputA, GregorianCalendar inputB)
	{
		int difference = 0;
		GregorianCalendar aux = (GregorianCalendar)inputA.clone();
		
//		diferen�as de anos
		while((inputB.get(Calendar.YEAR) - aux.get(Calendar.YEAR))*12 + (inputB.get(Calendar.MONTH) - aux.get(Calendar.MONTH)) > 12)
		{
			if (aux.get(Calendar.MONTH) == Calendar.JANUARY && aux.get(Calendar.DAY_OF_MONTH) == aux.getActualMinimum(Calendar.DAY_OF_MONTH))
			{
				difference += aux.getActualMaximum(Calendar.MONTH);
				aux.add(Calendar.YEAR, UNIT);
			}
			else
			{
				int diff = aux.getActualMaximum(Calendar.MONTH) - aux.get(Calendar.MONTH);
				aux.add(Calendar.MONTH, diff + UNIT);
				difference += diff;
			}
		}
		
//		diferen�as de meses
		while ((inputB.get(Calendar.MONTH) - aux.get(Calendar.MONTH) % 12) != 0) {
			difference++;
			aux.add(Calendar.MONTH, UNIT);
		}
		difference++;
		return difference;
	}
	
	/**
	 * Funcao para verificar se duas datas pertencem ao mesmo mes
	 * @param inputA Data
	 * @param inputB Data
	 * @return boolean
	 */
	public static boolean equalMonth(GregorianCalendar inputA, GregorianCalendar inputB)
	{
		if (inputA.get(Calendar.YEAR) == inputB.get(Calendar.YEAR) && inputA.get(Calendar.MONTH) == inputB.get(Calendar.MONTH))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Funcao para encontrar o mair numero inteiro
	 * @param vect Vector de pesquisa
	 * @return int max
	 */
	public static int maxInt(int [] vect)
	{
		int max = vect[0];
		for (int i = UNIT; i < vect.length; i++)
		{
			if (vect[i] > max) max = vect[i];
		}
		return max;
	}
	
	/**
	 * Funcao para verificar se uma determinada data esta entre outras duas
	 * @param inputA Data inicial
	 * @param inputB Data final
	 * @param inBetween Data interm�dia para validar
	 * @return boolean
	 */
	public static boolean betweenDate(GregorianCalendar inputA, GregorianCalendar inputB, GregorianCalendar inBetween)
	{
		if (inputA.getTimeInMillis() <= inBetween.getTimeInMillis() && inBetween.getTimeInMillis() <= inputB.getTimeInMillis())
		{
			return true;
		}
		return false;
	}
}
