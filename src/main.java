import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
		try {
			linha = br.readLine();
			while(linha!=null){
				
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
	System.out.println(comentarios);
	}
}
