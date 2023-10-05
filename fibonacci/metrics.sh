#!/bin/bash

eseguiFibonacci() {
  local NUM="$1"
  local TARGET="$2"
  local OUTPUT_FILE="$3"
  echo "Execution using $TARGET"

  if [ ! -e "$OUTPUT_FILE" ]; then
    echo "Esecuzione,Tempo (millisecondi)" > "$OUTPUT_FILE"
  fi

  for ((i = 1; i <= NUM; i++)); do
    echo "Esecuzione $i di $NUM"

    tempo_iniziale=$(date +%s%3N)
    ${TARGET} -n 900 -l
    tempo_finale=$(date +%s%3N)

    tempo_di_esecuzione=$(echo "$tempo_finale - $tempo_iniziale" | bc)

    dati_da_scrivere="$i,$tempo_di_esecuzione"
    echo "$dati_da_scrivere" >> "$OUTPUT_FILE"
  done
}

NUM_EXEC="$1"
if [ -z "$NUM_EXEC" ]; then
  echo "Specify num execution"
  exit 1
fi

shift 1
CHOICE="$1"
if [ -z "$CHOICE" ]; then 
	echo "Specify choice"
	exit 1
fi

if [ "$CHOICE" = "J" ]; then
  eseguiFibonacci "$NUM_EXEC" "java -jar ./target/fibonacci-1.0-jar-with-dependencies.jar" "tempi_fibonacci_java.csv"
elif [ "$CHOICE" = "N" ]; then
  eseguiFibonacci "$NUM_EXEC" "./target/fibonacci" "tempi_fibonacci_native.csv"
else
  echo "Input non valido. Devi inserire 'J' o 'N'."
fi
