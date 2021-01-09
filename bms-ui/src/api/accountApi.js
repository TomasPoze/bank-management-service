import HTTP from '.'

export default {
  fetchAccounts() {
    return HTTP.get('/account/list')
  }
}