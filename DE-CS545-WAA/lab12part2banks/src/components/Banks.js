import React, { useState, useEffect } from 'react';
import axios from 'axios';

export const Banks = () => {
    const cleanAccount = { accountNumber : "", accountHolder : "",};
    const [account, setAccount] = useState(cleanAccount);
    const [accounts, setAccounts] = useState([]);
    const [selectedAccount, setSelectedAccount] = useState(null);
    const [depositAmount, setDepositAmount] = useState("");
    const [withdrawAmount, setWithdrawAmount] = useState("");

    //validation
    const [accountNumberError, setAccountNumberError] = useState({});
    const [accountHolderError, setAccountHolderError] = useState({});
    const [depositError, setDepositError] = useState({});
    const [withdrawError, setWithdrawError] = useState({});

    const loadBanks = () => {
        axios
          .get("http://localhost:8080/banks")
          .then((response) => setAccounts(response.data))
          .catch((error) => console.log(error));
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        const isValid = formValidation();
        console.log("isValid " + isValid);
        if (account && isValid) {
            alert("Form is valid");
            addAccount(account);
        }
        setAccount(cleanAccount);
        loadBanks();
    }

    const removeAccount = (e) => {
        let url = "http://localhost:8080/banks/delete/" + e.target.value;
        axios.delete(url)
            .then((response) => {
                loadBanks();
            })
    }

    const addAccount = (account) => {
        axios.post("http://localhost:8080/banks/", account)
            .then((response) => {
                loadBanks();
            })
    }

    useEffect(() => {
        loadBanks();
      }, []);

    
    const formValidation = () => {
        const accountNumberError = {};
        const accountHolderError = {};
        const depositError = {};
        const withdrawError = {};
        let isValid = true;

        if (account.accountNumber < 0) {
            accountNumberError.accountNumberNegative = "Account Number is not negative";
            isValid = false;
        }

        if (account.accountHolder.trim().length < 5) {
            accountHolderError.accountHolderShort = "Account Holder is too short ";
            isValid = false;
        }

        if (depositAmount < 0) {
            depositError.depositNegative = "Deposit is not negative";
            isValid = false;
        }

        if (withdrawAmount < 0) {
            withdrawError.withdrawNegative = "Withdraw is not negative";
            isValid = false;
        }

        if (selectedAccount && withdrawAmount > selectedAccount.balance) {
            withdrawError.withdrawIsTooBig = "Balance is not enough!";
            isValid = false;
          }

        setAccountNumberError(accountNumberError);
        setAccountHolderError(accountHolderError);
        setDepositError(depositError);
        setWithdrawError(withdrawError);
        return isValid;
    }
    
    const handleDeposit = () => {
        const accountNumber = selectedAccount.accountNumber;
        axios
        .get(`http://localhost:8080/banks/deposit/${accountNumber}/${depositAmount}`)
        .then((response) => {
            setSelectedAccount(response.data);
            loadBanks();
        })
        .catch((error) => console.log(error));
    };

    const handleWithdraw = () => {
        const accountNumber = selectedAccount.accountNumber;
        axios
            .get(`http://localhost:8080/banks/withdraw/${accountNumber}/${withdrawAmount}`)
            .then((response) => {
                setSelectedAccount(response.data);
                loadBanks();
        })
            .catch((error) => console.log(error));
    };

    const handleAccountClick = (account) => {
        setSelectedAccount(account);
    };

    const handleDepositAmountChange = (event) => {
        setDepositAmount(event.target.value);
    };

    const handleWithdrawAmountChange = (event) => {
        setWithdrawAmount(event.target.value);
    };

    const handleFieldChange = (e) => {
        setAccount({...account, [e.target.name] : e.target.value });
    }

    const renderAddAccount = () => {
    return (
        <div className=''>
            <form onSubmit={handleSubmit}>
            <div className="field">
                    <label className="label">Account Number: </label>
                    <input
                        className="input"
                        type="accountNumber"
                        name="accountNumber"
                        placeholder='123456'
                        value={account.accountNumber}
                        onChange={handleFieldChange}
                    />
                    {Object.keys(accountNumberError).map((key) => {
                        return <div style={{color : "red"}}>{accountNumberError[key]}</div>
                    })}
                        </div>
                        <div className="field">
                            <label className="label">Account Holder: </label>
                            <input
                                className="input"
                                type="text"
                                name="accountHolder"
                                value={account.accountHolder}
                                onChange={handleFieldChange}
                            />
                            {Object.keys(accountHolderError).map((key) => {
                        return <div style={{color : "red"}}>{accountHolderError[key]}</div>
                    })}
                        </div>
                    <div className="field is-clearfix">
                        <button
                            className="button is-primary is-outlined is-pulled-right"
                            type="submit"
                        >
                            Create account
                        </button>
                    </div>
                    </form>
        </div>
        
    );
    };

    const renderAccounts = () => {
    return (
        <table>
        <thead>
            <tr>
            <th>Account Number</th>
            <th>Account Holder</th>
            <th>Balance</th>
            <th>Transactions</th>
            </tr>
        </thead>
        <tbody>
            {accounts.map((account) => (
            <tr
                key={account.accountNumber}
                onClick={() => handleAccountClick(account)}
                className={selectedAccount?.accountNumber === account.accountNumber ? "selected" : ""}
            >
                <td>{account.accountNumber}</td>
                <td>{account.accountHolder}</td>
                <td>{account.balance}</td>
                <td>
                {account.transactionList.map((transaction, transactionIndex) => (
                    <div key={transactionIndex}>
                    {transaction.description} {transaction.amount} {transaction.date}
                    </div>
                ))}
                </td>
                <td><button onClick={removeAccount} value = {account.accountNumber}>Remove</button></td>
            </tr>
            ))}
        </tbody>
        </table>
    );
    };

    const renderSelectedAccount = () => {
    if (!selectedAccount) return null;

    const handleDepositSubmit = (event) => {
        event.preventDefault();
        handleDeposit();
        setDepositAmount("");
        };
    
        const handleWithdrawSubmit = (event) => {
        event.preventDefault();
        handleWithdraw();
        setWithdrawAmount("");
        };

    return (
        <div>
        <h2>Account Details</h2>
        <div>
            <strong>Account Number:</strong> {selectedAccount.accountNumber}
        </div>
        <div>
            <strong>Account Holder:</strong> {selectedAccount.accountHolder}
        </div>
        <div>
            <strong>Balance:</strong> {selectedAccount.balance}
        </div>
        <div>
            <h3>Transactions</h3>
            {selectedAccount.transactionList.map((transaction, index) => (
            <div key={index}>
                {transaction.description} {transaction.amount} {transaction.date}
            </div>
            ))}
        </div>
        <div>
            <h3>Deposit</h3>
            <form onSubmit={handleDepositSubmit}>
                <div>
                <input type="number" value={depositAmount} onChange={handleDepositAmountChange} />
                {Object.keys(depositError).map((key) => {
                            return <div style={{color : "red"}}>{depositError[key]}</div> })}
                </div>
            <button type="submit">Deposit</button>
            </form>
        </div>
        <div>
            <h3>Withdraw</h3>
            <form onSubmit={handleWithdrawSubmit}>
                <div>
                <input type="number" value={withdrawAmount} onChange={handleWithdrawAmountChange} />
                {Object.keys(withdrawError).map((key) => {
                            return <div style={{color : "red"}}>{withdrawError[key]}</div> })}
                </div>
            <button type="submit">Withdraw</button>
            </form>
        </div>
    </div>
    );}

    return (
        <div>
            <div className="add-account">
                <h1>Add an account</h1>
                {renderAddAccount()}
            </div>
            <h1>Banks</h1>
            <div className="accounts">
            <div className="accounts-list">
                <h2>Accounts</h2>
                {renderAccounts()}
            </div>
            <div className="selected-account">
                {renderSelectedAccount()}
            </div>
            </div>
        </div>
        );

}