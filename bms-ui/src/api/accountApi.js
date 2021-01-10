import HTTP from '.'

export default {
  fetchAccounts() {
    return HTTP.get('/account/list')
  },
  getAccount(id) {
    return HTTP.get(`/account/${id}`)
  }
}