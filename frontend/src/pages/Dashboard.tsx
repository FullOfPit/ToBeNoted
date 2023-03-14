import HeaderBarTwo from "../components/HeaderBarTwo";
import HeaderBar from "../components/HeaderBar";
import {IconButton} from "@mui/material";
import {
    Add,
    AssignmentIndRounded,
    Backspace, Delete,
    Edit,
    HouseRounded,
    Inventory2Rounded,
    PeopleRounded
} from "@mui/icons-material";
import * as React from "react";

const pageIcons = [
    <HouseRounded/>,
    <PeopleRounded/>,
    <Inventory2Rounded/>,
    <AssignmentIndRounded/>,
    <Edit/>,
    <Delete/>,
    <Backspace/>,
    <Add/>

];
export default function Dashboard() {

    return (
        <div>
            <HeaderBar/>
            This de dashboard!
            <div>
                {pageIcons.map((icon) =>
                    <IconButton>{icon}</IconButton>
                )}
            </div>
        </div>
    )
}