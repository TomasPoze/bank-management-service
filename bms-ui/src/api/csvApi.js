import HTTP from '.'

export default {
  createCsv(localDateStart, localDateEnd) {
    return HTTP.get(`/csv/download?localDateStart=${localDateStart}&localDateEnd=${localDateEnd}`)
  },
  importCsv(file) {
    let data = new FormData();
    data.append("file", file);
    return HTTP.post('/csv', data)
  }
}