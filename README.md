# Quiz de Matemática em Java

Este é um simples jogo de quiz de matemática desenvolvido em Java com uma interface gráfica feita em Swing. O objetivo do jogador é responder ao maior número de questões matemáticas possível dentro de um tempo limite. As pontuações são salvas localmente em um arquivo para criar um ranking persistente.

## ✨ Funcionalidades

- **Quiz com Tempo Limite:** Um timer regressivo de 60 segundos (customizável) para cada rodada.
- **Geração de Problemas:** Criação de problemas matemáticos aleatórios envolvendo soma, subtração, multiplicação e divisão.
- **Interface Gráfica:** UI simples e intuitiva construída com a biblioteca nativa Java Swing.
- **Sistema de Pontuação:** A pontuação é incrementada a cada resposta correta.
- **Ranking Local:** As melhores pontuações são salvas em um arquivo local (`ranking.dat`), eliminando a necessidade de um banco de dados.
- **Persistência de Dados:** O ranking é carregado a cada início e exibido no final de cada partida.

## 🛠️ Tecnologias Utilizadas

- **Java:** Linguagem de programação principal.
- **Java Swing:** Biblioteca para a criação da interface gráfica do usuário (GUI).
- **Serialização de Objetos Java:** Para salvar e carregar os objetos de pontuação em um arquivo.

## 📋 Pré-requisitos

Para compilar e executar este projeto, você precisará ter o **JDK (Java Development Kit)** instalado em sua máquina. É recomendada a versão 8 ou superior.

## 🚀 Como Rodar o Projeto

Você pode rodar o projeto diretamente através de um terminal/prompt de comando.

1.  **Clone ou baixe** todos os arquivos `.java` para uma mesma pasta em seu computador.

2.  **Abra um terminal** (Prompt de Comando, PowerShell, ou Terminal do Linux/macOS).

3.  **Navegue até a pasta** onde você salvou os arquivos.
    ```bash
    cd caminho/para/a/pasta/do/projeto
    ```

4.  **Compile os arquivos `.java`** usando o compilador do Java. Este comando irá compilar todos os arquivos Java na pasta atual.
    ```bash
    javac *.java
    ```

5.  **Execute a classe principal** para iniciar o jogo.
    ```bash
    java Main
    ```

Após executar o último comando, a janela do quiz de matemática aparecerá e você poderá começar a jogar!

## 📂 Estrutura do Projeto

O código está organizado nas seguintes classes:

-   `Main.java`: Classe principal que inicia a aplicação e a interface gráfica.
-   `QuizScreen.java`: O coração do projeto. Responsável por toda a interface gráfica (GUI) e pela lógica principal do jogo (timer, pontuação, etc.).
-   `ProblemGenerator.java`: Funciona como uma "API" local para gerar as questões matemáticas de forma aleatória.
-   `ScoreManager.java`: Gerencia o salvamento e o carregamento das pontuações no arquivo local `ranking.dat`.
-   `PlayerScore.java`: Classe modelo para armazenar os dados de um jogador (nome e pontuação). É serializável para poder ser salva em arquivo.
-   `MathProblem.java`: Classe modelo simples para representar um problema matemático, contendo a pergunta e a resposta correta.