package readAndValidateData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.GregorianCalendar;

/**
 * Class ObjectFile - Gest�o de ficheiros de objectos
 * @author Goncalo Silva Pereira 2009111643
 * @author Paulo Miguel Guimaraes da Silva 2009116753
 */
public class ObjectFile {
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	
	/**
	 * Abre o fluxo de entrada de dados do ficheiro
	 * @param fileName Nome do ficheiro
	 * @throws IOException
	 */
	public void openRead(String fileName) throws IOException
	{
		inputStream = new ObjectInputStream(new FileInputStream(fileName));
	}
	
	/**
	 * Abre o fluxo de sa�da de dados do ficheiro
	 * @param fileName Nome do ficheiro
	 * @throws IOException
	 */
	public void openWrite(String fileName) throws IOException
	{
		outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
	}
	
	/**
	 * L� um objecto do fluxo de entrada
	 * @return Objecto Lido do fluxo
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object readObject() throws IOException, ClassNotFoundException
	{
		return inputStream.readObject();
	}
	
	/**
	 * Escreve um objecto no fluxo de sa�da
	 * @param o Objecto para escrever no fluxo
	 * @throws IOException
	 */
	public void writeObject(Object o) throws IOException
	{
		outputStream.writeObject(o);
	}
	
	/**
	 * L� um inteiro do fluxo de entrada
	 * @return int numero inteiro
	 * @throws IOException
	 */
	public int readInt() throws IOException
	{
		return inputStream.readInt();
	}
	
	/**
	 * Escreve um inteiro no fluxo de sa�da 
	 * @param number numero para escrever no fluxo
	 * @throws IOException
	 */
	public void writeInt(int number) throws IOException
	{
		outputStream.writeInt(number);
	}
	
	/**
	 * L� uma String do fluxo de entrada
	 * @return String string lida do fluxo
	 * @throws IOException
	 */
	public String readString() throws IOException
	{
		int size = readInt();
		String aux = new String();
		for (int i = 0; i < size; i++)
			aux += inputStream.readChar();
		return aux;
	}
	
	/**
	 * Escreve uma String no fluxo de sa�da 
	 * @param string para escrever no fluxo
	 * @throws IOException
	 */
	public void writeString(String string) throws IOException
	{
		outputStream.writeInt(string.length());
		outputStream.writeChars(string);
	}
	
	/**
	 * L� uma Data do fluxo de entrada
	 * @return GregorianCalendar date lida do fluxo
	 * @throws IOException
	 */
	public GregorianCalendar readDate() throws IOException
	{
		String date = readString();
		return new GregorianCalendar(Integer.parseInt(date.substring(6)), Integer.parseInt(date.substring(3, 5))-1, Integer.parseInt(date.substring(0,2)));
	}
	
	/**
	 * Escreve uma Data no fluxo de sa�da
	 * @param date para escrever no fluxo
	 * @throws IOException
	 */
	public void writeDate(GregorianCalendar date) throws IOException
	{
		writeString(Print.returnDate(date));
	}
	
	/**
	 * Fecha  o fluxo de entrada
	 * @throws IOException
	 */
	public void closeRead() throws IOException
	{
		inputStream.close();
	}
	
	/**
	 * Fecha o fluxo de sa�da
	 * @throws IOException
	 */
	public void closeWrite() throws IOException
	{
		outputStream.close();
	}
}
