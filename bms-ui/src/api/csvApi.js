import HTTP from '.'

export default {
  createCsv(localDateStart, localDateEnd) {
    return HTTP.get(`/csv/download?localDateStart=${localDateStart}&localDateEnd=${localDateEnd}`)
  }
}