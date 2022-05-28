package com.example.bank.bank.contracts;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Bank extends Contract {
    public static final String BINARY = "0x608060405234801561001057600080fd5b50610bf6806100206000396000f3fe60806040526004361061007f5760003560e01c80639b96eece1161004e5780639b96eece14610133578063a26759cb14610170578063ad7a672f1461017a578063d4822fbf146101a55761008e565b806327e235e3146100985780632baf57d3146100d557806349649fbf1461010057806369b9ee7f146101175761008e565b3661008e5761008c6101c1565b005b6100966101c1565b005b3480156100a457600080fd5b506100bf60048036038101906100ba919061085a565b6102ef565b6040516100cc91906108a0565b60405180910390f35b3480156100e157600080fd5b506100ea610307565b6040516100f791906108a0565b60405180910390f35b34801561010c57600080fd5b50610115610311565b005b610131600480360381019061012c91906108e7565b6103ac565b005b34801561013f57600080fd5b5061015a6004803603810190610155919061085a565b610502565b60405161016791906108a0565b60405180910390f35b6101786101c1565b005b34801561018657600080fd5b5061018f61054a565b60405161019c91906108a0565b60405180910390f35b6101bf60048036038101906101ba9190610914565b610550565b005b3460008111610205576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101fc906109d7565b60405180910390fd5b610256346000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546107cb90919063ffffffff16565b6000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506102ad346001546107cb90919063ffffffff16565b6001819055507f32193ef63276f35fa6fe0187906ebb1cc51dbd8837a8c2428e2a084ff742ef9c34336040516102e4929190610a06565b60405180910390a150565b60006020528060005260406000206000915090505481565b6000600154905090565b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205411610392576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161038990610a7b565b60405180910390fd5b600061039d33610502565b90506103a98133610550565b50565b80600081116103f0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103e7906109d7565b60405180910390fd5b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205411610471576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161046890610a7b565b60405180910390fd5b81806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410156104f3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104ea90610ae7565b60405180910390fd5b6104fd8333610550565b505050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60015481565b8160008111610594576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161058b906109d7565b60405180910390fd5b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205411610615576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161060c90610a7b565b60405180910390fd5b82806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015610697576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161068e90610ae7565b60405180910390fd5b6106e8846000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546107e190919063ffffffff16565b6000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061073f846001546107e190919063ffffffff16565b6001819055508273ffffffffffffffffffffffffffffffffffffffff166108fc859081150290604051600060405180830381858888f1935050505015801561078b573d6000803e3d6000fd5b507ff3f3943b36f75947b414c5c3da8a16ae4c7f3f833b0b65064c52e072e142bd1184846040516107bd929190610a06565b60405180910390a150505050565b600081836107d99190610b36565b905092915050565b600081836107ef9190610b8c565b905092915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000610827826107fc565b9050919050565b6108378161081c565b811461084257600080fd5b50565b6000813590506108548161082e565b92915050565b6000602082840312156108705761086f6107f7565b5b600061087e84828501610845565b91505092915050565b6000819050919050565b61089a81610887565b82525050565b60006020820190506108b56000830184610891565b92915050565b6108c481610887565b81146108cf57600080fd5b50565b6000813590506108e1816108bb565b92915050565b6000602082840312156108fd576108fc6107f7565b5b600061090b848285016108d2565b91505092915050565b6000806040838503121561092b5761092a6107f7565b5b6000610939858286016108d2565b925050602061094a85828601610845565b9150509250929050565b600082825260208201905092915050565b7f4572726f723a204e6f2073652068612065737461626c656369646f206c61206360008201527f616e746964616420646520666f6e646f73000000000000000000000000000000602082015250565b60006109c1603183610954565b91506109cc82610965565b604082019050919050565b600060208201905081810360008301526109f0816109b4565b9050919050565b610a008161081c565b82525050565b6000604082019050610a1b6000830185610891565b610a2860208301846109f7565b9392505050565b7f4e6f207469656e657320666f6e646f7300000000000000000000000000000000600082015250565b6000610a65601083610954565b9150610a7082610a2f565b602082019050919050565b60006020820190508181036000830152610a9481610a58565b9050919050565b7f4e6f207469656e657320666f6e646f7320737566696369656e74657300000000600082015250565b6000610ad1601c83610954565b9150610adc82610a9b565b602082019050919050565b60006020820190508181036000830152610b0081610ac4565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610b4182610887565b9150610b4c83610887565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115610b8157610b80610b07565b5b828201905092915050565b6000610b9782610887565b9150610ba283610887565b925082821015610bb557610bb4610b07565b5b82820390509291505056fea26469706673582212205e3699a4c2b0d3e1cda00829e5f6101f43725d20230dc2a2a734d14b6882235064736f6c634300080a0033";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_TOTALBALANCE = "totalBalance";

    public static final String FUNC_GETTOTALBALANCES = "getTotalBalances";

    public static final String FUNC_GETBALANCEOF = "getBalanceOf";

    public static final String FUNC_ADDFUNDS = "addFunds";

    public static final String FUNC_WHITDRAWFUNDS = "whitdrawFunds";

    public static final String FUNC_WITHDRAWALLFUNDS = "withdrawAllFunds";

    public static final String FUNC_TRANSFERTO = "transferTo";

    public static final Event EVENTADDFUNDS_EVENT = new Event("eventAddFunds", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    public static final Event EVENTTRANSFERFUNDS_EVENT = new Event("eventTransferFunds", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}));
    ;

    protected static final HashMap<String, String> _addresses;

    static {
        _addresses = new HashMap<String, String>();
        _addresses.put("5777", "0x49a8AC716739B7FAE2b2665F1C3aA3eB59345c52");
    }

    @Deprecated
    protected Bank(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Bank(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Bank(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Bank(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<EventAddFundsEventResponse> getEventAddFundsEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTADDFUNDS_EVENT, transactionReceipt);
        ArrayList<EventAddFundsEventResponse> responses = new ArrayList<EventAddFundsEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventAddFundsEventResponse typedResponse = new EventAddFundsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._sender = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventAddFundsEventResponse> eventAddFundsEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, EventAddFundsEventResponse>() {
            @Override
            public EventAddFundsEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTADDFUNDS_EVENT, log);
                EventAddFundsEventResponse typedResponse = new EventAddFundsEventResponse();
                typedResponse.log = log;
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._sender = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventAddFundsEventResponse> eventAddFundsEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTADDFUNDS_EVENT));
        return eventAddFundsEventFlowable(filter);
    }

    public List<EventTransferFundsEventResponse> getEventTransferFundsEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EVENTTRANSFERFUNDS_EVENT, transactionReceipt);
        ArrayList<EventTransferFundsEventResponse> responses = new ArrayList<EventTransferFundsEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EventTransferFundsEventResponse typedResponse = new EventTransferFundsEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._to = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EventTransferFundsEventResponse> eventTransferFundsEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, EventTransferFundsEventResponse>() {
            @Override
            public EventTransferFundsEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EVENTTRANSFERFUNDS_EVENT, log);
                EventTransferFundsEventResponse typedResponse = new EventTransferFundsEventResponse();
                typedResponse.log = log;
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._to = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<EventTransferFundsEventResponse> eventTransferFundsEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EVENTTRANSFERFUNDS_EVENT));
        return eventTransferFundsEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> balances(String param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> totalBalance() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getTotalBalances() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOTALBALANCES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getBalanceOf(String _address) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETBALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_address)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> addFunds(BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDFUNDS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> whitdrawFunds(BigInteger _amount, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WHITDRAWFUNDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> withdrawAllFunds() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAWALLFUNDS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferTo(BigInteger _amount, String _to, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.web3j.abi.datatypes.Address(_to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    @Deprecated
    public static Bank load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Bank(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Bank load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Bank(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Bank load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Bank(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Bank load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Bank(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Bank> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Bank.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Bank> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Bank.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Bank> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Bank.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Bank> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Bank.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    protected String getStaticDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static String getPreviouslyDeployedAddress(String networkId) {
        return _addresses.get(networkId);
    }

    public static class EventAddFundsEventResponse extends BaseEventResponse {
        public BigInteger _amount;

        public String _sender;
    }

    public static class EventTransferFundsEventResponse extends BaseEventResponse {
        public BigInteger _amount;

        public String _to;
    }
}
