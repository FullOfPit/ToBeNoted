import {EditRounded} from "@mui/icons-material";
import {IconButton} from "@mui/material";
import * as React from "react";

export default function EditButton() {

    const click = () => {
        console.log("Hello!")
    }

    return (
        <IconButton
            key={"EditButton"}
            sx={{padding: 1.5}}
            aria-label="round shadow"
            onClick={() => click()}
        >
            {<EditRounded/>}
        </IconButton>
    )
}