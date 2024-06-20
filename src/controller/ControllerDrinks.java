package controller;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
public class ControllerDrinks {
    private String os(){return System.getProperty("os.name");}
    private boolean verificarSO(){return os().contains("Linux");}
    public void gravarArq(){
        String caminho;
        if(verificarSO()){
            caminho = "\\tmp\\marg.json";
        }else{
            caminho = "C:\\TEMP\\marg.json";
        }
        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //Lendo a resposta
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            //Salvando a resposta
            FileWriter fileWriter = new FileWriter(caminho);
            fileWriter.write(response.toString());
            fileWriter.close();

            System.err.println("Dados salvos em:"+caminho);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void exibirInstr() throws IOException {
        String caminho;
        if (verificarSO()) {
            caminho = "\\tmp\\marg.json";
        } else {
            caminho = "C:\\TEMP\\marg.json";
        }
        BufferedReader br = new BufferedReader(new FileReader(caminho));
        String linha = br.readLine();
        String[] partes = linha.split(",");
        int x = 0;
        do{
            if(partes[x].contains("\"strDrink\"")){
                String[] pedaco = partes[x].split(":");
                System.out.println("Nome do Drink:"+pedaco[1]);
            }
            if(partes[x].contains("\"strInstructionsIT\"")) {
                String[] pedaco = partes[x].split(":");
                System.out.println("Instruções:" + pedaco[1]);
            }
            x++;
        }while (x< partes.length);


    }
}