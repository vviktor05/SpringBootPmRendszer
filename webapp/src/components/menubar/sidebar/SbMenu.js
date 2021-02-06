import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const SidebarLink = styled(Link)`
  display: flex;
  color: ${({ active }) => (active === "true" ? '#28a745' : 'white')};
  justify-content: space-between;
  align-items: center;
  letter-spacing: 1px;
  font-size: 14px;
  padding: 20px;
  &:hover {
    background: rgba(135, 143, 151, 0.055);
    border-left: 4px solid #28a745;
    color: ${({ active }) => (active === "true" ? '#28a745' : 'white')};
    text-decoration: none;
    cursor: pointer;
  }
`;

const SidebarLabel = styled.span`
  margin-left: 16px;
`;

const DropdownLink = styled(Link)`
  background: rgba(94, 102, 107, 0.3);
  padding: 10px 10px 10px 30px;
  display: flex;
  align-items: center;
  font-size: 12px;
  letter-spacing: .5px;
  color: ${({ active }) => (active === "true" ? '#21eb50' : 'white')};
  text-decoration: none;
  &:hover {
    background: rgba(94, 102, 107, 0.6);
    cursor: pointer;
    color: ${({ active }) => (active === "true" ? '#21eb50' : 'white')};
    text-decoration: none;
  }
`;

const DropDownLabel = styled.span`
  margin-left: 8px;
`;

function Dropdown({ dropdownData }) {
  const initialSubMenuList = () => {
    let subMenuList = Array(dropdownData.length).fill(false);
    subMenuList[0] = true;
    return subMenuList;
  }

  const [subMenuList, setSubMenuList] = useState(initialSubMenuList());

  const handleSubMenuClick = (i) => {
    const actSubMenuList = initialSubMenuList();
    actSubMenuList[i] = true;

    setSubMenuList(actSubMenuList);
  }

  return (
    <div>
      {
        dropdownData.map((subMenu, i) => {
          return (
            <DropdownLink
              to={subMenu.path}
              key={i}
              onClick={() => handleSubMenuClick(i)}
              active={subMenuList[i] + ""}
            >
              {subMenu.icon}
              <DropDownLabel>{subMenu.title}</DropDownLabel>
            </DropdownLink>
          );
        })
      }
    </div>
  )
}


export default function SbMenu({ menuData, activeNav, onClickNav }) {
  return (
    <div>
      <SidebarLink
        to={menuData.path ? menuData.path : "#"}
        onClick={onClickNav}
        active={activeNav.toString()}
      >
        <div>
          {menuData.icon}
          <SidebarLabel>{menuData.title}</SidebarLabel>
        </div>
        <div>
          {
            menuData.subNav &&
              activeNav ? menuData.iconOpened : menuData.iconClosed
          }
        </div>
      </SidebarLink>

      { menuData.subNav && activeNav &&
        <Dropdown
          dropdownData={menuData.subNav}
        />
      }
    </div>
  );
};