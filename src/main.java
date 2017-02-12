import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class main {
	public static void main(String[] args) throws FileNotFoundException{
		FileInputStream stream = new FileInputStream("main.py");
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader br = new BufferedReader(reader);
		String linha;
		ArrayList comentarios = new ArrayList();
		String[] variaveis;
		try {
			linha = br.readLine();
			while(linha!=null){
				if(linha.contains("def")){
					String aux = linha.substring(linha.indexOf("f")+1, linha.indexOf("(")).replace(" ", "");
					String instancias = linha.substring(linha.indexOf("(")+1, linha.indexOf(")"));
					variaveis = instancias.split(",");
					String[][] variaveisss = new String[variaveis.length][2];
					for(int i = 0; i<variaveis.length; i++){
						variaveis[i] = variaveis[i].replaceAll("\\s+", "-");
						if(variaveis[i].charAt(0) == '-' && variaveis[i].charAt(variaveis[i].length()-1) == '-'){
							variaveis[i] = variaveis[i].substring(1, variaveis[i].length()-1);
						}else if(variaveis[i].charAt(0) == '-' && variaveis[i].charAt(variaveis[i].length()-1) != '-'){
							variaveis[i] = variaveis[i].substring(1, variaveis[i].length());
						}else if(variaveis[i].charAt(0) != '-' && variaveis[i].charAt(variaveis[i].length()-1) == '-'){
							variaveis[i] = variaveis[i].substring(0, variaveis[i].length()-1);
						}else{
							variaveis[i] = variaveis[i].substring(0, variaveis[i].length());
						}
						String[] aux2 = variaveis[i].split("-");
						variaveisss[i][0] = aux2[0];
						variaveisss[i][1] = aux2[1];
					}
						System.out.println(aux);
					for(int i = 0; i<variaveis.length; i++){
						System.out.println(variaveisss[i][0]);
					}
					for(int i = 0; i<variaveis.length; i++){
						System.out.println(variaveisss[i][1]);
					}
				}

				// comentarios
				if(linha.contains("#")){
					String coments = linha;
					StringBuilder s = new StringBuilder(coments);
					int i = 0;
					while(true){
						if(s.charAt(i) == '"'){
							int x = i+1;
							while(s.charAt(x) != '"'){
								x++;
							}
							s.delete(0, i);
							x = x - i;
							i = 0;
							s.delete(i, x);
						}	
						if(s.charAt(i) == '#'){
							break;
						}
						i++;
					}
					String coisa = s.substring(s.indexOf("#"));
					comentarios.add(coisa);
				}
				linha = br.readLine();
			}
		} catch (IOException e1) {
			System.out.println("Erro");
		}
		
		try{
			  FileWriter fstream = new FileWriter("out2.xml");
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write((String) comentarios.get(0));
			  //Close the output stream
			  out.close();
		}catch(Exception e){
			
		}
	System.out.println(comentarios);
	}
}
