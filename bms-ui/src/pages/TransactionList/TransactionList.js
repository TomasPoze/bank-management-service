import { Button, Container } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import transactionApi from '../../api/transactionApi';
import accountApi from '../../api/accountApi';


export default () => {

  const { id } = useParams({});


  const [account, setAccount] = useState([]);
  const [transactions, setTransactions] = useState([]);
  const [min, setMin] = useState("");
  const [max, setMax] = useState("");
  const [filteredTrans, setFilteredTrans] = useState([]);

  const [accBalance, setAccBalance] = useState("");

  useEffect(() => {
    transactionApi.fetchTransactionsByAccountId(id)
      .then(response => setTransactions(response.data))
  }, [id]);

  useEffect(() => {
    accountApi.getAccount(id)
      .then(response => setAccount(response.data))
  }, [id]);

  useEffect(() => {
    setFilteredTrans(
      transactions.filter((transaction) =>
        transaction.operationDate >= min && transaction.operationDate <= max + "23:59")
    )
  }, [min, max, transactions])

  const onSubmit = (e) => {
    e.preventDefault();
    transactionApi.getBalance(min, max, account.accountNumber)
      .then(response => setAccBalance(response.data))
    return false;
  }


  return (
    <Container>
      <div>
        <form id="balance">
          <input type="text" onChange={(e) => setMin(e.target.value)} placeholder="Date from Ex:2020-12-01" value={min} />
          <input type="text" onChange={(e) => setMax(e.target.value)} placeholder="Date to Ex:2021-01-01" value={max} />
          <Button variant="contained" form="balance" color="primary" type="button" onClick={onSubmit}>
            Calculate Balance
          </Button>
        </form>



      </div>
      <hr />
      <div>
        <span>{account.user}</span>
        <br />
        <span>IBAN: {account.accountNumber}</span>
      </div>
      <div >
        <table className="tableM">
          <thead>
            <tr>
              <th>Id</th>
              <th>Date</th>
              <th>Beneficiary</th>
              <th>Comment</th>
              <th>Amount</th>
              <th>Currency</th>
            </tr>
          </thead>
          <tbody>
            {filteredTrans.map(transaction => (
              <tr key={transaction.id}>
                <td>{transaction.id}</td>
                <td>{transaction.operationDate}</td>
                <td>{transaction.beneficiary}</td>
                <td>{transaction.comment}</td>
                <td>{transaction.amount}</td>
                <td>{transaction.currency}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <span>Account Balance: {accBalance}</span>
    </Container>
  );
}