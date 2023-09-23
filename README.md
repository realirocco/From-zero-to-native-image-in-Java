# From zero to native image in Java

![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)


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
├── sort (demo Java)
│   ├── pom.xml
│   ├── src
├── spring-demo (demo Java Spring)
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   └── src
└── spring-demo.jmx (configurazione JMeter per test di carico)
```

### Prerequisiti:
- installazione di Docker
- installazione di docker-compose
- installazione Apache JMeter (per eseguire test di carico su web-app)

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

#### Filecount:
Applicazione che dato un '*path*' calcola il numero di file in esso contenuti e la loro grandezza totale. Come per l'applicazione ***fibonacci*** effettua la compilazione ed esegui l'applicazione passando come unico parametro un path di sistema, valido.

#### Sort:
Applicazione che genera un array di ***n*** numeri (parametro spcificabile all'avvio dell'applicazione) completamente disordinato e successivamente ordina gli elementi con l'algoritmo: *bubble-sort*. Come per l'applicazione ***fibonacci*** effettua la compilazione con ```b-native``` e successivamente esegui: ```./target/sort -h``` per consultare la lista dei parametri ammessi nell'esecuzione.

#### spring-demo

Applicazione spring-boot semplicissima con un solo endpoint (`/hello/{name}`).
Ha come sola dipendenza `spring-boot-starter-web:3.1.4`.
Per eseguire il build dell'applicazione sono disponibili `native-maven-plugin` o `spring-boot-maven-plugin` a seconda che si voglia un'app nativa o un jar eseguibile.

Per generare l'app nativa lanciare il seguente comando

```
mvn -Pnative native:compile [-DbuildArgs="options"]
```
`-DbuildArgs` è un parametro opzionale utile per passare parametri al tool `native-image` nel caso si voglia scegliere un garbage collector diverso da quello di default o si voglia definire la dimenzione massima dell'heap (per maggiori informazioni rimadiamo alla [documentazione ufficiale di graalvm](https://www.graalvm.org/22.0/reference-manual/native-image/Options/)


Eseguendo il comando sopra verranno generati sia il jar eseguibile che l'applicazione nativa.

#### spring-demo.jmx

In questo repo è disponibile un plan jmeter per eseguire un test di carico sulla web-app.
Per eseguire il test puoi usare questo comando
```
jmeter -n -t spring-demo.jmx -JTHREADS=10 -JLIMIT=2000 -l report.csv
```
il comando sopra avvierà un test di carico usando 10 threads, ognuno dei quali eseguira 2000 chiamate al servizio.
Le chiamate e il rispettivo esito saranno registrate su un file `.csv` che si chiama `report.csv`

### 👤 Autori:

| Nome  | Linkedin  |
|:----------|:----------|
| Carmine Paternoster | [![](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/carmine-paternoster-b5417794) |
| Rocco Reali | [![](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/realirocco) |


