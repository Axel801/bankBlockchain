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
    public static final String BINARY = "0x608060405234801561001057600080fd5b50610b39806100206000396000f3fe6080604052600436106100745760003560e01c80639b96eece1161004e5780639b96eece14610111578063a26759cb1461014e578063ad7a672f14610158578063d4822fbf1461018357610083565b806327e235e31461008d5780632baf57d3146100ca57806369b9ee7f146100f557610083565b366100835761008161019f565b005b61008b61019f565b005b34801561009957600080fd5b506100b460048036038101906100af919061079d565b6102cd565b6040516100c191906107e3565b60405180910390f35b3480156100d657600080fd5b506100df6102e5565b6040516100ec91906107e3565b60405180910390f35b61010f600480360381019061010a919061082a565b6102ef565b005b34801561011d57600080fd5b506101386004803603810190610133919061079d565b610445565b60405161014591906107e3565b60405180910390f35b61015661019f565b005b34801561016457600080fd5b5061016d61048d565b60405161017a91906107e3565b60405180910390f35b61019d60048036038101906101989190610857565b610493565b005b34600081116101e3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101da9061091a565b60405180910390fd5b610234346000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461070e90919063ffffffff16565b6000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061028b3460015461070e90919063ffffffff16565b6001819055507f32193ef63276f35fa6fe0187906ebb1cc51dbd8837a8c2428e2a084ff742ef9c34336040516102c2929190610949565b60405180910390a150565b60006020528060005260406000206000915090505481565b6000600154905090565b8060008111610333576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161032a9061091a565b60405180910390fd5b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054116103b4576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016103ab906109be565b60405180910390fd5b81806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020541015610436576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161042d90610a2a565b60405180910390fd5b6104408333610493565b505050565b60008060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b60015481565b81600081116104d7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016104ce9061091a565b60405180910390fd5b60008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205411610558576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161054f906109be565b60405180910390fd5b82806000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205410156105da576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105d190610a2a565b60405180910390fd5b61062b846000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461072490919063ffffffff16565b6000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506106828460015461072490919063ffffffff16565b6001819055508273ffffffffffffffffffffffffffffffffffffffff166108fc859081150290604051600060405180830381858888f193505050501580156106ce573d6000803e3d6000fd5b507ff3f3943b36f75947b414c5c3da8a16ae4c7f3f833b0b65064c52e072e142bd118484604051610700929190610949565b60405180910390a150505050565b6000818361071c9190610a79565b905092915050565b600081836107329190610acf565b905092915050565b600080fd5b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b600061076a8261073f565b9050919050565b61077a8161075f565b811461078557600080fd5b50565b60008135905061079781610771565b92915050565b6000602082840312156107b3576107b261073a565b5b60006107c184828501610788565b91505092915050565b6000819050919050565b6107dd816107ca565b82525050565b60006020820190506107f860008301846107d4565b92915050565b610807816107ca565b811461081257600080fd5b50565b600081359050610824816107fe565b92915050565b6000602082840312156108405761083f61073a565b5b600061084e84828501610815565b91505092915050565b6000806040838503121561086e5761086d61073a565b5b600061087c85828601610815565b925050602061088d85828601610788565b9150509250929050565b600082825260208201905092915050565b7f4572726f723a204e6f2073652068612065737461626c656369646f206c61206360008201527f616e746964616420646520666f6e646f73000000000000000000000000000000602082015250565b6000610904603183610897565b915061090f826108a8565b604082019050919050565b60006020820190508181036000830152610933816108f7565b9050919050565b6109438161075f565b82525050565b600060408201905061095e60008301856107d4565b61096b602083018461093a565b9392505050565b7f4e6f207469656e657320666f6e646f7300000000000000000000000000000000600082015250565b60006109a8601083610897565b91506109b382610972565b602082019050919050565b600060208201905081810360008301526109d78161099b565b9050919050565b7f4e6f207469656e657320666f6e646f7320737566696369656e74657300000000600082015250565b6000610a14601c83610897565b9150610a1f826109de565b602082019050919050565b60006020820190508181036000830152610a4381610a07565b9050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b6000610a84826107ca565b9150610a8f836107ca565b9250827fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff03821115610ac457610ac3610a4a565b5b828201905092915050565b6000610ada826107ca565b9150610ae5836107ca565b925082821015610af857610af7610a4a565b5b82820390509291505056fea2646970667358221220d2b58d85076112f29a75542da4d15a0375207299052b5ad42fdf4aef3b3c7e4964736f6c634300080a0033";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_TOTALBALANCE = "totalBalance";

    public static final String FUNC_GETTOTALBALANCES = "getTotalBalances";

    public static final String FUNC_GETBALANCEOF = "getBalanceOf";

    public static final String FUNC_ADDFUNDS = "addFunds";

    public static final String FUNC_WHITDRAWFUNDS = "whitdrawFunds";

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
        _addresses.put("5777", "0x711FB74F3f3cC890C5EA4056F018959DC6C60c79");
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
