truffle test --network development --show-events

--compile-none => No compila el SC de nuevo.


Deploy in network
truffle migrate --network development --reset


Generar web3j clase 

web3j generate truffle --truffle-json=.\build\contracts\Bank.json -o ./web3j -p com.example.bank.bank.contracts


let accounts = await web3.eth.getAccounts()

#### Truffle console

let bank = await Bank.deployed()
await bank.addFunds({value:1000,from:accounts[1]})
await bank.getBalanceOf(accounts[1]).then(r=>r.toNumber())