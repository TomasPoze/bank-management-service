import React, { useState, useEffect } from 'react';
import { Button, Container } from '@material-ui/core';
import { Field, Formik, Form } from 'formik';
import transactionApi from '../../api/transactionApi';
import csvApi from '../../api/csvApi'

export default () => {
  const [transactions, setTransactions] = useState([]);

  const initialValues = {
    localDateStart: '',
    localDateEnd: ''
  }

  useEffect(() => {
    transactionApi.fetchTransactions()
      .then(resp => setTransactions(resp.data))
  }, [])

  const onSubmit = (values) => {
    csvApi.createCsv(values.localDateStart, values.localDateEnd)
      .then((resp) => {
        const url = window.URL.createObjectURL(new Blob([resp.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', `statements.csv`)
        document.body.appendChild(link);
        link.click();
      })
  }

  return (
    <Container>
      <Formik
        initialValues={initialValues}
        onSubmit={onSubmit}
      >
        <Form>
          <div>
            <Field
              name="localDateStart"
              type="text"
              label="localDateStart"
              placeholder="Start Date From"
              required
            />
          </div>
          <div>
            <Field
              name="localDateEnd"
              type="text"
              label="localDateEnd"
              placeholder="End Date To"
              required
            />
          </div>
          <Button type="submit" variant="contained" color="primary">Download</Button>
        </Form>
      </Formik>
      <hr />
      <div className="pos-middle">
        <table className="tableM">
          <thead>
            <tr>
              <th>Date</th>
              <th>Account Number</th>
              <th>Beneficiary</th>
              <th>Comment</th>
              <th>Amount</th>
              <th>Currency</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map(transaction => (
              <tr key={transaction.id}>
                <td>{transaction.operationDate}</td>
                <td>{transaction.accountNumber}</td>
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
  )
}