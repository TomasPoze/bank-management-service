import HTTP from '.'

export default {
  fetchTransactionsByAccountId(id) {
    return HTTP.get(`/transaction/${id}`)
  },
  fetchTransactions(){
    return HTTP.get('/transaction/transactions')
  }
}