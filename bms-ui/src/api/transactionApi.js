import HTTP from '.'

export default {
  fetchTransactionsByAccountId(id) {
    return HTTP.get(`/transaction/${id}`)
  },
  fetchTransactions() {
    return HTTP.get('/transaction/transactions')
  },
  getBalance(dateStart, dateEnd, accountNumber) {
    return HTTP.get(`/transaction/balance?dateStart=${dateStart}&dateEnd=${dateEnd}&accountNumber=${accountNumber}`)
  }
}