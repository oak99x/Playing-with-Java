import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Renum {
	public List<String[]> lines;
	private static final int DIFERENCA_LINHAS = 10;

	public int indiceReferencia(int indexRef) {
		int indice = 1;
		for (String[] line : lines) {
			if (Integer.parseInt(line[0]) == indexRef) {
				return indice * 10;
			}
			indice++;
		}
		return 0;
	}

	public boolean renumberFile(String narq) {
		String fileName = narq;
		String diretorio = Paths.get("").toAbsolutePath().toString();
		String diretorioCompleto = diretorio + "\\" + fileName;
		Path path = Paths.get(diretorioCompleto);

		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path, StandardCharsets.UTF_8))) {

			int numeradorLinha = DIFERENCA_LINHAS;

			for (String[] linha : lines) {

				writer.print(numeradorLinha + " ");
				for (int i = 1; i < linha.length; i++) {
					if (!(linha[i - 1].equalsIgnoreCase("goto") || linha[i - 1].equalsIgnoreCase("gosub"))) {
						writer.print(linha[i] + " ");
					} else {
						writer.print(indiceReferencia(Integer.parseInt(linha[i])) + " ");
					}
				}
				numeradorLinha += DIFERENCA_LINHAS;
				writer.println();
			}
		} catch (IOException ex) {
			System.out.println("Erro de E/S");
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public void loadProgram(String narq) {
		String currDir = Paths.get("").toAbsolutePath().toString();
		String nameComplete = currDir + "\\" + narq;
		Path path2 = Paths.get(nameComplete);
		this.lines = new ArrayList<>();
		try (Scanner sc = new Scanner(Files.newBufferedReader(path2, Charset.defaultCharset()))) {

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] tokens = line.split(" ");
				lines.add(tokens);
			}

		} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
		}

	}

}
