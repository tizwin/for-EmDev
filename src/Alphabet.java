import java.io.*;
import java.io.IOException;

public class Alphabet {

	public void readText() throws IOException {//Считывание данных из файла и подсчет слов

		StringBuffer line = new StringBuffer();
		String line1;
		BufferedReader reader = null;
		int count = 0;

		try { //Считываем весь файл и переводим в строку
			reader = new BufferedReader(new FileReader("C:\\Users\\glush\\Desktop\\Java\\PROJECT\\IN\\input.txt"));
			String text;
			while ((text = reader.readLine()) != null) {
				if (text.length() != 0) { //Если введено хотя бы одно слово, тогда считаем
					count++;
					for (int i = 0; i < text.length(); i++) {
						if (text.charAt(i) == ' ') {  //Проверяем каждый символ, если пробел, увеличиваем количество слов
							count++;
						}
					}
				}
				line1 = text.replaceAll("\\s+", ""); //удаление из строк пробелов
				line.append(line1); //переносим все строки в одну
			}

			System.out.println("Введено слов: " + count);

		} catch (IOException e) {
			System.out.println(e);
		} finally { //закрыть поток BufferedReader
			try {
				reader.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}

	//Сортировка и подсчет букв, вывод данных в System.out + вывод в файл output.txt в папку OUT
		String str = line.toString();
		char temp;
		char[] simb = str.toCharArray();
		String countStr = "";
		int[] countAr = new int[simb.length];
		int index;


		for (int i = 0; i < simb.length; i++) { //сортируем буквы в алфавитном порядке
			for (int j = 0 ; j < simb.length; j++) {
				if(simb[j] > simb[i]) {
					temp = simb[i];
					simb[i] = simb[j];
					simb[j] = temp;
				}
			}
		}

		for (int i = 0; i < simb.length; i++) { //считаем количество вхождений каждой буквы
			index = countStr.indexOf(simb[i]);
			if (index>=0) {
				countAr[index]++;
			}
			else {
				countStr = countStr + simb[i];
				countAr[countStr.length()-1]++;
			}
		}

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\glush\\Desktop\\Java\\PROJECT\\out\\output.txt"))) {
			writer.write("Введено слов: " + count + "\n");
			for (int i = 0; i < countStr.length(); i++) { //вывод в System.out
				System.out.println(countStr.charAt(i) + " " + countAr[i]);
				writer.write(countStr.charAt(i) + " " + countAr[i] + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws IOException { //запус программы
		Alphabet alph = new Alphabet();
		alph.readText();
	}
}
