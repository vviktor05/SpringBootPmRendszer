import React from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import * as IoIcons from 'react-icons/io';
import * as RiIcons from 'react-icons/ri';
import * as TiIcons from 'react-icons/ti';

export const SidebarData = [
    // {
    //     title: "Irányítópult",
    //     path: "/dashboard",
    //     icon: <AiIcons.AiFillDashboard />
    // },
    {
        title: "Projektek",
        path: "/projects",
        icon: <AiIcons.AiOutlineProject />,
        iconClosed: <IoIcons.IoIosArrowDown size="15px" color="white" />,
        iconOpened: <IoIcons.IoIosArrowUp size="15px" color="white" />,
        subNav: [
            {
                title: "Projektek kezelése",
                path: "/projects",
                icon: <AiIcons.AiOutlineProject />
            },
            {
                title: "Projekten dolgozó csapatok",
                path: "/projects/projects_and_teams",
                icon: <RiIcons.RiTeamFill />
            }
        ]
    },
    {
        title: "Megrendelők",
        path: "/customers",
        icon: <TiIcons.TiBusinessCard />
    },
    {
        title: "Feladatok",
        path: "/tasks",
        icon: <FaIcons.FaTasks />
    },
    {
        title: "Jelentések",
        path: "/reports",
        icon: <IoIcons.IoIosPaper />
    },
    {
        title: "Dolgozók",
        path: "/employees",
        icon: <IoIcons.IoMdPeople />
    },
    {
        title: "Csapatok",
        path: "/teams",
        icon: <RiIcons.RiTeamFill />,
        iconClosed: <IoIcons.IoIosArrowDown size="15px" color="white" />,
        iconOpened: <IoIcons.IoIosArrowUp size="15px" color="white" />,
        subNav: [
            {
                title: "Csapatok kezelése",
                path: "/teams",
                icon: <RiIcons.RiTeamFill />
            },
            {
                title: "Csapatok összeállítása",
                path: "/teams/team_memberships",
                icon: <IoIcons.IoIosCreate />
            }
        ]
    }
]