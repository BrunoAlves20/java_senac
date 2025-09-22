***

# Bot de Notícias Google News

Este projeto é um bot Java simples que busca notícias no Google News pelo termo desejado, extrai e salva os links das notícias em um arquivo de texto exclusivo para cada termo pesquisado.

## Funcionalidades

- Recebe um termo de busca pelo terminal.
- Faz uma consulta ao Google News.
- Extrai todos os links das notícias relacionadas ao termo buscado.
- Salva todos os links em um arquivo chamado `links_noticias_TERMO.txt`, onde `TERMO` será o termo pesquisado, facilitando a organização dos resultados sem sobrescrever arquivos anteriores.


## Pré-Requisitos

- Java 8 ou superior instalado.
- VS Code instalado.
- jsoup-1.17.2.jar (baixado em `lib/`).


## Instalação do Jsoup

Baixe o arquivo jsoup-1.17.2.jar usando o PowerShell, já dentro da pasta `bot-noticias`:

```powershell
Invoke-WebRequest -Uri "https://repo1.maven.org/maven2/org/jsoup/jsoup/1.17.2/jsoup-1.17.2.jar" -OutFile "lib/jsoup-1.17.2.jar"
```


## Como Rodar pelo VS Code

1. Abra o VS Code na pasta `bot-noticias`.
2. Certifique-se de que a estrutura de pastas está igual à mostrada acima.
3. Compile o código (estando na pasta raiz do projeto):
   - No Windows:
```powershell
javac -cp "lib/jsoup-1.17.2.jar" src/BotNoticias.java
```

4. Execute o programa:
   - No Windows:
```powershell
java -cp "src;lib/jsoup-1.17.2.jar" BotNoticias
```

5. Digite o termo da busca quando solicitado, aguarde e verifique o arquivo gerado, por exemplo `links_noticias_tecnologia.txt`, na raiz da pasta do projeto.

## Explicação do Código

- O código pede um termo de busca ao usuário.
- Monta a URL do Google News usando esse termo.
- Usa a biblioteca Jsoup para baixar e parsear a página HTML.
- Filtra os links de notícias usando o seletor adequado.
- Salva apenas os links encontrados em um arquivo de texto, um por linha, nomeado conforme o termo pesquisado para que cada busca tenha seu próprio arquivo.


## Observações

- O Google pode exigir captcha para muitas requisições automáticas — em caso de bloqueio, tente rodar o programa novamente após alguns minutos.
- Este projeto é apenas para fins educacionais e demonstrações.

***
