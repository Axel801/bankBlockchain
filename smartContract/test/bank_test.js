const BankTest = artifacts.require("Bank");

/*
 * uncomment accounts to access the test accounts made available by the
 * Ethereum client
 * See docs: https://www.trufflesuite.com/docs/truffle/testing/writing-tests-in-javascript
 */

const getReason = (error) => {
  return error.data[Object.keys(error.data)[0]].reason;
}

let app;
contract("BankTest", function (accounts) {
  before(async () => {
    await BankTest.deployed().then(inst => { app = inst });
    return assert.ok(app.address);
  });

  it("Add funds to SC", async () => {
    let funds = 1000;
    await app.addFunds({ from: accounts[0], value: funds });

    let balanceSC = await app.getTotalBalances().then(r => r.toNumber());
    assert(balanceSC == funds);

  });

  it("Add funds to SC with 0 value", async () => {
    let reason = "";
    try {
      await app.addFunds({ from: accounts[0], value: 0 });
    } catch (error) {
      reason = getReason(error);
    }

    assert(reason == "Error: No se ha establecido la cantidad de fondos");
  });

  it("Get balance of account", async () => {
    let funds = 1000;
    let balanceAccount = await app.getBalanceOf(accounts[0]).then(r => r.toNumber());

    let balanceSC = await app.getTotalBalances().then(r => r.toNumber());
    assert(balanceSC == balanceAccount);

  });

  it("Withdraw balance", async () => {
    let withdraw = 500;
    let balanceAccount = await app.getBalanceOf(accounts[0]).then(r => r.toNumber());
    await app.whitdrawFunds(withdraw, { from: accounts[0] });


    let balanceAccountAfterWithdraw = await app.getBalanceOf(accounts[0]).then(r => r.toNumber());
    assert(balanceAccountAfterWithdraw == (balanceAccount - withdraw));

  });

  it("Withdraw balance without founds", async () => {
    let reason = "";
    try {
      let withdraw = 1000;
      await app.whitdrawFunds(withdraw, { from: accounts[1] });
    } catch (error) {
      reason = getReason(error);
    }
    assert(reason == "No tienes fondos");
  });

  it("Withdraw balance with 0 value", async () => {
    let reason = "";
    try {
      let withdraw = 0;
      await app.whitdrawFunds(withdraw, { from: accounts[0] });
    } catch (error) {
      reason = getReason(error);
    }
    assert(reason == "Error: No se ha establecido la cantidad de fondos");
  });

  it("Withdraw balance with 0 value", async () => {
    let reason = "";
    try {
      let withdraw = 10000;
      await app.whitdrawFunds(withdraw, { from: accounts[0] });
    } catch (error) {
      reason = getReason(error);
    }
    assert(reason == "No tienes fondos suficientes");
  });


  it("TransferTo ExternalAddress", async () => {
    let withdraw = 500;
    let actualBalance = await web3.eth.getBalance(accounts[1]).then(r => parseInt(r));

    await app.transferTo(withdraw, accounts[1], { from: accounts[0] });

    let balanceAccountAfterWithdraw = await web3.eth.getBalance(accounts[1]).then(r => parseInt(r));

    assert(balanceAccountAfterWithdraw == (actualBalance + withdraw));
  });

  it("Withdraw all funds", async () => {
    await app.addFunds({ from: accounts[0], value: 100000 });


    let balanceAccountBefore = await web3.eth.getBalance(accounts[0]).then(r => Number(r));
    let balanceAccountSC = await app.getBalanceOf(accounts[0]).then(r => r.toNumber());


    let receipt = await app.withdrawAllFunds({ from: accounts[0] });
    let gasUsed = receipt.receipt.gasUsed;
    let gasPrice = await web3.eth.getTransaction(receipt.tx).then(tx => Number(tx.gasPrice));


    let balanceAccountAfter = await web3.eth.getBalance(accounts[0]).then(r => Number(r));

    assert.equal(balanceAccountAfter + (gasPrice * gasUsed), balanceAccountBefore + balanceAccountSC);

  });





});
