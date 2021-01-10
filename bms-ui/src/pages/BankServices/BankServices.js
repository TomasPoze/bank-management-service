import React, { useState } from 'react';
import './service.css'
import Contrainer from '@material-ui/core/Container'
import Button from '@material-ui/core/Button';
import { NavLink, useHistory, useLocation } from 'react-router-dom';
import csvApi from '../../api/csvApi';

export default () => {

  const [file, setFile] = useState({});
  const location = useLocation()
  const history = useHistory();
  const { from } = location.state || { from: { pathname: '/accounts' } }

  return (
    <Contrainer>
      <div className="service-flex">
        <div>
          <form id="csvpost">
            <input type="file" onChange={(e) => setFile(e.target.files[0])} />
            <Button variant="contained" form="csvpost" color="primary" type="button" onClick={() => {
              csvApi.importCsv(file)
                .then(
                  history.replace(from)
                )
            }}>
              Import
            </Button>
          </form>
          <span className="under">from</span>
        </div>
        <div>
          <NavLink className="noDec" to="/export">
            <Button variant="contained" color="secondary">
              Export
            </Button>
          </NavLink>
          <span className="under">to</span>
        </div>
      </div>
      <div className="center">
        <span className="csv-span">CSV</span>
      </div>
    </Contrainer>
  )
}