import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

import * as FaIcons from 'react-icons/fa';
import * as BsIcons from 'react-icons/bs';
import * as RiIcons from 'react-icons/ri';
import * as CgIcons from 'react-icons/cg';
import * as IoIcons from 'react-icons/io';
import authService from '../../../services/AuthService';
import Context from '../../../store/Context';

class Navbar extends Component {
    static contextType = Context;
    container = React.createRef();

    state = {
        dropdownIsActive: false
    };

    componentDidMount() {
        document.addEventListener("mousedown", this.handleClickOutside);
    }

    componentWillUnmount() {
        document.removeEventListener("mousedown", this.handleClickOutside);
    }

    handleClickOutside = event => {
        if (this.container.current && !this.container.current.contains(event.target)) {
            this.setState({
                dropdownIsActive: false,
            });
        }
    };

    handleClickDropdown = () => {
        this.setState({
            dropdownIsActive: !this.state.dropdownIsActive
        });
    }

    logout = () => {
        const { globalDispatch } = this.context;

        this.handleClickDropdown();
        globalDispatch({ type: "LOGOUT" });
        globalDispatch({ type: "HIDE_SIDEBAR" });
        authService.logout();
    }

    render() {
        const { dropdownIsActive } = this.state;
        const { globalState, globalDispatch } = this.context;

        return (
            <div className="navigationBar bg-dark" ref={this.container}>
                <div className="navigationBar__left">
                    <div className="navigationBar__title">
                        <b>Projektmenedzsment</b> rendszer
                    </div>
                    {globalState.loggedInUser &&
                        <FaIcons.FaBars
                            className="navigationBar__hamburgerIcon"
                            color="#28a745"
                            onClick={() => globalState.isShowSidebar ? globalDispatch({ type: "HIDE_SIDEBAR" }) : globalDispatch({ type: "SHOW_SIDEBAR" })}
                        />
                    }
                </div>

                {globalState.loggedInUser &&
                    <div className="navigationBar__right">
                        <div className="navigationBar__employee" onClick={this.handleClickDropdown}>
                            <div className="navigationBar__employeeNameAndJob">
                                <div className="navigationBar__employeeName">{globalState.loggedInUser.employee.name}</div>
                                <div className="navigationBar__job">{globalState.loggedInUser.employee.job.name}</div>
                            </div>
                            <BsIcons.BsPeopleCircle className="navigationBar__icon" size="40px" color="#28a745" />
                            <IoIcons.IoIosArrowDown />
                        </div>

                        {dropdownIsActive &&
                            <div className="navigationBar__dropdown">
                                <Link to={"/profile"} onClick={this.handleClickDropdown}>
                                    <CgIcons.CgProfile className="navigationBar__icon" size="20px" />
                                    <span>Profilom</span>
                                </Link>

                                {/* Nincs implementálva */}
                                {/* <Link to={"/"} onClick={this.handleClickDropdown}>
                                    <RiIcons.RiLockPasswordLine className="navigationBar__icon" size="20px" />
                                    <span>Jelszó módosítása</span>
                                </Link> */}

                                <Link to={"/login"} onClick={this.logout}>
                                    <RiIcons.RiLogoutCircleRLine className="navigationBar__icon" size="20px" />
                                    <span>Kijelentkezés</span>
                                </Link>
                            </div>
                        }
                    </div>
                }
            </div>
        );
    }
}

export default Navbar;