import React from 'react';
import './service.css'
import Contrainer from '@material-ui/core/Container'
import Button from '@material-ui/core/Button';
import { NavLink } from 'react-router-dom';

export default () => {

  return (
    <Contrainer>
      <div className="service-flex">
        <div>
          <Button variant="contained" color="primary">
            Import
          </Button>
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