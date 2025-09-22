import java.io.*;                   // Importa classes para manipulação de arquivos
import java.util.Scanner;           // Importa a classe Scanner para leitura de entrada do usuário
import org.jsoup.Jsoup;             // Importa a biblioteca Jsoup para conexão HTTP e parse do HTML
import org.jsoup.nodes.Document;    // Representa o HTML completo da página obtida
import org.jsoup.nodes.Element;     // Representa um elemento HTML individual
import org.jsoup.select.Elements;   // Representa grupo de elementos HTML selecionados

public class BotNoticias {
    public static void main(String[] args) throws Exception {
        // Cria um Scanner para capturar a entrada do usuário pelo teclado
        Scanner scanner = new Scanner(System.in);

        // Exibe uma mensagem pedindo que o usuário digite o termo de busca
        System.out.print("Digite o termo para buscar notícias: ");

        // Lê o termo digitado pelo usuário
        String termo = scanner.nextLine();

        // Monta a URL para buscar notícias no Google, trocando espaços por '+'
        String url = "https://www.google.com/search?q=" + termo.replace(" ", "+") + "&tbm=nws";

        // Realiza a obtenção do HTML da página, simulando um navegador (userAgent)
        Document doc = Jsoup.connect(url)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)") // User-agent para evitar bloqueio pelo Google
                            .get();

        // Seleciona todos os elementos <a> com atributo href (links de notícia)
        Elements links = doc.select("a[href]");

        // Gera o nome do arquivo de saída com base no termo pesquisado
        // 1. Substitui espaços por "_" (underscore)
        // 2. Remove caracteres especiais para evitar problemas no sistema de arquivos
        String nomeArquivo = "links_noticias_" + termo.replace(" ", "_").replaceAll("[^a-zA-Z0-9_]", "") + ".txt";

        // Abre o arquivo para gravação dos links (cada termo tem seu próprio arquivo)
        FileWriter fw = new FileWriter(nomeArquivo);

        // Percorre todos os links encontrados na página
        for (Element link : links) {
            // Obtém o valor do href (endereço do link)
            String href = link.attr("href");

            // Verifica se é um resultado de notícia (links do Google começam com '/url?q=')
            if (href.startsWith("/url?q=")) {
                // Divide para remover parâmetros extras do Google usando o caractere '&'
                String[] partes = href.split("&");

                // Remove o prefixo '/url?q=' para pegar somente o link original da notícia
                String linkNoticia = partes[0].replace("/url?q=", "");

                // Escreve o link da notícia no arquivo (um por linha)
                fw.write(linkNoticia + "\n");
            }
        }

        // Fecha o arquivo após gravar todos os links
        fw.close();

        // Mostra mensagem informando o nome do arquivo salvo
        System.out.println("Links salvos em " + nomeArquivo);
    }
}
