import React from 'react';
import './Header.css'
import { NavLink } from 'react-router-dom';

export default () => {


  return (
    <div className="header-container">

      <div className="site-name">Bank Management Service</div>

      <div className="header-bar">
        <NavLink to="/services">
          <span>Import / Export</span>
        </NavLink>
        <NavLink to="/accounts">
          <span>Account List</span>
        </NavLink>
      </div>
    </div>
  )

}