import {AddCircleRounded} from "@mui/icons-material";
import {IconButton} from "@mui/material";
import * as React from "react";

export default function AddButton() {

    return (
        <IconButton
            key={"AddButton"}
            sx={{padding: 1.5}}
            aria-label="round shadow"
        >
            {<AddCircleRounded/>}
        </IconButton>
    )
}