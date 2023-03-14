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
import {useNavigate} from "react-router-dom";

const pageIcons: { icon: React.ReactNode, linkTo: string}[] = [
        {icon: <HouseRounded/>, linkTo: "/"},
        {icon: <AssignmentIndRounded/>, linkTo: "/brief"},
        {icon: <PeopleRounded/>, linkTo: "/staff"},
        {icon: <Inventory2Rounded/>, linkTo: "/archive"},
        {icon: <LogoutRounded/>, linkTo: "/logout"}
];

export default function HeaderBar() {

    const navigate = useNavigate();

    return (
        <AppBar position={"static"}>
            <Container maxWidth={"xl"}>
                <Toolbar className={"A"}>
                    <SmallLogo/>
                    <div className={"AA"}>
                        {pageIcons.map((pageIcon) =>
                            <IconButton
                                key={pageIcon.linkTo}
                                sx={{padding: 1.5}}
                                aria-label="round shadow"
                                onClick={() => navigate(pageIcon.linkTo)}
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