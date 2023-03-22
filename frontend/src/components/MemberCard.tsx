import {StaffMember} from "../types/StaffMember";
import {Card, CardActions, CardContent, Checkbox, Stack, Typography} from "@mui/material";
import React from "react";

export default function UserCard(
    {
        user,
        buttons
    }:{
        user: StaffMember
        buttons?: React.ReactNode[]
    }) {

    return (
        <Card sx={{ maxWidth: 400 , minWidth: 275 }}>
            <Stack direction={"row"}
                   alignContent={"center"}
            >
                <CardContent sx={{flexGrow: 1}}>

                    <Typography sx={{ fontSize: 26}} color="text.secondary">
                        {user.username}
                    </Typography>

                    <Stack
                        direction={"row"}
                        alignItems={"center"}
                    >
                        <Typography sx={{ fontSize: 14 }} color="text.secondary">
                            18 y/o:
                        </Typography>

                        <Checkbox checked={user.eighteenYears}/>
                    </Stack>
                </CardContent>
                <CardActions>
                    <Stack
                        spacing={0}
                        direction={"column"}
                    >
                        {buttons ? buttons.map((icon) => <> {icon} </>) :  null}
                    </Stack>
                </CardActions>
            </Stack>
        </Card>
    )
}