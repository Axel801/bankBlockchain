// SPDX-License-Identifier: MIT
pragma solidity ^0.8.9;
import "@openzeppelin/contracts/utils/math/SafeMath.sol";

contract Bank {
    using SafeMath for uint256;
    mapping(address => uint256) public balances;
    uint256 public totalBalance;

    event eventTransferFunds(uint256 _amount, address _to);

    event eventAddFunds(uint256 _amount, address _sender);

    modifier haveFunds() {
        require(balances[msg.sender] > 0, "No tienes fondos");
        _;
    }

    modifier haveFundsAmount(uint256 _amount) {
        require(
            balances[msg.sender] >= _amount,
            "No tienes fondos suficientes"
        );
        _;
    }

    modifier amountNotZero(uint256 _amount) {
        require(
            _amount > 0,
            "Error: No se ha establecido la cantidad de fondos"
        );
        _;
    }

    function getTotalBalances() public view returns (uint256) {
        return totalBalance;
    }

    function getBalanceOf(address _address) public view returns (uint256) {
        return balances[_address];
    }

    function addFunds() public payable amountNotZero(msg.value) {
        balances[msg.sender] = balances[msg.sender].add(msg.value);
        totalBalance = totalBalance.add(msg.value);
        emit eventAddFunds(msg.value, msg.sender);
    }

    function withdrawFunds(uint256 _amount)
        public
        payable
        amountNotZero(_amount)
        haveFunds
        haveFundsAmount(_amount)
    {
        transferTo(_amount, msg.sender);
    }

    function withdrawAllFunds() public haveFunds {
        uint256 balanceUser = getBalanceOf(address(msg.sender));
        transferTo(balanceUser, address(msg.sender));
    }

    function transferTo(uint256 _amount, address _to)
        public
        payable
        amountNotZero(_amount)
        haveFunds
        haveFundsAmount(_amount)
    {
        balances[msg.sender] = balances[msg.sender].sub(_amount);

        totalBalance = totalBalance.sub(_amount);
        payable(_to).transfer(_amount);
        emit eventTransferFunds(_amount, _to);
    }

    fallback() external payable {
        addFunds();
    }

    receive() external payable {
        addFunds();
    }
}
