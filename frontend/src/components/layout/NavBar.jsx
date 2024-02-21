
import React, { useState, useContext} from 'react'
import { Link, NavLink } from 'react-router-dom'
import Logout from "../auth/Logout"
import { AuthContext } from '../auth/AuthProvider'

function NavBar() {
  const [showAccount, setShowAccount] = useState(false)
  const { user } = useContext(AuthContext)

  const handleAccountClick = () => {
    setShowAccount(!showAccount)
  }

  //track if user is loggedIn
  const isLoggedIn = user !== null
  const userRole = localStorage.getItem("userRole")

  return (
    <nav className='navbar navbar-expand-lg bg-body-tertiary px-5 shadow mt-5 sticky-top'>
      <div className='container-fluid'>
        <Link to={"/"}>
          <span className='navbar-brand'>Optical Telephone Company</span>
        </Link>
        <button 
          className='navbar-toggler'
          type='button'
          data-bs-toggle='collapse'
          data-bs-target='#navbar-scroll'
          aria-controls='#navbar-scroll'
          aria-expanded='false'
          aria-label='Toggle navigation'>
            <span className='navbar-toogler-icon'></span>
        </button>
        <div className='collapse navbar-collapse' id='navbarScroll'>
          <ul className='navbar-nav me-auto my-2 my-lg-0 navbar-scroll'>
            <li className='nav-item'>
              <NavLink className='nav-link' aria-current='page' to={'/login'}>
                View all calls
              </NavLink>
            </li>
            {isLoggedIn && userRole === "ADMIN" && (
              <li className='nav-item'>
                <NavLink className='nav-link' aria-current='page' to={'/admin'}>
                Admin
                </NavLink>
                </li>
              )}
          </ul>
          <ul className='d-flex navbar-nav'>
            <li className='nav-item'>
              <NavLink className='nav-link' to={'/login'}>
              find a call
              </NavLink>
            </li>
          </ul>
          <ul>
              <li className='nav-item dropdown'>
              <a
                className={`nav-link dropdown-toggle ${showAccount ? "show" : ""}`}
                href='#'
                roel='button'
                data-bs-toggle='dropdown'
                aria-expanded='false'
                onClick={handleAccountClick}>
                {" "}
                Account
              </a>
            </li>
          { /* </ul>*/}
            <ul
                className={`dropdown-menu ${showAccount ? "show" : ""}`}
                aria-labelledby="navbarDropdown">
              {isLoggedIn ? (
                <li>
                  <Logout />
                </li>
              ) : (
                <li>
                   <Link className='dropdown-item' to={'/login'} >
                    Login
                  </Link>
                </li>
             
              )}
            </ul>
            </ul>
        </div>
      </div>
    </nav>
  )
}

export default NavBar
