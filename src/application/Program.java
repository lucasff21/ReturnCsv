package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Dados;

public class Program {
	public static void main(String[] args) {

		String path = "c:\\temp\\input.csv";

		List<Dados> items = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();

			while (line != null) {
				System.out.println(line);
				String[] values = line.split(",");
				line = br.readLine();
				Dados dados = createDados(values);
				items.add(dados);

			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}



		String path2 = "C:\\temp\\out\\post.csv";
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(path2))) {
			for (Dados line : items) {
				bw.write(line.getName() + "," + String.format("%.2f", line.soma()));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Dados createDados(String[] valores) {
		String name = valores[0];
		Double price = Double.parseDouble(valores[1]);
		Integer amount = Integer.parseInt(valores[2]);

		return new Dados(name, price, amount);
	}

}
