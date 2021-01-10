import { Container } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import transactionApi from '../../api/transactionApi';
import accountApi from '../../api/accountApi';

export default () => {

  const { id } = useParams({});

  const [account, setAccount] = useState([]);
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    transactionApi.fetchTransactionsByAccountId(id)
      .then(response => setTransactions(response.data))
  }, [id])

  useEffect(() => {
    accountApi.getAccount(id)
      .then(response => setAccount(response.data))
  }, [id])

  return (
    <Container>
      <div>
        <span>{account.user}</span>
        <br/>
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
            {transactions.map(transaction => (
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
    </Container>
  );
}