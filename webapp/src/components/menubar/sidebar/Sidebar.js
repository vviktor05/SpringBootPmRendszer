import React, { Component } from 'react';
import styled from 'styled-components';
import { IconContext } from 'react-icons/lib';
import { SidebarData } from './SidebarData';
import SbMenu from './SbMenu';
import Context from '../../../store/Context';

const SidebarNav = styled.nav`
    width: 230px;
    height: 93vh;
    background: rgba(94, 102, 107, 0.205);
    display: ${({ active }) => (active ? 'flex' : 'none')};
    flex-direction: column;
    letter-spacing: 1px;
`;

class Sidebar extends Component {
    static contextType = Context;

    constructor(props) {
        super(props);
        this.state = {
            menuList: Array(SidebarData.length).fill(false)
        };
    }

    componentDidMount() {
        const actMenuList = this.state.menuList.slice();
        actMenuList[0] = true;

        this.setState({
            menuList: actMenuList
        });
    }

    handleClickNav = (i) => {
        const actMenuList = this.state.menuList.slice();

        for (let j = 0; j < actMenuList.length; j++) {
            actMenuList[j] = false;
            if (j === i) {
                actMenuList[i] = true;
            }
        }

        this.setState({
            menuList: actMenuList
        });
    }

    handleClickOpen = () => {
        this.setState({
            active: !this.state.active
        });
    }

    render() {
        const { menuList } = this.state;
        const { globalState } = this.context;

        return (
            <div>
                <IconContext.Provider value={{ size: '20px' }}>
                    <SidebarNav active={globalState.isShowSidebar}>
                        {SidebarData.map((menuData, i) => {
                            return <SbMenu
                                menuData={menuData} key={i}
                                onClickNav={() => this.handleClickNav(i)}
                                activeNav={menuList[i]}
                            />;
                        })}
                    </SidebarNav>
                </IconContext.Provider>
            </div>
        );
    }
}

export default Sidebar;