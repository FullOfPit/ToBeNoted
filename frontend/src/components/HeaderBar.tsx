import * as React from 'react';
import {AppBar, Container, IconButton, Toolbar} from "@mui/material";
import {
    HouseRounded,
    PeopleRounded,
    Inventory2Rounded,
    AssignmentIndRounded, LogoutRounded
} from '@mui/icons-material';
import SmallLogo from "./SmallLogo";

const pageIcons = [
    <HouseRounded/>,
    <AssignmentIndRounded/>,
    <PeopleRounded/>,
    <Inventory2Rounded/>,
    <LogoutRounded/>
];

export default function HeaderBar() {

    return (
        <AppBar position={"static"}>
            <Container maxWidth={"xl"}>
                <Toolbar className={"A"}>
                    <SmallLogo/>
                    <div className={"AA"}>
                        {pageIcons.map((icon) =>
                            <IconButton
                                sx={{padding: 1.5}}
                                aria-label="round shadow"
                            >
                                {icon}
                            </IconButton>
                        )}
                    </div>
                </Toolbar>
            </Container>
        </AppBar>
    )
}