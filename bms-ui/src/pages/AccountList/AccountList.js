import React, { useEffect, useState } from 'react';
import './style.css'
import { Container } from '@material-ui/core';
import accountApi from '../../api/accountApi'

export default () => {

  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    accountApi.fetchAccounts()
    .then(response => setAccounts(response.data))
  }, [])

  return (
    <Container>
      <table className="tableM">
        <thead>
          <tr>
            <th>ID</th>
            <th>Account Number</th>
            <th>Total Balance</th>
            <th>Currency</th>
          </tr>
        </thead>
        <tbody>
          {accounts.map(account => (
            <tr key={account.id}>
              <td>{account.id}</td>
              <td>{account.accountNumber}</td>
              <td>{account.totalBalance}</td>
              <td>{account.currency}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </Container>
  )
}