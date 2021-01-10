import React from 'react';
import { Redirect, Route, Switch } from 'react-router-dom';
import BankServices from '../../pages/BankServices/BankServices';
import AccountList from '../../pages/AccountList/AccountList';
import TransactionList from '../../pages/TransactionList/TransactionList';
import CsvExport from '../../pages/CsvExport/CsvExport';

export default () => (
  <Switch>
    <Redirect exact from="/" to="/services" />

    <Route path="/services">
      <BankServices />
    </Route>

    <Route path="/accounts">
      <AccountList />
    </Route>

    <Route path="/account/:id">
      <TransactionList />
    </Route>

    <Route path="/export">
      <CsvExport />
    </Route>
  </Switch>
)