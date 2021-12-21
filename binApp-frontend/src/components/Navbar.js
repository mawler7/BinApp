import React, { useState } from "react";
import {Link} from 'react-router-dom'
import Logo from '../assets/logo.png'
import {FaBars, FaTimes} from 'react-icons/fa'
import './NavbarStyles.css'


const Navbar = () => {
    const[click, setClick] = useState(false)
    const handleClick = () => setClick(!click)

    return (
        <header>
            <nav className='navbar'>
                <div className='logo'>
                    <Link to='/'><img src={Logo} alt='' /></Link>
                </div>
                <ul className={click ? "nav-menu active" : 'nav-menu'}>
                    <li className='nav-item'>
                        <Link to='/' className='nav-link'>Home</Link>
                    </li>
                    <li className='nav-item'>
                        <Link to = '/containers' className='nav-link'>Containers</Link>
                    </li>
                    <li className='nav-item'>
                        <Link to = '/trucks' className='nav-link'>Trucks</Link>
                    </li>
                    <li className='nav-item'>
                        <Link to = '/orders/list' className='nav-link'>Orders</Link>
                    </li>
                    <li className='nav-item'>
                        <Link to = '/users' className='nav-link'>Users</Link>
                    </li>
                </ul>
                <div className='hamburger' onClick={handleClick}>
                    {click ? (<FaTimes size={20} style={{ color: '#ffffff' }} />) : (<FaBars size={20} style={{ color: '#ffffff' }} />)}
                </div>
            </nav>
        </header>
    )
}

export default Navbar