import * as React from 'react';
import {
    AppBar,
    Container,
    IconButton,
    Toolbar
} from "@mui/material";
import {
    HouseRounded,
    PeopleRounded,
    Inventory2Rounded,
    AssignmentIndRounded, LogoutRounded
} from '@mui/icons-material';
import SmallLogo from "./SmallLogo";
import {useLocation, useNavigate} from "react-router-dom";
import {useCallback} from "react";
import axios from "axios";

export default function HeaderBar() {

    const navigate = useNavigate();
    const location = useLocation();

    const logout = useCallback(async () => {
        await axios.get("/api/app-users/logout");
        navigate("/login?redirect=" + encodeURIComponent(location.pathname + location.search));
        window.document.cookie = "";
        window.localStorage.clear();
    }, [location, navigate]);

    const pageIcons: { icon: React.ReactNode, key: string, buttonFunction: () => void }[] = [
        {icon: <HouseRounded/>, key: "home", buttonFunction: () => navigate("/")},
        {icon: <AssignmentIndRounded/>, key: "brief", buttonFunction: () => navigate("/brief")},
        {icon: <PeopleRounded/>, key: "staff", buttonFunction: () => navigate("/staff")},
        {icon: <Inventory2Rounded/>, key: "archive", buttonFunction: () => navigate("/archive")},
        {icon: <LogoutRounded />, key: "logout", buttonFunction: () => logout()}
    ];

    return (
        <AppBar position={"static"}>
            <Container maxWidth={"xl"}>
                <Toolbar className={"A"}>
                    <SmallLogo/>
                    <div className={"AA"}>
                        {pageIcons.map((pageIcon) =>
                            <IconButton
                                key={pageIcon.key}
                                sx={{padding: 1.5}}
                                aria-label="round shadow"
                                onClick={() => pageIcon.buttonFunction()}
                            >
                                {pageIcon.icon}
                            </IconButton>
                        )}
                    </div>
                </Toolbar>
            </Container>
        </AppBar>
    )
}