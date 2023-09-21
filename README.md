# From zero to native image in Java


### Contenuto del repo:
```
.
├── Dockerfile (Ubuntu + GraalVM + Maven)
├── docker-compose.yml (build and run Dockerfile + mount demo app)
├── fibonacci (demo Java)
│   ├── pom.xml
│   ├── src
├── filecount (demo Java)
│   ├── pom.xml
│   ├── src
└── sort (demo Java)
    ├── pom.xml
    ├── src
```

### Prerequisiti:
- installazione di Docker
- installazione di docker-compose

### Test and benchmark:
1. Clona il repository
1. Crea la cartella maven
1. Esegui: ```docker-compose up -d``` (effettua la build e la run del Dockerfile e monta all'interno del container le cartelle delle applicazioni Java con cui effettuare i benchmark)
1. Esegui: ```docker-compose exec graalvm bash``` (ti collegherai alla console del container)

### Le applicazioni:
Le applicazioni demo sono applicazioni Java + Maven. Per compilarle basta utilizzare: ```mvn clean package``` oppure ```mvn clean package -Pnative``` per creare la corrispondente applicazione nativa. (Per brevità sono stati previsti degli *alias* nel container docker: ***b-java*** per la build in Java e ***b-native*** per la build in applicazione nativa)

#### Fibonacci:
Applicazione che dato un numero, calcola la sequenza di Fibonacci.
Entra nella cartella ```fibonacci/``` ed esegui: ```b-native``` (produrrà sia il file *jar* sia l'applicazione nativa). Quindi esegui: ```./target/fibonacci -h``` per poter consultare la lista dei parametri accettati dall'applicazione. 

Per eseguire un primo benchmark:

- ```./target/fibonacci -n 10 -a```
- ```java -jar ./target/fibonacci-1.0-jar-with-dependencies.jar -n 10 -a```

calcola la sequenza di Fibonacci con il numero 10 e ci mostra il tempo di esecuzione dell'applicazione java e della corrispondente applicazione nativa. Prosegui poi con:

- ```./target/fibonacci -n 100 -l```
- ```java -jar ./target/fibonacci-1.0-jar-with-dependencies.jar -n 100 -l```

per valutare e confrontare i tempi di esecuzione. Prosegui in autonomia con altri numeri.