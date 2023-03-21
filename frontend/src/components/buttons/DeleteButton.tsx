import {DeleteForeverRounded} from "@mui/icons-material";
import {IconButton} from "@mui/material";
import * as React from "react";

export default function DeleteButton() {

    return (
        <IconButton
            key={"EditButton"}
            sx={{padding: 1.5}}
            aria-label="round shadow"
        >
            {<DeleteForeverRounded/>}
        </IconButton>
    )
}