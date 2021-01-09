import React from 'react';
import './Header.css'
import { NavLink } from 'react-router-dom';

export default () => {


  return (
    <div className="header-container">

      <div className="site-name">Bank Management Service</div>

      <div className="header-bar">
        <NavLink to="">
          <span>Import / Export</span>
        </NavLink>
      </div>
    </div>
  )

}