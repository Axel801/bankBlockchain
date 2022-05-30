La propuesta es la siguiente:

API Rest para simular un pequeño banco:
 - Registro usuario
 - Creación de cuenta (wallet)
 - Realización de depósito de dinero
 - Visualización de cuenta (wallet) --> Balance y movimientos
 - Transferencia de una cuenta A a una cuenta B

Puntos a destacar:
 - Arquitectura hexagonal y testing (Obligatorio)
- Libertad en el stack usado en la prueba, aunque preferiblemente algún lenguaje de la JVM, Java, Groovy o  haciendo uso de Spring, Micronaut o Quarkus.
- Conceptos DDD
- Base de datos (puedes usar una base de datos simulada en memoria, H2 en memoria, etc)
- Parte Blockchain (Obligatorio)  

[Video demo](https://www.youtube.com/watch?v=vroECrMF62g)

Levantar proyecto Java:

- Servidor SQL (cambiar las properties en el fichero *application.properties*)

- Ganache levantado con el contrato desplegado
- Generar la nueva clase del SmartContract con web3j para que modifique su nueva address y moverlo al paquete `com.example.bank.bank.domain.contracts`

```
web3j generate truffle --truffle-json=.\build\contracts\Bank.json -o ./web3j -p com.example.bank.bank.contracts
```